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

	<h1>Join</h1>

	<script>
		let submitJoinFormDone = false;	

		function checkEmailDup(el) {
			$('.emailDup-msg').empty();
			const form = $(el).closest('form').get(0);

			$.get('../member/getEmailDup', {
				isAjax : 'Y',
				email : form.email.value
			}, function(data) {

				if (data.success) {
					$('.emailDup-msg').html(
							'<div class="y">' + data.msg + '</div>')
				} else {
					$('.emailDup-msg').html(
							'<div class="n">' + data.msg + '</div>')
				}

			}, 'json');

		}
	</script>

	<section class="joinSection">
		<form action="../member/doJoin" method="POST" onsubmit="submitJoinForm(this); return false;">
			<div class="joinBox">
				<div class="">이메일</div>
				<input onblur="checkEmailDup(this)" class="input" name="email" type="text" autocomplete="off" placeholder="email" />
				<div class="emailDup-msg"></div>
				<div class="">비밀번호</div>
				<input class="input" name="loginPw" type="text" autocomplete="off" placeholder="loginPw" />
				<div class="">비번확인</div>
				<input class="input" name="loginPwConfirm" type="text" autocomplete="off" placeholder="loginPwConfirm" />
				<div class="">이름</div>
				<input class="input" name="name" type="text" autocomplete="off" placeholder="name" />
				<div class="">닉네임</div>
				<input class="input" name="nickname" type="text" autocomplete="off" placeholder="nickname" />
				<div class="">전화번호</div>
				<input class="input" name="cellphoneNum" type="text" autocomplete="off" placeholder="cellphoneNum" />
				<button class="submit_btn" type="submit">제출</button>
			</div>
		</form>
	</section>
	<a href="/">메인으로</a>
</body>
</html>