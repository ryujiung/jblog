<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<Link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/jblog.css">
</head>
<body>
	<div id="container">
		<div id="header">
			<h1>${map.blogVo.title }</h1>
			<ul>
			<c:choose>
				<c:when test="${empty authUser }">
					<li><a href="${pageContext.request.contextPath }/">홈</a>
					<li><a href="${pageContext.request.contextPath }/user/login">로그인</a>
				</c:when>
				<c:otherwise>
				<li><a href="${pageContext.request.contextPath }/">홈</a>
					<li><a href="${pageContext.request.contextPath }/${authUser.id }/admin/basic">블로그관리</a>
					<li><a href="${pageContext.request.contextPath }/user/logout">로그아웃</a>
				</c:otherwise>
			</c:choose>
			</ul>
		</div>
		<div id="wrapper">
			<div id="content">
				<div class="blog-content">
					<h4>${map.postlist[map.postNo].title }</h4>
					<p>
						${map.postlist[map.postNo].contents }
					<p>
				</div>
				<ul class="blog-list">
					<c:forEach items = "${map.postlist }" var = "vo" varStatus = "status">
					<li>
					<a href="${pageContext.request.contextPath }/${map.blogId}/${vo.category_no}/${status.index}">${vo.title }</a>
					</li>
					</c:forEach>
				</ul>
			</div>
		</div>

		<div id="extra">
			<div class="blog-logo">
				<img style = "width:120px" id="image" src="${pageContext.request.contextPath }/${map.blogVo.image }">
			</div>
		</div>

		<div id="navigation">
			<h2>카테고리</h2>
			<ul>
				<c:forEach items = "${map.categorylist }" var = "vo" varStatus = "status">
				<li>
					<a href="${pageContext.request.contextPath }/${map.blogId}/${vo.no}">${vo.name }</a>
					</li>
				</c:forEach>
			</ul>
		</div>

		<div id="footer">
			<p>
				<strong>${map.blogVo.title }</strong> is powered by JBlog (c)2016
			</p>
		</div>
	</div>
</body>
</html>