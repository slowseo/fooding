<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>payment</title>
</head>
<body>
<!-- 헤더 들어가는곳 -->
<!-- 헤더 들어가는곳 -->


<!-- 본문들어가는곳(결제페이지) -->

<!-- 장바구니 정보 출력하기 출력하기(리스트) -->

<table border="1">
	<tr>
		<td>아이디</td>
		<td>이름</td>
		<td>나이</td>
		<td>성별</td>
		<td>이메일</td>
		<td>회원가입일</td>
	</tr>
		<c:forEach var = "dto" items="${cartList }">
	<tr>
		<td>${dto.id}</td>
		<td>${dto.name }</td>
		<td>${dto.age }</td>
		<td>${dto.gender }</td>
		<td>${dto.email }</td>
		<td>${dto.regdate }</td>
	</tr>
	</c:forEach>
</table>

<!-- 장바구니 정보 출력하기 출력하기(리스트) -->

<!-- 트럭 픽업위치, 주문시간(주문일) 출력하기 -->
<table border="1">
	<tr>
		<td>픽업 주소</td>
		<td> 날짜 </td>

	</tr>
	<tr>
		<td>${dto.id}</td>
		<td>${dto.name }</td>
	</tr>
</table>

<!-- 트럭 픽업위치, 주문시간(주문일) 출력하기 -->

<!-- 결제방법 선택하기(2~3개) -->
<input type="radio" name="pay" value="결제1"> 결제1
<input type="radio" name="pay" value="결제2"> 결제2
<input type="radio" name="pay" value="결제1"> 결제3


<!-- 결제방법 선택하기(2~3개) -->

<!-- 총 주문금액(=결제금액)  -->


<!-- 결제하기 버튼 -->
<input type="submit" value="결제하기" onclick="location.href='./paymentAfter.pay'">

<!-- 주문취소 버튼 (장바구니 페이지로 이동)  -->
<button onclick="location.href=''">주문취소</button>
<!-- 본문들어가는곳(결제페이지) -->

<!-- 푸터들어가는곳 -->
<!-- 푸터들어가는곳 -->
</body>
</html>