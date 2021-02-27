<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>Board Project</title>
</head>

<body>
    <h1>게시글 목록</h1>
    <p><a href="add">신규 게시글</a></p>
    <table border="1">
        <tr>
            <th>번호</th>
            <th>제목</th>
            <th>작성자</th>
            <th>작성일</th>
            <th>조회수</th>
        </tr>
        <c:forEach var="board" items="${ boardList }">
            <tr>
                <td>${ board.bdNum}</td>
                <td>${ board.bdTitle}</td>
                <td>${ board.bdAuthor}</td>
                <td>${ board.bdCreateDate}</td>
                <td>${ board.bdViews}</td>
            </tr>
        </c:forEach>
    </table>
</body>

</html>