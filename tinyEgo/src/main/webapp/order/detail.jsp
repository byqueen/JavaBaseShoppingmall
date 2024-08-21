<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<jsp:include page="/include/top.jsp" />
<br />

<section>

<h2>주문 상세</h2><br />

<table>
	<c:if test="${not empty li}">
		<tr><th colspan=4>배송 정보</th></tr>
		<tr><td width="100px">&nbsp;${li[0].dname}</td>
			<td width="120px">&nbsp;${li[0].dphone}</td>
			<td style="text-align:left;width:400px">&nbsp;${li[0].daddr}</td>
			<td style="text-align:left;width:280px">&nbsp;${li[0].dmemo}</td></tr>
	</c:if>
</table><br />

<table>
	<tr><th width=120px>주문번호</th>
		<th width=70px>상품번호</th>
		<th width=150px>상품이미지</th>
		<th width=90px>브랜드</th>
		<th width=210px>상품명</th>
		<th width=70px>상품가격</th>
		<th width=60px>주문수량</th>
		<th width=60px>배송비</th>
		<th width=70px>비고</th></tr>
	
	<c:if test="${empty li}">
		<tr><td colspan=9>조회된 레코드가 없습니다.</td></tr>
	</c:if>
	
	<c:forEach var="m" items="${li}">
	<c:set var="i" value="${i + 1}" />
	<tr>
		<td><input type="hidden" name="orderid" value="${m.orderid}" />
					   ${m.orderid}
		<td><input type="hidden" name="pid" value="${m.pid}" />
					    ${m.pid}</td>
		<td><img src="${path}/product/files/${m.pimg}" style="width:140px;height:auto;" /></td>
		<td>${m.brand}</td>
		<td>${m.pname}</td>
		<td><input type="hidden" name="orderPrice" value="${m.orderPrice}" />
					   <fmt:formatNumber>${m.orderPrice}</fmt:formatNumber></td>
		<td><input type="hidden" name="orderAmount" value="${m.orderAmount}" style="width:40px;height:25px;" />
					   ${m.orderAmount}</td>
		<td><input type="hidden" name="delcost" value="${m.delcost}" />
					   <fmt:formatNumber>${m.delcost}</fmt:formatNumber></td>
	 	<td><a href="${path}/ReviewController?sw=E&orderid=${m.orderid}&pid=${m.pid}">후기<br />작성하기</a></td>
	</tr>
	<c:set var="totalPprice" value="${totalPprice+(m.salePrice*m.amount)}"></c:set>
	<c:set var="totalDelcost" value="${totalDelcost+m.delcost}"></c:set>
	</c:forEach>
</table>

	<h5>
	총 상품금액 : <fmt:formatNumber>${totalPprice}</fmt:formatNumber> 원<br />
	&nbsp;총 배송비 : <fmt:formatNumber>${totalDelcost}</fmt:formatNumber> 원<br />
	총 결제금액 : <fmt:formatNumber>${totalPprice+totalDelcost}</fmt:formatNumber> 원
	</h5><br /><br />
    
</section>

<br />
<jsp:include page="/include/bottom.jsp" />