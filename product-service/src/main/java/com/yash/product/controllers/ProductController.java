package com.yash.product.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yash.product.entity.Product;
import com.yash.product.service.ProductService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


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
	public ResponseEntity<Product> getProductBySkuCode(@RequestParam String skuCode){
		Optional<Product> product = productService.getProduct(skuCode) ;
		return product.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
	
	@PostMapping
	public ResponseEntity<String> createProduct(@RequestBody Product product) {
		productService.createProduct(product);
		return new ResponseEntity<String>("Product Created", HttpStatus.CREATED);
	}
	
	
	
}
