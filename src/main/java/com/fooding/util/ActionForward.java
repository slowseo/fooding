package com.fooding.util;

/**
 * 
 * ActionForward - 페이지 이동에 필요한 정보를 저장하는 객체 (기차표)
 * 	 이동할 주소 path (목적지)
 * 	 이동할 방식 isRedirect - true  : sendRedirect() 방식으로 이동 (직행/환승)
 * 										가상주소 -> 가상주소로 이동(주소변경 O, 화면 변경O)
 * 							- false : forward() 방식으로 이동 
 * 									   가상주소 -> 실제주소로 이동 (주소변경X, 화면변경O)
 *
 */

public class ActionForward {
	private String path;
	private boolean isRedirect;
	
	public ActionForward() { 
		System.out.println("-------------------");
		System.out.println(" 티켓정보 생성");
		System.out.println(" 목적지 x, 방식 x");
		System.out.println("-------------------");
	}
	
	// 알트 쉬프트 에스 + 알 키
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public boolean isRedirect() {	//get이 아니다 => boolean 타입에서 변수명이 is로 시작면 get으로 메서드로 취급함
		return isRedirect;
	}
	public void setRedirect(boolean isRedirect) {	//is가 사라진걸 확인할 수 있다
		this.isRedirect = isRedirect;
	}
	
	// 알트 쉬프트 에스 + 에스 키
	@Override
	public String toString() {
		return "ActionForward(티켓) [path(목적지)=" + path + ", isRedirect(방법)=" + isRedirect + "]";
	}
	
	
	
}








