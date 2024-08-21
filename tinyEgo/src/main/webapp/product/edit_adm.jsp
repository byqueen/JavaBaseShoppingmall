<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:include page="/login/login_action.jsp" />
<jsp:include page="/include/top.jsp" />
<br />

<section>

<h2>상품 상세 수정</h2><br />

<!-- binary data 처리 
	(1) enctype=multipart/form-data : 바이너리 -> 문자열로 변환하여 반환 
	(2) method=post  -->
<form action="${path}/ProductController" enctype=multipart/form-data method=post>
<input type="hidden" name="sw" value="U" />
<input type="hidden" name="pid" value="${m.pid}" />
	<table>
		<tr><th width=120px>상품명</th><td colspan=2 style="width:430px;"><input type="text" name="pname" value="${m.pname}" style="height:25px;" /></td></tr>
		<tr><th>브랜드명</th><td style="text-align:left;"><input type="text" name="brand" value="${m.brand}" style="width:50%;height:25px;" /></td>
						   <td rowspan=5><img src="${path}/product/files/${m.pimg}" style="width:150px;height:auto;" /></td></tr>
		<tr><th>공급가격</th><td align=left><input type="number" name="supplyPrice" value="${m.supplyPrice}" style="width:50%;height:25px;" /></td></tr>
		<tr><th>판매가격</th><td align=left><input type="number" name="salePrice" value="${m.salePrice}" style="width:50%;height:25px;" /></td></tr>
		<tr><th>배송비</th><td align=left><input type="number" name="delcost" value="${m.delcost}" style="width:50%;height:25px;" /></td></tr>
		<tr><th>상품이미지</th><td align=left><input type="file" name="pimg" /></td></tr>
		<tr><th>상품설명</th><td colspan=2 style="text-align:left;"><textarea name="pnote">${m.pnote}</textarea></td></tr>
	</table>
	<div style="margin:5px;">
		<input type="submit" value="상품수정하기" />
	</div>
</form><br /><br />

</section>

<br />
<jsp:include page="/include/bottom.jsp" />
