package com.stocks.market;

import org.junit.Test;

import com.stocks.market.utils.MailUtil;

public class TradingStrategiesTest {
	
//	private static Logger logger = Logger.getLogger(TradingStrategiesTest.class);

	@Test
	public void test() {
		
		/*StockBean stockBean = new StockBean();
		stockBean.setName("匹凸匹");
		stockBean.setCode("sh600696");
		stockBean.setHigh(9.53);
		stockBean.setCurrent(9.31);
		stockBean.setLastDayClose(9.57);
		stockBean.setLow(9.27);
		stockBean.setOpen(9.45);
		
		try {
			MailUtil.send("当前振幅超过5.0%的股票", TradingStrategies.stockInfo(stockBean));
		} catch (MessagingException e) {
			logger.error("error", e);
		}*/
		
		/*try {
			System.out.println(1/0);
		} catch (Exception e) {
			logger.error("", e);
		}*/
		
		MailUtil.listenQueueSize();
	}

}
