package com.wayfair.datanalytics.starter;

import java.util.List;

import com.wayfair.datanalytics.business.AnalyticsServiceImpl;
import com.wayfair.datanalytics.business.IAnalyticService;
import com.wayfair.datanalytics.model.Sale;

/**
 * Class used to run the analytics service,
 * accepting parms from user and printing the result. 
 * @author daheza
 *
 */
public class AnalyticsRunner {

	public static void main(String[] args) {
		IAnalyticService analyticService=new AnalyticsServiceImpl();
		List<Sale> sales=analyticService.getProductsWithToScore(5);
		if(sales!=null && !sales.isEmpty()) {
			sales.forEach(sale->{
				System.out.println(String.format("%s: %.3f\n", sale.getProductType(),sale.getNpsScore()));
			});
		}

	}

}
