<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>radio 버튼 테스트</title>
<script type="text/javascript">
<!--
  function check(country){
 alert(country);
   }
 function check2(form){
 for(var i=0; i<form.country.length;i++){
  if(form.country[i].checked == true){
   alert(i+"번째의 "+form.country[i].value + " 선택");
  }
 }
   }
//-->
</script>
</head>
<body>

1. onclick을 이용해서 radio 버튼 개별적으로 이벤트 체크 <br>
<form name="mform">
 <input type="radio" name="country" value="Korea" onclick="check(this.value)" checked>대한민국 
 <input type="radio" name="country" value="USA" onclick="check(this.value)">미국 
 <input type="radio" name="country" value="China" onclick="check(this.value)">중국 
</form>

2. radio 버튼 선택 후 선택한 radio 버튼의 index 및 value 출력 <br>
<form name="sform">
 <input type="radio" name="country" value="Korea" checked="checked">대한민국 
 <input type="radio" name="country" value="USA">미국 
 <input type="radio" name="country" value="China">중국 
 <input type="button" value="확인" onclick="check2(this.form)">
</form>
</body>
</html>

