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
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		
		ActionForward forward = new ActionForward();
		if(id==null) {
			forward.setPath("./MemberLogin.mem");
			forward.setRedirect(true);
		}
		
		/*  --------------------------------------------------------     */
		// 0. 전달정보 저장하기
		// ArrayList 로 오는 전달정보 저장하기 (일단 어떻게 테스트하지)
		String[] getCartList  = request.getParameterValues("cartList"); // <-이거 써야함(맞는지 확인필요)
//		ArrayList<CartDTO> cartList = new ArrayList<>();
//		for (String cartData : getCartList) {
//			
//		    String[] dataArray = cartData.split(","); // 데이터는 쉼표(,)로 구분되어 있다고 가정
//
//		    CartDTO cartDTO = new CartDTO();
//		    cartDTO.setCart_id(Integer.parseInt(dataArray[0]));
//		    cartDTO.setMember_id(Integer.parseInt(dataArray[1]));
//		    cartDTO.setProduct_id(Integer.parseInt(dataArray[2]));
//		    cartDTO.setQuantity(Integer.parseInt(dataArray[3]));
//		    cartDTO.setAddress(dataArray[4]);
//		    cartDTO.setStopdate_id(Integer.parseInt(dataArray[5]));
//
//		    cartList.add(cartDTO);
//		}
		
		
		/*  --------------------------------------------------------     */
		
		
		
		
		
		
		
		 String[] arr = {"23","24"}; // 임시
		 
		// 뒤로가기 막기 . 장바구니 데이터가 전달되지 않은 경우!
		if(arr == null) {
			forward.setPath("./main.me");
			forward.setRedirect(true);
		}
		
		
		//===========================================================================================
		PaymentDAO pdao = new PaymentDAO();
		CartDTO CDto = new CartDTO();
		
		// 1. 장바구니 번호 String[] 이거 ArrayList로 변경하기
		ArrayList cart_id = pdao.stringToArrayList(arr); // 임시
//		ArrayList cart_id = pdao.cartList(cartList); // 장바구니 번호 얻어오기
//		 ArrayList stop_date = pdao.stopDateIdList(cartList); // 운행정보 가져오기
		
		//4. 장바구니 번호 ArrayList로 조회해서 상품정보 가져오기
		ArrayList purchaseList = pdao.getPurchase(cart_id);
		
		//5. 로그인 아이디로 회원정보 조회하기
		MemberDTO member = pdao.getMember("id234"); // 여기에 session id 값 넣어야함
		
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
