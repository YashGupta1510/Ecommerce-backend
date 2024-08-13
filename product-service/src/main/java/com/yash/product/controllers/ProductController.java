package com.yash.product.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yash.product.entity.Product;
import com.yash.product.service.ProductService;


@RestController
@RequestMapping("/api/products")
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@GetMapping
	public ResponseEntity<List<Product>> getAllProducts(){
		List<Product> products = productService.getAllProducts();
		return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
	}
	
	@GetMapping(value = "/{skuCode}")
	public ResponseEntity<Product> getProductBySkuCode(@PathVariable String skuCode){
		Product product = productService.getProduct(skuCode);
		return new ResponseEntity<>(product, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<String> createProduct(@RequestBody Product product) {
		productService.createProduct(product);
		return new ResponseEntity<String>("Product Created", HttpStatus.CREATED);
	}
	
	
	
}
