package com.fooding.payment.db;

public class PurchaseDetailDTO {
	// 주문상세내역(purchase_detail) 테이블
	private int detail_id;
	private int purchase_id;
	private int product_id;
	private int quantity;
	private String address;
	private int payinfo_id;
	private int payment_id;
	public int getDetail_id() {
		return detail_id;
	}
	public void setDetail_id(int detail_id) {
		this.detail_id = detail_id;
	}
	public int getPurchase_id() {
		return purchase_id;
	}
	public void setPurchase_id(int purchase_id) {
		this.purchase_id = purchase_id;
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
	public int getPayinfo_id() {
		return payinfo_id;
	}
	public void setPayinfo_id(int payinfo_id) {
		this.payinfo_id = payinfo_id;
	}
	public int getPayment_id() {
		return payment_id;
	}
	public void setPayment_id(int payment_id) {
		this.payment_id = payment_id;
	}
	
	@Override
	public String toString() {
		return "PurchaseDetailDTO [detail_id=" + detail_id + ", purchase_id=" + purchase_id + ", product_id="
				+ product_id + ", quantity=" + quantity + ", address=" + address + ", payinfo_id=" + payinfo_id
				+ ", payment_id=" + payment_id + "]";
	}
	
}
