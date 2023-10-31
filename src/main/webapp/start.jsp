<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
	<h1>start.jsp</h1>
	
	MVC 프로젝트의 시작지점
	
	<%
		System.out.println("MVC 프로젝트의 시작지점");
		System.out.println("* MVC 프로젝트에서 실행가능한 유일한 JSP 파일");
		System.out.println("* 주소줄에 .jsp 호출 x");
		
		// 실행주소
// 		response.sendRedirect("./member");
// 		response.sendRedirect("./member2"); // 이동은 한번밖에 못한다. 반드시 하나만 쓴다
// 		response.sendRedirect("./how.me"); //.me가 붙으면 실행되는지 확인하기

		//[ jsp 내장객체 request,response] 가지고 있음

// 		response.sendRedirect("./MemberJoin.me"); 
// 		response.sendRedirect("./Main.me"); //조인에서 로그인으로 변경함
		
// 		response.sendRedirect("./test.bo");
		response.sendRedirect("./test.pay");


	%>
</body>
</html>