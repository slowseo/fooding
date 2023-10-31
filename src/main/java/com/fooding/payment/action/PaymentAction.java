package com.fooding.payment.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fooding.payment.db.PaymentDAO;
import com.fooding.payment.db.PurchaseDTO;
import com.fooding.paymentResult.action.PaymentResultAction;
import com.fooding.util.Action;
import com.fooding.util.ActionForward;

public class PaymentAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// ArrayList 로 오는 전달정보 저장하기
		// (request로 구매할 장바구니번호 받아오기) => 결제완료 후 삭제 (일단 어떻게 테스트하지)
		 String[] arr = request.getParameterValues("cart_id");
//		 int[] cart_id = null; 	//int변환은 마지막에 하기
//		 if( arr != null ){ 
//			 cart_id = new int[ arr.length ]; 
//			 	for( int i=0; i<arr.length;i++) {
//			 		cart_id[i]=Integer.parseInt(arr[i]);
//			 	}
//		 }
//		 System.out.println(cart_id);

		
		// 로그인 세션 제어
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		
		ActionForward forward = new ActionForward();
		if(id==null) {
			forward.setPath("./MemberLogin.mem");
			forward.setRedirect(true);
		}
		
		//===========================================================================================
		
		// 1. 장바구니 번호 String[] 이거 ArrayList로 변경하기
		PaymentDAO pdao = new PaymentDAO();
		ArrayList cart_id = pdao.stringToArrayList(arr);
		
		// 2. 장바구니 번호 ArrayList로 DB 조회해서 장바구니 정보 가져오기
		ArrayList cartList = pdao.getCart(cart_id); 
		
		// 장바구니에서 상품번호 가져오기(getId) => 저장
		
		
		// 상품번호로 상품이름 가져오기(getName)  => 저장
		
		// 상품번호로 상품이미지주소 가져오기(getImgSrc)  => 저장
		
		
		// reqest 영역에 정보 저장하기
		// 장바구니 번호
		// 조회한 장바구니 정보
		// 상품이름
		// 상품번호
		request.setAttribute("cart_id", cart_id);
		request.setAttribute("cartList", cartList);
		
		
		
		
		
		// 페이지 이동(./payment/payment.jsp)
//		forward = new ActionForward();
//		forward.setPath("./payment/payment.jsp");
//		forward.setRedirect(false);
//		
		return null;
		
		
	}
	

}
