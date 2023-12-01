<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>payment</title>
</head>
 <script type="text/javascript">
 window.history.forward();
 function noBack(){window.history.forward();}
</script>
<body>
<body onload="noBack();" onpageshow="if(event.persisted) noBack();" onunload="./payment/paymentResult.jsp">
테스트 출력

주문내역

주문상세번호
주문번호
회원번호
상품번호
수량
주소

</body>
</html>