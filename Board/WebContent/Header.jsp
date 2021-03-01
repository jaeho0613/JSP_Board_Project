<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div style="background-color: #00008b; color: #ffffff; height: 20px; padding: 5px;">
	[디지털 컨버전스]자바(JAVA) 안드로이드 웹&앱 개발자(BL) B 시험 
	
	<c:if test="${ !empty userDTO }">
		<span style="float: right;"> 
			<a style="color: white;">${userDTO.userName}</a>
			<a style="color: white;" href="logout">로그아웃</a>
		</span>
	</c:if>
</div>