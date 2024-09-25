package com.yash.order.clients;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.yash.order.dtos.InventoryUpdateDTO;


@FeignClient(name = "inventory-service")
public interface InventoryClient {

    @GetMapping("/api/inventory")
    Boolean isInStock(@RequestParam String skuCode, @RequestParam Integer quantity);
    
    @PutMapping("/{skuCode}")
	void updateQuantity(@PathVariable String skuCode, @RequestBody InventoryUpdateDTO update);
	
}
