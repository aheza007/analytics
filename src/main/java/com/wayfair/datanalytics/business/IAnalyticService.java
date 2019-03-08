package com.wayfair.datanalytics.business;

import java.util.List;

import com.wayfair.datanalytics.model.Sale;

public interface IAnalyticService {
	List<Sale> getProductsWithToScore(int n,String pathDataSet1,String pathDataSet2);
}
