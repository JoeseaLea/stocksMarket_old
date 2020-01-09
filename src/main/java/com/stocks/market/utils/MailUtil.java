package com.stocks.market.utils;

import java.util.Properties;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;

/**
 * 邮件工具类
 *    说明：需要发送的邮件插入邮件队列，有监听器监听邮件队列，每30秒发送一封邮件（避免频繁发送邮件造成邮件服务器拒绝发送邮件）
 * @author Joesea Lea
 */
public class MailUtil {
	
	private static Logger logger = Logger.getLogger(MailUtil.class);
	
	public static Properties props = null;
	
	/**
	 * 邮件队列
	 */
	private static BlockingQueue<MailUtil.MailInfo> mailNeedSend = new ArrayBlockingQueue<MailUtil.MailInfo>(100);
	
	/**
	 * 监听邮件队列发送邮件
	 *   注：每发一封邮件暂停一段时间发下一封
	 */
	public static void listen() {
		logger.info("MailUtil is running...");
		new Thread(new Runnable() {
			public void run() {
				int queueSize = 0;
				int sleepTime = 60;
				while (true) {
					if (null != mailNeedSend && 0 < mailNeedSend.size()) {
						MailUtil.MailInfo mailInfo = mailNeedSend.poll();
						
						if (null != mailInfo) {
							try {
								logger.info("发送邮件（" + mailInfo.getSubject() + "）");
								send(mailInfo.getSubject(), mailInfo.getContent());
							} catch (MessagingException e) {
								/*
								 * 邮件发送失败，重新入队
								 */
								mailNeedSend.offer(mailInfo);
								logger.error("Mail send failed:", e);
								//切换发件邮箱属性配置
								changeMailProperties();
							}
						}
					}
					
					/*
					 * 暂停sleepTime秒
					 */
					try {
						if (1 < queueSize) {
							sleepTime = 60 / queueSize;
						} else {
							sleepTime = 60;
						}
						
						if (sleepTime < 8) {
							sleepTime = 8;
						}
						
						Thread.sleep(sleepTime * 1000);
					} catch (InterruptedException e) {}
				}
			}
		}).start();
	}
	
	/**
	 * 监听需要发送的邮件队列长度
	 */
	public static void listenQueueSize() {
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				while (true) {
					if (0 < mailNeedSend.size()) {
						logger.info("Mail need to send queue size: " + mailNeedSend.size());
					}
					
					try {
						Thread.sleep(30 * 1000);
					} catch (InterruptedException e) {}
				}
			}
		}).start();
	}
	
	/**
	 * 需要发送的邮件入队
	 * @param subject
	 * @param content
	 */
	public static void addSendMail(String subject, String content) {
		MailInfo mailInfo = new MailUtil.MailInfo(subject, content);
		mailNeedSend.offer(mailInfo);
	}
	
	/**
	 * 发送邮件
	 * @param content
	 * @throws MessagingException
	 */
	public static void send(String content) throws MessagingException {
		send("无标题", content);
	}
	
	/**
	 * 发送邮件
	 * @param subject
	 * @param content
	 * @throws MessagingException
	 */
	public static void send(String subject, String content) throws MessagingException {
		//构建授权信息，用于进行SMTP进行身份验证
		Authenticator authenticator = new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				// 用户名、密码
				String userName = props.getProperty("mail.user");
				String password = props.getProperty("mail.password");
				return new PasswordAuthentication(userName, password);
			}
		};
		
		// 使用环境属性和授权信息，创建邮件会话
		Session mailSession = Session.getInstance(props, authenticator);
		// 创建邮件消息
		MimeMessage message = new MimeMessage(mailSession);
		// 设置发件人
		InternetAddress form = new InternetAddress(props.getProperty("mail.user"));
		message.setFrom(form);
		// 设置收件人的邮箱
//			message.setRecipient(Message.RecipientType.TO, new InternetAddress("JoeseaLea@163.com"));   //单发
//			message.setRecipients(Message.RecipientType.TO, new InternetAddress[]{new InternetAddress("JoeseaLea@163.com"), new InternetAddress("354046335@qq.com")});   //多发
		message.setRecipients(Message.RecipientType.TO, "JoeseaLea@163.com,354046335@qq.com");  //多发
		
		if (null == subject || "".equals(subject)) {
			subject = "无标题";
		}
		//设置邮件标题
		message.setSubject(subject);
		
		//设置邮件的内容体
		if (subject.equals("无标题")) {
			message.setContent(content, "text/html;charset=UTF-8");
		} else {
			message.setContent("<h2>" + subject + "</h2>" + content, "text/html;charset=UTF-8");
		}
		
		//发送邮件
		Transport.send(message);
	}
	
	/**
	 * 切换邮箱配置
	 */
	private static void changeMailProperties() {
		if (props.getProperty("mail.smtp.host").equals("smtp.qq.com")) {
			logger.info("Change 163 mail to send mail");
			use163Mail();
		} else {
			logger.info("Change QQ mail to send mail");
			useQQMail();
		}
	}
	
	/**
	 * 使用163邮箱
	 */
	public static void use163Mail() {
		// 创建Properties 类用于记录邮箱的一些属性
		props = new Properties();
		// 表示SMTP发送邮件，必须进行身份验证
		props.put("mail.smtp.auth", "true");
		// 此处填写SMTP服务器
		props.put("mail.smtp.host", "smtp.163.com");
		// 端口号，QQ邮箱给出了两个端口，但是另一个我一直使用不了，所以就给出这一个587
		props.put("mail.smtp.port", "25");
		// 此处填写你的账号
		props.put("mail.user", "JoeseaLea@163.com");
		// 此处的密码就是前面说的16位STMP口令
		props.put("mail.password", "******");
	}
	
	/**
	 * 使用QQ邮箱
	 */
	public static void useQQMail() {
		// 创建Properties 类用于记录邮箱的一些属性
		props = new Properties();
		// 表示SMTP发送邮件，必须进行身份验证
		props.put("mail.smtp.auth", "true");
		// 此处填写SMTP服务器
		props.put("mail.smtp.host", "smtp.qq.com");
		// 端口号，QQ邮箱给出了两个端口，但是另一个我一直使用不了，所以就给出这一个587
		props.put("mail.smtp.port", "587");
		// 此处填写你的账号
		props.put("mail.user", "354046335@qq.com");
		// 此处的密码就是前面说的16位STMP口令
		props.put("mail.password", "igzyodsuzvcrbiac");
	}
	
	static {
		useQQMail();
	}
	
	/**
	 * 邮件信息
	 * @author Joesea Lea
	 */
	private static class MailInfo {
		
		public String subject;
		public String content;
		
		public MailInfo(String subject, String content) {
			this.subject = subject;
			this.content = content;
		}
		
		public String getSubject() {
			return subject;
		}
		public String getContent() {
			return content;
		}
	}
}
