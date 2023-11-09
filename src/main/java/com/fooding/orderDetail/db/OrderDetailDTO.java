package com.fooding.orderDetail.db;

import java.sql.Time;
import java.sql.Timestamp;

public class OrderDetailDTO {
	
	private int detail_id;		// 인덱스번호
	private int purchaseid;		// 주문번호(결제할때 사용)
	private int cart_id;		// 장바구니 번호
	private int member_id;		// 회원번호
	private int product_id;		// 상품번호
	private int quantity;		// 수량
	private Timestamp orderdate; // 주문일자
	private String date; 		// 운행일
	private String address;		// 주소
	private String stoptime; 	// 운행시간
	
	private String name; 		// 상품이름
	private int price; 			// 가격
	private String image; 		// 상품 이미지경로
	
	private String foodtruckName; // 푸드트럭 이름

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

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getStoptime() {
		return stoptime;
	}

	public void setStoptime(String stoptime) {
		this.stoptime = stoptime;
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

	public String getFoodtruckName() {
		return foodtruckName;
	}

	public void setFoodtruckName(String foodtruckName) {
		this.foodtruckName = foodtruckName;
	}

	public Timestamp getOrderdate() {
		return orderdate;
	}

	public void setOrderdate(Timestamp orderdate) {
		this.orderdate = orderdate;
	}

	@Override
	public String toString() {
		return "OrderDetailDTO [detail_id=" + detail_id + ", purchaseid=" + purchaseid + ", cart_id=" + cart_id
				+ ", member_id=" + member_id + ", product_id=" + product_id + ", quantity=" + quantity + ", orderdate="
				+ orderdate + ", date=" + date + ", address=" + address + ", stoptime=" + stoptime + ", name=" + name
				+ ", price=" + price + ", image=" + image + ", foodtruckName=" + foodtruckName + "]";
	}



	
}
