package com.wayfair.datanalytics.model;

/**
 * Sale objects that represent Product Type, count of number of sales in Peak
 * Period(pp) and number of sales in non Peak Period (nps) and nps ration which
 * is (pp/nps).
 * 
 * @author daheza
 *
 */
public class Sale implements Comparable<Sale> {

	private String productType;
	private Integer numberOfSalesInPeakPeriod;
	private Integer numberOfSalesInNonPeakPeriod;
	private double npsScore;// peak / non-peak sales ratio

	public Sale() {
	}

	public Sale(String productType, Integer numberOfSalesInPeakPeriod, Integer numberOfSalesInNonPeakPeriod) {
		this.productType = productType;
		this.numberOfSalesInPeakPeriod = numberOfSalesInPeakPeriod;
		this.numberOfSalesInNonPeakPeriod = numberOfSalesInNonPeakPeriod;
		//to avoid dividing zero. 
		this.npsScore = numberOfSalesInPeakPeriod
				/ (numberOfSalesInNonPeakPeriod == 0 ? 1.0 : numberOfSalesInNonPeakPeriod * 1.0);
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public Integer getNumberOfSalesInPeakPeriod() {
		return numberOfSalesInPeakPeriod;
	}

	public void setNumberOfSalesInPeakPeriod(Integer numberOfSalesInPeakPeriod) {
		this.numberOfSalesInPeakPeriod = numberOfSalesInPeakPeriod;
	}

	public Integer getNumberOfSalesInNonPeakPeriod() {
		return numberOfSalesInNonPeakPeriod;
	}

	public void setNumberOfSalesInNonPeakPeriod(Integer numberOfSalesInNonPeakPeriod) {
		this.numberOfSalesInNonPeakPeriod = numberOfSalesInNonPeakPeriod;
	}

	public double getNpsScore() {
		return npsScore;
	}

	@Override
	public int compareTo(Sale o) {
		return Double.compare(o.npsScore, this.npsScore);
	}

	@Override
	public String toString() {
		return "Sale [productType=" + productType + ", numberOfSalesInPeakPeriod=" + numberOfSalesInPeakPeriod
				+ ", numberOfSalesInNonPeakPeriod=" + numberOfSalesInNonPeakPeriod + ", npsScore=" + npsScore + "]";
	}

}
