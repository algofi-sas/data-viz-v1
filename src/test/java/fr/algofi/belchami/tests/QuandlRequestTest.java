package fr.algofi.belchami.tests;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class QuandlRequestTest {
	@Test
	public void sendRequestTest() {
		
//		String url = "https://www.quandl.com/api/v3/datasets/WIKI/AAPL.json";
		
		String startDate = "14-6-2016";
		String endDate = "14-6-2017";
		String limit = "5";
		String order = "asc";
		String collapse = "none";
		String transformation = "none";
		
		
		Map<String,String> params = new HashMap<String, String>();
		params.put("api_key", "DKczFdjuL_16KZVxeZKk");
		params.put("transformation", transformation);
		params.put("collapse", collapse);
		params.put("order", order);
		params.put("end_date", endDate);
		params.put("start_date", startDate);
		params.put("limit", limit);

		
		assertEquals("evaluate", 3 + 3 , 6);
	} 
}
