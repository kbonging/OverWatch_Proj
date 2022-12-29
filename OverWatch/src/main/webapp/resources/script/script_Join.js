/**
 * 
 */

$(function(){
	
	///////////////////////////////////////////////////////////////////////////
	/*///////   회원가입(/member/joinAgreement.jsp) 동의 시작   //////*/
	///////////////////////////////////////////////////////////////////////////
	
	/* 회원가입 동의 체크박스 전체 선택 시작   /member/joinAgreement.jsp 	 */
		
	// 정방향 전체 체크 적용		
	$(".joinAgree input#chkAll").click(function(){
		
		let boolChk = $(this).prop("checked");
		//alert("boolChk : " + boolChk);
		// boolean => true/false
		// attr => attribute, 애트리뷰트, 속성
		// prop => property, 프라퍼티, 속성		
		$(".joinAgree  div.termArea input[type=checkbox]").prop("checked", boolChk);
		
	});
	
	
	// 역방향 전체 체크 적용
	$(".joinAgree .termArea input[type=checkbox]").click(function(){
		
		let  boolChk = false;				
		//let idx = $(this).attr("data-link");
		//alert("idx : " + idx);
		let chk0 =$(".joinAgree .termArea").eq(0).find("input").prop("checked");
		let chk1 =$(".joinAgree .termArea").eq(1).find("input").prop("checked");
		let chk2 =$(".joinAgree .termArea").eq(2).find("input").prop("checked");
		
		// .eq(인덱스번호)  => 형제요소들의 인덱스번호에 해당하는 대상을 선택, eq = equal
		//let str = "chk0 : " + chk0 + "\nchk1 : " + chk1 + "\nchk2 : " + chk2;
		//alert(str);
		
		if (chk0 && chk1 && chk2) {
			boolChk = true;    // 3개의 약관 모두 체크 되었을 때 실행됨.
		}
		
		$(".joinAgree input#chkAll").prop("checked", boolChk);
	});
	
	/* 회원가입 동의 체크박스 전체 선택 끝  	/member/joinAgreement.jsp 	 */
	
	
	/* 이용약관 form 실행 시작 */
	$("form#joinFrm button").click(function(){
		//$("form#joinFrm").action="/member/member.jsp";
		// 점검
		let chkReq0 = $(".joinAgree .termArea").eq(0).find("input").prop("checked");
		let chkReq1 = $(".joinAgree .termArea").eq(1).find("input").prop("checked");
		if (chkReq0 == false) {
			alert("이용약관 동의를 체크해주세요");
			$(".joinAgree .termArea").eq(0).find("input").css({"outline": "1px solid #08f"});
			$(".joinAgree .termArea").eq(0).find("input").focus();
		} else if(chkReq1 == false) {			
			alert("개인정보 수집 및 이용 동의를 체크해주세요");
			$(".joinAgree .termArea").eq(1).find("input").css({"outline": "1px solid #08f"});
			$(".joinAgree .termArea").eq(1).find("input").focus();
		} else {
			$("form#joinFrm").submit();			
		}
		
	});
	
	/* 이용약관 form 실행 끝 */
		
	///////////////////////////////////////////////////////////////////////////
	/*//////////   회원가입(/member/joinAgreement.jsp) 동의 끝   /////////*/
	///////////////////////////////////////////////////////////////////////////
	
	
	
	///////////////////////////////////////////////////////////////////////////
	/*//////////   회원가입 입력(/member/member.jsp)  시작   /////////*/
	///////////////////////////////////////////////////////////////////////////
	
	// 필수입력 영역 배경색 적용
	$("#regFrmTbl td.req").parent("tr").css({
		"background-color": "rgba(128, 128, 128, 0.07)"
	});
	
	// 선택된 Email 도메인 자동입력  
	$("#regFrm select#emailDomain").change(function(){
		// change 이벤트 핸들러 => value값이 변경되었을때 인식하는 이벤트
		let emailDomain = $(this).val().trim();
		$("input#uEmail_02").val(emailDomain);
		if (emailDomain == "") {
			$("input#uEmail_02").focus();
		} else {
			$("input#uEmail_01").focus();
		}
	});
	
	
	// 아이디 중복체크 팝업 
	$("#regFrm button#idChkBtn").click(function(){
		
		let uId = $("#uId").val().trim();    // <input id="uId">의 값 반환
		//alert("uId.length : " + uId.length);
		$("#uId").val(uId);
		$("#checkuId").val(uId);
		
		// 정규표현식 시작
		let regExp = /[^a-z|A-Z|0-9]/g;
		let rExpRes = regExp.test(uId);   // 정규표현식에 부합하면 true
														   // 정규표현식에 부합하면지 않으면 false
		//alert("rExpRes : " + rExpRes);	
		// 정규표현식 끝		
		
		
		if (uId == "") {    // 전체 공백 체크
			
			alert("아이디를 입력해주세요");
			$("#uId").focus();
			
		} else if (uId.length < 3) {    // 글자수 체크
		
			alert("아이디는 최소 3글자 이상입니다.");
			$("#uId").focus();
			
		} else if (rExpRes) {    // 정규표현식 체크
		
			alert("영어대/소문자, 숫자 조합만 가능합니다.");
			$("#uId").focus();
			
		} else {			
			
			let url = "/idCheck?uId=" + uId;
			let nickName = "idChkPop";
	
			let w = screen.width;     // 1920
			let popWidth = 480;
			let leftPos = (w - popWidth) / 2; // left Position 팝업창 왼쪽 시작위치
	
			let h = screen.height;    // 1080
			let popHeight = 320;
			let topPos = (h - popHeight) / 2; 		
			
	
			let prop = "width="+ popWidth +", height="+ popHeight;
				  prop += ", left=" + leftPos + ", top=" + topPos; 
			window.open(url, nickName, prop);
			
			// 사용 예 : 팝업창의 가로폭 200px, 높이 100px 이며
			//            화면의 왼쪽에서 300px, 위쪽에서 400px 에 위치한 곳에 팝업창 출력
			//             =>  window.open("파일명", "닉네임", 
			//                      "width=200, height=100, left=300, top=400")          
		}
		
		
	});
	
	// 아이디 중복체크 팝업창 닫기
	$("div#closeBtnArea>button").click(function(){
		window.close();
		opener.regFrm.uId.focus();
		
		$("#yn", opener.document).val($("#yn").val());
		
		// DOM으로 접근
		// opener객체는 팝업창을 실행한 부모페이지를 지칭함.
	});
	
	
	
	
	// 비밀번호 표시하기 
	$("#wrap #regFrmTbl input#pwView").click(function(){
		let chkTF = $(this).prop("checked");     ;     // YN => Yes or No,   TF => True or False
		//alert("chkTF : " + chkTF);
		if (chkTF) {
			$("input#uPw").attr("type", "text");
		} else {
			$("input#uPw").attr("type", "password");
		}
	});
	
	// 비밀번호 / 비밀번호 확인 동일성 검사   +  유효성검사(정규표현식)
	$("#uPw_Re").keyup(function(){ 
		let uPw = $("#uPw").val();
		let uPw_Re = $("#uPw_Re").val();
		
		// after( )메서드는 선택자로 지정된 요소 다음에 생성되는 요소를 만드는 기능
		if (uPw != uPw_Re) {
			$("span#pwChk").text("비밀번호가 다릅니다.");
		} else {
			$("span#pwChk").text("");
		}
		
	});
	
	
	
	// 이름 유효성 검사(정규표현식) : 공백처리, 한글, 영어대소, 숫자, 마침표, 밑줄, 대시기호(-)
	// 그냥 한글만으로 실습해봄. => 위의 모든 정규표현식을 적용해봄 
	// 한글 정규표현식 =>  /[ㄱ-ㅎ|ㅏ-ㅣ|가-힣]/
	
	
	// Email 유효성 검사 => 공백검사만!
	$("#emailAuthBtn").click(function(){
		
		let uEmail_01 = $("#uEmail_01").val().trim();
		let uEmail_02 = $("#uEmail_02").val().trim();
		
		if (uEmail_01 == "") {
			alert("이메일 주소를 확인해주세요");
			$("#uEmail_01").focus();			
		} else if (uEmail_02 == "") {
			alert("이메일 주소를 확인해주세요");
			$("#uEmail_02").focus();
		} else {
			$("#emailAuthArea").css({"display": "block"});
		}
		
		
	});
	
	
	// 우편번호 찾기 팝업 
	$("#regFrm button#findZipBtn").click(function(){
		
		let url = "/zipCheck";
		let nickName = "zipChkPop";

		let w = screen.width;     // 1920
		let popWidth = 640;
		let leftPos = (w - popWidth) / 2; // left Position 팝업창 왼쪽 시작위치

		let h = screen.height;    // 1080
		let popHeight = 400;
		let topPos = (h - popHeight) / 2; 		
		
		let prop = "width="+ popWidth +", height="+ popHeight;
			  prop += ", left=" + leftPos + ", top=" + topPos; 
		window.open(url, nickName, prop);
		
	});
	
	
	// 우편번호 팝업창에서 주소 검색
	$("#addrSearchBtn").click(function(){
		let area3 = $("#area3").val().trim();
		if (area3 == "") {
			alert("검색어를 입력해주세요.");
			$("#area3").focus();
		} else {
			$("#zipFrm").submit();
		}
	});
	
	
	// 우편번호 팝업창에서 주소 선택
	$("table#zipResTbl td").click(function(){
		let txtAddr = $(this).children("span").text();
		let zipcode = txtAddr.substring(0, 7);
		let addr = txtAddr.substring(8);
		window.opener.uZipcode.value = zipcode;
		window.opener.uAddr.value = addr;
		window.close();
	});
	
				
					
	
	/* 회원가입 및 회원수정 버튼 전송 실행 */	
	$("#joinSbmBtn").click(function(){		
		fnJoinSbm();		
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
		if (code == 13) fnJoinSbm();
    });

	function fnJoinSbm() {
		
		let uId = $("#uId").val();
		$("#uId").val(uId);
		let uPw = $("#uPw").val().trim();		
		$("#uPw").val(uPw);		
		let uPw_Re = $("#uPw_Re").val().trim();	
		let uName = $("#uName").val().trim();
		$("#uName").val(uName);
		let uEmail_01 = $("#uEmail_01").val().trim();
		let uEmail_02 = $("#uEmail_02").val().trim();
		$("#uEmail").val(uEmail_01+"@"+uEmail_02);
		let uBirthday = $("#uBirthday").val().trim();
		let checkuId = $("#checkuId").val().trim();
		let yn = $("#yn").val().trim();
		let gender = $("input[type=radio]:checked").val();
		
		if(gender == null){
			gender = "";
		}else{
			gender = $("input[type=radio]:checked").val();
		}
		
		if (uId == "") {
			alert("아이디를 입력해주세요.");
			$("#uId").focus();
			return;
		} else if (uPw == "") {
			alert("비밀번호를 입력해주세요.");
			$("#uPw").focus();
			return;
		} else if (uPw_Re == "" || uPw != uPw_Re) {
			alert("비밀번호 일치여부를 확인해주세요.");
			$("#uPw_Re").focus();
			return;
		} else if (uName == "") {
			alert("이름을 입력해주세요.");
			$("#uName").focus();
			return;
		} else if (uEmail_01 == "") {
			alert("이메일 주소를 입력해주세요.");
			$("#uEmail_01").focus();
			return;
		} else if (uEmail_02 == "") {
			alert("이메일 주소를 입력해주세요.");
			$("#uEmail_02").focus();
			return;
		} else if (uBirthday != "" && isNaN(uBirthday)) {
			// 생년월일 숫자유효성 검사
			alert("생년월일은 숫자만 입력할 수 있습니다.");
			$("#uBirthday").val("").focus();
			return;
		} else {
		
			if($("#memEdit").val() == '수정'){
				let chkSbmTF = confirm("회원수정하시겠습니까?");
				
				if (chkSbmTF) {
					if(uId != checkuId){
						checkuId = uId;
					}
					
					$("#regFrm").attr("action", "/memberMod");
					$("#regFrm").submit();
				}
			}else{
				let chkSbmTF = confirm("회원가입하시겠습니까?");
				
				if (chkSbmTF) {
					 if(uId != checkuId ){ 
						alert("아이디 중복체크를 해주세요.");
						$("#uId").focus();
						return;
					}else if(yn == 'N'){
						alert("이미 가입된 아이디입니다.");
						$("#uId").focus();
						return;
					}else{
						$("#regFrm").attr("action", "/member?gender="+gender);
						$("#regFrm").submit();
					}
				}//if	
			}//else
			
		}//else
		 
	}//function
    
	
	
	
	
	///////////////////////////////////////////////////////////////////////////
	/*//////////   회원가입 입력(/member/member.jsp)  끝   /////////*/
	///////////////////////////////////////////////////////////////////////////
	
	/* 회원탈퇴 버튼 전송 실행 */	
	$("#memQuitBtn").click(function(){		
		fnQuitSbm();		
	});
	
	function fnQuitSbm(){
		let chkSbmQu = confirm("회원 탈퇴하시겠습니까?");
		
		if(chkSbmQu){
			$("#memQuitFrm").attr("action","/memberQuitProc");
			$("#memQuitFrm").submit();
		}
	
	};
	
});











