<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="/include/top.jsp" />
<br />

<link rel="stylesheet" href="${path}/test1004/style.css" >

<script>
// 체크박스를 통해 값 가져오기
function chK() {
	// 배열 변수 초기화
    var selectedNames = [];
    var selectedAmounts = [];

    var checkboxes = document.getElementsByName('ch'); // 이름이 'ch'인 체크박스 요소를 변수에 저장함
    for (var i = 0; i < checkboxes.length; i++) {
        if (checkboxes[i].checked) { // 선택된 체크박스를 찾음
            var parent = checkboxes[i].parentNode; // 해당 체크박스 부모 요소를 통해 
            var name = parent.querySelector('[name="name"]').value; // 같은 행의 이름이 name, amount 인 필드를 가져옴
            var amount = parent.querySelector('[name="amount"]').value;
            
            selectedNames.push(name); // 가져온 name, amount 값을 각각 배열에 추가함
            selectedAmounts.push(amount);
        }
    }

    // 선택된 체크박스가 없는 경우
    if (selectedNames.length === 0) {
        alert("하나 이상의 항목을 선택하세요.");
        return false; // 폼 제출 중단
    }
	
    // 선택된 항목들의 이름과 수량을 각 쉼표로 구분된 문자열로 변환하여 숨겨진 입력 필드 값에 저장함
    document.getElementById('selectedNames').value = selectedNames.join(',');
    document.getElementById('selectedAmounts').value = selectedAmounts.join(',');
    return true; // 폼 제출 허용
}
</script>

<section>

<h2>쇼핑하기</h2><br />

<form action="${path}/TestController" onSubmit="return chK()">
<input type="hidden" name="sw" value="I" />
<input type="hidden" name="selectedNames" id="selectedNames" />
<input type="hidden" name="selectedAmounts" id="selectedAmounts" />
	<table>
		<tr>
			<td style="padding:5px">
				<img src="${path}/test1004/img/사과.png" alt="사과" style="width:100px;height:auto;">
				<input type="checkbox" name="ch" />
				<input type="hidden" name="name" value="사과" />청송 사과<br />
				&emsp;수량 <input type="number" name="amount" value=0 style="width:50px;height:25px;" /></td>
			<td style="padding:5px">
				<img src="${path}/test1004/img/수박.png" alt="수박" style="width:100px;height:auto;">
				<input type="checkbox" name="ch" />
				<input type="hidden" name="name" value="수박" />씨없는 수박<br />
				&emsp;수량 <input type="number" name="amount" value=0 style="width:50px;height:25px;" /></td>
			<td style="padding:5px">
				<img src="${path}/test1004/img/딸기.png" alt="딸기" style="width:100px;height:auto;">
				<input type="checkbox" name="ch" />
				<input type="hidden" name="name" value="딸기" />설향 딸기<br />
				&emsp;수량 <input type="number" name="amount" value=0 style="width:50px;height:25px;" /></td>
		</tr>
		<tr>
			<td style="padding:5px">
				<img src="${path}/test1004/img/체리.png" alt="체리" style="width:100px;height:auto;">
				<input type="checkbox" name="ch" />
				<input type="hidden" name="name" value="체리" />미국산 체리 생과<br />
				&emsp;수량 <input type="number" name="amount" value=0 style="width:50px;height:25px;" /></td>
			<td style="padding:5px">
				<img src="${path}/test1004/img/키위.png" alt="키위" style="width:100px;height:auto;">
				<input type="checkbox" name="ch" />
				<input type="hidden" name="name" value="키위" />썬골드키위 점보<br />
				&emsp;수량 <input type="number" name="amount" value=0 style="width:50px;height:25px;" /></td>
			<td style="padding:5px">
				<img src="${path}/test1004/img/블루베리.png" alt="블루베리" style="width:100px;height:auto;">
				<input type="checkbox" name="ch" />
				<input type="hidden" name="name" value="블루베리" />무농약 블루베리<br />
				&emsp;수량 <input type="number" name="amount" value=0 style="width:50px;height:25px;" /></td>
		</tr>
		<tr>
			<td style="padding:5px">
				<img src="${path}/test1004/img/귤.png" alt="귤" style="width:100px;height:auto;">
				<input type="checkbox" name="ch" />
				<input type="hidden" name="name" value="귤" />제주 천혜향<br />
				&emsp;수량 <input type="number" name="amount" value=0 style="width:50px;height:25px;" /></td>
			<td style="padding:5px">
				<img src="${path}/test1004/img/바나나.png" alt="바나나" style="width:100px;height:auto;">
				<input type="checkbox" name="ch" />
				<input type="hidden" name="name" value="바나나" />스미후루 바나나<br /> 
				&emsp;수량 <input type="number" name="amount" value=0 style="width:50px;height:25px;" /></td>
			<td style="padding:5px">
				<img src="${path}/test1004/img/토마토.png" alt="토마토" style="width:100px;height:auto;">
				<input type="checkbox" name="ch" />
				<input type="hidden" name="name" value="토마토" />스테비아 토마토<br />
				&emsp;수량 <input type="number" name="amount" value=0 style="width:50px;height:25px;" /></td>
		</tr>
	</table>
	<div style="margin:5px;">
		<input type="submit" value="주문하기" />
	</div>
</form><br /><br />

</section>

<br />
<jsp:include page="/include/bottom.jsp" />