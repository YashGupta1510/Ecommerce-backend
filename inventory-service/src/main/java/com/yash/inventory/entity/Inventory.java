package com.yash.inventory.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "inventory")
public class Inventory {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String skuCode;
	private Integer quantity;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getSkuCode() {
		return skuCode;
	}
	public void setSkuCode(String skuCode) {
		this.skuCode = skuCode;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public Inventory(long id, String skuCode, Integer quantity) {
		super();
		this.id = id;
		this.skuCode = skuCode;
		this.quantity = quantity;
	}
	public Inventory() {
		super();
	}
	@Override
	public String toString() {
		return "Inventory [id=" + id + ", skuCode=" + skuCode + ", quantity=" + quantity + "]";
	}
	
	
	
}
