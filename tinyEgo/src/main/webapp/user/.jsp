<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<jsp:include page="/include/top.jsp" />
<br />
<section>

<h2>회원가입</h2>
<form method="post" action="/MemberController">
	<table>
		<tr><th>아이디</th><td></td></tr>
		<tr><th>비밀번호</th><td></td></tr>
		<tr><th>이름</th><td></td></tr>
		<tr><th>나이</th><td></td></tr>
		<tr><th>성별</th><td></td></tr>
		<tr><th>휴대폰번호</th><td></td></tr>
		<tr><th>우편번호</th><td></td></tr>
		<tr><th>주소</th><td></td></tr>
		<tr><th>상세주소</th><td></td></tr>
	</table>
		<input type="submit" value="가입" />
</form>

</section>
<br />
<jsp:include page="/include/bottom.jsp" />