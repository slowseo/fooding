<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<script>
var blockBackOnSpecificPage = false;

// 특정 페이지에서 A 페이지로 이동할 때 뒤로가기를 막음
function goToAFromSpecificPage() {
  blockBackOnSpecificPage = true;
  // A 페이지로 이동
  window.location.href = 'paymentResult.jsp'; // A.html 대신 실제로 이동할 페이지 주소를 사용합니다.
}

</script>

<button onclick="goToAFromSpecificPage()">test</button>
</body>
</html>