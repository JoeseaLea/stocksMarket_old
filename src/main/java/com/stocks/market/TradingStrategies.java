package com.stocks.market;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.stocks.market.beans.StockBean;
import com.stocks.market.utils.MailUtil;

/**
 * 股票交易策略，满足策略的股票发邮件通知买入
 *    说明：每支股票同一策略只发送一次邮件
 * @author Joesea Lea
 */
public class TradingStrategies {
	
//	private static Logger logger = Logger.getLogger(TradingStrategies.class);
	
	private static Map<String, String> hadSendMail = new HashMap<String, String>();
	
	/**
	 * 最高价与当前价差5%
	 * @param stockBean 股票数据
	 */
	public static void hight2current(StockBean stockBean) {
		hight2current(stockBean, 0.05);
	}
	/**
	 * 最高价与当前价差5%
	 * @param stockBeans 股票数据
	 */
	public static void hight2current(List<StockBean> stockBeans) {
		hight2current(stockBeans, 0.05);
	}
	/**
	 * 最高价与当前价差指定比例
	 * @param stockBean 股票数据
	 * @param gap 价差比例
	 */
	public static void hight2current(StockBean stockBean, Double gap) {
		if (gap <= (stockBean.getHigh() - stockBean.getCurrent()) / stockBean.getLastDayClose()
				&& StringUtils.isEmpty(hadSendMail.get("hight2current_" + gap + "-" + stockBean.getCode()))) {
			hadSendMail.put("hight2current_" + gap + "-" + stockBean.getCode(), "Y");
			MailUtil.addSendMail("当前振幅超过" + gap * 100 + "%的股票", stockInfo(stockBean));
		}
	}
	/**
	 * 最高价与当前价差指定比例
	 * @param stockBeans 股票数据
	 * @param gap 价差比例
	 */
	public static void hight2current(List<StockBean> stockBeans, Double gap) {
		
		List<StockBean> list = new ArrayList<StockBean>();
		
		for (StockBean stockBean : stockBeans) {
			if (gap <= (stockBean.getHigh() - stockBean.getCurrent()) / stockBean.getLastDayClose()
					&& StringUtils.isEmpty(hadSendMail.get("hight2current_" + gap + "-" + stockBean.getCode()))) {
				hadSendMail.put("hight2current_" + gap + "-" + stockBean.getCode(), "Y");
				list.add(stockBean);
			}
		}
		if (null != list && 0 < list.size()) {
			MailUtil.addSendMail("当前振幅超过" + gap * 100 + "%的股票", stockInfo(list));
		}
	}
	
	/**
	 * 当前跌幅超过3%（当前价与昨天收市价差3%）
	 * @param stockBean 股票数据
	 */
	public static void lastDayClose2current(StockBean stockBean) {
		lastDayClose2current(stockBean, 0.03);
	}
	/**
	 * 当前跌幅超过3%（当前价与昨天收市价差3%）
	 * @param stockBeans 股票数据
	 */
	public static void lastDayClose2current(List<StockBean> stockBeans) {
		lastDayClose2current(stockBeans, 0.03);
	}
	/**
	 * 当前跌幅超过指定比例（当前价与昨天收市价差指定比例）
	 * @param stockBean 股票数据
	 * @param gap 下跌比例
	 */
	public static void lastDayClose2current(StockBean stockBean, Double gap) {
		if (gap <= 1 - stockBean.getCurrent()/stockBean.getLastDayClose()
				&& StringUtils.isEmpty(hadSendMail.get("lastDayClose2current_" + gap + "-" + stockBean.getCode()))) {
			hadSendMail.put("lastDayClose2current_" + gap + "-" + stockBean.getCode(), "Y");
			MailUtil.addSendMail("当前跌幅超过" + gap * 100 + "%的股票", stockInfo(stockBean));
		}
	}
	/**
	 * 当前跌幅超过指定比例（当前价与昨天收市价差指定比例）
	 * @param stockBeans 股票数据
	 * @param gap 下跌比例
	 */
	public static void lastDayClose2current(List<StockBean> stockBeans, Double gap) {
		
		List<StockBean> list = new ArrayList<StockBean>();
		
		for (StockBean stockBean : stockBeans) {
			if (gap <= 1 - stockBean.getCurrent()/stockBean.getLastDayClose()
					&& StringUtils.isEmpty(hadSendMail.get("lastDayClose2current_" + gap + "-" + stockBean.getCode()))) {
				hadSendMail.put("lastDayClose2current_" + gap + "-" + stockBean.getCode(), "Y");
				list.add(stockBean);
			}
		}
		if (null != list && 0 < list.size()) {
			MailUtil.addSendMail("当前跌幅超过" + gap * 100 + "%的股票", stockInfo(list));
		}
	}
	
