<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
<link rel="stylesheet" type="text/css"
	href='https://cdn.jsdelivr.net/gh/orioncactus/pretendard/dist/web/static/pretendard.css'>
</head>
<body>

<section>
<form action="loginPro.jsp" method="post">
	아이디<input type="text" name="id"><br>
	비밀번호<input type="password" name="pw"><br>
	<input type="submit" value="로그인">
</form>
<br>
<input type="button" value="고객센터" onclick="location.href='http://localhost:8088/TEAM2/Support.sup';">
</section>

</body>
</html>