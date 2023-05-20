<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- 제이쿼리 불러오기 -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
<link rel="stylesheet" href="/resource/common.css" />
</head>
<body>
	
	<h1>Write</h1>


	<section class="joinSection">
		<form action="../article/doWrite" method="POST" onsubmit="">
			<div>
				<span>작성자 : ${rq.loginedMemberId }</span>
			</div>
			<div>
				<span>제목 : </span>
				<span><input class="" type="text" name="title" placeholder="제목을 입력해주세요" /></span>
			</div>
			<div>
				<span>내용 : </span>
				<span><textarea class="" type="text" name="body" placeholder="내용을 입력해주세요" /></textarea></span>
			</div>
			<div><button type="submit">작성</button></div>
			
		</form>
	</section>
	<a href="/">메인으로</a>
</body>
</html>