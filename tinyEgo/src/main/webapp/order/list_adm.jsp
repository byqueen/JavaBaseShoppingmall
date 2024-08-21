<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<jsp:include page="/login/login_action.jsp" />
<jsp:include page="/include/top.jsp" />
<br />

<section>

<h2>주문 목록</h2><br />

<table>
	<tr><th width=120px>주문번호</th>
		<th width=100px>회원아이디</th>
		<th width=80px>회원명</th>
		<th width=280px>주문내역</th>
		<th width=150px>주문일자</th>
		<th width=70px>비고</th></tr>
	
	<c:if test="${empty li}">
		<tr><td colspan=6>조회된 레코드가 없습니다.</td></tr>
	</c:if>
	
	<c:forEach var="m" items="${li}">
	<c:set var="i" value="${i + 1}" />
	<tr>
		<td><input type="hidden" name="orderid" value="${m.orderid}" />${m.orderid}
		<td><input type="hidden" name="uid" value="${m.uid}" />${m.uid}</td>
		<td>${m.uname}</td>
		<td align=left><c:choose>
						<c:when test="${m.totalNumber>1}">
						&nbsp;${m.pname}외 ${m.totalNumber}건
						</c:when>
						<c:otherwise>
						&nbsp;${m.pname} 1건
						</c:otherwise>
						</c:choose>
		</td>
		<td><input type="hidden" name="orderdate" value="${m.orderdate}" />
					   ${m.orderdate}</td>
		<td><a href="${path}/CartController?sw=order_detail&uid=${m.uid}&orderid=${m.orderid}">상세보기</a></td>
	</tr>
	</c:forEach>
</table><br /><br />
    
</section>

<br />
<jsp:include page="/include/bottom.jsp" />