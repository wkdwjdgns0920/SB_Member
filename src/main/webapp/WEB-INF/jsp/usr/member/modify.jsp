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

	<h1>Modify</h1>



	<section class="joinSection">
		<form action="../member/doModify" method="POST">
			<input type="hidden" value="${member.id }" name="id" />
			<div>
				<div class="mt_3 input">
					<span>가입일 : </span>
					<span>${member.regDate }</span>
				</div>

				<div class="mt_3 input">
					<span>이메일 : </span>
					<span>${member.email }</span>
				</div>

				<div class="mt_3 input">
					<span>비밀번호 : </span>
				</div>
				<input class="input" name="loginPw" type="text" autocomplete="off" placeholder="수정할 비밀번호" />

				<div class="mt_3 input">
					<span>비밀번호 확인 : </span>
				</div>
				<input class="input" name="loginPwConfirm" type="text" autocomplete="off" placeholder="비밀번호확인" />

				<div class="mt_3 input">
					<span>이름 : </span>
					<span>${member.name }</span>
				</div>
				<input class="input" name="name" type="text" autocomplete="off" placeholder="수정할 이름" />

				<div class="mt_3 input">
					<span>닉네임 : </span>
					<span>${member.nickname }</span>
				</div>
				<input class="input" name="nickname" type="text" autocomplete="off" placeholder="수정할 닉네임" />

				<div class="mt_3 input">
					<span>전화번호 : </span>
					<span>${member.cellphoneNum }</span>
				</div>
				<input class="input" name="cellphoneNum" type="text" autocomplete="off" placeholder="수정할 전화번호" />

				<div class="mt_3">
					<button type="submit">수정하기</button>
				</div>

			</div>
		</form>
	</section>
	<a href="/">메인으로</a>
</body>
</html>