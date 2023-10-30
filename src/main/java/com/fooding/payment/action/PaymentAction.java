package com.fooding.payment.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fooding.payment.db.PaymentDAO;
import com.fooding.payment.db.PurchaseDTO;
import com.fooding.payment.db.cartDetailDTO;
import com.fooding.util.Action;
import com.fooding.util.ActionForward;

public class PaymentAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// 전달정보 저장하기
		
		
		// 로그인 세션 제어 (비로그인 접근불가)

		
		
		//리스트로저장
		// DB에서 장바구니 정보 가져오기
		// PaymentDAO 객체 생성 - 장바구니정보 조회 메서드 호출
		PaymentDAO pdao = new PaymentDAO();
		ArrayList cartList = pdao.getCart(); 
		
		
		//리스트로저장
		// DB에서 장바구니디테일 정보 가져오기
		// PaymentDAO 객체 생성 - 정차지정보 조회 메서드 호출
		ArrayList detailList = pdao.getDetail();
		
		
		// 결제방식 테이블에서 결제방식 정보 dto에 저장
		
		
		
		
		
		// reqest 영역에 정보 저장하기
		request.setAttribute("cartList", cartList);
		request.setAttribute("stopdto", stopdto);
		
		
		
		
		
		// 페이지 이동(./payment/payment.jsp)
		forward = new ActionForward();
		forward.setPath("./payment/payment.jsp");
		forward.setRedirect(false);
		
		return forward;
		
		
	}
	

}
