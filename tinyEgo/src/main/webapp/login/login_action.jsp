<%@ page language="java" contentType="text/html; charset=UTF-8" 
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${!session.getAttribute('ugrade').equals('admin')}">
	${response.sendRedirect(path+"/index.jsp")}
</c:if>