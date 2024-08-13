package com.yash.product.entity;

import java.util.Map;

import org.springframework.data.mongodb.core.mapping.Document;

@Document("products")
public class Product {

	String name;
	double price;
	String description;
	String skuCode;
	Map<String, Object> additionalProperties;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getSkuCode() {
		return skuCode;
	}

	public void setSkuCode(String skuCode) {
		this.skuCode = skuCode;
	}
	
	public Map<String, Object> getAdditionalProperties() {
		return additionalProperties;
	}

	public void setAdditionalProperties(Map<String, Object> additionalProperties) {
		this.additionalProperties = additionalProperties;
	}

	public Product(String name, double price, String description, String skuCode,
			Map<String, Object> additionalProperties) {
		super();
		this.name = name;
		this.price = price;
		this.description = description;
		this.skuCode = skuCode;
		this.additionalProperties = additionalProperties;
	}

	@Override
	public String toString() {
		return "Product [ name=" + name + ", price=" + price + ", description=" + description
				+ ", skuCode=" + skuCode + ", additionalProperties=" + additionalProperties + "]";
	}

	

}
