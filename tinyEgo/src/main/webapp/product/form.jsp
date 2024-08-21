<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<jsp:include page="/login/login_action.jsp" />
<jsp:include page="/include/top.jsp" />
<br />

<section>

<h2>상품 등록</h2><br />

<!-- binary data 처리 
	(1) enctype=multipart/form-data : 바이너리 -> 문자열로 변환하여 반환 
	(2) method=post  -->
<form enctype=multipart/form-data method=post action="${path}/ProductController" >
<input type="hidden" name="sw" value="I" />
	<table>
		<tr><th width=150px>브랜드명</th><td style="width:300px;text-align:left"><input type="text" name="brand" style="width:30%;height:25px;" /></td></tr>
		<tr><th>상품명</th><td><input type="text" name="pname" style="height:25px;"/></td></tr>
		<tr><th>공급가격</th><td align=left><input type="number" name="supplyPrice" style="width:30%;height:25px;" /></td></tr>
		<tr><th>판매가격</th><td align=left><input type="number" name="salePrice" style="width:30%;height:25px;" /></td></tr>
		<tr><th>배송비</th><td align=left><input type="number" name="delcost" style="width:30%;height:25px;" /></td></tr>
		<tr><th>상품이미지</th><td align=left><input type="file" name="pimg" /></td></tr>
		<tr><th>상세</th><td align=left><textarea name="pnote"></textarea></td></tr>
	</table>
	<div style="margin:5px;">
		<input type="submit" value="상품등록하기" />&nbsp;
		<input type="reset" value="새로입력하기" />
	</div>
</form><br /><br />

</section>

<br />
<jsp:include page="/include/bottom.jsp" />
