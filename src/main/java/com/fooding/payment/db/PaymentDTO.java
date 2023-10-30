package com.fooding.payment.db;

public class PaymentDTO {
	private int payment_id;
	private String method; // 결제방식번호
	
	public int getPayment_id() {
		return payment_id;
	}
	public void setPayment_id(int payment_id) {
		this.payment_id = payment_id;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	@Override
	public String toString() {
		return "PaymentDTO [payment_id=" + payment_id + ", method=" + method + "]";
	}
	
}
