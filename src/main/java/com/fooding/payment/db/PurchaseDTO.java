package com.fooding.payment.db;

public class PurchaseDTO {

	// 주문내역(purchase) 테이블
	private int detail_id;		//주문상세번호
	private int purchase_id;		// 주문번호 (결제처리할때 하나 만들어넣기)
	private int product_id;		//상품번호
	private int member_id;		// 회원번호
	private int quantity;		//수량
	private String address;		//주소
	
	public int getMember_id() {
		return member_id;
	}
	public void setMember_id(int member_id) {
		this.member_id = member_id;
	}
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
	@Override
	public String toString() {
		return "PurchaseDTO [detail_id=" + detail_id + ", purchaseid=" + purchase_id + ", product_id=" + product_id
				+ ", quantity=" + quantity + ", address=" + address + "]";
	}
	
}
