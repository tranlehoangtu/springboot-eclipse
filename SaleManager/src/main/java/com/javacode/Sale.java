package com.javacode;

public class Sale {
	private Long id;
	private String item;
	private int quantity;
	private float amout;

	public Sale() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public float getAmout() {
		return amout;
	}

	public void setAmout(float amout) {
		this.amout = amout;
	}

}
