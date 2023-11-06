package com.fooding.payment.action;

import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fooding.payment.db.CartDTO;
import com.fooding.payment.db.MemberDTO;
import com.fooding.payment.db.PaymentDAO;
import com.fooding.payment.db.PurchaseDTO;
import com.fooding.util.Action;
import com.fooding.util.ActionForward;

public class PaymentAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// 0. 전달정보 저장하기
		// ArrayList 로 오는 전달정보 저장하기
		// (request로 구매할 장바구니번호 받아오기) => 결제완료 후 삭제 (일단 어떻게 테스트하지)
		CartDTO cartDto = new CartDTO();
		
		 String[] arr = {"23","24"}; // 임시
//      ArrayList<CartDTO> cartList  = request.getParameterValues("cartList"); <-이거 써야함
		 
		// 로그인 세션 제어
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		
		ActionForward forward = new ActionForward();
		if(id==null) {
			forward.setPath("./MemberLogin.mem");
			forward.setRedirect(true);
		}
		
		// 뒤로가기 막기 . 장바구니 데이터가 전달되지 않은 경우!
		if(arr == null) {
			forward.setPath("./main.me");
			forward.setRedirect(true);
		}
		//===========================================================================================
		PaymentDAO pdao = new PaymentDAO();
		
		// 1. 장바구니 번호 String[] 이거 ArrayList로 변경하기
		ArrayList cart_id = pdao.stringToArrayList(arr); // 임시
//		ArrayList cart_id = pdao.cartList(cartList); // 상품번호 얻어오기
		
		//4. 장바구니 번호 ArrayList로 조회해서 상품정보 가져오기
		ArrayList purchaseList = pdao.getPurchase(cart_id);
		
		//5. 로그인 아이디로 회원정보 조회하기
		MemberDTO member = pdao.getMember("id234");
		
		//6. 상품이름들을 하나의 String으로 만들기
		
		
		// reqest 영역에 정보 저장하기
		request.setAttribute("cart_id", cart_id);
		request.setAttribute("purchaseList", purchaseList);
		request.setAttribute("member", member);
		
		
		
		
		// 페이지 이동

		forward = new ActionForward();
		forward.setPath("./payment/payment.jsp");
		forward.setRedirect(false);
		
		return forward;
		
		
	}
	

}
