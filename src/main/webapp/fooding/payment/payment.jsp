<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta name="description" content="" />
<meta name="author" content="" />
<title>fooding</title>
<link rel="icon" type="image/x-icon" href="assets/favicon.ico" />
<!-- Bootstrap icons-->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css"
	rel="stylesheet" />
<!-- Google fonts-->
<link rel="preconnect" href="https://fonts.gstatic.com" />
<link
	href="https://fonts.googleapis.com/css2?family=Newsreader:ital,wght@0,600;1,600&amp;display=swap"
	rel="stylesheet" />
<link
	href="https://fonts.googleapis.com/css2?family=Mulish:ital,wght@0,300;0,500;0,600;0,700;1,300;1,500;1,600;1,700&amp;display=swap"
	rel="stylesheet" />
<link
	href="https://fonts.googleapis.com/css2?family=Kanit:ital,wght@0,400;1,400&amp;display=swap"
	rel="stylesheet" />
<!-- Core theme CSS (includes Bootstrap)-->
<link href="css/styles.css" rel="stylesheet" />

<!-- 구글폰트 (변경가능)-->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Bagel+Fat+One&family=Black+Han+Sans&family=Lato:wght@700&family=Noto+Sans+KR&family=Playpen+Sans&display=swap"
	rel="stylesheet">

<!-- 테마 기본 설정 끝 -->

<!-- css -->
<link rel="stylesheet" href="css/team2.css">
<link rel="stylesheet" href="css/payment.css">
<!-- iamport.payment.js -->
<script type="text/javascript"
	src="https://cdn.iamport.kr/js/iamport.payment-1.1.5.js"></script>
<!-- 포트원 결제연동 소스 -->
<script src="https://cdn.iamport.kr/v1/iamport.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/jquery@3.6.4/dist/jquery.min.js"></script>
<meta charset="UTF-8">
<title>결제창</title>
</head>
<body>

	<!-- 헤더 들어가는곳 -->
	<!-- Navigation-->
   <!-- Navigation-->
		 <jsp:include page="../css/top.jsp"></jsp:include>
		 
	<!-- 헤더 들어가는곳 -->
	<br>

	<!-- 본문들어가는곳(결제페이지) -->
	<fieldset id="field">
		<h1 class="orderPay">주문/결제</h1>
		<div class="formCenter">
			<form action="./PaymentResult.pay" method="post" id="mypayment">
				<!-- 주문번호 -->
				<input type="hidden" id="purchase_id" name="purchase_id" value="">
				<c:forEach var="dto" items="${purchaseList}" varStatus="loop">
				<!-- 이거 트럭고유번호를 입력해줘야함 -->
<%-- 				<span onclick="location.href='가상주소?트럭고유번호'" title="'${dto.foodtruckName}' 상세페이지로 이동">  --%>
					<!-- 장바구니 정보 출력하기(리스트) -->
					<!-- 장바구니 번호 -->
					<input type="hidden" name="cart_id" value="${dto.cart_id}">
					<!-- 회원번호 -->
					<input type="hidden" name="member_id" value="${dto.member_id}">
					<!-- 상품번호 -->
					<input type="hidden" name="product_id" value="${dto.product_id}">
					<!-- 주소 -->
					<input type="hidden" name="address" value="${dto.address}">
					<!-- 정차시간 -->
					<input type="hidden" name="stoptime" value="${dto.stoptime}">
					<!-- date -->
					<input type="hidden" name="date" value="${dto.date}">
					<c:choose>
						<c:when
							test="${loop.first || !dto.foodtruckName.equals(purchaseList[loop.index - 1].foodtruckName)}">
							<!-- 첫 번째 아이템 또는 이전 아이템과 날짜가 다를 때만 출력 -->
							<h3 class="pickup">${dto.foodtruckName}</h3>
						</c:when>
						<c:otherwise>
							<!-- 이전 아이템과 날짜가 같으면 아무것도 출력하지 않음 -->
						</c:otherwise>
					</c:choose>
					<div class="pickup">픽업 일자 : ${dto.date}</div>
					<span class="pickup"> 픽업 시간 : ${dto.stoptime } </span>
					<span class="pickup-"> 픽업 위치: ${dto.address }</span>
					<br>
					<div class="productIMG">
						<img src="${dto.image}">
					</div>
					<br>
					<sapn class="text-line"> 상품명 : <input type="text"
						name="name" value="${dto.name}" readonly> </sapn>
					<br>
					<sapn class="text-line"> 수량 : <input type="number"
						name="quantity" value="${dto.quantity}" readonly> </sapn>
					<br>
					<sapn class="text-line"> 가격 : <input type="text"
						name="price" value="${dto.price}" readonly> </sapn>
					<hr>
