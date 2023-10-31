<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>payment</title>
</head>
<body>


<fieldset>
<form action="./PaymentAfterAction" method="post" id="test">

상품사진 : <img src="" alt="">
상품이름 : <input type="text" name="name"  >
수량 : <input type="number" name="quantity" >


주소 : <input type="text" name="address" >
주문일 : <input type="text" name="date" >
<hr>
상품사진 : <img src="" alt="">
상품이름 : <input type="text" name="name"  >
수량 : <input type="number" name="quantity" >

주소 : <input type="text" name="address" >
주문일 : <input type="text" name="date" >
<hr>
상품사진 : <img src="" alt="">
상품이름 : <input type="text" name="name"  >
수량 : <input type="number" name="quantity" >

주소 : <input type="text" name="address" >
주문일 : <input type="text" name="date" >
<hr>


<input type="submit" value="전송">
</form>
</fieldset>
<br>

<!-- 본문들어가는곳(결제페이지) -->

<!-- 푸터들어가는곳 -->
<!-- 푸터들어가는곳 -->
</body>
</html>