package com.fooding.payment.action;

import java.io.IOException;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fooding.util.Action;
import com.fooding.util.ActionForward;


public class PaymentFrontController extends HttpServlet{

	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("\n\n C : PaymentFrontController_doProcess() 호출 ");
		System.out.println(" C : GET/POST방식 모두 처리 ");
		
		System.out.println("\n -----------------1. 가상주소 계산 시작 --------------------");
		String requestURI = request.getRequestURI();
		System.out.println(" C : requestURI : "+ requestURI);
		String CTXPath = request.getContextPath();
		System.out.println(" C : CTXPath : "+CTXPath);
		String command = requestURI.substring(CTXPath.length());
		System.out.println(" C : command : "+command);		
		
		System.out.println("\n -----------------1. 가상주소 계산 시작 --------------------");

		
		System.out.println("\n -----------------2. 가상주소 매핑 시작 --------------------");
		Action action = null;
		ActionForward forward = null;
		
		if(command.equals("/Payment.pay")) { // 디비정보 가져와서 출력페이지 내용 보여야함
			System.out.println(" C : /Payment.pay 매핑" );
			System.out.println(" C : 패턴 3 - DB사용O, 페이지 출력 ");			
		
			action = new PaymentAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		else if(command.equals("/PaymentResult.pay")) { // 디비에 정보를 저장하고 이동해야함
			System.out.println(" C : /PaymentResult.pay 호출 ");
			System.out.println(" C : 패턴 2 - 데이터처리O, 페이지로 이동");
		
			action = new PaymentAfterAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/TestAjax.pay")) {
			System.out.println(" C : /TestAjax.pay 호출 ");
			System.out.println(" C : 패턴 2 - 데이터처리O, 페이지로 이동");
		
			action = new PaymentAfterAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
//<<<<<<< HEAD
//				
//				
//=======
		else if(command.equals("/payementResult.jsp")) {
			forward = new ActionForward();
			forward.setPath("./payment/paymentResult.jsp"); // 결제내역창 주소 입력해야함
			forward.setRedirect(false);
		}
			
//>>>>>>> branch 'feat-ajax' of https://github.com/slowseo/fooding.git
		
		System.out.println("\n -----------------2. 가상주소 매핑 시작 --------------------");

		System.out.println("\n -----------------3. 가상주소 이동 시작 --------------------");
		
		if(forward != null) { //이동정보가 존재할때 
			
			if(forward.isRedirect()) { // true
				System.out.println(" C : "+forward.getPath()+"로, 이동방식 : sendRedirect() ");
				
				response.sendRedirect(forward.getPath());
			}else { // false
				System.out.println(" C : "+forward.getPath()+"로, 이동방식 : forward() ");
				RequestDispatcher dis 
				           = request.getRequestDispatcher(forward.getPath());
				dis.forward(request, response);				
			}
			
		}		
		System.out.println("\n -----------------3. 가상주소 이동 시작 --------------------");
		
	}
	
	
	
	
	
	
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 System.out.println("\n\n C : PaymentFrontController_doGet() 호출 ");
		 doProcess(request, response);

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("\n\n C : PaymentFrontController_doPost() 호출 ");
		 doProcess(request, response);

	}
	

}// controller end
