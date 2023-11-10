package com.fooding.payment.action;

import java.util.ArrayList;
import java.util.List;

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
		
		// 로그인 세션 제어
//		HttpSession session = request.getSession();
//		String id = (String)session.getAttribute("id");
//		System.out.println("DAO id 값 : "+id);
//		ActionForward forward = new ActionForward();
//		if(id==null) { 
//			forward.setPath("./MemberLogin.mem");
//			forward.setRedirect(true);
//			return forward;
//		}
		
		String id="koki9625"; // 임시

		/*  --------------------------------------------------------     */
		// 0. 전달정보 저장하기 (물어보기)
//		ArrayList<CartDTO> cartList = (ArrayList<CartDTO>) request.getAttribute("cartList"); // <-이거 써야함(맞는지 확인필요)
		
//		 String[] arr = request.getParameterValues("cart_id"); // 카트번호만 받아오기
		
		/*  --------------------------------------------------------     */
		 String[] arr = {"1","2","5","6"}; // 임시
		 
		//  장바구니 데이터가 전달되지 않은 경우!
//		 if (cartList == null || cartList.isEmpty()) { 
//			    response.sendRedirect("./main.me");
//			    return null; // 중요: 이후 코드 실행을 막기 위해 return 사용
//			}
		
		
		//===========================================================================================
		PaymentDAO pdao = new PaymentDAO();
		CartDTO CDto = new CartDTO();
		
		// 1. 장바구니 번호 String[] 이거 ArrayList로 변경하기
		ArrayList cart_id = pdao.stringToArrayList(arr); // 임시
//		ArrayList cart_id = pdao.cartList(cartList); // 장바구니 번호 얻어오기
		
		//4. 장바구니 번호 조회해서 장바구니 정보 + 상품정보 + 날짜 가져오기
		ArrayList purchaseList = pdao.getPurchase(cart_id);
		
		//5. 로그인 아이디로 회원정보 조회하기
		MemberDTO member = pdao.getMember(id); // 여기에 session id 값 넣어야함
		
		// combinedName(purchaseList)
		String combinedName = pdao.combinedName(purchaseList);
	
		// reqest 영역에 정보 저장하기
		request.setAttribute("cart_id", cart_id);
		request.setAttribute("purchaseList", purchaseList);
		request.setAttribute("member", member);
		request.setAttribute("combinedName", combinedName);

		
		
		
		// 페이지 이동

		ActionForward forward = new ActionForward();
//		forward = new ActionForward();
		forward.setPath("./payment/payment.jsp");
		forward.setRedirect(false);
		
		return forward;
		
		
	}
	

}
