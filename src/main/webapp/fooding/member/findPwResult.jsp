<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
</script>
</head>
<body>


	<c:if test="${not empty temporaryPassword}">
	<fieldset>
	
	    임시 비밀번호를 고객님의 이메일로 전송하였습니다.!<br>
	    
	     <hr>
		 <input type="button" value="로그인페이지로">
	</fieldset>
	</c:if>


	
	
	<c:if test="${empty temporaryPassword}">
		 일치하는 회원정보가 없습니다. <br>
		 
		 <hr>
		 <input type="button" value="로그인페이지로">
	</c:if>
	
	


</body>
</html>