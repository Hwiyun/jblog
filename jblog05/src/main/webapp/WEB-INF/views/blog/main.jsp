<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<Link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
</head>
<body>
	<div id="container">
		<div id="header">
			<h1>${blogvo.title }</h1>
			<ul>
				<c:if test="${empty authUser }">
				<li><a href="${pageContext.request.contextPath}/user/login">로그인</a></li>
				</c:if>
				<c:if test="${not empty authUser }">
				<li><a href="${pageContext.request.contextPath}/user/logout">로그아웃</a></li>
				</c:if>
				<li><a href="${pageContext.request.contextPath}/${id }/admin-basic">블로그 관리</a></li>
				<li><a href="${pageContext.request.contextPath}/${id }">메인</a></li>
			</ul>
		</div>
		<div id="wrapper">
			<div id="content">
				<div class="blog-content">
					<h4>${postvo.title }<br></h4>
					<p>
					${postvo.contents }<br>
					<p>
				</div>
				<c:forEach items="${postlist }" var="vo">
				<ul class="blog-list">
					<li><a href="${pageContext.request.contextPath}/${id }?no=${categoryNo }&id=${categoryId }">${vo.title }</a> <span>${vo.regDate }</span></li>
				</ul>
				</c:forEach>
			</div>
		</div>

		<div id="extra">
			<div class="blog-logo">
				<img src="${pageContext.request.contextPath}${blogvo.profile }">
			</div>
		</div>

		<div id="navigation">
			<h2>카테고리</h2>
			<ul>
				<c:forEach items="${categorylist }" var="vo">
				<li><a href="${pageContext.request.contextPath}/${id }/${vo.no }">${vo.name }</a></li>
				</c:forEach>
			</ul>
		</div>
		
		<div id="footer">
			<p>
				<strong>휘윤의 봄</strong> is powered by JBlog (c)2023
			</p>
		</div>
	</div>
</body>
</html>