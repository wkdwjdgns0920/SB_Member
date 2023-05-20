<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>메인</h1>
<a href="../member/join">Join</a>
<a href="../member/login">Login</a>
<a href="../member/doLogout">Logout</a>
<a href="../member/profile?id=${rq.getLoginedMemberId() }">Profile</a>
<a href="../article/write">Write</a>
<a href="../article/list">List</a>
</body>
</html>