package com.yash.product.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="products")
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	long id;
	
	@Column(nullable = false)
	String name;
	
	@Column(nullable = false)
	long price;
	
	@Column(nullable = true)
	String description;
	
	@Column(nullable = false, unique = true)
	String skuCode;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getPrice() {
		return price;
	}

	public void setPrice(long price) {
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

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", price=" + price + ", description=" + description
				+ ", skuCode=" + skuCode + "]";
	}

	public Product(long id, String name, long price, String description, String skuCode) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.description = description;
		this.skuCode = skuCode;
	}

	public Product() {
		super();
	}
	
	
	
}
