<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<jsp:include page="/include/top.jsp" />
<br />

<section>

<h2>쇼핑하기</h2><br />

<%-- 페이지idx : ${pidx} / 총레코드수 : ${totalRecord} / 총페이지수 : ${totalPage}<br />
현재페이지 : ${currentPage} / 페이지사이즈 : ${pageSize} / 리스트사이즈 : ${listSize}<br /> 
리스트시작페이지 : ${listStartPage} / 리스트끝페이지 : ${listEndPage} --%>

<div style="margin:5px;">
<form action="${path}/ProductController" method="get">
<input type="hidden" name="sw" value="S_user" />
	<select name="ch1" style="width:100px;height:25px;">
		<option value="pname">상품명</option>
		<option value="brand">브랜드명</option>
	</select>
	<input type="text" name="ch2" style="width:200px;height:25px;" />
	<input type="submit" value="검색하기" />
</form>
</div>

<form>
	<table>
		<c:if test="${empty li}">
			<tr><td colspan=11>조회된 레코드가 없습니다.</td></tr>
		</c:if>

		<tr>
		<c:forEach var="m" items="${li}">
		<c:set var="i" value="${i + 1}" />
			<td width=150px><a href="${path}/ProductController?sw=E_user&pid=${m.pid}">
				<img src="${path}/product/files/${m.pimg}" style="width:140px;height:auto;" />
				</a>
				${m.brand}<br />
				<c:choose>
					<c:when test="${fn:length(m.pname) > 12 }">
						<c:out value="${fn:substring(m.pname, 0, 12)}" />...<br />
					</c:when>
					<c:otherwise>
						${m.pname}<br />
					</c:otherwise>
				</c:choose>
				
				가격: <fmt:formatNumber>${m.salePrice}</fmt:formatNumber>원
			</td>
			<c:if test="${i % 4 == 0}">
			<tr>
			</c:if>
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