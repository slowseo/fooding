package com.fooding.payment.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fooding.payment.db.PaymentDAO;
import com.fooding.payment.db.PaymentDTO;
import com.fooding.payment.db.PurchaseDTO;
import com.fooding.payment.db.cartDetailDTO;
import com.fooding.paymentResult.action.PaymentResultAction;
import com.fooding.util.Action;
import com.fooding.util.ActionForward;

public class PaymentAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// 전달정보 저장하기(request로 구매할 장바구니 정보 받아오기) => 결제완료 후 삭제
		// arraylist 로 받아와서 동적처리하기
		
		
		// 로그인 세션 제어
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		
		ActionForward forward = new ActionForward();
		if(id==null) {
			forward.setPath("./MemberLogin.mem");
			forward.setRedirect(true);
		}
		
		
		//리스트로저장
		// DB에서 장바구니 정보 가져오기(저장한 전달정보 이용하기)
		// PaymentDAO 객체 생성 - 장바구니정보 조회 메서드 호출
		PaymentDAO pdao = new PaymentDAO();
		ArrayList cartList = pdao.getCart(); 
		
		
		//리스트로저장
		// DB에서 장바구니디테일 정보 가져오기
		// PaymentDAO 객체 생성 - 정차지정보 조회 메서드 호출
		ArrayList detailList = pdao.getDetail();
		
		
		
		
		// reqest 영역에 정보 저장하기
		request.setAttribute("cartList", cartList);
		
		
		
		
		
		// 페이지 이동(./payment/payment.jsp)
		forward = new ActionForward();
		forward.setPath("./payment/payment.jsp");
		forward.setRedirect(false);
		
		return forward;
		
		
	}
	

}
