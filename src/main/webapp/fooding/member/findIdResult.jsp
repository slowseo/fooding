<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

 
    <%
        // 세션에서 foundId 값을 가져옴
        String foundId = (String) session.getAttribute("foundId");
    %>
    
	<c:if test="${not empty foundId}">
		<p>ID : ${foundId}</p>
	</c:if>
	<c:if test="${empty foundId}">
		<p> 일치하는 회원정보가 없습니다.</p>
	</c:if>



	
</body>
</html>