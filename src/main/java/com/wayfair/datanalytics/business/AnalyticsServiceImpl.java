package com.wayfair.datanalytics.business;

import java.time.Month;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.wayfair.datanalytics.dao.AnalyticsDaoImpl;
import com.wayfair.datanalytics.dao.IAnalyticsDao;
import com.wayfair.datanalytics.model.Sale;
import com.wayfair.datanalytics.model.SaleData;

public class AnalyticsServiceImpl implements IAnalyticService {

	private IAnalyticsDao dao = new AnalyticsDaoImpl();

	/**
	 * Method to return the top score n products. 
	 * @return List<Sale> - which is a list of Sale objects that represent 
	 * Product Type, count of number of sales in Peak Period(pp)
	 * and number of sales in non Peak Period (nps)
	 * and nps ration which is (pp/nps). 
	 */
	@Override
	public List<Sale> getProductsWithToScore(int n,String pathDataSet1,String pathDataSet2) {

		List<Sale> topProd = new ArrayList<>();
		List<SaleData> data = dao.loadData(pathDataSet1,pathDataSet2);
		for (SaleData dataItem : data) {
			Map<Month, Integer> salesCount = dataItem.getProductIdsAndSaleDateMap();
			Integer numberOfSalesInPeakPeriod = 0;
			Integer numberOfSalesInNonPeakPeriod = 0;
			for (Month m : salesCount.keySet()) {
				if (m == Month.OCTOBER || m == Month.NOVEMBER || m == Month.DECEMBER) {
					numberOfSalesInPeakPeriod += salesCount.get(m);
				} else {
					numberOfSalesInNonPeakPeriod += salesCount.get(m);
				}

			}
			topProd.add(new Sale(dataItem.getProductType(), numberOfSalesInPeakPeriod, numberOfSalesInNonPeakPeriod));
		}
		Collections.sort(topProd);//Sorting in ascending order based on Compare
		n=n>topProd.size()?n%topProd.size():n;//in case n is bigger than number of sales
		return topProd.subList(0, n);
	}

}
