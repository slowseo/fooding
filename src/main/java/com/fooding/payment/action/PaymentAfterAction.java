package com.fooding.payment.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fooding.payment.db.PaymentDAO;
import com.fooding.payment.db.PurchaseDTO;
import com.fooding.util.Action;
import com.fooding.util.ActionForward;

public class PaymentAfterAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// 전달정보 저장하기
		//장바구니 번호
			// 이게 있어야지 데이터 지울 수 있음
		
		//장바구니 정보(회원번호, 수량, 주소)
		// purchase 테이블에 저장되어야할 정보
		String[] memberid = request.getParameterValues("member_id"); // 회원번호
		String[] productid = request.getParameterValues("product_id"); // 상품번호
        String[] quantities = request.getParameterValues("quantity"); //수량
        String[] addresses = request.getParameterValues("address"); // 주소
		// 저장안해도 되는 정보
        String[] prices = request.getParameterValues("price"); //가격
        String[] names = request.getParameterValues("name"); //상품이름
		// 주문번호
			// purchaseid 값을 가져와야함. 그러면..? AJax를 이용해야해돼
		
		// 로그인 세션 제어
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		
		ActionForward forward = new ActionForward();
		if(id==null) {
			forward.setPath("./Main.me");
			forward.setRedirect(true);
		}
		
		// 결제처리? ( 일단 하나의 DTO에 모든 정보를 때려박은거)
		// 0. arrylist로 만들기
		PaymentDAO pdao = new PaymentDAO();
		// 주문번호, 회원번호, 상품번호, 수량, 주소 순으로 입력)
		ArrayList purchaseList = pdao.stringToArrayList(names, memberid, productid, quantities, addresses);
		//1. insert 메서드 호출하기
		

		// 뒤로가기 막기(뒤로로가기 두번누르는것도 막기)
		// => 메인페이지로 이동하게 만들기
		
		
		// 장바구니 테이블 데이터 지우기
		// => 사용한 장바구니 데이터만 지우기! (장바구니 번호 이용)
		
		
		
		
		
		// 모든 처리 후 결제정보확인 페이지로 이동하기
		forward = new ActionForward();
		forward.setPath("./paymentList/pay");
		forward.setRedirect(true);
		
		return forward;
		
	}
	
}
