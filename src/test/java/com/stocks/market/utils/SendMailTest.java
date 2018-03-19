package com.stocks.market.utils;

import javax.mail.MessagingException;

import org.junit.Test;

public class SendMailTest {

	@Test
	public void testSendString() {
		try {
			MailUtil.send("Hello world!");
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

}
