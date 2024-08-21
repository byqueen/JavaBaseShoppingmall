<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<jsp:include page="/include/top.jsp" />
<br />

<script>
function funcK(str) {
	if (str === 'join') {
		location.href="${path}/UserController?sw=F"
	}
}
</script>

<section>

<h2>로그인</h2><br />

<form name="f1" action="${path}/LoginController" method="post">
<input type="hidden" name="sw" value="login" />
	<table>
		<tr><th width=150px>아이디</th><td width=150px align="left"><input type="text" name="uid" style="width:98%;height:25px;" /></td></tr>
		<tr><th>비밀번호</th><td align="left"><input type="password" name="upwd" style="width:98%;height:25px;" /></td></tr>
	</table>
	<div style="margin:5px;">
		<input type="submit" value="로그인하기" />
		<input type="button" onclick="funcK('join')" value="회원가입하기" />
	</div>
</form><br /><br />

<table id="easyLoginT">
	<tr>
		<td id="easyLoginT" width="50px">
			<a id="kakao-login-btn" href="javascript:loginWithKakao()">
				<img src="${path}/user/img/kakaoLogin.png" style="width:30px;height:auto" />
				카카오톡<br />로그인
			</a>
		</td>
	</tr>
</table>

<p id="token-result"></p> <!-- 카카오 로그인 -->

</section>

<br /><br /><br /><br /><br />
<script src="https://t1.kakaocdn.net/kakao_js_sdk/2.3.0/kakao.min.js"
 		integrity="sha384-70k0rrouSYPWJt7q9rSTKpiTfX6USlMYjZUtr1Du+9o4cGvhPAWxngdtVZDdErlh" crossorigin="anonymous"></script>
<script>
  // (1) 사용하려는 앱의 JavaScript 키 입력
  Kakao.init('f08e992c5a0d94906865f261196b6095');
</script>
<script src="${path}/login/kakaoLogin.js"></script>
<jsp:include page="/include/bottom.jsp" />