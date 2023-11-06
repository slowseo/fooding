package com.fooding.payment.db;

import java.sql.Time;
import java.sql.Timestamp;

public class PaymentDTO {
	
	private int cart_id;		// 장바구니 번호
	private int member_id;		// 회원번호
	private int product_id;		// 상품번호
	private int quantity;		// 수량
	private String address;		// 주소
	private int stopdate_id;	// 운행정보
	private String name;  //상품이름
	private int price;  //가격
	private String image;  //이미지경로
	private String time;
	
	
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	@Override
	public String toString() {
		return "paymentDTO [cart_id=" + cart_id + ", member_id=" + member_id + ", product_id=" + product_id
				+ ", quantity=" + quantity + ", address=" + address + ", stopdate_id=" + stopdate_id + ", name=" + name
				+ ", price=" + price + ", image=" + image + "]";
	}

}
