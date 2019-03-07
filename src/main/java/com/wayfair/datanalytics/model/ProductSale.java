package com.wayfair.datanalytics.model;

import java.math.BigDecimal;

public class ProductSale {
//Product_ID,Order_Date,Ship_Date,Price_In_Cents,NPS_Score,Postal_Code,Service_Level
	private String productId;
	private String orderDate;
	private String shipDate;
	private BigDecimal price;//in cents
	private int npc;
	private String zipCode;
	private int serviceLevel;
	
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}
	public String getShipDate() {
		return shipDate;
	}
	public void setShipDate(String shipDate) {
		this.shipDate = shipDate;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public int getNpc() {
		return npc;
	}
	public void setNpc(int npc) {
		this.npc = npc;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	public int getServiceLevel() {
		return serviceLevel;
	}
	public void setServiceLevel(int serviceLevel) {
		this.serviceLevel = serviceLevel;
	}
	
}
