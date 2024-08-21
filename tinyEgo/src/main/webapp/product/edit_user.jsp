<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:include page="/include/top.jsp" />
<br />

<section>

<h2>상품 상세페이지</h2><br />

<form action="${path}/CartController" method=post>
<input type="hidden" name="sw" value="I" />
<input type="hidden" name="uid" value="${uid}" />
<input type="hidden" name="pid" value="${m.pid}" />
	<table>
		<tr><th width=120px>상품명</th><td style="width:280px;text-align:left"> ${m.pname}</td>
									 <td rowspan=6><img src="${path}/product/files/${m.pimg}" style="width:150px;height:auto;" /></td></tr>
		<tr><th>브랜드명</th><td style="text-align:left;">${m.brand}</td></tr>
		<tr><th>판매가격</th><td align=left><fmt:formatNumber>${m.salePrice}</fmt:formatNumber></td></tr>
		<tr><th>배송비</th><td align=left><fmt:formatNumber>${m.delcost}</fmt:formatNumber></td></tr>
		<tr><th>상품설명</th><td align=left>${m.pnote}</td></tr>
		<tr><th>주문수량</th><td align=left><input type="number" name="cartAmount" value=1 style="width:15%;height:25px" /></td></tr>
	</table>
	<div style="margin:5px;">
		<input type="submit" value="주문하기" />
	</div>
</form><br /><br />

</section>

<br />
<jsp:include page="/include/bottom.jsp" />