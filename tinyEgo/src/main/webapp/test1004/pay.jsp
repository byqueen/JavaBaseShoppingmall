<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<jsp:include page="/include/top.jsp" />
<br />

<script>
function kakaoCk() {
	quantity = f1.quantity.value;
	price = f1.price.value;
	f1.total_amount.value = quantity*price;
	// alert ("확인 :"+eval(quantity*price));
	// return false;
}
</script>

<section>

<h2>카카오페이 결제 테스트</h2><br />

<form name="f1" action="${path}/PaymentServlet" method="post" onSubmit="return kakaoCk()">
	<table>
		<tr><th width=150px>주문번호</th><td style="text-align:left;width:300px;"><input type="text" name="order_id" style="width:50%;height:25px" /></td></tr>
		<tr><th>고객번호</th><td style="text-align:left;"><input type="text" name="user_id" style="width:50%;height:25px" /></td></tr>
		<tr><th>상품명</th><td style="text-align:left;"><input type="text" name="item_name" style="width:98%;height:25px" /></td></tr>
		<tr><th>상품수량</th><td style="text-align:left;"><input type="number" name="quantity" style="width:50%;height:25px" /></td></tr>
		<tr><th>가격</th><td style="text-align:left;"><input type="number" name="price" style="width:50%;height:25px" /></td></tr>
		<tr><th>총결제금액</th><td style="text-align:left;"><input type="number" name="total_amount" style="width:50%;height:25px" /></td></tr>
	</table>
	
	<div style="margin:5px;">
    <button type="submit">카카오페이로 결제하기</button>
    </div>
</form>

</section>

<br />
<jsp:include page="/include/bottom.jsp" />