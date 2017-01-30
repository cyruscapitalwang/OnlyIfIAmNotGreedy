package com.cyrus.commom;

import java.util.List;

public interface QueryQuoteIF {

	public List<StockQuote> queryQuote(String tickerName);
}
