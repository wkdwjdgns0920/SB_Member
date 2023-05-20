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

	<h1>Profile</h1>



	<section class="joinSection">
		<div>
			<div class="mt_3">
				<span>가입일 : </span>
				<span>${member.regDate }</span>
			</div>
			<div class="mt_3">
				<span>이메일 : </span>
				<span>${member.email }</span>
			</div>
			<div class="mt_3">
				<span>이름 : </span>
				<span>${member.name }</span>
			</div>
			<div class="mt_3">
				<span>닉네임 : </span>
				<span>${member.nickname }</span>
			</div>
			<div class="mt_3">
				<span>전화번호 : </span>
				<span>${member.cellphoneNum }</span>
			</div>
			<div class="mt_3">
				<a href="checkPw?id=${member.id }">회원정보 수정하기</a>
			</div>
		</div>
	</section>
	<a href="/">메인으로</a>
</body>
</html>