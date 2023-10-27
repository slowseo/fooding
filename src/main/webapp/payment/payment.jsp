<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<!-- jQuery -->
<script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js" ></script>
<!-- iamport.payment.js -->
<script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.1.5.js"></script>
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
		<td>상품이름</td>
		<td>가격</td>
		<td>갯수</td>
	</tr>
		<c:forEach var = "dto" items="${cartList }">
	<tr>
		<td>${dto.id}</td>
		<td>${dto.name }</td>
		<td>${dto.age }</td>
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

가격*갯수 + 가격*갯수 = 총금액이렇게 구하기
<c:forEach var = "dto" items="${cartList }">
 총가격 : ${dto.가격} * ${dto. 갯수}

</c:forEach>

<!-- 총 주문금액(=결제금액)  -->

<!-- 결제하기 버튼 -->
<input type="submit" value="결제하기" >

<!-- 주문취소 버튼 (장바구니 페이지로 이동)  -->
<button onclick="history.back()">주문취소</button>
<!-- 본문들어가는곳(결제페이지) -->

<!-- 푸터들어가는곳 -->
<!-- 푸터들어가는곳 -->
</body>
</html>