package com.fooding.payment.db;

public class PurchaseDetailDTO {
	// 주문상세내역(purchase_detail) 테이블
	private int detail_id;
	private int purchase_id;
	private int product;
	private String name;
	private int quantity;
	private int payinfo_id;
}
