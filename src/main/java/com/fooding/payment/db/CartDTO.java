package com.fooding.payment.db;

public class CartDTO {
	// 장바구니(cart) 테이블
	private int cart_id;
	private int member_id;
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
	@Override
	public String toString() {
		return "CartDTO [cart_id=" + cart_id + ", member_id=" + member_id + "]";
	}

}
