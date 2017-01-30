package com.cyrus.commom;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 *
 * EXCHANGE%3DNYSEARCA
 * <p>
 * MARKET_OPEN_MINUTE=570
 * <p>
 * MARKET_CLOSE_MINUTE=960
 * <p>
 * INTERVAL=60
 * <p>
 * COLUMNS=DATE,CLOSE,HIGH,LOW,OPEN,VOLUME
 * <p>
 * DATA=
 * <p>
 * TIMEZONE_OFFSET=-300
 * <p>
 * a1483972200,22.9,22.93,22.9,22.93,336361
 * <p>
 * 1,22.87,22.91,22.84,22.9,184156
 * <p>
 * 2,22.73,22.88,22.73,22.87,126149
 * <p>
 * 3,22.71,22.78,22.69,22.73,194802
 * <p>
 * 4,22.775,22.795,22.71,22.71,226723
 * <p>
 * 5,22.73,22.775,22.72,22.77,116102
 * <p>
 * 6,22.65,22.74,22.65,22.74,118192
 * <p>
 * 7,22.65,22.69,22.64,22.65,193091
 * <p>
 * 8,22.57,22.66,22.55,22.64,176229
 * <p>
 * 
 * .......
 * <p>
 * 390,22.46,22.485,22.45,22.465,3796059
 * <p>
 * a1484058600,22.67,22.68,22.66,22.68,99961
 * <p>
 * 1,22.65,22.7,22.61,22.67,127538
 * <p>
 * 2,22.75,22.78,22.66,22.66,176075
 * <p>
 * 
 * @author twang
 *
 */

public class IntraQuoteFromGoogle implements QueryQuoteIF {

	private static final String ADDRESS = "https://www.google.com/finance/getprices?i=60&p=10d&f=d,o,h,l,c,v&df=cpct&q=";

	private static Logger LOGGER = LogManager.getLogger(IntraQuoteFromGoogle.class);

	public List<StockQuote> queryQuote(String tickerName) {

		List<StockQuote> list = new ArrayList<StockQuote>();
		URLConnection connection = null;
		BufferedReader in = null;
		String marketStartingDateTimeInSecond = null;
		LocalDateTime marketStartingDateTime = null;
		LocalDateTime intraDateTime = null;
		String[] lineArray = null;
		StockQuote intradayQuote = null;
		String close = null;
		String high = null;
		String low = null;
		String open = null;
		String volume = null;
		try {
			URL google = new URL(ADDRESS + tickerName.trim().toUpperCase());
			connection = google.openConnection();
			in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String inputLine;
			while ((inputLine = in.readLine()) != null) {
				System.out.println(inputLine);
				LOGGER.info(inputLine);

				/**
				 * Ignore the first few lines
				 */

				if (inputLine.startsWith("EXCHANGE") || inputLine.startsWith("MARKET") || inputLine.startsWith("INTERVAL") || inputLine.startsWith("COLUMNS") || inputLine.startsWith("TIMEZONE")
						|| inputLine.startsWith("DATA=")) {
					continue;
				}

				/**
				 * Expect to retrieve first line as after-hour, and next 390
				 * lines as intraday.
				 */
				lineArray = inputLine.split(",");

				if (inputLine.startsWith("a")) {
					marketStartingDateTimeInSecond = lineArray[0].substring(1, lineArray[0].length());
					marketStartingDateTime = LocalDateTime.ofInstant(Instant.ofEpochSecond(Integer.parseInt(marketStartingDateTimeInSecond)), ZoneId.systemDefault());
					continue;
				}

				intradayQuote = new StockQuote();
				intradayQuote.setTicker(tickerName);
				intraDateTime = marketStartingDateTime.plusMinutes(Integer.parseInt(lineArray[0]));
				intradayQuote.setDateTime(intraDateTime);
				intradayQuote.setClosePrice(Double.parseDouble(lineArray[1]));
				intradayQuote.setHighPrice(Double.parseDouble(lineArray[2]));
				intradayQuote.setLowPrice(Double.parseDouble(lineArray[3]));
				intradayQuote.setOpenPrice(Double.parseDouble(lineArray[4]));
				intradayQuote.setVolumn(Long.parseLong(lineArray[5]));

				list.add(intradayQuote);

			}

			in.close();

		} catch (Throwable e) {
			LOGGER.error(e);
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (Throwable e) {
				}
			}
		}

		return list;
	}

}
