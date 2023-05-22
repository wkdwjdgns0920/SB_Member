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
<!-- 테일윈드 불러오기 -->
<!-- <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/tailwindcss/2.2.7/tailwind.min.css" /> -->
<link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2/dist/tailwind.min.css" rel="stylesheet" type="text/css" />

<!-- 데이지 UI -->
<link href="https://cdn.jsdelivr.net/npm/daisyui@2.51.5/dist/full.css" rel="stylesheet" type="text/css" />

<!-- 폰트어썸 불러오기 -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.0/css/all.min.css" />
</head>
<body>

	<h1>List</h1>


	<section class="joinSection">
		<div>
			<table class="table table-zebra w-full">
				<colgroup>
					<col width="70" />
					<col width="140" />
					<col width="140" />
					<col width="140" />
				</colgroup>
				<tbody>
					<tr>
						<th>번호</th>
						<td>${article.id }</td>
					</tr>
					<tr>
						<th>작성날짜</th>
						<td>${article.regDate }</td>
					</tr>
					<tr>
						<th>수정날짜</th>
						<td>${article.updateDate }</td>
					</tr>
					<tr>
						<th>작성자</th>
						<td>${article.extra__writer }</td>
					</tr>
					<tr>
						<th>제목</th>
						<td>${article.title }</td>
					</tr>
					<tr>
						<th>내용</th>
						<td>${article.body }</td>
					</tr>
				</tbody>
			</table>
		</div>
	</section >
	
	<section class="">
	<c:if test="${rq.logined }">
		<form action="../reply/doWrite" method="POST" >
			<input type="hidden" name="relId" value="${article.id }" />
			<div class="">
				<div>댓글을 입력해주세요</div>
				<div class="">
					<div>
						<textarea class="" type="text" name="body" placeholder="내용을 입력해주세요" /></textarea>
					</div>
					<button class="btn reply_write_btn" type="submit" value="작성">댓글작성</button>
				</div>
			</div>
		</form>
	</c:if>
	<c:if test="${!rq.logined}">
		<div class="reply_box_check_login">
			댓글을 작성하려면 &nbsp;
			<a class="reply_box_a" href="../member/login">로그인</a>
			&nbsp; 해주세요
		</div>
	</c:if>
</section>

<section class="mt-5">
	<div class="container mx-auto px-3">
		<h1 class="text-3xl">댓글 리스트(${repliesCount })</h1>
		<table class="table table-zebra w-full">
			<colgroup>
				<col width="70" />
				<col width="100" />
				<col width="50" />
				<col width="100" />

			</colgroup>
			<thead>
				<tr>
					<th>번호</th>
					<th>날짜</th>
					<th>작성자</th>
					<th>내용</th>
				</tr>
			</thead>

			<tbody>
				<c:forEach var="reply" items="${replies }">
					<tr class="hover">
						<td>
							<div class="badge">${reply.id}</div>
						</td>
						<td>${reply.regDate }</td>
						<td>${reply.extra__writer}</td>
						<td align="left">${reply.body}</td>
					</tr>
				</c:forEach>
			</tbody>

		</table>
	</div>
</section>


	<a href="/">메인으로</a>
</body>
</html>