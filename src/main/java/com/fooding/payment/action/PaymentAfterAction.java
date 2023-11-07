package com.fooding.payment.action;

import java.util.ArrayList;

import javax.management.ConstructorParameters;
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
		String[] arr = request.getParameterValues("cart_id"); 
		
		 ArrayList<PurchaseDTO> purchaseList = new ArrayList<>();
		//장바구니 정보(회원번호, 수량, 주소)
		// purchase 테이블에 저장되어야할 정보
		String[] memberid = request.getParameterValues("member_id"); // 회원번호
		String[] productid = request.getParameterValues("product_id"); // 상품번호
        String[] quantities = request.getParameterValues("quantity"); //수량
        String[] addresses = request.getParameterValues("address"); // 주소
		// 저장안해도 되는 정보
        String[] prices = request.getParameterValues("price"); //가격
        String[] names = request.getParameterValues("name"); //상품이름
        
        // 주문번호 purchaseid
        int purchaseid = (Integer.parseInt(request.getParameter("purchase_id")));
        
        for (int i = 0; i < memberid.length; i++) {
            PurchaseDTO dto = new PurchaseDTO();
            dto.setMember_id(Integer.parseInt(memberid[i]));
            dto.setProduct_id(Integer.parseInt(productid[i]));
            dto.setQuantity(Integer.parseInt(quantities[i]));
            dto.setAddress(addresses[i]);
            dto.setPurchase_id(purchaseid);
            purchaseList.add(dto);
        }
        
		
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
		ArrayList cart_id = pdao.stringToArrayList(arr);
//		// 주문번호, 회원번호, 상품번호, 수량, 주소 순으로 입력)
//		ArrayList purchaseList = pdao.stringToArrayList(purchaseid, memberid, productid, quantities, addresses);
		
		//1. insert 메서드 호출하기
		// 오류났던게 지금 내가 사용하는 데이터베이스(구)에 date컬럼이 남아있어서!
		// 차후 수정해야함(date 지우는거 잊지말것)
		PurchaseDTO dto = new PurchaseDTO();
		pdao.insertPurchase(purchaseList);

		// 2. 장바구니 테이블 데이터 지우기
		// => 사용한 장바구니 데이터만 지우기! (장바구니 번호 이용)
//		pdao.deleteMember(cart_id);
		

		
		// 모든 처리 후 결제정보확인 페이지로 이동하기
		forward = new ActionForward();
		forward.setPath("./payment/paymentResult.jsp"); // 결제내역창 주소 입력해야함
		forward.setRedirect(false);
		
		return forward;
		
	
	}
}
