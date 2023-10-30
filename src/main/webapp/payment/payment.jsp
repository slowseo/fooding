<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<!-- 포트원 결제연동 소스 -->
<script src="https://cdn.iamport.kr/v1/iamport.js"></script>
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
<h1> 트럭 </h1>
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

<!-- 결제방법 선택하기(2~3개) 
value에 dto.method 하면 될 듯-->
<h1> 결제방법</h1>
<form>
<input type="radio" name="pay" value="INIBillTst"> 카드결제
<input type="radio" name="pay" value="kakaopay"> 카카오 간편결제
<input type="radio" name="pay" value="결제1"> 결제3
</form>

<!-- 결제방법 선택하기(2~3개) -->

<!-- 총 주문금액(=결제금액)  -->
<h1> 결제금액 </h1>
가격*갯수 + 가격*갯수 = 총금액이렇게 구하기
<c:forEach var = "dto" items="${cartList }">
<%--  총가격 : ${dto.가격 * dto. 갯수} --%>

</c:forEach>

<!-- 총 주문금액(=결제금액)  -->
<br>
<!-- 결제하기 버튼 라디오버튼 값에 따라 결제수단 변경 -->
<button onclick="requestPay()">결제하기</button>

<script>
function checkRadioAndPay() {
  var selectedRadio = document.querySelector('input[name="pay"]:checked');
  
  if (selectedRadio) {
    // 라디오 버튼이 선택된 경우, 결제를 진행
    requestPay();
  } else {
    // 라디오 버튼이 선택되지 않은 경우, 알림을 표시
    alert("라디오 버튼을 선택하세요.");
  }
}

const userCode = "imp75410442";
IMP.init(userCode);

function requestPay() {
	// 라디오 버튼 선택에 따라 pg 값을 동적으로 설정
	var selectedPG = document.querySelector('input[name="pay"]:checked').value; 

// 	var merchant_uid = generateRandomMerchantUID();

  IMP.request_pay({
    pg: selectedPG, // 라디오 버튼마다 결제방식 달라지게 하기
    pay_method: "card",
    merchant_uid: "sgeseg", // 랜덤으로 출력하기?? 고유아이디....넣기?
    amount: 10,
    buyer_tel: "01012345678",
  } , function(data){ 
	  if(data.success){ // 결제성공후
		 var msg ="송공";
		 jQuery.ajax({ // !!!!! 이거 어떻게 하면 되는거지...
		        url: "/PaymentActionAfter.pay", 
		        method: "POST",
		        headers: { "Content-Type": "application/json" },
		        data: {
		          amount : data.amount,            // 결제 고유번호
		          merchant_uid: data.merchant_uid   // 주문번호
		        }
		      }).done(function (data) {
		        // 가맹점 서버 결제 API 성공시 로직
		      })
		    }else{ // 결제취소할때, 중복결제하려고 할 때
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