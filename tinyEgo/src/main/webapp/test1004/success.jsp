<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/include/top.jsp" />
<br />

<section>

<h2>카카오페이 결제 성공</h2><br />

<div align="center">
결제가 성공적으로 완료되었습니다.<br />
주문하신 내역은 아래와 같습니다.<br />
배송 관련 사항은 주문내역에서 참고하여 주시기 바랍니다.<br /><br />
<table>
	<tr><th width=150px>주문번호</th><td style="text-align:left;width:300px;"> ${order_id}</td></tr>
	<tr><th>고객번호</th><td style="text-align:left;"> ${user_id}</td></tr>
	<tr><th>상품명</th><td style="text-align:left;"> ${item_name}</td></tr>
	<tr><th>상품수량</th><td style="text-align:left;"> ${quantity}</td></tr>
	<tr><th>총결제금액</th><td style="text-align:left;"> ${total_amount}</td></tr>
</table>
</div>

</section>

<br />
<jsp:include page="/include/bottom.jsp" />