<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<jsp:include page="../inc/top.jsp" />

    <form action="./MemberPwFindAction.mem" method="post">
        <fieldset>
            <legend>비밀번호 찾기</legend>
            <div>
                아이디  <input type="text" name="id">
            </div>
            <div>
                이메일  <input type="email" name="email" id="email">
            </div>
            <hr>
            <input type="submit" value="비밀번호 찾기">
        </fieldset>
    </form>

</body>
</html>