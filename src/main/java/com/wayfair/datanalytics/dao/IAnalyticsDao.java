package com.wayfair.datanalytics.dao;

import java.util.List;

import com.wayfair.datanalytics.model.SaleData;

public interface IAnalyticsDao {
	List<SaleData> loadData();
}
