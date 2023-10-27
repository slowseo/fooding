package com.fooding.payment.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fooding.payment.db.PaymentDAO;
import com.fooding.payment.db.PaymentDTO;
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
		PaymentDAO dao = new PaymentDAO();
		PaymentDTO dto = dao.getCart(id);
		
		
		// DB에서 주소, 주문일 정보 조회
		// 
		
		
		
		
		
		
		
		
		
		
		
		return null;
	}
	

}