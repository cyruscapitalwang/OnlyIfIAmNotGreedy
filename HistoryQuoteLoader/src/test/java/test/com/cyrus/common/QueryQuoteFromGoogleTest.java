package test.com.cyrus.common;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.cyrus.commom.IntraQuoteFromGoogle;
import com.cyrus.commom.StockQuote;

public class QueryQuoteFromGoogleTest {

	@Test
	public void query() {
		IntraQuoteFromGoogle google = new IntraQuoteFromGoogle();
		List<StockQuote> list = google.queryQuote("GDX");
		Assert.assertTrue(list.size() > 0);
	}

	@Test
	public void timeZone(){
		long time = 1483972200;
		LocalDateTime date = LocalDateTime.ofEpochSecond(time, 0, ZoneOffset.ofTotalSeconds(-300));
		
		
		
		
		System.out.println(date);
	}
}
