package com.stocks.market.beans;

public class StockBean {
	/**
	 * 股票名字
	 */
	private String name;
	/**
	 * 股票代码
	 */
	private String code;
	/**
	 * 今日开盘价
	 */
	private Double open;
	/**
	 * 昨日收盘价
	 */
	private Double lastDayClose;
	/**
	 * 当前价格
	 */
	private Double current;
	/**
	 * 今日最高价
	 */
	private Double high;
	/**
	 * 今日最低价
	 */
	private Double low;
	/**
	 * 竞买价，即“买一”报价
	 */
	private Double biddingPrice;
	/**
	 * 竞卖价，即“卖一”报价
	 */
	private Double auctionPrice;
	/**
	 * 成交的股票数
	 * 注：由于股票交易以一百股为基本单位，所以在使用时，通常把该值除以一百
	 */
	private Integer countOfTrade;
	/**
	 * 成交金额(单位:元)
	 * 注：为了一目了然，通常以“万元”为成交金额的单位，所以通常把该值除以一万
	 */
	private Double amountOfTrade;
	/**
	 * “买一”申请量
	 */
	private Integer countOfBuy1;
	/**
	 * “买一”报价
	 */
	private Double amountOfBuy1;
	/**
	 * “买二”申请量
	 */
	private Integer countOfBuy2;
	/**
	 * “买二”报价
	 */
	private Double amountOfBuy2;
	/**
	 * “买三”申请量
	 */
	private Integer countOfBuy3;
	/**
	 * “买三”报价
	 */
	private Double amountOfBuy3;
	/**
	 * “买四”申请量
	 */
	private Integer countOfBuy4;
	/**
	 * “买四”报价
	 */
	private Double amountOfBuy4;
	/**
	 * “买五”申请量
	 */
	private Integer countOfBuy5;
	/**
	 * “买五”报价
	 */
	private Double amountOfBuy5;
	/**
	 * “卖一”申请量
	 */
	private Integer countOfSale1;
	/**
	 * “卖一”报价
	 */
	private Double amountOfSale1;
	/**
	 * “卖二”申请量
	 */
	private Integer countOfSale2;
	/**
	 * “卖二”报价
	 */
	private Double amountOfSale2;
	/**
	 * “卖三”申请量
	 */
	private Integer countOfSale3;
	/**
	 * “卖三”报价
	 */
	private Double amountOfSale3;
	/**
	 * “卖四”申请量
	 */
	private Integer countOfSale4;
	/**
	 * “卖四”报价
	 */
	private Double amountOfSale4;
	/**
	 * “卖五”申请量
	 */
	private Integer countOfSale5;
	/**
	 * “卖五”报价
	 */
	private Double amountOfSale5;
	/**
	 * 最新交易日期
	 */
	private String tradeDate;
	/**
	 * 最新交易时间
	 */
	private String tradeTime;
	/**
	 * 查询时间
	 */
	private String queryTime;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Double getOpen() {
		return open;
	}
	public void setOpen(Double open) {
		this.open = open;
	}
	public Double getLastDayClose() {
		return lastDayClose;
	}
	public void setLastDayClose(Double lastDayClose) {
		this.lastDayClose = lastDayClose;
	}
	public Double getCurrent() {
		return current;
	}
	public void setCurrent(Double current) {
		this.current = current;
	}
	public Double getHigh() {
		return high;
	}
	public void setHigh(Double high) {
		this.high = high;
	}
	public Double getLow() {
		return low;
	}
	public void setLow(Double low) {
		this.low = low;
	}
	public Double getBiddingPrice() {
		return biddingPrice;
	}
	public void setBiddingPrice(Double biddingPrice) {
		this.biddingPrice = biddingPrice;
	}
	public Double getAuctionPrice() {
		return auctionPrice;
	}
	public void setAuctionPrice(Double auctionPrice) {
		this.auctionPrice = auctionPrice;
	}
	public Integer getCountOfTrade() {
		return countOfTrade;
	}
	public void setCountOfTrade(Integer countOfTrade) {
		this.countOfTrade = countOfTrade;
	}
	public Double getAmountOfTrade() {
		return amountOfTrade;
	}
	public void setAmountOfTrade(Double amountOfTrade) {
		this.amountOfTrade = amountOfTrade;
	}
	public Integer getCountOfBuy1() {
		return countOfBuy1;
	}
	public void setCountOfBuy1(Integer countOfBuy1) {
		this.countOfBuy1 = countOfBuy1;
	}
	public Double getAmountOfBuy1() {
		return amountOfBuy1;
	}
	public void setAmountOfBuy1(Double amountOfBuy1) {
		this.amountOfBuy1 = amountOfBuy1;
	}
	public Integer getCountOfBuy2() {
		return countOfBuy2;
	}
	public void setCountOfBuy2(Integer countOfBuy2) {
		this.countOfBuy2 = countOfBuy2;
	}
	public Double getAmountOfBuy2() {
		return amountOfBuy2;
	}
	public void setAmountOfBuy2(Double amountOfBuy2) {
		this.amountOfBuy2 = amountOfBuy2;
	}
	public Integer getCountOfBuy3() {
		return countOfBuy3;
	}
	public void setCountOfBuy3(Integer countOfBuy3) {
		this.countOfBuy3 = countOfBuy3;
	}
	public Double getAmountOfBuy3() {
		return amountOfBuy3;
	}
	public void setAmountOfBuy3(Double amountOfBuy3) {
		this.amountOfBuy3 = amountOfBuy3;
	}
	public Integer getCountOfBuy4() {
		return countOfBuy4;
	}
	public void setCountOfBuy4(Integer countOfBuy4) {
		this.countOfBuy4 = countOfBuy4;
	}
	public Double getAmountOfBuy4() {
		return amountOfBuy4;
	}
	public void setAmountOfBuy4(Double amountOfBuy4) {
		this.amountOfBuy4 = amountOfBuy4;
	}
	public Integer getCountOfBuy5() {
		return countOfBuy5;
	}
	public void setCountOfBuy5(Integer countOfBuy5) {
		this.countOfBuy5 = countOfBuy5;
	}
	public Double getAmountOfBuy5() {
		return amountOfBuy5;
	}
	public void setAmountOfBuy5(Double amountOfBuy5) {
		this.amountOfBuy5 = amountOfBuy5;
	}
	public Integer getCountOfSale1() {
		return countOfSale1;
	}
	public void setCountOfSale1(Integer countOfSale1) {
		this.countOfSale1 = countOfSale1;
	}
	public Double getAmountOfSale1() {
		return amountOfSale1;
	}
	public void setAmountOfSale1(Double amountOfSale1) {
		this.amountOfSale1 = amountOfSale1;
	}
	public Integer getCountOfSale2() {
		return countOfSale2;
	}
	public void setCountOfSale2(Integer countOfSale2) {
		this.countOfSale2 = countOfSale2;
	}
	public Double getAmountOfSale2() {
		return amountOfSale2;
	}
	public void setAmountOfSale2(Double amountOfSale2) {
		this.amountOfSale2 = amountOfSale2;
	}
	public Integer getCountOfSale3() {
		return countOfSale3;
	}
	public void setCountOfSale3(Integer countOfSale3) {
		this.countOfSale3 = countOfSale3;
	}
	public Double getAmountOfSale3() {
		return amountOfSale3;
	}
	public void setAmountOfSale3(Double amountOfSale3) {
		this.amountOfSale3 = amountOfSale3;
	}
	public Integer getCountOfSale4() {
		return countOfSale4;
	}
	public void setCountOfSale4(Integer countOfSale4) {
		this.countOfSale4 = countOfSale4;
	}
	public Double getAmountOfSale4() {
		return amountOfSale4;
	}
	public void setAmountOfSale4(Double amountOfSale4) {
		this.amountOfSale4 = amountOfSale4;
	}
	public Integer getCountOfSale5() {
		return countOfSale5;
	}
	public void setCountOfSale5(Integer countOfSale5) {
		this.countOfSale5 = countOfSale5;
	}
	public Double getAmountOfSale5() {
		return amountOfSale5;
	}
	public void setAmountOfSale5(Double amountOfSale5) {
		this.amountOfSale5 = amountOfSale5;
	}
	public String getTradeDate() {
		return tradeDate;
	}
	public void setTradeDate(String tradeDate) {
		this.tradeDate = tradeDate;
	}
	public String getTradeTime() {
		return tradeTime;
	}
	public void setTradeTime(String tradeTime) {
		this.tradeTime = tradeTime;
	}
	public String getQueryTime() {
		return queryTime;
	}
	public void setQueryTime(String queryTime) {
		this.queryTime = queryTime;
	}
}
