<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<jsp:include page="/include/top.jsp" />
<br />

<section>

<h2>후기 목록</h2><br />

<div style="margin:5px;">
<form action="${path}/ReviewController" method="post">
<input type="hidden" name="sw" value="S" />
	<select name="ch1" style="width:100px;height:25px;">
		<option value="pname">상품명</option>
	</select>
	<input type="text" name="ch2" style="width:200px;height:25px;" />
	<input type="submit" value="검색하기" />
</form>
</div>

<table>
	<tr><th width=120px>작성자</th>
		<th width=200px>상품명</th>
		<th width=480px>내용</th></tr>
	<c:forEach var="m" items="${li}">
	<!-- 별표(*)를 이스케이프 처리 -->
		<tr><td>${fn:substring(m.uid, 0, 2)}
				**
				${fn:substring(m.uid, fn:length(m.uid) - 1, fn:length(m.uid))}
			</td>
			<td align=left>
			<c:set var="pname" value="${m.pname}" />&nbsp;${m.pname}</td>
			<td align=left>&nbsp;${m.rnote}</td></tr>
	</c:forEach>
</table><br />

<%-- 
<h5>
페이지idx : ${pidx} / 총레코드수 : ${totalRecord} / 총페이지수 : ${totalPage}<br />
현재페이지 : ${currentPage} / 페이지사이즈 : ${pageSize} / 리스트사이즈 : ${listSize}<br /> 
리스트시작페이지 : ${listStartPage} / 리스트끝페이지 : ${listEndPage}
</h5>
 --%>

<!-- 페이지 나누기 -->
<c:url value="/NoteController?sw=S" var="url">
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