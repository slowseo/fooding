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

		response.sendRedirect("./CartList.car");


	%>
</body>
</html>