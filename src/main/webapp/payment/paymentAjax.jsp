<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<!-- iamport.payment.js -->
<script type="text/javascript"
	src="https://cdn.iamport.kr/js/iamport.payment-1.1.5.js"></script>
<!-- 포트원 결제연동 소스 -->
<script src="https://cdn.iamport.kr/v1/iamport.js"></script>
<script src="https://cdn.jsdelivr.net/npm/jquery@3.6.4/dist/jquery.min.js"></script>

<meta charset="UTF-8">
<title>결제창</title>
</head>
<body>

	<!-- 헤더 들어가는곳 -->
	<!-- 헤더 들어가는곳 -->


	
			<!-- 결제방법 선택하기(2~3개) -->
			<h1>결제방법</h1>
			<input type="radio" name="pay" value="INIBillTst"> 카드결제 <br>
			<input type="radio" name="pay" value="kakaopay"> 카카오페이 <br>
			<input type="radio" name="pay" value="tosspay"> 토스페이 <br>


	<script>
    </script>

	<br>
	<!-- 결제하기 버튼 라디오버튼 값에 따라 결제수단 변경 -->
	<button id="order-btn" onclick="findSubject()">결제하기</button>
	<!-- 주문취소 버튼 (장바구니 페이지로 이동)  -->

<script src="Ajax.js"></script>


	<script>
		let money = 100; // 결제금액
// 		let productName = // 구매상품명 계산은 어ㄸㅎ게하지
		let email = "email@mail.com"; // 구매자 이메일
		let userName = "서현정"; // 구매자 이름
		
		
		// 상품번호(랜덤생성) 저장
	    const purchase_id = createOrderNum();
	    document.getElementById("purchase_id").value = purchase_id;
		
		// 상품번호 생성
		function createOrderNum(){
			const date = new Date();
			const year = date.getFullYear().toString().slice(-2);
			const month = String(date.getMonth() + 1).padStart(2, "0");
			const day = String(date.getDate()).padStart(2, "0");
			
			let orderNum = year+day;
			for(let i=0;i<4;i++) {
				orderNum += Math.floor(Math.random() * 10);	
			}
			return orderNum;
		}
		
		// 주문버튼을 클릭했을때 주문한게 없으면 없다고 alert창으로 알려주기 
			function findSubject(){
				var arrRadio = document.getElementsByName("pay");
			    var selected = false;
			    for (var i = 0; i < arrRadio.length; i++) {
			        if (arrRadio[i].checked) {
			            selected = true;
			            break; } }
			    if (selected) {
			        requestPay();
			    } else {
			        alert('결제수단을 선택하세요'); } }

		// 포트원(구 아임포트) API
		function requestPay() {
			const userCode = "imp75410442";
			IMP.init(userCode);

			// 라디오 버튼 선택에 따라 pg 값을 동적으로 설정
			var selectedPG = document.querySelector('input[name="pay"]:checked').value;

			IMP.request_pay({ //param
				pg : selectedPG, // 라디오 버튼
				pay_method : "card",
				merchant_uid : purchase_id, 
				name : "Fooding", 
				amount : money,
				buyer_email: email,
				buyer_name: userName
			},  rsp => {
			    if (rsp.success) {   
				      // axios로 HTTP 요청
				      axios({
				        url: "/paymentResult.pay",
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
	</script>


	<!-- 본문들어가는곳(결제페이지) -->

	<!-- 푸터들어가는곳 -->
	<!-- 푸터들어가는곳 -->
</body>
</html>