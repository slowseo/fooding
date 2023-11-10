<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<script type="text/javascript"
	src="https://cdn.iamport.kr/js/iamport.payment-1.1.5.js"></script>
<!-- 포트원 결제연동 소스 -->
<script src="https://cdn.iamport.kr/v1/iamport.js"></script>
<script src="https://cdn.jsdelivr.net/npm/jquery@3.6.4/dist/jquery.min.js"></script>
<script src="Ajax.js"></script>
    <title>라디오 버튼 예제</title>
<script>
$(document).ready(function(){
	
	$.ajax({
		url: "https://api.iamport.kr/users/getToken",
		method: "POST",
		contentType: "application/json",
		dataType: "json",
		data: JSON.stringify({ imp_key: "RestApiKey", imp_secret: "RESTApiSecret" }),
		success: function(data){
			consol.log(data);
			$(data).find('response').each(function(){
				   var a = $(this).find('access_token').text();
				   console.log(a);	
		});
		},
		error: function(data){
			console.log(data);}
			
	});

});
</script>
</head>
<body>

 
 <%= request.getParameter("IMP")%>
</body>
</html>