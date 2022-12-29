 /**
 * 
 */

$(function(){
	/*아이디 포커스*/
	$("#uId").focus();
	
	/* 로그인 버튼 전송 실행 */	
	$("#loginBtn").click(function(){		
		fnLoginSbm();		
	});
	
	/* 폼실행 엔터키 이벤트 처리 */	
	$(window).keydown(function(){
		let code = event.keyCode;
		if (code == 13) return false;
	});
	
	/* 폼실행 엔터키 이벤트 처리 */
	$(window).keyup(function(){		
		let code = event.keyCode;
		//alert("code : " + code);
		if (code == "13") fnLoginSbm();
    });
	
	
	function fnLoginSbm() {
		
		let uId = $("#uId").val().trim();
		$("#uId").val(uId);
		let uPw = $("#uPw").val().trim();		
		$("#uPw").val(uPw);
		
		
		if (uId == "") {
			alert("아이디를 입력해주세요.");
			$("#uId").focus();
			return;
		} else if (uPw == "") {
			alert("비밀번호를 입력해주세요.");
			$("#uPw").focus();
			return;
		} else {			
			$("#loginFrm").attr("action", "/login");
			$("#loginFrm").submit();
		}
		
		
	}
	
});