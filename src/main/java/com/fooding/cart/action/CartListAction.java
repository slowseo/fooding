package com.fooding.cart.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fooding.cart.db.CartDAO;
import com.fooding.cart.db.CartDTO;
import com.fooding.cart.db.CartListDTO;
import com.fooding.util.Action;
import com.fooding.util.ActionForward;

public class CartListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println(" M : CartListAction_execute() 호출");
		
		// 1. 로그인 세션 제어
		// 로그인 세션제어
				HttpSession session = request.getSession();
				
				session.setAttribute("id","hi"); //*
				
				String id = (String) session.getAttribute("id");
				
				ActionForward forward = new ActionForward();
				if(id == null) {
					forward.setPath("로그인페이지경로");
					forward.setRedirect(true);
					return forward;
				}
				
	// DB에서 회원번호를 조회
	// CartDAO 객체생성 	
			CartDAO dao = new CartDAO();
	// 회원번호 조회 메서드 호출 getMemberId(id)
			int memberId = dao.getMemberId(id);
	
	// 삭제할 운행일 불러오는 메서드 호출 deleteDate(memberId)
		ArrayList<CartDTO> deleteCartList = dao.deleteDate(memberId);
		dao.deleteCart(memberId, deleteCartList);
		
	// 장바구니 목록 가져오는 메서드 호출 getCartList(cartId)
			ArrayList<CartListDTO> cartList = dao.getCartList(memberId);
			
	// request 영역에 정보를 저장
			request.setAttribute("cartList", cartList);
			
	// ./cart/cart.jsp에 출력
			//forward.setPath("./cart/cart.jsp");
			forward.setPath("./fooding/cart/cart.jsp");
			forward.setRedirect(false);
			
			return forward;
			
			
		
	}

}
