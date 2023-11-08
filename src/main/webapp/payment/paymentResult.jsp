<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<script>
 var blockBackOnSpecificPage = false;
 blockBackOnSpecificPage = request.getAttribute("blockBackOnSpecificPage");
// 일단 뒤로가기 무조건 막음(다른방법 필요)
// if(blockBackOnSpecificPage){
	history.pushState(null, null, "./PaymentResult.pay");
		window.onpopstate = function (event) {
		alert('해당페이지에서는 뒤로 갈 수 없습니다');
		history.forward();
		};
// }
		
</script>
    <script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
    <script src="https://cdn.iamport.kr/v1/iamport.js"></script>
    <script type="text/javascript" src="https://unpkg.com/axios/dist/axios.min.js"></script>
    <script src="https://code.jquery.com/jquery-3.3.1.min.js" integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8="
    crossorigin="anonymous"></script>
<meta charset="UTF-8">
<title>payment</title>
</head>
<body>
<h1>페이지(뒤로가기 막기용)</h1>

	<button onclick="cancelPay()">환불하기</button>
		
		<script>
  function cancelPay() {
    jQuery.ajax({
      "url": "/PaymentResult.pay", 
      "type": "POST",
      "contentType": "application/json",
      "data": JSON.stringify({
        "merchant_uid": "{결제건의 주문번호}", // 예: ORD20180131-0000011
        "cancel_request_amount": 2000, // 환불금액
        "reason": "테스트 결제 환불" // 환불사유
        // [가상계좌 환불시 필수입력] 환불 수령계좌 예금주
        "refund_holder": "홍길동", 
        // [가상계좌 환불시 필수입력] 환불 수령계좌 은행코드(예: KG이니시스의 경우 신한은행은 88번)
        "refund_bank": "88" 
        // [가상계좌 환불시 필수입력] 환불 수령계좌 번호
        "refund_account": "56211105948400" 
      }),
      "dataType": "json"
    });
  }
</script>
		
		
		
		
		
		
		
</body>
</html>