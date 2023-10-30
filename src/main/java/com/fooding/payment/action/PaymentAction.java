package com.fooding.payment.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fooding.payment.db.PaymentDAO;
import com.fooding.payment.db.PurchaseDTO;
import com.fooding.util.Action;
import com.fooding.util.ActionForward;

public class PaymentAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// 로그인 세션 제어 (비로그인 접근불가)
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		
		ActionForward forward = new ActionForward();
		if(id==null) {
			forward.setPath("./Main.me");
			forward.setRedirect(true);
		}
		
		
		
		
		
		// DB에서 장바구니 정보를 조회 + 주소, 주문일 정보 조회
		// PaymentDAO 객체 생성 - 장바구니정보 조회 메서드 호출
		PaymentDAO pdao = new PaymentDAO();
		ArrayList cartList = pdao.getCart(id); 
		
		
		// DB에서 주소, 주문일 정보 조회
		// PaymentDAO 객체 생성 - 정차지정보 조회 메서드 호출
		StopDTO stopdto = pdao.getStop();
		
		
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
