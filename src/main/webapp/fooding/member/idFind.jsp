<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
var getEmail = /^[\w-]+(\.[\w-]+)*@[\w-]+(\.[\w-]+)+$/;

	function check(){
	
	var name = doucument.fr.name.value;
	var email = document.fr.email.value;
	
	
	// 이름
	if(name == ""){
		alert(' 이름을 입력하세요! ');
		document.fr.name.focus();
		return false;
	} // NA
	
	
	// 이메일
	else if(email == ""){
		alert(' 이메일을 입력하세요! ');
		document.fr.email.focus();
		return false;
	} 
	
	// 이메일 형식
	else if(!getEmail.test(email)){
		alert(' 이메일 형식에 맞게 입력해주세요! ');
		return false;
	}
	
} // check()
</script>


</head>
<body>

<jsp:include page="../inc/top.jsp" />

	
    <form action="./MemberIdFindAction.mem" method="post" name="fr" onsubmit="return check();">
        <fieldset>
            <legend>아이디 찾기</legend>
            <div>
                이름 : <input type="text" name="name" id="name" placeholder="이름">
            </div>
            <div>
                이메일 : <input type="email" name="email" id="email" placeholder="이메일">
            </div>
            <hr>
            <input type="submit" value="아이디 찾기">
        </fieldset>
    </form>
	
	

</body>
</html>