	/**
	 * 当前股票价格低于指定价格
	 * @param stockBean 股票数据
	 * @param price 指定价格
	 */
	public static void currentPriceLess(StockBean stockBean, Double price) {
		if (stockBean.getCurrent() < price
				&& StringUtils.isEmpty(hadSendMail.get("currentPriceLess_" + price + "-" + stockBean.getCode()))) {
			hadSendMail.put("currentPriceLess_" + price + "-" + stockBean.getCode(), "Y");
			MailUtil.addSendMail(stockBean.getName() + "(" + stockBean.getCode() + ")当前价格低于" + price, stockInfo(stockBean));
		}
	}
	/**
	 * 当前股票价格低于指定价格
	 * @param stockBean 股票数据
	 * @param priceMap 指定价格
	 */
	public static void currentPriceLess(List<StockBean> stockBeans, Map<String, Double> priceMap) {
		List<StockBean> list = new ArrayList<StockBean>();
		
		for (StockBean stockBean : list) {
			if (stockBean.getCurrent() < priceMap.get(stockBean.getCode().substring(2))
					&& StringUtils.isEmpty(hadSendMail.get("currentPriceLess_" + priceMap.get(stockBean.getCode().substring(2)) + "-" + stockBean.getCode()))) {
				hadSendMail.put("currentPriceLess_" + priceMap.get(stockBean.getCode().substring(2)) + "-" + stockBean.getCode(), "Y");
				list.add(stockBean);
			}
		}
		if (null != list && 0 < list.size()) {
			MailUtil.addSendMail("价格低于指定值的股票", stockInfo(list));
		}
	}
	
	/**
	 * html格式返回股票信息
	 * @param stockBean
	 * @return
	 */
	public static String stockInfo(StockBean stockBean) {
		StringBuilder stockInfo = new StringBuilder();
		stockInfo.append("<style type=\"text/css\">\n\r");
//		stockInfo.append("table {width: 100%; border-collapse: collapse; margin: 1em 0;} \n\r");
		stockInfo.append("table {border-collapse: collapse; margin: 1em 0;} \n\r");
		stockInfo.append("tbody {display: table-row-group; vertical-align: middle; border-color: inherit;} \n\r");
		stockInfo.append("tr {display: table-row; vertical-align: inherit; border-color: inherit;} \n\r");
		stockInfo.append("td {text-align: center; padding: 1px 22px; border: 1px solid #eee; font-size: 16px; height: 45px;} \n\r");
		stockInfo.append("tbody>tr>td {height: 45px;} \n\r");
		stockInfo.append("</style> \n\r");
		stockInfo.append("<table cellspacing=\"0\" cellpadding=\"0\"> \n\r");
		stockInfo.append("<thead> \n\r");
		stockInfo.append("<tr style=\"background-color: #b4b3c0; color: #fff\"> \n\r");
//		stockInfo.append("<td width=\"120px\">股票名称</td> \n\r");
		stockInfo.append("<td>股票名称</td> \n\r");
//		stockInfo.append("<td width=\"160px\">股票代码</td> \n\r");
		stockInfo.append("<td>股票代码</td> \n\r");
		stockInfo.append("<td>昨日收盘价</td> \n\r");
		stockInfo.append("<td>今日开盘价</td> \n\r");
		stockInfo.append("<td>今日最高价</td> \n\r");
		stockInfo.append("<td>今日最低价</td> \n\r");
		stockInfo.append("<td>当前交易价</td> \n\r");
//		stockInfo.append("<td width=\"320px\">当前时间</td> \n\r");
		stockInfo.append("<td>当前时间</td> \n\r");
		stockInfo.append("</tr> \n\r");
		stockInfo.append("</thead> \n\r");
		stockInfo.append("<tbody> \n\r");
		stockInfo.append("<tr> \n\r");
		
		/**
		 * 持有、特别关注、监控三类股票区分展示样式
		 *       持有：加粗加大红色字体
		 *       特别关注：红色字体
		 *       监控：黑色字体
		 */
		if (StocksMarket.holdStocks.contains(stockBean.getCode().substring(2))) {
			stockInfo.append("<td style=\"color: #ff0000; font-weight:bolder; font-size: 18px;\">" + stockBean.getName() + "</td> \n\r");
			stockInfo.append("<td style=\"color: #ff0000; font-weight:bolder; font-size: 18px;\">" + stockBean.getCode() + "</td> \n\r");
		} else if (StocksMarket.specialTreatStocks.contains(stockBean.getCode().substring(2))) {
			stockInfo.append("<td style=\"color: #ff0000;\">" + stockBean.getName() + "</td> \n\r");
			stockInfo.append("<td style=\"color: #ff0000;\">" + stockBean.getCode() + "</td> \n\r");
		} else {
			stockInfo.append("<td>" + stockBean.getName() + "</td> \n\r");
			stockInfo.append("<td>" + stockBean.getCode() + "</td> \n\r");
		}
		
		stockInfo.append("<td>" + stockBean.getLastDayClose() + "</td> \n\r");
		stockInfo.append("<td>" + stockBean.getOpen() + "</td> \n\r");
		stockInfo.append("<td>" + stockBean.getHigh() + "</td> \n\r");
		stockInfo.append("<td>" + stockBean.getLow() + "</td> \n\r");
		stockInfo.append("<td>" + stockBean.getCurrent() + "</td> \n\r");
		stockInfo.append("<td>" + stockBean.getQueryTime() + "</td> \n\r");
		stockInfo.append("</tr> \n\r");
		stockInfo.append("</tbody> \n\r");
		stockInfo.append("</table> \n\r");
		
		return stockInfo.toString();
	}
	
