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


	<!-- 본문들어가는곳(결제페이지) -->
	<h1>주문/결제</h1>
	<fieldset>
		<form action="./PaymentResult.pay" method="post" id="mypayment">
		<!-- 주문번호 -->
   		 <input type="hidden" id="purchase_id" name="purchase_id" value="">
			<c:forEach var="dto" items="${purchaseList}" varStatus="">
				<!-- 장바구니 정보 출력하기 출력하기(리스트) -->
				<!-- 장바구니 번호 -->
				<input type="hidden" name="cart_id" value="${dto.cart_id}">
				<!-- 회원번호 -->
				<input type="hidden" name="member_id" value="${dto.member_id}">
				<!-- 상품번호 -->
				<input type="hidden" name="product_id" value="${dto.product_id}">
			상품사진 : <img src="${dto.image}">
				<br>
			상품이름 : <input type="text" name="name" value="${dto.name}" readonly>
				<br>
			수량 : <input type="number" name="quantity" value="${dto.quantity }"
					readonly>
				<br>
			가격 : <input type="text" name="price" value="${dto.price }" readonly>
			<hr>
				<!-- 트럭 픽업위치, 주문시간(주문일) 출력하기 -->
				<input type="hidden" name="address" value="${dto.address}">
				<br>
			</c:forEach>
			<!-- 중복이 없는 주소를 출력할 엘리먼트 -->
			<h2>주소 :</h2>
			<span id="addressOutput"></span>

			<!-- 결제방법 선택하기(2~3개) -->
			<h1>결제방법</h1>
			<input type="radio" name="pay" value="INIBillTst"> 카드결제 <br>
			<input type="radio" name="pay" value="kakaopay"> 카카오페이 <br>
			<input type="radio" name="pay" value="tosspay"> 토스페이 <br>


			<!-- 총 주문금액(=결제금액)  -->
			<!-- 			가격*갯수 + 가격*갯수 = 총금액이렇게 구하기 -->
			<c:forEach var="dto" items="${purchaseList}">
				<c:set var="total" value="${(dto.price * dto.quantity)+total}" />
				<c:set var="test" value="${dto.name}" />
			</c:forEach>
			<h2>
				총 결제 금액 : <c:out value="${total}" />
			</h2>
		</form>
	</fieldset>

	<script>
    </script>

	<br>
	<!-- 결제하기 버튼 라디오버튼 값에 따라 결제수단 변경 -->
	<button id="order-btn" onclick="findSubject()">결제하기</button>
	<!-- 주문취소 버튼 (장바구니 페이지로 이동)  -->
	<button id="cancel" onclick="cartBack()">장바구니로</button>



	<script>

        // JavaScript로 중복 주소 제거
        var addresses = []; // 주소를 저장할 배열
        var uniqueAddresses = new Set(); // 중복을 체크할 Set
        // 주소 정보를 가져와서 배열에 저장
        var addressElements = document.getElementsByName("address");
        for (var i = 0; i < addressElements.length; i++) {
            addresses.push(addressElements[i].value);
        }
        // 중복 주소를 체크하고 중복이 없는 주소를 Set에 저장
        for (var i = 0; i < addresses.length; i++) {
            uniqueAddresses.add(addresses[i]);
        }
        // 중복이 없는 주소를 다시 배열에 저장
        var uniqueAddressesArray = Array.from(uniqueAddresses);
        // 중복이 없는 주소를 출력
        var addressOutput = document.getElementById("addressOutput");
        for (var i = 0; i < uniqueAddressesArray.length; i++) {
            var address = uniqueAddressesArray[i];
            var addressElement = document.createElement("div");
            addressElement.textContent = address;
            addressOutput.appendChild(addressElement);
        }
        
        
		let money = "<c:out value="${total}"/>" // 결제금액
// 		let productName = // 구매상품명 계산은 어ㄸㅎ게하지
		let email = "<c:out value="${member.email}"/>" // 구매자 이메일
		let userName = "<c:out value="${member.name}"/>" // 구매자 이름
		
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
		
		// 장바구니로 돌아가기
		function cartBack() {
			var confirmResult = confirm("장바구니로 돌아가시겠습니까?");
			if (confirmResult) {
				location.href = './cart.car'
			}
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

			IMP.request_pay({
				pg : selectedPG, // 라디오 버튼마다 결제방식 달라짐
				pay_method : "card",// card는 고정
				merchant_uid : purchase_id, //상품번호+주문날짜
				name : "Fooding", // 여기에 브랜드명 입력 (\n 뒤에 자세한 내용은 주문내역확인 누르니까 결제가 안넘어감)
				amount : money,
				buyer_email: email,
				buyer_name: userName
			}, function(data) {
              console.log(data); //ajax처럼 콜백 성공 유무
                  if (data.success) { // 결제성공후
                  var msg = "결제 완료";
                  
                  // 폼 데이터 submit 실행
                  document.getElementById("mypayment").submit(); // 넘어감!

             }else { // 결제취소할때, 중복결제하려고 할 때
                    var msg = "결제를 취소하셨습니다";
                }
                alert(msg);
            });
        }
			 
	</script>


	<!-- 본문들어가는곳(결제페이지) -->

	<!-- 푸터들어가는곳 -->
	<!-- 푸터들어가는곳 -->
</body>
</html>