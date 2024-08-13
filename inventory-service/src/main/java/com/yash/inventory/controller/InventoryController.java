package com.yash.inventory.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yash.inventory.entity.Inventory;
import com.yash.inventory.model.Action;
import com.yash.inventory.model.InventoryUpdateDTO;
import com.yash.inventory.service.InventoryService;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

	@Autowired
	InventoryService service;
	
	@GetMapping
    public ResponseEntity<Boolean> isInStock(@RequestParam String skuCode, @RequestParam Integer quantity) {
        return new ResponseEntity<Boolean>(service.isInStock(skuCode, quantity), HttpStatus.OK);
    }
	
	@PutMapping("/{skuCode}")
	public ResponseEntity<Inventory> updateQuantity(@PathVariable String skuCode, @RequestBody InventoryUpdateDTO update){
		Inventory inv = null;
		if(update.getAction() == Action.INCREASE) {
			inv = service.increaseQuantity(skuCode, update.getQuantity());
		}else if(update.getAction() == Action.DECREASE){
			inv = service.decreaseQuantity(skuCode, update.getQuantity());
		}else {
			return new ResponseEntity<Inventory>(inv, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Inventory>(inv, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Inventory> create(@RequestBody Inventory inventory){
		return new ResponseEntity<Inventory>(service.create(inventory), HttpStatus.OK);
	}
	
}
