package com.wayfair.datanalytics.model;

import java.time.Month;
import java.util.HashMap;
import java.util.Map;

public class SaleData {

	private String productType;
	private  Map<Month, Integer> productIdsAndSaleDateMap = new HashMap<>();

	public SaleData() {
	}

	public SaleData(String prodType, Map<Month, Integer> productIdsAndSale) {
		this.productType = prodType;
		this.productIdsAndSaleDateMap = productIdsAndSale;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public Map<Month, Integer> getProductIdsAndSaleDateMap() {
		return productIdsAndSaleDateMap;
	}

	public void setProductIdsAndSaleDateMap(Map<Month, Integer> productIdsAndSaleDateMap) {
		this.productIdsAndSaleDateMap = productIdsAndSaleDateMap;
	}

	@Override
	public String toString() {
		return "SaleData [productType=" + productType + "]";
	}
	
	
}
