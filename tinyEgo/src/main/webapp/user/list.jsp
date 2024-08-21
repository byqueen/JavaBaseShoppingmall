<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<jsp:include page="/login/login_action.jsp" />
<jsp:include page="/include/top.jsp" />
<br />

<section>

<h2>회원 목록</h2><br />

<!--  
페이지idx : ${pidx} / 총레코드수 : ${totalRecord} / 총페이지수 : ${totalPage}<br />
현재페이지 : ${currentPage} / 페이지사이즈 : ${pageSize} / 리스트사이즈 : ${listSize}<br /> 
리스트시작페이지 : ${listStartPage} / 리스트끝페이지 : ${listEndPage}
 -->

<div style="margin:5px;">
<form action="${path}/UserController" method="get">
<input type="hidden" name="sw" value="S" />
	<select name="ch1" style="width:100px;height:25px;">
		<option value="uname">이름</option>
		<option value="uphone">휴대폰번호</option>
		<option value="ugrade">등급</option>
	</select>
	<input type="text" name="ch2" style="width:200px;height:25px;" />
	<input type="submit" value="검색하기" />
</form>
</div>

<form>
	<table>
		<tr><th width=100px>아이디</th>
			<th width=150px>이름</th>
			<th width=90px>생년월일</th>
			<th width=50px>성별</th>
			<th width=100px>휴대폰번호</th>
			<th width=50px>등급</th>
			<th width=90px>가입일자</th>
			<th width=50px>비고</th>
		</tr>
		<c:if test="${empty li}">
			<tr><td colspan=11>조회된 레코드가 없습니다.</td></tr>
		</c:if>
		<c:forEach var="m" items="${li}">
			<tr><td>${m.get("uid")}</td>
				<td>${m.get("uname")}</td>
				<td>${m.get("ubirth")}</td>
				<td><c:if test="${m.get('ugen') eq 'm'}">남</c:if>
					<c:if test="${m.get('ugen') eq 'f'}">여</c:if>
				<td>${m.get("uphone")}</td>			
				<td>${m.get("ugrade")}</td>
				<td>${fn:substring(m.joindate, 0, 10)}</td>
				<td><a href="${path}/UserController?sw=E&uid=${m.uid}">상세</a></td>
			</tr>
		</c:forEach>
	</table><br />
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