<!-- 				</span> -->
				</c:forEach>
				<!-- 결제방법 선택하기(2~3개) 아이콘 왜 안나오지-->
				<h1 id="choice">결제방법</h1>
				<input type="radio" name="pay" value="INIBillTst" class="pay">
				카드결제 <br> <input type="radio" name="pay" value="kakaopay"
					class="pay"> 카카오페이 <img src="../../upload/kakao.png" width="50px" /><br>
				<input type="radio" name="pay" value="tosspay" class="pay">
				토스페이 <img src="../../upload/Toss.png" width="30px" /><br> <br>
				<br>
				<br>

				<!-- 총 주문금액(=결제금액)  -->
				<!-- 			가격*갯수 + 가격*갯수 = 총금액이렇게 구하기 -->
				<c:forEach var="dto" items="${purchaseList}">
					<c:set var="total" value="${(dto.price * dto.quantity)+total}" />
					<c:set var="test" value="${dto.name}" />
				</c:forEach>
				<h2 id="finalMoney">
					총 결제 금액 :
					<c:out value="${total}" />
				</h2>
				<br>
			</form>

		</div>
				<!-- 결제하기 버튼 라디오버튼 값에 따라 결제수단 변경 -->
				<button id="order-btn" onclick="findSubject()">결제하기</button>
				<!-- 주문취소 버튼 (장바구니 페이지로 이동)  -->
				<button id="cancle" onclick="cartBack()">장바구니로</button>
	</fieldset>



	<script>
		let money = '<c:out value="${total}"/>'; // 결제금액
		let name = '${combinedName}';
		let email = '<c:out value="${member.email}"/>'; // 구매자 이메일
		let userName = '<c:out value="${member.name}"/>'; // 구매자 이름

		// 상품번호(merchant_uid)
		const purchase_id = createOrderNum();
		document.getElementById("purchase_id").value = purchase_id;

		// 상품번호 생성
		function createOrderNum() {
			const date = new Date();
			const year = date.getFullYear().toString().slice(-2);
			const month = String(date.getMonth() + 1).padStart(2, "0");
			const day = String(date.getDate()).padStart(2, "0");

			let orderNum = day;
			for (let i = 0; i < 6; i++) {
				orderNum += Math.floor(Math.random() * 10);
			}
			return orderNum; // 총 8자리 숫자
		}

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

		// 장바구니로 돌아가기
		function cartBack() {
			var confirmResult = confirm("장바구니로 돌아가시겠습니까?");
			if (confirmResult) {
				location.href = './cart.car'
			}
		}

		// 결제수단 미선택시 알림창 알려주기
		function findSubject() {
			var arrRadio = document.getElementsByName("pay");
			var selected = false;
			for (var i = 0; i < arrRadio.length; i++) {
				if (arrRadio[i].checked) {
					selected = true;
					break;
				}
			}
			if (selected) {
				requestPay();
			} else {
				alert('결제수단을 선택하세요');
			}
		}

		// 포트원(구 아임포트) API
		function requestPay() {
			const userCode = "imp75410442";
			IMP.init(userCode);

			// 라디오 버튼 선택에 따라 pg 값을 동적으로 설정
			var selectedPG = document
					.querySelector('input[name="pay"]:checked').value;

			IMP.request_pay({
				pg : selectedPG, // 라디오 버튼마다 결제방식 달라짐
				pay_method : "card",// card는 고정
				merchant_uid : purchase_id, //상품번호+주문날짜
				name : name, // 상품명 순서대로 2개까지 표시 후 '외 남은상품수'
				amount : money,
				buyer_email : email,
				buyer_name : userName
			}, function(data) {
				console.log(data); //ajax처럼 콜백 성공 유무
				if (data.success) { // 결제성공후
					var msg = "결제 완료";

					// 폼 데이터 submit 실행
					document.getElementById("mypayment").submit();

				} else { // 결제취소할때, 중복결제하려고 할 때
					var msg = "결제를 취소하셨습니다";
				}
				alert(msg);
			});
		}
	</script>


	<!-- 본문들어가는곳(결제페이지) 끝 -->

	<!-- 푸터들어가는곳 CSS 위치 조절 필요-->
	 <jsp:include page="../css/bottom.jsp"></jsp:include>
	
	<!-- Bootstrap core JS-->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
	<!-- Core theme JS-->
	<script src="js/scripts.js"></script>
	<!-- * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *-->
	<!-- * *                               SB Forms JS                               * *-->
	<!-- * * Activate your form at https://startbootstrap.com/solution/contact-forms * *-->
	<!-- * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *-->
	<script src="https://cdn.startbootstrap.com/sb-forms-latest.js"></script>
	<!-- 푸터들어가는곳 -->
</body>
</html>