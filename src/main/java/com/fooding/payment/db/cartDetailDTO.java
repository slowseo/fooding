package com.fooding.payment.db;

public class cartDetailDTO {
	private int cardetail_id;
	private int cart_id;
	private int product_id;
	private int quantity;
	private String address;
	public int getCardetail_id() {
		return cardetail_id;
	}
	public void setCardetail_id(int cardetail_id) {
		this.cardetail_id = cardetail_id;
	}
	public int getCart_id() {
		return cart_id;
	}
	public void setCart_id(int cart_id) {
		this.cart_id = cart_id;
	}
	public int getProduct_id() {
		return product_id;
	}
	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	@Override
	public String toString() {
		return "cartDetailDTO [cardetail_id=" + cardetail_id + ", cart_id=" + cart_id + ", product_id=" + product_id
				+ ", quantity=" + quantity + ", address=" + address + "]";
	}
	
}
