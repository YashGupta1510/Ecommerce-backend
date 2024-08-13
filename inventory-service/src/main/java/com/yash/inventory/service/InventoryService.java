package com.yash.inventory.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yash.inventory.entity.Inventory;
import com.yash.inventory.repository.InventoryRepository;

@Service
public class InventoryService {

	@Autowired
	private InventoryRepository inventoryRepository;

	public boolean isInStock(String skuCode, Integer quantity) {
		boolean isInStock = inventoryRepository.existsBySkuCodeAndQuantityIsGreaterThanEqual(skuCode, quantity);
		return isInStock;
	}
	
	public Inventory decreaseQuantity(String skuCode, Integer byQuantity) {
		Inventory inventory = inventoryRepository.findBySkuCode(skuCode).get();
		inventory.setQuantity(inventory.getQuantity()-byQuantity);
		return inventoryRepository.save(inventory);
	}

	public Inventory increaseQuantity(String skuCode, Integer byQuantity) {
		Inventory inventory = inventoryRepository.findBySkuCode(skuCode).get();
		inventory.setQuantity(inventory.getQuantity()+byQuantity);
		return inventoryRepository.save(inventory);
	}
	
	public Inventory create(Inventory inventory) {
		return inventoryRepository.save(inventory);
	}
	
}
