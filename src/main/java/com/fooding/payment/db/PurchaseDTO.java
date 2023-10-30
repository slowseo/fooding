package com.fooding.payment.db;

public class PurchaseDTO {

	// 주문내역(purchase) 테이블
	private int purchase_id;
	private int member_id;
	private java.sql.Timestamp date; // 주문일
	public int getPurchase_id() {
		return purchase_id;
	}
	public void setPurchase_id(int purchase_id) {
		this.purchase_id = purchase_id;
	}
	public int getMember_id() {
		return member_id;
	}
	public void setMember_id(int member_id) {
		this.member_id = member_id;
	}
	public java.sql.Timestamp getDate() {
		return date;
	}
	public void setDate(java.sql.Timestamp date) {
		this.date = date;
	}
	@Override
	public String toString() {
		return "PurchaseDTO [purchase_id=" + purchase_id + ", member_id=" + member_id + ", date=" + date + "]";
	}

	
}
