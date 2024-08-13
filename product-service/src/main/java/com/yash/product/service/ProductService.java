package com.yash.product.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yash.product.entity.Product;
import com.yash.product.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;
	
	public List<Product> getAllProducts(){
		 return productRepository.findAll();
	}
	
	public Product getProduct(String code) {
		return productRepository.findProductBySkuCode(code);
	}
	
	public void createProduct(Product product) {
		productRepository.save(product);
	}
	
}
