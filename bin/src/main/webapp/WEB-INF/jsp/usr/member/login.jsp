<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
	
	<h1>Login</h1>


	<section class="joinSection">
		<form action="../member/doLogin" method="POST" onsubmit="">
			<div class="joinBox">
				<div class="">이메일</div>
				<input class="input" name="email" type="text" autocomplete="off"
					placeholder="loginId" />
				<div class="checkDup-msg"></div>
				<div class="">비밀번호</div>
				<input class="input" name="loginPw" type="text" autocomplete="off" placeholder="loginPw" />
				<button class="submit_btn" type="submit">제출</button>
			</div>
		</form>
	</section>
	<a href="/">메인으로</a>
</body>
</html>