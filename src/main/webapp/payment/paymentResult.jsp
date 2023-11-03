<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>payment</title>
</head>
    <script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
    <script src="https://cdn.iamport.kr/v1/iamport.js"></script>
    <script type="text/javascript" src="https://unpkg.com/axios/dist/axios.min.js"></script>
    <script src="https://code.jquery.com/jquery-3.3.1.min.js" integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8="
    crossorigin="anonymous"></script>
<body>
<script>
 function requestPay() {
	 	var IMP = window.IMP;
		IMP.init(imp75410442);
		
    IMP.request_pay({
      pg: "kcp.{상점ID}",
      pay_method: "card",
      merchant_uid: "ORD20180131-0000011",   // 주문번호
      name: "노르웨이 회전 의자",
      amount: 64900,                         // 숫자 타입
      buyer_email: "gildong@gmail.com",
      buyer_name: "홍길동",
      buyer_tel: "010-4242-4242",
      buyer_addr: "서울특별시 강남구 신사동",
      buyer_postcode: "01181"
    },  function (rsp) {
    		 // 결제검증
    	    $.ajax({
            type : "POST",
            url : "/verifyIamport/" + res.imp_uid
       		 })
      	  if (rsp.success) {
            // 결제 성공 시: 결제 승인 또는 가상계좌 발급에 성공한 경우
            // jQuery로 HTTP 요청
            jQuery.ajax({
              url: "/PaymentResult.pay", 
              method: "POST",
              headers: { "Content-Type": "application/json" },
              data: {
                imp_uid: rsp.imp_uid,            // 결제 고유번호
                merchant_uid: rsp.merchant_uid   // 주문번호
                
              }
            }).done(function (data) {
              // 가맹점 서버 결제 API 성공시 로직
            })
          } else {
            alert("결제에 실패하였습니다. 에러 내용: " + rsp.error_msg);
          }
        
    });
  }













</script>
</body>
</html>