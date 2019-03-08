package com.test.business;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.wayfair.datanalytics.business.AnalyticsServiceImpl;
import com.wayfair.datanalytics.business.IAnalyticService;
import com.wayfair.datanalytics.model.Sale;

public class TestAnalyticsBusiness {

	private final static String LOCAL_CSV_FILE_PATH = "/data/sales_data.csv";
	private final static String LOCAL_JSON_FILE_PATH = "/data/product_data.json";
	IAnalyticService service = new AnalyticsServiceImpl();
	List<Sale> salesData = new ArrayList<>();

	@Before
	public void setup() {
		salesData = service.getProductsWithToScore(6, LOCAL_JSON_FILE_PATH, LOCAL_CSV_FILE_PATH);
	}

	@Test
	public void testLoadData() {
		Assert.assertNotNull(salesData);
		Assert.assertFalse(salesData.isEmpty());
		Assert.assertFalse(salesData.size() == 20);
		Assert.assertTrue(salesData.size() == 6);
		int countOfItem = 0;
		for (Sale sale : salesData) {

			countOfItem += sale.getNumberOfSalesInNonPeakPeriod() + sale.getNumberOfSalesInPeakPeriod();
		}
		Assert.assertTrue(20 == countOfItem);
		Assert.assertTrue("Accent Chairs".equals(salesData.get(0).getProductType()));
		Assert.assertTrue(6.0 == salesData.get(0).getNpsScore());
		Assert.assertFalse(6.0 == salesData.get(4).getNpsScore());
		Assert.assertTrue("Tabling".equals(salesData.get(4).getProductType()));
		List<Sale> tempSaleData = salesData.subList(0, 5);
		int tempCountOfItem = 0;
		for (Sale sale : tempSaleData) {

			tempCountOfItem += sale.getNumberOfSalesInNonPeakPeriod() + sale.getNumberOfSalesInPeakPeriod();
		}
		// item type All-Clad PPPPP has 4 items that we sold in the non peak time!
		Assert.assertTrue(16 == tempCountOfItem);
	}
}