	/**
	 * html格式返回股票信息
	 * @param stockBeans
	 * @return
	 */
	public static String stockInfo(List<StockBean> stockBeans) {
		StringBuilder stocksInfo = new StringBuilder();
		stocksInfo.append("<style type=\"text/css\">\n\r");
//		stocksInfo.append("table {width: 100%; border-collapse: collapse; margin: 1em 0;} \n\r");
		stocksInfo.append("table {border-collapse: collapse; margin: 1em 0;} \n\r");
		stocksInfo.append("tbody {display: table-row-group; vertical-align: middle; border-color: inherit;} \n\r");
		stocksInfo.append("tr {display: table-row; vertical-align: inherit; border-color: inherit;} \n\r");
		stocksInfo.append("td {text-align: center; padding: 1px 22px; border: 1px solid #eee; font-size: 16px; height: 45px;} \n\r");
		stocksInfo.append("tbody>tr>td {height: 45px;} \n\r");
		stocksInfo.append("</style> \n\r");
		stocksInfo.append("<table cellspacing=\"0\" cellpadding=\"0\"> \n\r");
		stocksInfo.append("<thead> \n\r");
		stocksInfo.append("<tr style=\"background-color: #b4b3c0; color: #fff\"> \n\r");
//		stocksInfo.append("<td width=\"120px\">股票名称</td> \n\r");
		stocksInfo.append("<td>股票名称</td> \n\r");
//		stocksInfo.append("<td width=\"160px\">股票代码</td> \n\r");
		stocksInfo.append("<td>股票代码</td> \n\r");
		stocksInfo.append("<td>昨日收盘价</td> \n\r");
		stocksInfo.append("<td>今日开盘价</td> \n\r");
		stocksInfo.append("<td>今日最高价</td> \n\r");
		stocksInfo.append("<td>今日最低价</td> \n\r");
		stocksInfo.append("<td>当前交易价</td> \n\r");
//		stocksInfo.append("<td width=\"280px\">当前时间</td> \n\r");
		stocksInfo.append("<td>当前时间</td> \n\r");
		stocksInfo.append("</tr> \n\r");
		stocksInfo.append("</thead> \n\r");
		stocksInfo.append("<tbody> \n\r");
		
		for (StockBean stockBean : stockBeans) {
			stocksInfo.append("<tr> \n\r");
			
			/**
			 * 持有、特别关注、监控三类股票区分展示样式
			 *       持有：加粗加大红色字体
			 *       特别关注：红色字体
			 *       监控：黑色字体
			 */
			if (StocksMarket.holdStocks.contains(stockBean.getCode().substring(2))) {
				stocksInfo.append("<td style=\"color: #ff0000; font-weight:bolder; font-size: 18px;\">" + stockBean.getName() + "</td> \n\r");
				stocksInfo.append("<td style=\"color: #ff0000; font-weight:bolder; font-size: 18px;\">" + stockBean.getCode() + "</td> \n\r");
			} else if (StocksMarket.specialTreatStocks.contains(stockBean.getCode().substring(2))) {
				stocksInfo.append("<td style=\"color: #ff0000;\">" + stockBean.getName() + "</td> \n\r");
				stocksInfo.append("<td style=\"color: #ff0000;\">" + stockBean.getCode() + "</td> \n\r");
			} else {
				stocksInfo.append("<td>" + stockBean.getName() + "</td> \n\r");
				stocksInfo.append("<td>" + stockBean.getCode() + "</td> \n\r");
			}
			
			stocksInfo.append("<td>" + stockBean.getLastDayClose() + "</td> \n\r");
			stocksInfo.append("<td>" + stockBean.getOpen() + "</td> \n\r");
			stocksInfo.append("<td>" + stockBean.getHigh() + "</td> \n\r");
			stocksInfo.append("<td>" + stockBean.getLow() + "</td> \n\r");
			stocksInfo.append("<td>" + stockBean.getCurrent() + "</td> \n\r");
			stocksInfo.append("<td>" + stockBean.getQueryTime() + "</td> \n\r");
			stocksInfo.append("</tr> \n\r");
		}
		
		stocksInfo.append("</tbody> \n\r");
		stocksInfo.append("</table> \n\r");
		
		return stocksInfo.toString();
	}
}
