package com.test.doa;

import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.wayfair.datanalytics.dao.AnalyticsDaoImpl;
import com.wayfair.datanalytics.dao.IAnalyticsDao;
import com.wayfair.datanalytics.model.SaleData;

public class TestAnlyticsDao {

	IAnalyticsDao dao = new AnalyticsDaoImpl();
	private final static String LOCAL_CSV_FILE_PATH = "/data/sales_data.csv";
	private final static String LOCAL_JSON_FILE_PATH = "/data/product_data.json";
	List<SaleData> salesData = new ArrayList<>();
	
	@Before
	public void setup() {
		salesData = dao.loadData(LOCAL_JSON_FILE_PATH, LOCAL_CSV_FILE_PATH);
	}
	@Test
	public void testLoadData() {
		Assert.assertNotNull(salesData);
		Assert.assertFalse(salesData.isEmpty());
		Assert.assertTrue(salesData.size() == 6);
		int countOfItem = 0;
		for (SaleData sale : salesData) {
			
			Map<Month, Integer> monthlySale = sale.getProductIdsAndSaleDateMap();
			
			for (Month m : monthlySale.keySet()) {
				countOfItem+=monthlySale.get(m);	
			}
		}
		
		Assert.assertTrue(20==countOfItem);
	}
}
