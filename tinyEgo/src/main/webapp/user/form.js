/**
 * 
 */
function funcE(str) {
	if (str==='S') {
		editF.sw.value="S";
		editF.action="UserController";
	} else if (str==='U') {
		editF.sw.value="U";
		editF.action="UserController";
	} else if (str==='D') {
		if (confirm("정말로 삭제하시겠습니까?")) {
			editF.sw.value="D";
			editF.action="UserController";
		} else {
			return false;
		}
	}
	editF.submit();
}