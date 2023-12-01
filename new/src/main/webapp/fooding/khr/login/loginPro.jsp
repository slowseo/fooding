<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
	// 한글 처리
	request.setCharacterEncoding("UTF-8");
	
	// 전달 정보 저장
	String id = request.getParameter("id");
	String pw = request.getParameter("pw");
	
	// 1. 드라이버 로드
	Class.forName("com.mysql.cj.jdbc.Driver");
	
	// 2. DB 연결
	Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/c7d2307t2_2", "root", "1234");
	
	// 3. SQL 구문 작성 및 pstmt 객체 생성
	String sql = "SELECT pw FROM member WHERE id = ? AND activation = 'Y'";
	PreparedStatement pstmt = con.prepareStatement(sql);
	
	// ? 정보 추가
	pstmt.setString(1, id);
	
	// 4. SQL 구문 실행
    ResultSet rs = pstmt.executeQuery();
	
	// 5. 데이터 처리
	int result = 0;
	if(rs.next()){ // 회원 정보 O
		if(pw.equals(rs.getString(1))){ // 비밀번호 일치
			session.setAttribute("id", id);
			response.sendRedirect("http://localhost:8088/TEAM2/Profile.pro");
		}else{ // 비밀번호 불일치
			
		}
	}else{ // 회원 정보 X
		%>
		<script>
		alert("일치하는 회원 정보가 없습니다.");
		history.back();
		</script>
		<%
	}

	%>

</body>
</html>