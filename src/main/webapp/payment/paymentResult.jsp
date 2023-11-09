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
<link rel="stylesheet" href="css/orderDetail.css">

<!-- 구글폰트 (변경가능)-->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Bagel+Fat+One&family=Black+Han+Sans&family=Lato:wght@700&family=Noto+Sans+KR&family=Playpen+Sans&display=swap"
	rel="stylesheet">
<!-- 모달용 -->
<!-- 테마 기본 설정 끝 -->
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
	<span><h2>결제 날짜 : ${item.orderdate }</h2></span>
	<img src="${item.image}" id="productImg">
	<div>상품명 : ${item.name }</div>
	<span>픽업날짜 : ${item.stoptime }</span>
	<div>픽업위치 : ${item.address}</div>
	<button type="button" class="btn btn-info btn-lg" 
	data-toggle="modal" data-target="#myModal">상세보기</button>
	<button onclick="">리뷰쓰기</button>
	<button onclick="cancelPay()">결제취소</button>
</div> 
<!-- 상세보기 -->
  <div class="modal fade" id="myModal" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button> <!-- &times; == X -->
                    <h4 class="modal-title">주문 상세 내역</h4>
                  </div>
        <div class="modal-body">
      <!-- 모달안에 들어갈 내용들 -->
      <p>Some text in the Modal..</p>
	<img src="${item.image}" id="productImgDetail">
  	  <div> 주문번호 : ${item.purchaseid }</div>
  	  <div> 상품명 : ${item.name }</div>
  	  <div> 가격 : ${item.price }</div>
  	  <div> 개수 : ${item.quantity }</div>
  	  <div> 결제금액 : ${item.price * item.quantity }</div>
  	  <div> 픽업날짜 : ${item.stoptime}</div>
  	  <div> 픽업주소 : ${item.address}</div>
      <!-- 모달안에 들어갈 내용들 -->
 </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        </div>
      </div>
       </div>
    </div>
</c:forEach>




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
		
</fieldset>
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
<!-- 결제내역 보이게 하기 -->
<script>
// 모달
//Get the modal
var modal = document.getElementById("myModal");

// Get the button that opens the modal
var btn = document.getElementById("myBtn");

// Get the <span> element that closes the modal
var span = document.getElementsByClassName("close")[0];

// When the user clicks on the button, open the modal
btn.onclick = function() {
  modal.style.display = "block";
}

// When the user clicks on <span> (x), close the modal
span.onclick = function() {
  modal.style.display = "none";
}

// When the user clicks anywhere outside of the modal, close it
window.onclick = function(event) {
  if (event.target == modal) {
    modal.style.display = "none";
  }
}




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
		
	<!-- 푸터들어가는곳 CSS 위치 조절 필요-->
	 <jsp:include page="../css/bottom.jsp"></jsp:include>
			
			
		
</body>
</html>