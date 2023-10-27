package com.fooding.payment.db;

public class PaymentDTO {

	// 장바구니(cart) 테이블
	private int cart_id;
	private int member_id;
	private int product_id;
	private String name;
	private int quantity;
	private String price;
	
	// 주문내역(order) 테이블
	private int order_id;
//	private int member_id;
	private String address; //주소
	private java.sql.Timestamp date; // 주문일
	private int status;
	
	// 주문상세내역(order_detail) 테이블
	private int detail_id;
//	private int order_id;
	private int product;
	
	// 정차지(stop) 테이블 
	private int stop_id;
	private String beginaddress; //정착지 주소
	private java.sql.Timestamp begintime;
	private java.sql.Timestamp endtime;
	
	
}
