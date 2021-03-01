<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>Board Project</title>
</head>

<body>

	<jsp:include page="/Header.jsp"/>

	<h1>게시글 목록</h1>
	<p>
		<a href="signUp">회원 가입</a>
	</p>
	<table border="1">
		<tr>
			<td colspan="5" align="left">
				1-${ paging.pageSize } [${ paging.currentPage }/${ paging.totalRow }]
			</td>
		</tr>
		
		<tr>
			<th>번호</th>
			<th>제목</th>
			<th>작성자</th>
			<th>작성일</th>
			<th>조회수</th>
		</tr>

		<!-- 출력 될 게시판 -->
		<c:forEach var="board" items="${ boardList }">
			<tr>
				<td>${ board.bdNum }</td>
				<td>${ board.bdTitle }</td>
				<td>${ board.bdAuthor }</td>
				<td>${ board.bdCreateDate }</td>
				<td>${ board.bdViews }</td>
			</tr>
		</c:forEach>

		<!-- 페이지 링크 -->
		<tr>
			<td colspan="5" align="center">
				<c:if test="${paging.startPage > paging.pageBlock}">
					<a href="indexController?no=${paging.startPage - 10}"> [이전] </a>
				</c:if> 
				<c:forEach var="num" begin="${ paging.startPage }" end="${ paging.endPage }">
					<c:if test="${ num <= paging.totalRow }">
						<a href="indexController?no=${ num }"> [${ num }]</a>
					</c:if>
				</c:forEach> 
				<c:if test="${paging.endPage < paging.totalRow}">
					<a href="indexController?no=${paging.startPage + 10}"> [다음] </a>
				</c:if>
			</td>
		</tr>
	</table>
</body>

</html>