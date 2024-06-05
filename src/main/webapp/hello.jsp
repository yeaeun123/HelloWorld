<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Hello JSP World</title>
</head>
<body>
<!-- 사용자로부터 name을 받아서 환영 메세지를 출력 -->
	<%
	String name = request.getParameter("name");
	if (name == null) {	// 사용자가 전달한 매개변수가 없으면
		name = "아무개";
	}
	%>
	<h1>Hello JSP World</h1>
	<p>안녕하세요, <%= name %>님</p>
	<p>이것은 JSP로 만들어진 동적 페이지입니다.</p>
	
	<!-- 주소창에 주소 뒤에 ?name=홍길동 입력하면 name에 들어감-->
	<!-- http://localhost:8080/HelloWorld/hello.jsp?name=홍길동 -->
	
</body>
</html>