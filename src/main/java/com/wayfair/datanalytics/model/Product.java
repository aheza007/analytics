package com.wayfair.datanalytics.model;

import com.fasterxml.jackson.annotation.JsonSetter;

public class Product {

	private String id;
	private String name;
	private String type;
	private String productClass;//make this enum
	private String packaging;//make this enum
	
	public String getId() {
		return id;
	}
	@JsonSetter("Product_Id")
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	@JsonSetter("Product_Name")
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	@JsonSetter("Product_Type")
	public void setType(String type) {
		this.type = type;
	}
	public String getProductClass() {
		return productClass;
	}
	@JsonSetter("Product_Class")
	public void setProductClass(String productClass) {
		this.productClass = productClass;
	}
	public String getPackaging() {
		return packaging;
	}
	@JsonSetter("Packaging")
	public void setPackaging(String packaging) {
		this.packaging = packaging;
	}
}
