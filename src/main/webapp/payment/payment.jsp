<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<script src="https://cdn.iamport.kr/v1/iamport.js"></script>
<script type="text/javascript">
<!--
  function check(country){
 alert(country);
   }
 function check2(form){
 for(var i=0; i<form.country.length;i++){
  if(form.country[i].checked == true){
   alert(i+"번째의 "+form.country[i].value + " 선택");
  }
 }
   }
//-->
</script>
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
<h1> 주문내역 </h1>
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
<h1> 트럭 ㄴ</h1>
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
<h1> 결제방법</h1>
<form>
<input type="radio" name="pay" value="requestPay()"> 결제1
<input type="radio" name="pay" value="kakao"> 카카오
<input type="radio" name="pay" value="결제1"> 결제3
</form>

<!-- 결제방법 선택하기(2~3개) -->

<!-- 총 주문금액(=결제금액)  -->
<h1> 결제금액 </h1>
가격*갯수 + 가격*갯수 = 총금액이렇게 구하기
<c:forEach var = "dto" items="${cartList }">
<%--  총가격 : ${dto.가격} * ${dto. 갯수} --%>

</c:forEach>

<!-- 총 주문금액(=결제금액)  -->
<br>
<!-- 결제하기 버튼 라디오버튼 값에 따라 온클릭 이벤트 변경 -->
<script type="text/javascript">
var checkVal = $('input[name=pay]:checked').val();
</script>
<button onclick="requestPay()">결제하기</button>

<script>
const userCode = "imp75410442";
IMP.init(userCode);

function requestPay() {
  IMP.request_pay({
    pg: "INIBillTst", // 라디오 버튼마다 이거 달라지게 하기
    pay_method: "card",
    merchant_uid: "www",
    amount: 10,
    buyer_tel: "01012345678",
  } , function(data){ 
	  if(data.success){ // 결제성공후
		 var msg ="송공";
		 $.ajax({
         	type : 'post',
         	url : '/PaymentAfter.pay',
         });
	  }else{ // 결제취소할때
		 var msg ="결제를 취소하셨습니다";
	  }
	  alert(msg);
  });
}

  
  
</script>

<!-- 주문취소 버튼 (장바구니 페이지로 이동)  -->
<button onclick="history.back()">주문취소</button>
<!-- 본문들어가는곳(결제페이지) -->

<!-- 푸터들어가는곳 -->
<!-- 푸터들어가는곳 -->
</body>
</html>