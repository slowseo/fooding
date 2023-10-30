package com.fooding.payment.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fooding.util.Action;
import com.fooding.util.ActionForward;

public class PaymentAfterAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// 전달정보 저장하기
		
		
		// 로그인 세션 제어
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		
		ActionForward forward = new ActionForward();
		if(id==null) {
			forward.setPath("./Main.me");
			forward.setRedirect(true);
		}
		
		// 결제처리??
		
		
		
		
		// 뒤로가기 막기(뒤로로가기 두번누르는것도 막기)
		// => 메인페이지로 이동하게 만들기
		
		
		// 장바구니 테이블 데이터 지우기
		// 장바구니 dto 초기화
		
		
		
		
		
		// 모든 처리 후 결제정보확인 페이지로 이동하기
		
		
		return null;
	}
	
}
