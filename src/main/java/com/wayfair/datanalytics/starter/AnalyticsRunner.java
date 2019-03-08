package com.wayfair.datanalytics.starter;

import java.util.List;

import com.wayfair.datanalytics.business.AnalyticsServiceImpl;
import com.wayfair.datanalytics.business.IAnalyticService;
import com.wayfair.datanalytics.model.Sale;
import com.wayfair.datanalytics.utils.Utils;

/**
 * Class used to run the analytics service, accepting parms from user and
 * printing the result.
 * 
 * @author daheza
 *
 */
public class AnalyticsRunner {
	private static String CSV_FILE_PATH = "/data/sales_data.csv";
	private static String JSON_FILE_PATH = "/data/product_data.json";

	public static void main(String[] args) {

		// validate file paths,
		validateFilePath(args);

		setDataFilesPath(args);

		IAnalyticService analyticService = new AnalyticsServiceImpl();
		List<Sale> sales = analyticService.getProductsWithToScore(5, JSON_FILE_PATH, CSV_FILE_PATH);
		if (sales != null && !sales.isEmpty()) {
			System.out.println();
			System.out.println("5 product types with the best peak / non-peak sales ratio ");
			System.out.println("**********************************************************");
			sales.forEach(sale -> {
				System.out.println(String.format("%s: %.3f\n", sale.getProductType(), sale.getNpsScore()));
			});
		}

	}

	private static void setDataFilesPath(String[] args) {
		if (args != null && args.length == 2) {
			if (args[0].endsWith(".json") && args[1].endsWith(".csv")) {
				JSON_FILE_PATH = args[0];
				CSV_FILE_PATH = args[1];
			} else if (args[0].endsWith(".csv") && args[1].endsWith(".json")) {
				JSON_FILE_PATH = args[1];
				CSV_FILE_PATH = args[0];
			}
		}
	}

	private static void validateFilePath(String[] args) {
		if (args != null && args.length == 2) {
			for (String s : args) {
				if (!Utils.validateFile(s)) {
					System.out.println(String
							.format("Invalid Path %s please provide valid paths or let the Tool use cached data", s));
					System.exit(1);
				}
			}
		} else
			System.out.println("!!!!!!!!!!!!!! Invalid number of argument, The tool will use local files in its resources. !!!!!!!!!!!!!!");

	}

}
