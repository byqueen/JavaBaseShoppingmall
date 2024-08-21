<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 

<c:set var="path" scope="session" value="${pageContext.request.contextPath}"></c:set> 

<%  String path = request.getContextPath();
	String uid = (String) session.getAttribute("uid");
	String ugrade = (String) request.getAttribute("ugrade");
	%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>index</title>
<link rel="stylesheet" href="${path}/css/style.css" >
<style type="text/css">
</style>
</head>
<body>
<header><a href="${path}/index.jsp" class="LinkW">tinyEgo</a></header>
<nav>

<a href="${path}/index.jsp" class="LinkW">홈</a>&emsp;

<c:if test="${uid==null}">
	<a href="${path}/LoginController?sw=F" class="LinkW">로그인</a>&emsp;
</c:if>

<c:if test="${uid.equals('admin')}">
	<a href="${path}/LoginController?sw=out" class="LinkW">로그아웃</a>&emsp;
	<a href="${path}/UserController?sw=S" class="LinkW">회원관리</a>&emsp;
	<a href="${path}/ProductController?sw=S_adm" class="LinkW">상품관리</a>&emsp;
	<a href="${path}/CartController?sw=orderS_adm" class="LinkW">주문관리</a>&emsp;
	<a href="${path}/ReviewController?sw=S" class="LinkW">전체후기</a>&emsp;
</c:if>

<c:if test="${uid!=null && !uid.equals('admin')}">
	<a href="${path}/LoginController?sw=out" class="LinkW">로그아웃</a>&emsp;
	<a href="${path}/ProductController?sw=S_user" class="LinkW">쇼핑하기</a>&emsp;
	<a href="${path}/CartController?sw=S" class="LinkW">장바구니</a>&emsp;
	<a href="${path}/CartController?sw=orderS_user" class="LinkW">주문내역</a>&emsp;
	<a href="${path}/ReviewController?sw=S" class="LinkW">전체후기</a>&emsp;
</c:if>

<%-- <a href="${path}/TestController?sw=F" class="LinkW">주문(배열)</a>&emsp; --%>

</nav>