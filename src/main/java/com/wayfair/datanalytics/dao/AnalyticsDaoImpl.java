package com.wayfair.datanalytics.dao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.time.Month;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wayfair.datanalytics.model.Product;
import com.wayfair.datanalytics.model.SaleData;
import com.wayfair.datanalytics.utils.Utils;

/**
 * Data Access object used to load and read data stored in files. it exposes
 * also a method to return {@code SaleData} which is an object representing
 * productType and a map of productids and their corresponding order date.
 */
public class AnalyticsDaoImpl implements IAnalyticsDao {
	/*
	 * Jackson object mapper to help me map json object to java object.
	 */
	private ObjectMapper mapper = new ObjectMapper();
	/*
	 * used to keep track of product ID a give product type is associated to.
	 */
	private Map<String, Set<String>> productTypesAndIdMap = new HashMap<>();
	/*
	 * used to keep track of what month(s) and quantity a given product Id has been
	 * ordered.
	 */
	private Map<String, Map<Month, Integer>> productIdAndSaleDateMap = new HashMap<>();

	/**
	 * Loading products data from a local resource file or from given path.
	 * currently're cashing data we want to only load this file stored locally once. 
	 */
	@SuppressWarnings("unchecked")
	private void loadProducts() {

		try {
			if (productTypesAndIdMap != null && productTypesAndIdMap.isEmpty()) {
				// get file from resources
				BufferedReader reader = getFileReader("product_data.json");
				// make object to Json
				((List<Product>) mapper.readValue(reader, new TypeReference<List<Product>>() {
				})).forEach(
						p -> productTypesAndIdMap.computeIfAbsent(p.getType(), k -> new HashSet<>()).add(p.getId()));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private BufferedReader getFileReader(String name) {
		InputStream in = getClass().getResourceAsStream("/data/" + name);
		return new BufferedReader(new InputStreamReader(in));
	}

	/**
	 * load and read data in .csv format representing dates of product sales
	 * and group them per product id, date of sale and count. 
	 * currently're cashing data we want to only load this file stored locally once. 
	 */
	public void loadProductsSale() {
		try {
			if (productIdAndSaleDateMap.isEmpty()) {
				BufferedReader reader = getFileReader("sales_data.csv");
				/*
				 * we're skipping the first line as it contains the columns titles
				 * i.e Product_ID,Order_Date,Ship_Date,Price_In_Cents,NPS_Score,Postal_Code,Service_Level
				 */
				reader.lines().skip(1).forEach(line -> {
					try {
						//line is comma separated, we're stripping based on ','
						String[] sale = line.split(",");
						//entry at index 0 is Product_ID and entry at index 1 is Order_Date 
						//we're counting ordered product and grouping them based on product Id, and month ordered. 
						productIdAndSaleDateMap.computeIfAbsent(sale[0], k -> new HashMap<>())
								.merge(Utils.getMonth(sale[1]), 1, (old, n) -> old + n);
						// ProductSale p=new ProductSale();
						// p.setProductId(sale[0]);
						// p.setOrderDate(sale[1]);
						// p.setShipDate(sale[2]);
						// p.setPrice(new BigDecimal(sale[3]));
						// p.setNpc(Integer.valueOf(sale[4]));
						// p.setZipCode(sale[5]);
						// p.setServiceLevel(Integer.valueOf(sale[6]));
					} catch (Exception e) {
						e.printStackTrace();
					}

				});
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		// return productSale;
	}

	private Reader getPath(String name) {
		String path = null;
		try {
			InputStream in = getClass().getResourceAsStream("data/" + name);
			return new BufferedReader(new InputStreamReader(in));

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Trigger loading data and then group data based on product Type of {@code Product}
	 * and monthly sales count. 
	 * @return List<SaleData>
	 */
	@Override
	public List<SaleData> loadData() {

		loadProducts();
		loadProductsSale();
		List<SaleData> data = new ArrayList<>();
		if (!productTypesAndIdMap.isEmpty() && !productIdAndSaleDateMap.isEmpty()) {
			productTypesAndIdMap.forEach((prodType, prodIds) -> {

				Map<Month, Integer> prodsSalePerMonth = new HashMap<>();
				//grouping product sales count based on product type and month ordered
				prodIds.forEach(id -> {
					Integer count = 0;
					Map<Month, Integer> monthSale = productIdAndSaleDateMap.get(id);
					for (Month m : monthSale.keySet()) {
						count = prodsSalePerMonth.get(m);
						if (count == null)
							count = 0;
						//summing current sales with previous sales of the same month. 
						prodsSalePerMonth.put(m, count + monthSale.get(m));
					}
				});
				//new sale record
				data.add(new SaleData(prodType, prodsSalePerMonth));
			});
		}
		return data;
	}

}
