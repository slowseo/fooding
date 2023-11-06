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

		<!-- 게시판 -->
		<article>
			<h1>Notice</h1>
			<table id="notice">
			<c:forEach var="dto" items="${boardList }" varStatus="">
				<tr>
					<th >${dto.detail_id }</th>
					<th >${dto.purchase_id }</th>
					<th>${dto.product_id }</th>
					<th>${dto.quantity }</th>
					<th>${dto.address }</th>
				</tr>
			</c:forEach>
			</table>
			
			
			
			<div class="clear"></div>
			<div id="page_control">
				<c:if test="${startPage > pageBlock }">
				<a href="./BoardList.bo?pageNum=${startPage-pageBlock }&search=${param.search}">Prev</a> 
				</c:if>
				
				<c:forEach var="i" begin="${startPage}" end="${endPage }" step="1">
				<a href="./BoardList.bo?pageNum=${i}&search=${param.search}">${i}</a>
				</c:forEach>		
				
				<c:if test="${endPage < pageCount }">
				<a href="./BoardList.bo?pageNum=${startPage+pageBlock }&search=${param.search}">Next</a>
				</c:if>
			</div>
		</article>
</body>
</html>