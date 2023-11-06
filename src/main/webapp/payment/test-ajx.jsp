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
<script>
	
	const IMP = "imp75410442";
	const RestApiLey = "1500428451315773";
	const RESTApiSecret = "T1rLNPm2YQzEDqYOEmXYGFthFdwEi15aqlhCtkrB2C2pJW455YVrfltaCLqiO7IJw1z8PS0tLtWzcr6B";

	async function getData(){
		const url =https://api.iamport.kr/payments?imp_uid%5B%5D={"여기에 IMP 입력 "}&merchant_uid%5B%5D={"여기에 주문번호(생성한거) 입력 "}
	const response = await fetch(url)
	const data = await response.json()
	console.log("data",data)
	}
	
	// => 이걸로 데이터 정보 받아와서 가격 비교?하면 되지 않을까?
	// 사전 검증을 여기서 하고  script src 로 가져오면 될듯...
</script>
	
</body>
</html>