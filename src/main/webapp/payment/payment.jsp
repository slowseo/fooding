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
<!-- 포트원 결제연동 소스 -->
<script src="https://cdn.iamport.kr/v1/iamport.js"></script>
<script src="https://cdn.jsdelivr.net/npm/jquery@3.6.4/dist/jquery.min.js" ></script>
<meta charset="UTF-8">
<title>payment</title>
</head>
<body>
<!-- 헤더 들어가는곳 -->
<!-- 헤더 들어가는곳 -->


<!-- 본문들어가는곳(결제페이지) -->
<fieldset>
<form action="./paymentAfter.pay" method="post" id="mypayment">
<!-- 장바구니 정보 출력하기 출력하기(리스트) -->
상품사진 : <img src="" alt="">
상품이름 : <input type="text" name="name" value="${dto.name}" readonly>
수량 : <input type="number" name="quantity" value="${dto.quantity }" readonly>

<!-- 트럭 픽업위치, 주문시간(주문일) 출력하기 -->
주소 : <input type="text" name="address" value="${dto.address}" readonly>
주문일 : <input type="text" name="date" value="${dto.date}" readonly>
<!-- 결제방법 선택하기(2~3개) -->
<h1> 결제방법</h1>

<input type="radio" name="pay" value="INIBillTst"> 카드결제
<input type="radio" name="pay" value="kakaopay"> 카카오페이
<input type="radio" name="pay" value="tosspay"> 토스페이


<!-- 총 주문금액(=결제금액)  -->
<h1> 결제금액 </h1>
가격*갯수 + 가격*갯수 = 총금액이렇게 구하기
<c:forEach var = "dto" items="${cartList }">
<%--  총가격 : ${dto.가격 * dto. 갯수} --%>
</c:forEach>

</form>
</fieldset>
<br>
<!-- 결제하기 버튼 라디오버튼 값에 따라 결제수단 변경 -->
<button onclick="requestPay()">결제하기</button>
<!-- 주문취소 버튼 (장바구니 페이지로 이동)  -->
<button id="cancel" onclick="cartBack()">장바구니로</button>

<script>
	let money = "{결제금액 옮기기}"
	let name = "{결제물건이름..?}"

function cartBack(){
	var confirmResult = confirm("장바구니로 돌아가시겠습니까?");
	if(confirmResult){
		location.href='./cart.car'
	}
}

function requestPay() {
const userCode = "imp75410442";
IMP.init(userCode);

	// 라디오 버튼 선택에 따라 pg 값을 동적으로 설정
	var selectedPG = document.querySelector('input[name="pay"]:checked').value;


// 	var merchant_uid = generateRandomMerchantUID();

  IMP.request_pay({
    pg: selectedPG, // 라디오 버튼마다 결제방식 달라짐
    pay_method: "card",// card는 고정
    merchant_uid: 'merchant_' + new Date().getTime(), // +new Date().getTime()상품번호+주문날짜
    name: "테스트 결제", // 여기에 주문자 이름
    amount: 10,
//     buyer_tel: "01012345678",
  } , function(data){ 
	  console.log(data); //ajax처럼 콜백 성공 유무
  	  if(data.success){ // 결제성공후
  		 var msg ="결제 완료";
  		 
  	  	// 폼 데이터 submit 실행
  	  	document.getElementById("mypayment").submit(); // 넘어감!
  		    
  		    }else{ // 결제취소할때, 중복결제하려고 할 때
  		 var msg ="결제를 취소하셨습니다";
  	  }
  	  alert(msg);
    });
  }
  
</script>

<script>
</script>
<!-- 본문들어가는곳(결제페이지) -->

<!-- 푸터들어가는곳 -->
<!-- 푸터들어가는곳 -->
</body>
</html>