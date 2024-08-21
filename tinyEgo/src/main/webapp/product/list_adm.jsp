<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<jsp:include page="/login/login_action.jsp" />
<jsp:include page="/include/top.jsp" />
<br />

<section>

<h2>상품 목록</h2><br />

<div style="margin:5px;">
<form action="${path}/ProductController" method="get">
<input type="hidden" name="sw" value="S_adm" />
	<select name="ch1" style="width:100px;height:25px;">
		<option value="pname">상품명</option>
		<option value="brand">브랜드명</option>
	</select>
	<input type="text" name="ch2" style="width:200px;height:25px;" />
	<input type="submit" value="검색하기" />
	&nbsp;<u><a href="${path}/ProductController?sw=F">상품 등록</a></u>
</form>
</div>

<form>
	<table>
		<tr><th width=70px>상품번호</th>
			<th width=90px>브랜드명</th>
			<th width=280px>상품명</th>
			<th width=70px>공급가격</th>
			<th width=70px>판매가격</th>
			<th width=65px>배송비</th>
			<th width=90px>등록일자</th>
			<th width=65px>비고</th>
		</tr>
		<c:if test="${empty li}">
			<tr><td colspan=8>조회된 레코드가 없습니다.</td></tr>
		</c:if>
		<c:forEach var="m" items="${li}">
		<c:set var="i" value="${i + 1}" />
			<tr>
				<td>${m.pid}</td>
				<td>${m.brand}</td>
				<td align=left>&nbsp;${m.pname}</td>
				<td align=right><fmt:formatNumber>${m.supplyPrice}</fmt:formatNumber>&nbsp;</td>
				<td align=right><fmt:formatNumber>${m.salePrice}</fmt:formatNumber>&nbsp;</td>
				<td align=right><fmt:formatNumber>${m.delcost}</fmt:formatNumber>&nbsp;</td>
				<td><c:out value="${fn:substring(m.regdate, 0, 11)}" /></td>
				<td><a href="${path}/ProductController?sw=E_adm&pid=${m.pid}"><u>상세보기</u></a></td>
			</tr>
		</c:forEach>
	</table>
</form>

<!-- 페이지 나누기 -->
<c:url value="/UserController?sw=S&ch1=${ch1}" var="url">
	<c:param name="ch2" value="${ch2}" /> <!-- 한글 인코딩 -->
</c:url>

<div style="margin:5px;">

<!-- 첫페이지 -->
<c:if test="${currentPage<=1}">&emsp;&emsp;&emsp;</c:if>
<c:if test="${currentPage>1}">
	<c:set var="firstPidx" value="0" />
	<a href="${url}&pidx=${firstPidx}">첫페이지</a>&nbsp;
</c:if>

<!-- 이전10페이지 -->
<c:if test="${currentPage<=listSize}">&emsp;&emsp;&emsp;</c:if>
<c:if test="${currentPage>listSize}">
	<c:set var="prePidx" value="${(pageSize*listSize)*((listStartPage-listSize-1)/listSize)}" />
	<fmt:parseNumber var="prePidx" value="${prePidx}" integerOnly="true" />
	<a href="${url}&pidx=${prePidx}">이전${listSize}페이지</a>&nbsp;
</c:if>

<!-- 숫자페이지 -->
<c:forEach var="i" begin="${listStartPage}" end="${listEndPage}">
	<c:set var="noidx" value="${(i-1)*pageSize}" />
	<c:if test="${i<=totalPage}">
	<a href="${url}&pidx=${noidx}">[${i}]</a>
	</c:if>
</c:forEach>

<!-- 다음10페이지 -->
<c:if test="${listEndPage>=totalPage}">&emsp;&emsp;&emsp;</c:if>
<c:if test="${listEndPage<totalPage}">
<c:set var="nextPidx" value="${pageSize*listSize*(listEndPage/listSize)}" />
<fmt:parseNumber var="nextPidx" value="${nextPidx}" integerOnly="true" />
<a href="${url}&pidx=${nextPidx}">다음${listSize}페이지</a>&nbsp;
</c:if>

<!-- 마지막페이지 -->
<c:if test="${currentPage>=totalPage}">&emsp;&emsp;&emsp;</c:if>
<c:if test="${currentPage<totalPage}">
<c:set var="lastPidx" value="${(totalPage-1)*pageSize}" />
<fmt:parseNumber var="lastPidx" value="${lastPidx}" integerOnly="true" />
<a href="${url}&pidx=${lastPidx}">마지막페이지</a>&nbsp;
</c:if>

</div><br /><br />

</section>

<br />
<jsp:include page="/include/bottom.jsp" />