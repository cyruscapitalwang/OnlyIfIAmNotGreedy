package com.cyrus.commom;

import java.time.LocalDateTime;

/**
 * Stock price at certain time.
 * 
 * @author twang
 *
 */
public class StockQuote {

	private String ticker;
	private LocalDateTime dateTime;
	private double openPrice;
	private double highPrice;
	private double lowPrice;
	private double closePrice;
	private long volumn;

	public StockQuote() {
	}

	/**
	 * @return the ticker
	 */
	public String getTicker() {
		return ticker;
	}

	/**
	 * @param ticker
	 *            the ticker to set
	 */
	public void setTicker(String ticker) {
		this.ticker = ticker;
	}

	/**
	 * @return the dateTime
	 */
	public LocalDateTime getDateTime() {
		return dateTime;
	}

	/**
	 * @param dateTime
	 *            the dateTime to set
	 */
	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}

	/**
	 * @return the openPrice
	 */
	public double getOpenPrice() {
		return openPrice;
	}

	/**
	 * @param openPrice
	 *            the openPrice to set
	 */
	public void setOpenPrice(double openPrice) {
		this.openPrice = openPrice;
	}

	/**
	 * @return the closePrice
	 */
	public double getClosePrice() {
		return closePrice;
	}

	/**
	 * @param closePrice
	 *            the closePrice to set
	 */
	public void setClosePrice(double closePrice) {
		this.closePrice = closePrice;
	}

	/**
	 * @return the highPrice
	 */
	public double getHighPrice() {
		return highPrice;
	}

	/**
	 * @param highPrice
	 *            the highPrice to set
	 */
	public void setHighPrice(double highPrice) {
		this.highPrice = highPrice;
	}

	/**
	 * @return the lowPrice
	 */
	public double getLowPrice() {
		return lowPrice;
	}

	/**
	 * @param lowPrice
	 *            the lowPrice to set
	 */
	public void setLowPrice(double lowPrice) {
		this.lowPrice = lowPrice;
	}

	/**
	 * @return the volumn
	 */
	public long getVolumn() {
		return volumn;
	}

	/**
	 * @param volumn
	 *            the volumn to set
	 */
	public void setVolumn(long volumn) {
		this.volumn = volumn;
	}

}
