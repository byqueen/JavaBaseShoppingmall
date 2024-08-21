<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:include page="/include/top.jsp" />
<br />

<section>

<h2>후기 작성하기</h2><br />

<form action="${path}/ReviewController?">
<input type="hidden" name="sw" value="I" />
<input type="hidden" name="orderid" value="${m.get('orderid')}" />
<input type="hidden" name="uid" value="${m.get('uid')}" />
<input type="hidden" name="pid" value="${m.get('pid')}" />
	<table>
		<tr><th width=150px>작성자</th>
			<td style="text-align:left">
			<input type="hidden" name="uname" value="${m.get('uname')}" />
			&nbsp;${m.get("uname")}</td>
		</tr>
		<tr><th>주문내역</th>
			<td align="left">
			<input type="hidden" name="pname" value="${m.get('pname')}" />
			&nbsp;${m.get("pname")}</td>
		</tr>
		<tr><th>내용</th>
			<td width=380px align="left"><textarea name="rnote"></textarea>
			</td>
		</tr>
	</table>
	<div style="margin:5px;">
		<input type="submit" value="저장하기" />
	</div>
</form><br /><br />

</section>

<br />
<jsp:include page="/include/bottom.jsp" />