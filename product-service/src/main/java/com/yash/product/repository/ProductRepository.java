package com.yash.product.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.yash.product.entity.Product;

public interface ProductRepository extends MongoRepository<Product, Long>{

	
	@Query("{name:'?0'}")
	Product findItemByName(String name);

	@Query("{skuCode:'?0'}")
	Product findProductBySkuCode(String sku_code);
	
}
