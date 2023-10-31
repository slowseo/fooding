package com.fooding.payment.db;

public class CartDTO {
	// 장바구니(cart) 테이블
	private int cart_id;		// 장바구니 번호
	private int member_id;		// 회원번호
	private int product_id;		// 상품번호
	private int quantity;		// 수량
	private String address;		// 주소
	private int stopdate_id;	// 운행정보
	public int getCart_id() {
		return cart_id;
	}
	public void setCart_id(int cart_id) {
		this.cart_id = cart_id;
	}
	public int getMember_id() {
		return member_id;
	}
	public void setMember_id(int member_id) {
		this.member_id = member_id;
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
	public int getStopdate_id() {
		return stopdate_id;
	}
	public void setStopdate_id(int stopdate_id) {
		this.stopdate_id = stopdate_id;
	}
	@Override
	public String toString() {
		return "CartDTO [cart_id=" + cart_id + ", member_id=" + member_id + ", product_id=" + product_id + ", quantity="
				+ quantity + ", address=" + address + ", stopdate_id=" + stopdate_id + "]";
	}
	
}
