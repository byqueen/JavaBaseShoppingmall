<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<jsp:include page="/include/top.jsp" />
<br />

<script>
function funck(str) {
	if (str==='U') {
		var confirmation = confirm("장바구니의 수량을 수정하시겠습니까?");
		if (confirmation) {
			f1.sw.value="U";
			f1.action="CartController";
			f1.submit();
		}
	} else if (str==='D_all') {
		var confirmation = confirm("장바구니의 상품 전체를 삭제하시겠습니까?");
		if (confirmation) {
			f1.sw.value="D_all";
			f1.action="CartController";
			f1.submit();
		}
	}
}

function kakaoCk() {
	var cartAmounts = document.getElementsByName('cartAmount');
    var salePrices = document.getElementsByName('salePrice');
    var delcosts = document.getElementsByName('delcost');
    
    var totalprice = 0;
    var totalPprice = 0;
    var totaldelcost = 0;
    for (var i = 0; i < cartAmounts.length; i++) {
    	totalPprice += parseInt(cartAmounts[i].value) * parseInt(salePrices[i].value);
        totaldelcost += parseInt(delcosts[i].value);
    }
    totalprice = totalPprice + totaldelcost; // 총 상품금액 + 총 배송비
    alert("totalprice 확인: " + totalprice);
    
    f1.totalprice.value = totalprice;
    f1.action = "${path}/PaymentServlet";
    return true;
}
</script>

<section>

<h2>장바구니 목록</h2><br />

<form name="f1" method="post">
<input type="hidden" name="sw" value="U" />
<input type="hidden" name="uid" value="${uid}" />
	<table>
		<tr><th width=70px>상품번호</th>
			<th width=150px>상품이미지</th>
			<th width=90px>브랜드</th>
			<th width=220px>상품명</th>
			<th width=70px>상품가격</th>
			<th width=60px>주문수량</th>
			<th width=60px>배송비</th>
			<th width=70px>비고</th></tr>
		
		<c:if test="${empty li}">
			<tr><td colspan=9>조회된 레코드가 없습니다.</td></tr>
		</c:if>
		
		<c:forEach var="m" items="${li}">
		<c:set var="i" value="${i + 1}" />
		<tr>
			<input type="hidden" name="cartid" value="${m.cartid}" />
			<td><input type="hidden" name="pid" value="${m.pid}" />${m.pid}</td>
			<td><img src="${path}/product/files/${m.pimg}" style="width:140px;height:auto;" /></td>
			<td>${m.brand}</td>
			<td>${m.pname}</td>
			<td><input type="hidden" name="salePrice" value="${m.salePrice}" />
						   <fmt:formatNumber>${m.salePrice}</fmt:formatNumber></td>
			<td><input type="number" name="cartAmount" value="${m.cartAmount}" style="width:40px;height:25px;" /></td>
			<td><input type="hidden" name="delcost" value="${m.delcost}" />
						   <fmt:formatNumber>${m.delcost}</fmt:formatNumber></td>
			<td><a href="${path}/CartController?sw=D&pid=${m.pid}"><u>삭제</u></a></td>
		</tr>
		<c:set var="totalPprice" value="${totalPprice+(m.salePrice*m.cartAmount)}"></c:set>
		<c:set var="totaldelfee" value="${totaldelfee+m.delcost}"></c:set>
		</c:forEach>
	</table><br />
	
	<c:if test="${not empty li}">
	<table>
		<tr><th width=100px>받는분<br />정보</th>
			<td style="width:700px;text-align:left;">
				<input type="text" name="dname" placeholder="받는분 성함" style="width:20%;height:25px" />
				<input type="text" name="dphone" placeholder="받는분 휴대폰번호" maxlength="13" style="width:20%;height:25px" /><br />
				<input type="text" id="postcode" name="dzip" placeholder="우편번호" style="width:20%;height:25px" />
				<input type="button" onclick="execDaumPostcode()" value="우편번호 찾기" /><br>
				<input type="text" id="roadAddress" name="daddr1" placeholder="도로명 주소" style="width:48%;height:25px" />
				<input type="hidden" id="jibunAddress" placeholder="지번 주소" />
				<span id="guide" style="color:#999;display:none"></span>
				<input type="text" id="detailAddress" name="daddr2" placeholder="상세 주소" style="width:48%;height:25px" /><br />
				<input type="hidden" id="extraAddress" placeholder="참고 항목" />
				<input type="text" name="dmemo" placeholder="배송 메모" style="width:48%;height:25px" />
			</td>
		</tr>
	</table>
	</c:if>
	
	<h5>
	총 상품금액 : <fmt:formatNumber>${totalPprice}</fmt:formatNumber> 원<br />
	&nbsp;총 배송비 : <fmt:formatNumber>${totaldelfee}</fmt:formatNumber> 원<br />
	총 결제금액 : <input type="hidden" name="totalprice" value="${totalPprice+totaldelfee}" />
			   <fmt:formatNumber>${totalPprice+totaldelfee}</fmt:formatNumber> 원
	</h5>
	
	<div style="margin:5px;">
		<input type="button" onClick="funck('U')" value="수량수정하기" />
		<input type="button" onClick="funck('D_all')" value="전체삭제하기" />
		<input type="submit" onClick="return kakaoCk()" value="카카오페이 결제" />
	</div>

</form><br /><br />
    
</section>

<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
//본 예제에서는 도로명 주소 표기 방식에 대한 법령에 따라, 내려오는 데이터를 조합하여 올바른 주소를 구성하는 방법을 설명합니다.
function execDaumPostcode() {
    new daum.Postcode({
        oncomplete: function(data) {
            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

            // 도로명 주소의 노출 규칙에 따라 주소를 표시한다.
            // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
            var roadAddr = data.roadAddress; // 도로명 주소 변수
            var extraRoadAddr = ''; // 참고 항목 변수

            // 법정동명이 있을 경우 추가한다. (법정리는 제외)
            // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
            if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                extraRoadAddr += data.bname;
            }
            // 건물명이 있고, 공동주택일 경우 추가한다.
            if(data.buildingName !== '' && data.apartment === 'Y'){
               extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
            }
            // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
            if(extraRoadAddr !== ''){
                extraRoadAddr = ' (' + extraRoadAddr + ')';
            }

            // 우편번호와 주소 정보를 해당 필드에 넣는다.
            document.getElementById('postcode').value = data.zonecode;
            document.getElementById("roadAddress").value = roadAddr;
            document.getElementById("jibunAddress").value = data.jibunAddress;
            
            // 참고항목 문자열이 있을 경우 해당 필드에 넣는다.
            if(roadAddr !== ''){
                document.getElementById("extraAddress").value = extraRoadAddr;
            } else {
                document.getElementById("extraAddress").value = '';
            }

            var guideTextBox = document.getElementById("guide");
            // 사용자가 '선택 안함'을 클릭한 경우, 예상 주소라는 표시를 해준다.
            if(data.autoRoadAddress) {
                var expRoadAddr = data.autoRoadAddress + extraRoadAddr;
                guideTextBox.innerHTML = '(예상 도로명 주소 : ' + expRoadAddr + ')';
                guideTextBox.style.display = 'block';

            } else if(data.autoJibunAddress) {
                var expJibunAddr = data.autoJibunAddress;
                guideTextBox.innerHTML = '(예상 지번 주소 : ' + expJibunAddr + ')';
                guideTextBox.style.display = 'block';
            } else {
                guideTextBox.innerHTML = '';
                guideTextBox.style.display = 'none';
            }
        }
    }).open();
}
</script>

<br />
<jsp:include page="/include/bottom.jsp" />