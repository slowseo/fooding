package com.fooding.profile.db;

import java.sql.Date;
import java.sql.Timestamp;

public class ProfileDTO {
	// 1. 멤버 변수 선언
	// member 테이블
	private int member_id;
	private String id;
	private String pw;
	private String name;
	private String phone;
	private String email;
	private Timestamp regdate;
	private String reason;
	
	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	// product 테이블
	private int product_id;
	private String product_name;
	private int price;
	private String largeclass;
	private String smallclass;
	private String image;
	private String info;
	private int foodtruck_id;
	private String activation;
	
	// purchase 테이블
	private int detail_id;
	private int purchaseid;
	private int quantity;
	private String pickup;
	private String address;
	
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

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

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getPickup() {
		return pickup;
	}

	public void setPickup(String pickup) {
		this.pickup = pickup;
	}

	public String getActivation() {
		return activation;
	}

	public void setActivation(String activation) {
		this.activation = activation;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public int getFoodtruck_id() {
		return foodtruck_id;
	}

	public void setFoodtruck_id(int foodtruck_id) {
		this.foodtruck_id = foodtruck_id;
	}

	// foodtruck 테이블
	private String foodtruck_name;
	
	
	
	
	
	
	public int getProduct_id() {
		return product_id;
	}

	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}

	public String getProduct_name() {
		return product_name;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getLargeclass() {
		return largeclass;
	}

	public void setLargeclass(String largeclass) {
		this.largeclass = largeclass;
	}

	public String getSmallclass() {
		return smallclass;
	}

	public void setSmallclass(String smallclass) {
		this.smallclass = smallclass;
	}

	public String getFoodtruck_name() {
		return foodtruck_name;
	}

	public void setFoodtruck_name(String foodtruck_name) {
		this.foodtruck_name = foodtruck_name;
	}

	// 2. 기본 생성자 정의
	public ProfileDTO() {
		// TODO Auto-generated constructor stub
	}

	// 3. Getter, Setter 메서드 정의
	public int getMember_id() {
		return member_id;
	}

	public void setMember_id(int member_id) {
		this.member_id = member_id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Timestamp getRegdate() {
		return regdate;
	}

	public void setRegdate(Timestamp regdate) {
		this.regdate = regdate;
	}

	// toString 오버라이딩
	@Override
	public String toString() {
		return "ProfileDTO [member_id=" + member_id + ", id=" + id + ", pw=" + pw + ", name=" + name + ", phone="
				+ phone + ", email=" + email + ", regdate=" + regdate + "]";
	}
}
