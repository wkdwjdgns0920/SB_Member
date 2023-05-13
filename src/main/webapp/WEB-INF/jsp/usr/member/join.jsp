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

	<h1>Join</h1>

	<script>
		let submitJoinFormDone = false;
		let validLoginId = "";

		/* function submitJoinForm(form) {
			if (submitJoinFormDone) {
				alert('처리중입니다@@');
				return;
			}
			form.loginId.value = form.loginId.value.trim();
			if (form.loginId.value == 0) {
				alert('아이디를 입력해주세요');
				return;
			}

			form.loginPw.value = form.loginPw.value.trim();
			if (form.loginPw.value == 0) {
				alert('비밀번호를 입력해주세요');
				return;
			}
			form.loginPwConfirm.value = form.loginPwConfirm.value.trim();
			if (form.loginPwConfirm.value == 0) {
				alert('비밀번호 확인을 입력해주세요');
				return;
			}
			if (form.loginPwConfirm.value != form.loginPw.value) {
				alert('비밀번호가 일치하지 않습니다');
				form.loginPw.focus();
				return;
			}
			form.name.value = form.name.value.trim();
			if (form.name.value == 0) {
				alert('이름을 입력해주세요');
				return;
			}
			form.nickname.value = form.nickname.value.trim();
			if (form.nickname.value == 0) {
				alert('닉네임을 입력해주세요');
				return;
			}
			form.email.value = form.email.value.trim();
			if (form.email.value == 0) {
				alert('이메일을 입력해주세요');
				return;
			}

			form.cellphoneNum.value = form.cellphoneNum.value.trim();
			if (form.cellphoneNum.value == 0) {
				alert('전화번호를 입력해주세요');
				return;
			}
			submitJoinFormDone = true;
			form.submit();
		} */

		function checkLoginIdDup(el) {
			$('.checkDup-msg').empty();
			const form = $(el).closest('form').get(0);

			if (form.loginId.value.length == 0) {
				validLoginId = '';
				return;
			}

			if (validLoginId == form.loginId.value) {
				return;
			}

			$.get('../member/getLoginIdDup', {
				isAjax : 'Y',
				loginId : form.loginId.value
			}, function(data) {

				if (data.success) {
					$('.checkDup-msg').html(
							'<div class="y">' + data.msg + '</div>')
					validLoginId = data.data1;
				} else {
					$('.checkDup-msg').html(
							'<div class="n">' + data.msg + '</div>')
					validLoginId = '';
				}

			}, 'json');

		}

		/* function CheckEmail(el) { 
			var email_rule = /^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/i;
			
			return email_rule.test(el);
		} 
		
		function doCheckEmail(el){
			if(CheckEmail(el) == false){
				alert('이메일 형식이 올바르지 않습니다');
			} else {
				alert('사용가능!');
			}
		} */
	</script>

	<section class="joinSection">
		<form action="../member/doJoin" method="POST" onsubmit="submitJoinForm(this); return false;">
			<div class="joinBox">
				<div class="">아이디</div>
				<input onblur="checkLoginIdDup(this);" class="input" name="loginId" type="text" autocomplete="off"
					placeholder="loginId" />
				<div class="checkDup-msg"></div>
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
				<div class="">이메일</div>
				<input onblur="" class="input" name="email" type="text" autocomplete="off" placeholder="email" />
				<div class="emailDup-msg"></div>
				<button class="submit_btn" type="submit">제출</button>
			</div>
		</form>
	</section>
	<a href="/">메인으로</a>
</body>
</html>