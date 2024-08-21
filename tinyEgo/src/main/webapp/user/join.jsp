<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<jsp:include page="/include/top.jsp" />
<br />

<script  src="http://code.jquery.com/jquery-1.10.2.js" ></script>
<script>
var path='${pageContext.request.contextPath}';

jQuery.ajaxSetup({cache:false});

$(document).ready(function () {
	$('#ckID').click(function () {
		var uid = $('#uid').val().trim(); // = f1.uid.value.trim();
		
		if (uid.length < 5) {
			alert("ID는 5자리 이상 입력해주세요.");
			$('#uid').focus();
			return false;
		}
		
		var dataStr = {
			uid : uid,
			sw : 'ckId'
		};

		$.ajax({
			type : "GET",
			url : path + "/UserController",
			data : dataStr,
			success : function (data) {
				if (data == "T") {	// 중복값이 있는 경우 
					alert("사용 불가능한 ID 입니다.");
					$('#uid').val(""); // f1.uid.value = "";
				} else {			// 중복값이 없는 경우
					alert("사용 가능한 ID 입니다.");
				}
			}
		})
	})
})
</script>

<section>

<h2>회원 가입</h2><br />

<form name="f1" onClick="return ck()" action="${path}/UserController">
<input type="hidden" name="sw" value="I" />
	<table>
		<tr><th width=150px>아이디</th><td width=300px align="left">
									 <input type="text" id="uid" name="uid" required style="width:50%;height:25px;" />
									 <input type="button" id="ckID" value="중복확인" /></td></tr>
		<tr><th>비밀번호</th><td align="left"><input type="password" id="upwd" name="upwd" required style="width:50%;height:25px;" /></td></tr>
		<tr><th>이름</th><td align="left"><input type="text" id="uname" name="uname" required style="width:50%;height:25px;" /></td></tr>
		<tr><th>생년월일</th><td align="left"><input type="text" id="ubirth" name="ubirth" required placeholder="yyyymmdd" maxlength="8" style="width:28%;height:25px;" /></td></tr>
		<tr><th>성별</th><td align="left"><input type="radio" name="ugen" id="user_m" value="m" />남
										 <input type="radio" name="ugen" id="user_f" value="f"/>여</td></tr>
		<tr><th>휴대폰번호</th><td align="left"><input type="text" id="uphone" name="uphone" placeholder="000-0000-0000" maxlength="13" required style="width:35%;height:25px;"/></td></tr>
		<tr><th>주소</th><td align="left"><input type="text" id="postcode" name="uzip" placeholder="우편번호" style="width:30%;height:25px;" />
										 <input type="button" onclick="execDaumPostcode()" value="우편번호찾기"><br />
										 <input type="text" id="roadAddress" name="uaddr1" placeholder="도로명주소" style="height:25px;" />
										 <input type="text" id="detailAddress" name="uaddr2" placeholder="상세주소" style="height:25px;" /></td></tr>
	</table>
	<div style="margin:5px;">
		<input type="submit" value="가입하기" />&nbsp;
		<input type="reset" value="새로입력하기" />
	</div>
</form><br /><br />

<input type="hidden" id="jibunAddress" placeholder="지번주소">
<span id="guide" style="color:#999;display:none"></span>
<input type="hidden" id="extraAddress" placeholder="참고항목">

</section>

<br />

<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script type="text/javascript" src="${path}/user/kakaoAddr.js"></script>
<jsp:include page="/include/bottom.jsp" />