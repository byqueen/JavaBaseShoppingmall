<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/login/login_action.jsp" />
<jsp:include page="/include/top.jsp" />
<br />

<section>

<h2>회원 상세</h2><br />

<form name="editF" action="${path}/UserController" method="post">
<input type="hidden" name="sw" value="" />
	<table>
		<tr><th width=150px>아이디</th><td width=300px align="left"><input type="text" id="uid" name="uid" value="${m.uid}" style="width:50%;height:25px;" /></td></tr>
		<tr><th>비밀번호</th><td align="left"><input type="password" id="upwd" name="upwd" style="width:50%;height:25px;" /></td></tr>
		<tr><th>이름</th><td align="left"><input type="text" id="uname" name="uname" value="${m.uname}" style="width:50%;height:25px;" /></td></tr>
		<tr><th>생년월일</th><td align="left"><input type="text" id="ubirth" name="ubirth" value="${m.ubirth}" placeholder="yyyymmdd" style="width:28%;height:25px;" /></td></tr>
		<tr><th>성별</th><td align="left"><input type="radio" name="ugen" id="user_m" value="m" <c:if test="${m.ugen eq 'm'}"> checked </c:if> />남
        								 <input type="radio" name="ugen" id="user_f" value="f" <c:if test="${m.ugen eq 'f'}"> checked </c:if> />여</td></tr>
		<tr><th>휴대폰번호</th><td align="left"><input type="text" id="uphone" name="uphone" value="${m.uphone}" placeholder="000-0000-0000" style="width:35%;height:25px;"/></td></tr>
		<tr><th>주소</th><td align="left"><input type="text" id="postcode" name="uzip" value="${m.uzip}" placeholder="우편번호" style="width:30%;height:25px;" />
										 <input type="button" onclick="execDaumPostcode()" value="우편번호찾기"><br />
										 <input type="text" id="roadAddress" name="uaddr1" value="${m.uaddr1}" placeholder="도로명주소" style="height:25px;" />
										 <input type="text" id="detailAddress" name="uaddr2" value="${m.uaddr2}" placeholder="상세주소" style="height:25px;" /></td></tr>
		<tr><th>고객등급</th><td align="left"><input type="text" id="ugrade" name="ugrade" value="${m.ugrade}" placeholder="Friends,Gold,VIP" style="width:35%;height:25px;"/></td></tr>
		<tr><th>고객메모</th><td align="left"><textarea name="unote">${m.unote}</textarea></td></tr>
	</table>
	<div style="margin:5px;">
		<input type="submit" onClick="funcE('U')" value="수정하기" />
		<input type="submit" onClick="funcE('D')" value="삭제하기" />
		<input type="submit" onClick="funcE('S')" value="뒤로가기" />
	</div>
</form><br /><br />

		<input type="hidden" id="jibunAddress" placeholder="지번주소">
		<span id="guide" style="color:#999;display:none"></span>
		<input type="hidden" id="extraAddress" placeholder="참고항목">

</section>

<br />

<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script type="text/javascript" src="${path}/user/kakaoAddr.js"></script>
<script type="text/javascript" src="${path}/user/form.js"></script>
<jsp:include page="/include/bottom.jsp" />