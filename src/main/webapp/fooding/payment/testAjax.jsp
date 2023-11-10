<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>


			<input type="radio" name="pay" value="INIBillTst"> 카드결제 <br>
			<input type="radio" name="pay" value="kakaopay"> 카카오페이 <img src="kakao.png" width="50px"/><br>
			<input type="radio" name="pay" value="tosspay"> 토스페이 <img src="Toss.png" width="30px"/><br>

	<button id="order-btn" onclick="requestPay()">결제하기</button>

<!-- iamport.payment.js -->
<script type="text/javascript"
	src="https://cdn.iamport.kr/js/iamport.payment-1.1.5.js"></script>
<!-- 포트원 결제연동 소스 -->
<script src="https://cdn.iamport.kr/v1/iamport.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/jquery@3.6.4/dist/jquery.min.js"></script>
<!-- 내가만든 js 파일 연결 -->
<script src="Ajax.js"></script>

<script>


function requestPay() {
	const userCode = "imp75410442";
	IMP.init(userCode);

	// 라디오 버튼 선택에 따라 pg 값을 동적으로 설정
	var selectedPG = document
			.querySelector('input[name="pay"]:checked').value;

	IMP.request_pay({ 
		pg : selectedPG, // 라디오 버튼마다 결제방식 달라짐
		pay_method : "card",// card는 고정
		merchant_uid : 123465s, //상품번호+주문날짜
		name : '이름', // 상품명 순서대로 2개까지 표시 후 '외 남은상품수'
		amount : 100,
		buyer_email : email },
			  rsp => {
			    if (rsp.success) {   
			      // axios로 HTTP 요청
			      axios({
			        url: "{서버의 결제 정보를 받는 endpoint}",
			        method: "post",
			        headers: { "Content-Type": "application/json" },
			        data: {
			          imp_uid: rsp.imp_uid,
			          merchant_uid: rsp.merchant_uid
			        }
			      }).then((data) => {
			        // 서버 결제 API 성공시 로직
			      })
			    } else {
			      alert(`결제에 실패하였습니다. 에러 내용: ${rsp.error_msg}`);
			    }
			  });
}

// function requestPay() {
// 	const userCode = "imp75410442";
// 	IMP.init(userCode);

// 	// 라디오 버튼 선택에 따라 pg 값을 동적으로 설정
// 	var selectedPG = document
// 			.querySelector('input[name="pay"]:checked').value;

// 	IMP.request_pay({
// 		pg : selectedPG, // 라디오 버튼마다 결제방식 달라짐
// 		pay_method : "card",// card는 고정
// 		merchant_uid : purchase_id, //상품번호+주문날짜
// 		name : name, // 상품명 순서대로 2개까지 표시 후 '외 남은상품수'
// 		amount : money,
// 		buyer_email : email,
// 		buyer_name : userName
// 	},  rsp => {
// 	    if (rsp.success) {   
// 	        // axios로 HTTP 요청
// 	        axios({
// 	          url: "{서버의 결제 정보를 받는 endpoint}",
// 	          method: "post",
// 	          headers: { "Content-Type": "application/json" },
// 	          data: {
// 	            imp_uid: rsp.imp_uid,
// 	            merchant_uid: rsp.merchant_uid
// 	          }
// 	        }).then((data) => {
// 	          // 서버 결제 API 성공시 로직
// 	        })
// 	      } else {
// 	        alert('결제에 실패하였습니다. 에러 내용: ${rsp.error_msg}');
// 	      }
// 	    });
	
	
 // function 끝




















</script>
</body>
</html>