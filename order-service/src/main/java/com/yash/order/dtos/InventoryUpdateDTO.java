package com.yash.order.dtos;

public class InventoryUpdateDTO {
	private Integer quantity;
	private Action action;

	public InventoryUpdateDTO(Integer quantity, Action action) {
		super();
		this.quantity = quantity;
		this.action = action;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Action getAction() {
		return action;
	}

	public void setAction(Action action) {
		this.action = action;
	}

	public enum Action {
		INCREASE, DECREASE
	}

}
