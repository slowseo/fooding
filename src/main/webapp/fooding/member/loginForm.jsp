<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>푸딩 로그인</title>
</head>
<jsp:include page="../inc/top.jsp" />
<body>
    <!-- 여기에 top.jsp 내용을 포함하세요 -->
    
    <form action="./MemberLoginAction.mem" method="post">
        <fieldset>
            <legend>로그인</legend>
            <div>
                아이디  <input type="text" name="id" placeholder="아이디를 입력하세요!">
            </div>
            <div>
                비밀번호  <input type="password" name="pw" placeholder="비밀번호를 입력하세요">
            </div>
            <div>
                <input type="submit" value="로그인">
                <input type="button" value="회원가입" onclick="location.href='./MemberJoin.mem';">
                <input type="button" value="아이디찾기" onclick="location.href='./MemberIdFind.mem';">
                <input type="button" value="비밀번호찾기" onclick="location.href='./MemberPwFind.mem';">
            </div>
        </fieldset>
    </form>
    
    
</body>
</html>