/**
 * 
 */
package com.fooding.orderDetail.com;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fooding.orderDetail.db.orderDetailDAO;
import com.fooding.util.Action;
import com.fooding.util.ActionForward;

/**
 * 주문내역 출력하기
 * 1. 디비에 있는 Purchase 테이블 내역 전부 출력
 * 2. 해당 내용을 게시판 형태로 정돈하기
 * 
 *
 */
public class orderDetailAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// 로그인 세션 제어
//		HttpSession session = request.getSession();
//		String id = (String)session.getAttribute("id");
//		
//		ActionForward forward = new ActionForward();
//		if(id==null) {
//			forward.setPath("./Main.me");
//			forward.setRedirect(true);
//			return forward;
//		}
		
		String id = "koki9625";
		
		// 기존에 저장된 데이터 가져와서 화면에 출력
		orderDetailDAO odao= new orderDetailDAO();
		// 0. 멤버번호 조회 - getMemberId(string id)
		int idNum = odao.getMemberId(id);
		
		int count = 0;
		count = odao.getPurchaseCount();
		
		//****************페이징처리1****************/
		// 한 페이지에 출력 할 글의 개수 설정
		int pageSize = 5; // 한페이지에 글 5개씩
		
		// 현 페이지가 몇페이지 인지확인
		String pageNum = request.getParameter("pageNum");
		if(pageNum==null){
			pageNum="1";
		} //5페이지 가고싶다? 주소값에 5 입력
		
		// 시작행 번호 계산하기
		int currentPage = Integer.parseInt(pageNum); // 현재 내가 몇페이지에 있는지 정보
		int startRow = (currentPage - 1)*pageSize + 1;
		
		// 끝행 번호 계산
		int endRow = currentPage * pageSize;
		//****************페이징처리1****************/
		
		// DAO - 주문내역정보 모두(list)를 가져오는 메서드
		ArrayList purchaseList = new ArrayList();
		purchaseList = odao.getPurchaseList(startRow, pageSize, idNum);
		
		System.out.println(" M : size : "+purchaseList.size());

		request.setAttribute("purchaseList", purchaseList);

		//**********페이징처리 2 *********************
				// 페이지 하단부에 페이지 블럭 생성(=다음, 다른 페이지로 넘어가게 하는거)
				// 게시판에 글이 있을 때
					
					// 전체 페이지수 계산
					// 글 15개 / 페이지당 10개씩 출력 => 페이지 2개 필요
					// 글 78개 / 페이지당 10개씩 출력 => 페이지 8개 필요
					// 필요한 페이지 = 글개수/페이지당 출력개수 + 1
					
					int pageCount = count/pageSize + (count%pageSize == 0? 0:1);
					
					// 한 화면에 보여줄 페이지 블럭 개수
					int pageBlock = 5;
					
					// 페이지 블럭 시작번호 계산
					// 1페이지 => 1		, 11페이지 => 11
					// 5페이지 => 1		, 25페이지 => 21
					//= 페이지블럭 시작번호는 10번을 넘어가면 바뀐다.
					int startPage = ((currentPage-1)/pageBlock) * pageBlock + 1;
					
					// 페이지블럭 마지막번호 (글이 꽉 차있을때)
					int endPage = startPage + pageBlock -1;
					// 페이지의 글이 없는 경우
					if(endPage > pageCount){ // 마지막페이지의 개수로 맞추기
						endPage = pageCount;
						
					}// result != 0
			
			//**********페이징처리 2 *********************
		
					// 페이징 처리에 필요한 정보 모두를 request영억에 저장해서 전달
					request.setAttribute("pageNum", pageNum);
					request.setAttribute("count", count);
					request.setAttribute("pageCount", pageCount);
					request.setAttribute("pageBlock", pageBlock);
					request.setAttribute("startPage", startPage);
					request.setAttribute("endPage", endPage);
					// 총 7개 request에 담아서 보내기
		
		
		ActionForward forward = new ActionForward();
		forward = new ActionForward();
		forward.setPath("./payment/paymentResult.jsp"); // 결제내역창 주소 입력해야함
		forward.setRedirect(false); // 전체출력이라 데이터 넘길거 없음
		
		return forward;
	}

}
