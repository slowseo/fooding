package com.fooding.payment.db;

public class PurchaseDTO {

	// 주문내역(purchase) 테이블
	private int detail_id;		//주문상세번호
	private int purchaseid;		// 주문번호 (결제처리할때 하나 만들어넣기)
	private int product_id;		//회원번호
	private int quantity;		//수량
	private String address;		//주소
	public int getDetail_id() {
		return detail_id;
	}
	public void setDetail_id(int detail_id) {
		this.detail_id = detail_id;
	}
	public int getPurchaseid() {
		return purchaseid;
	}
	public void setPurchaseid(int purchaseid) {
		this.purchaseid = purchaseid;
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
		return "PurchaseDTO [detail_id=" + detail_id + ", purchaseid=" + purchaseid + ", product_id=" + product_id
				+ ", quantity=" + quantity + ", address=" + address + "]";
	}
	
}
