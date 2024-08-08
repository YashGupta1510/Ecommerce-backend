package com.yash.product.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yash.product.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{

	Optional<Product> findProductBySkuCode(String code);

}
