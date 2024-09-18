package com.yash.order.dtos;

public class ProductDTO {
    private String skuCode;
    private String name;
    private double price;
    private int stock;
	public String getSkuCode() {
		return skuCode;
	}
	public void setSkuCode(String skuCode) {
		this.skuCode = skuCode;
	}
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
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	@Override
	public String toString() {
		return "ProductDto [skuCode=" + skuCode + ", name=" + name + ", price=" + price + ", stock=" + stock + "]";
	}
}