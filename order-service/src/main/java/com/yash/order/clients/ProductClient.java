package com.yash.order.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.yash.order.dtos.ProductDTO;

@FeignClient(name = "product-service")
public interface ProductClient {

    @GetMapping("/api/products/{skuCode}")
    ProductDTO getProductBySkuCode(@PathVariable("skuCode") String productId);
}

