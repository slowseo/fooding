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
<!-- Core theme CSS (includes Bootstrap)-->
<link href="css/styles.css" rel="stylesheet" />
<link rel="stylesheet" href="css/orderDetail.css">

<!-- 테마 기본 설정 끝 -->
<script src="https://code.jquery.com/jquery-3.3.1.min.js"
	integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8="
	crossorigin="anonymous"></script>
	<!-- iamport.payment.js -->
<script type="text/javascript"
	src="https://cdn.iamport.kr/js/iamport.payment-1.1.5.js"></script>
<!-- 포트원 결제연동 소스 -->
<script src="https://cdn.iamport.kr/v1/iamport.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/jquery@3.6.4/dist/jquery.min.js"></script>
<script type="text/javascript">
$(document).ready(function(){	
history.pushState(null, null, location.href);
window.onpopstate = function () {
    history.go(1);
};
});
</script>
</head>
<body>
	<!-- 헤더 들어가는곳 -->
	<jsp:include page="../css/top.jsp"></jsp:include>
	<!-- 헤더 들어가는곳 -->
	<fieldset>
		<h1>페이지(뒤로가기 막기용)</h1>
		<!-- 결제내역 보이게 하기 -->
		<h1>결제내역</h1>

		<c:forEach var="item" items="${purchaseList}">
			<!-- 주문내역 반복출력 -->
			<div id="simpleCheck">
				<span><h2>결제 날짜 : ${item.orderdate }</h2></span> <img
					src="${item.image}" id="productImg">
				<div>상품명 : ${item.name }</div>
				<span>픽업날짜 : ${item.stoptime }</span>
				<div>픽업위치 : ${item.address}</div>
				<button class="news-img">상세보기</button>
				<button onclick="">리뷰쓰기</button>
				<button
					onclick="refund(${item.purchaseid },${item.price}, ${item.quantity})">결제취소</button>
			</div>
			<!-- 상세보기 -->

			<!-- 모달안에 들어갈 내용들 -->
			<div class="modal">
				<div class="modal_content">
					<span class="close">&times;</span>
					<h4>주문 상세 내역</h4>
					<p>Some text in the Modal..</p>
					<img src="${item.image}" id="productImgDetail">
					<div>주문번호 : ${item.purchaseid }</div>
					<div>상품명 : ${item.name }</div>
					<div>가격 : ${item.price }</div>
					<div>개수 : ${item.quantity }</div>
					<div>결제금액 : ${item.price * item.quantity }</div>
					<div>픽업날짜 : ${item.stoptime}</div>
					<div>픽업주소 : ${item.address}</div>
				</div>
			</div>
			<!-- 모달안에 들어갈 내용들 -->
		</c:forEach>

		<!-- 페이지 컨트롤러  -->
		<div id="page_control">
			<c:if test="${startPage > pageBlock }">
				<a href="./OrderDetails.pay?pageNum=${startPage-pageBlock }">Prev</a>
			</c:if>

			<c:forEach var="i" begin="${startPage}" end="${endPage }" step="1">
				<a href="./OrderDetails.pay?pageNum=${i}">${i}</a>
			</c:forEach>

			<c:if test="${endPage < pageCount }">
				<a href="./OrderDetails.pay?pageNum=${startPage+pageBlock }">Next</a>
			</c:if>
		</div>
		<!-- 페이지 컨트롤러  -->

	</fieldset>


	<!-- 결제내역 보이게 하기 -->
	<script>
// 모달 스크립트(메인페이지에 있던거)
const modal = document.querySelectorAll(".modal");
const img = document.querySelectorAll(".news-img");
const modal_img = document.querySelectorAll(".modal_content");
const span = document.querySelectorAll(".close");

for(let i=0; i<modal.length;i++){
  img[i].addEventListener('click', ()=>{
  modalDisplay(i,"block");
});
}

for(let i=0; i<modal.length;i++){
  span[i].addEventListener('click', ()=>{
  modalDisplay(i,"none");
});
}

for(let i=0; i<modal.length;i++){
  modal[i].addEventListener('click', ()=>{
  modalDisplay(i,"none");
});
}


function modalDisplay(i,text){
  modal[i].style.display = text;
}

  function refund(id,price, quantity) {
	// 결제건 주문번호
	var purchaseid = id;
	// 수량
	var quantt =quantity;
	// 금액
	var howMuch = price;
	// 총 금액?
	var money = quantt*howMuch;	// 금액 계산됨
	if(confirm('환불')){
		// 환불 들어가야함
		alert('주문취소하셧습니다');
	}else{
		alert('환불을 취소하셨습니다');
	}

	}
</script>

	<!-- 푸터들어가는곳 CSS 위치 조절 필요-->
	<jsp:include page="../css/bottom.jsp"></jsp:include>



</body>
</html>