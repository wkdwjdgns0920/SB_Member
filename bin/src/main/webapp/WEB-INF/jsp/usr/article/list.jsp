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
				<colgroup >
					<col width="70" />
					<col width="140" />
					<col width="140" />
					<col width="140" />
				</colgroup >
				<thead>
					<tr >
						<th >번호</th>
						<th >날짜</th>
						<th >제목</th>
						<th >작성자</th>
					</tr>
				</thead>

				<tbody>
					<c:forEach var="article" items="${articles }">
						<tr class="hover">
							<td >
								<div>${article.id}</div>
							</td>
							<td >${article.regDate.substring(2,16)}</td>
							<td ><a href="detail?id=${article.id }">${article.title}</a></td>
							<td >${article.extra__writer}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<div class="pagination flex justify-center mt-3">
			<div class="btn-group">

				<c:set var="paginationLen" value="2" />
				<c:set var="startPage" value="${page - paginationLen >= 1 ? page - paginationLen : 1}" />
				<c:set var="endPage" value="${startPage + itemInAPage-1 <= pagesCount ? startPage + itemInAPage-1 : pagesCount}" />


				<c:if test="${startPage > 1 }">
					<a class="btn" href="?page=1"> << </a>
					<button class="btn btn-disabled">...</button>
				</c:if>

				<c:forEach begin="${startPage }" end="${endPage }" var="i">
					<a class="btn ${page == i ? 'btn-active' : '' }" href="?page=${i}">${i }</a>
				</c:forEach>

				<c:if test="${endPage < pagesCount }">
					<button class="btn btn-disabled">...</button>
					<a class="btn" href="?page=${pagesCount}">>></a>
				</c:if>
			</div>
		</div>
		</div>
	</section>
	<a href="/">메인으로</a>
</body>
</html>