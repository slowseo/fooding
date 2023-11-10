<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://cdn.jsdelivr.net/npm/jquery@3.6.4/dist/jquery.min.js" ></script>
<script type="text/javascript">

// 유효성 관련 정규식
var getId = RegExp(/^[a-z0-9]{8,15}$/i); // 아이디 8~15자 숫자 또는 영문으로 구성
var getPw = RegExp(/^(?=.*\d)(?=.*[a-z])[a-zA-Z0-9]{8,15}$/i); // 8~15자, 숫자&영문 반드시 포함
	// -> RegExp는 생략 해도된다! 똑같은 효과를 나타냄
var getEmail = /^[\w-]+(\.[\w-]+)*@[\w-]+(\.[\w-]+)+$/;

// 아이디 중복확인 여부
var isIdConfirmed = false;




// 유효성 검사 메서드
function check(){
	
	var id = document.fr.id.value;
	var pw = document.fr.pw.value;
	var pw2 = document.fr.pw2.value;  		
	var name = document.fr.name.value;
	var phone = document.fr.phone.value;
	var email = document.fr.email.value;
	
 	// 아이디 유효성 검사
	if(id == ""){
		alert(' 아이디를 입력하세요! ');
		return false;
	}
	
	else if(!getId.test(id)){
		alert(' 아이디 형식에 맞게 입력해주세요. ');
		return false;
	}
 	
	// 아이디 중복체크 확인
	else if(!isIdConfirmed){
		alert("아이디 중복확인을 해주세요.")
		$("#id").focus();
		return false;
	} // ID
	
	
	// 비밀번호
	else if(pw == ""){
		alert(' 비밀번호를 입력하세요! ');
		document.fr.pw.focus();
		return false;
	}
 	
	// 비밀번호 형식
	else if(!getPw.test(pw)){
		alert(' 비밀번호 형식에 맞게 입력해주세요. ');
		return false;
	}  // PW 
 	
 	
	// 이름
	else if(name == ""){
		alert(' 이름을 입력하세요! ');
		document.fr.name.focus();
		return false;
	} // NA
	
	
 	// 전화번호
	else if(phone == ""){
		alert(' 전화번호를 입력해주세요! ');
		document.fr.phone.focus();
		return false;
	} // PH
	
	
 	
 	// 이메일
	else if(email == ""){
		alert(' 이메일을 입력하세요! ');
		document.fr.email.focus();
		return false;
	}// EM 
 	
	
	// 이메일 형식
	else if(!getEmail.test(email)){
		alert(' 이메일 형식에 맞게 입력해주세요! ');
		return false;
	}
	
	
	
 	// 비밀번호 형식
	else if(!getPw.test(pw)){
		alert(' 비밀번호 형식에 맞게 입력해주세요. ');
		return false;
	}  // id
 	
 	
 		
	// 비밀번호 일치
	else if(pw != pw2){
		alert(' 비밀번호가 일치하지 않습니다! ');
		document.fr.pw2.focus();
		return false;
	} // PW2
	
	
	
	
	
	
 		
} // 유효성 검사 ----------------------------------------



//전화번호에 자동으로 하이픈 넣기, 중복확인 후 아이디 수정시
$(function(){
	// 하이픈
	$("#phone").on("keyup", function(){
		var telVal = $(this).val(); // 자신의 값()
		telVal = telVal.replace(/[^0-9]/g, ''); // 숫자 이외의 문자 제거
		var telLen = telVal.length;
		if(telLen > 3){
			telVal = telVal.substring(0,3) + "-" + telVal.substring(3);
		}
		if(telLen > 7){
			telVal = telVal.substring(0,8) + "-" +telVal.substring(8);
		}
		$(this).val(telVal);
	});
	
	// 중복확인 후 아이디 수정
	$("#id").on("keyup", function(){
		isIdConfirmed = false;
	});

}); // 하이픈


// 아이디 중복확인
$(function() {
	$("#confirmId").on("click", function() {
		let idValue = $("#id").val();
	
		if (idValue === "") {
			alert("아이디를 입력해주세요.");
			return false;
		}
	
	
		// 아이디 유효성검사
		if(!getId.test($("#id").val())){
			alert("8~15자 영문, 숫자를 입력해주세요.");
			$("#id").focus();
			return false;
		}
		
		
		$.ajax({
			type: "POST",
			url: "./MemberConfirmIdAction.mem",
			dataType: "JSON",
			success: function (data) {
			console.log(data);
			
				let checkId = false;
		
				for (let i = 0; i < data.memberList.length; i++) {
					if (data.memberList[i].id === idValue) {
						checkId = true;
						break;
					}
				}
			
				if (checkId) {
					alert("이미 사용중인 아이디 입니다.");
					isIdConfirmed = false;
				} else {
					alert("사용 가능한 아이디 입니다.");
					isIdConfirmed = true;
				}
			}
		}); // ajax
	}); // 중복관리 버튼

});

</script>
</head>
<body>
	<jsp:include page="../inc/top.jsp" />
   
        <fieldset>
            <legend>푸딩 회원가입</legend>
            <form action="./MemberJoinAction.mem" method="post" name="fr" onsubmit="return check();">
                <div>
                    아이디 <input type="text" name="id" id="id" placeholder="숫자, 영문 8-15 자">
                    <input type="button" value="중복확인" id="confirmId">
                </div>
                <div>
                    비밀번호 <input type="password" name "pw" placeholder="숫자, 영문 조합 8-15 자">
                </div>
                <div>
                    비밀번호 확인 <input type="password" name="pw2">
                </div>
                <div>
                    이름 <input type="text" name="name" id="name">
                </div>
                <div>
                    전화번호 <input type="text" name="phone" id="phone">
                </div>
                <div>
                    이메일 <input type="email" name="email" id="email">
                </div>
                <hr>
                <input type="submit" value="회원가입">
            </form>
        </fieldset>

</body>
</html>