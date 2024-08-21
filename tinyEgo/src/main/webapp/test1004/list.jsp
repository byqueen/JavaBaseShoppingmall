<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/include/top.jsp" />
<br />

<section>

<h2>주문 내역</h2><br />

<table>
	<tr><th width=110px>상품이미지</th>
		<th width=100px>상품명</th>
		<th width=100px>수량</th>
	</tr>
	
	<c:set var="total" value="0" />
	<c:forEach var="m" items="${li}">
		<tr><td><img src="${path}/test1004/img/${m.name}.png" style="width:100px;height:auto;" /></td>
			<td>${m.name}</td>
			<td>${m.amount}</td>
		</tr>
		<c:set var="total" value="${total+m.amount}" />
	</c:forEach>
		<tr><td colspan="2">수량 합계</td>
			<td>${total}</td>
		</tr>
	
	
</table><br /><br />


</section>

<br />
<jsp:include page="/include/bottom.jsp" />