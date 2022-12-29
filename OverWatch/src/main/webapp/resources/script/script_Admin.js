/**
 * 
 */
$(function(){
	
	/* 파일 다운로드 */
	$('#downloadFile').click(function(){
		
		let p1 = $("#num").val(); 
		let p2 = $("#hiddenFname").val();
		
		param = "/download?num="+p1;
		param += "&fileName="+p2;
	
		location.href=param;
	});
	
	
	/* 리스트 페이지 글쓰기 버튼 시작 /bbs/list.jsp */	
	$("#loginAlertBtn").click(function(){		
		alert("로그인 후 게시글을 작성하실 수 있습니다.");
	});	
	
	$("#writeBtn").click(function(){		
		let p3 = $("#pKeyField").val().trim();  // p3 : keyField
	    let p4 = $("#pKeyWord").val().trim();  // p4 : keyWord
		let param = $("#nowPage").val();
	     
		let url = "/bbsWrite?nowPage=" + param;		    
		    url += "&keyField="+p3;
	     	url += "&keyWord="+p4 ; 
		location.href=url;
		
		
		
	});
	/* 리스트 페이지 글쓰기 버튼 끝 /bbs/list.jsp */
	
	
	/* 글쓰기 페이지 게시글 등록 시작 /bbs/write.jsp */
	$("#regBtn").click(function(){
		let subject = $("#subject").val().trim();		
		
		 if (subject == "") {
			alert("제목은 필수입력입니다.");
			$("#subject").focus();
		} else {
			$("#writeFrm").attr("action", "/bbsWrite");
			$("#writeFrm").submit();
		}
	
	});	
	
	/* 글쓰기 페이지 게시글 등록 끝 /bbs/write.jsp */
	
	
	/* 게시글 삭제버튼 시작 /bbs/read.jsp */
	$("button#delBtn").click(function(){
		
		let chkTF = confirm("게시글을 삭제하시겠습니까?");
		
		if (chkTF) {
			let nowPage = $("input#nowPage").val().trim();
			let num = $("input#num").val().trim();
			
					
			let p3 = $("#pKeyField").val().trim();  // p3 : keyField
		    let p4 = $("#pKeyWord").val().trim();  // p4 : keyWord
		    
			let url = "/deleteProc?";
				url += "num="+num+"&nowPage="+nowPage;
				url += "&keyField="+p3;
				url += "&keyWord="+p4;
			location.href=url;
		} else {
			alert("취소하셨습니다.");	
		}
		
	});
	/* 게시글 삭제버튼 끝 /bbs/read.jsp */
	
	
	
	/* 회원 상세 정보 페이지에서 수정버튼 시작 /admin/memRead.jsp */
	$("#modBtn").click(function(){
	
		let nowPage = $("input#nowPage").val().trim();
		let num = $("input#num").val().trim();
				
		let p3 = $("#pKeyField").val().trim();  // p3 : keyField
	    let p4 = $("#pKeyWord").val().trim();  // p4 : keyWord
	
		let url = "/memMod?";
			url += "num="+num;
			url += "&nowPage="+nowPage;
			url += "&keyField="+p3;
	     	url += "&keyWord="+p4; 
	     	url += "&gnbParam=adminPage";
		location.href=url;
	});
	
	/* 회원 상세 정보 페이지에서 수정버튼 끝 /admin/memRead.jsp */
	
	
	/* 회원 수정 페이지에서 수정내용 전송 시작 /admin/memMod.jsp */
	$("td.update>button#modProcBtn").click(function(){
		let subject = $("#subject").val().trim();
		
		//let keyField = $("#keyField").val().trim();
		//let keyWord = $("#keyWord").val().trim();
		
		if (subject == "") {
			alert("제목은 필수입력입니다.");
			$("#subject").focus();
		} else {
		
			//alert("keyField : " + keyField + "\nkeyWord : " + keyWord);
			//return;
			$("#modifyFrm").submit();
		}
	
	});	
	/* 회원 수정 페이지에서 수정내용 전송 끝 /admin/memMod.jsp */
	
	
			
	/* 게시글 내용보기페이지에서 답변버튼 시작 /bbs/read.jsp */
	$("td.read>button#replyBtn").click(function(){
		
		let sessionuId = $("input#sessionuId").val();
	
		if (sessionuId == null || sessionuId == "") {
			alert("로그인 후 이용해주세요");
			return;
		}
	
		let nowPage = $("input#nowPage").val().trim();
		let num = $("input#num").val().trim();
				
		let p3 = $("#pKeyField").val().trim();  // p3 : keyField
	    let p4 = $("#pKeyWord").val().trim();  // p4 : keyWord
	
		let url = "/reply?";
			url += "num="+num;
			url += "&nowPage="+nowPage;
			url += "&keyField="+p3;
	     	url += "&keyWord="+p4; 
	     	url += "&gnbParam=adminPage";
		location.href=url;
	
	});
	/* 게시글 내용보기페이지에서 답변버튼 끝 /bbs/read.jsp */
	
	
	/* 답변입력양식 페이지에서 답변내용 전송 시작 /bbs/reply.jsp */
	$("td.reply>button#replyBtn").click(function(){
		
		let subject = $("#subject").val().trim();
		
		if (subject == "") {
			alert("제목은 필수입력입니다.");
			$("#subject").focus();
		} else {		
			$("#replyFrm").submit();
		}
		
	});
	/* 답변입력양식 페이지에서 답변내용 전송 끝 /bbs/reply.jsp */		
	
	
	
	/* 리스트페이지 검색 시작 /admin/memList.jsp */	
	$("button#searchBtn").click(function(){
		let keyWord = $("#keyWord").val();    // 검색어에서는 .trim()을 지양하는 추세
		                                                      // 단, 로그인, 회원가입, 회원정보 수정에서 사용하는
                                                              // ID 에는 입력값 전후의 공백을 제거한다. 
		//alert("keyWord : " + keyWord +"\nkeyWord 글자수 : " + keyWord.length);
		if (keyWord=="") {
			alert("검색어를 입력해주세요.");
			$("#keyWord").focus();			
		} else {
			$("#searchFrm").submit();
		}
	});	
	/* 리스트페이지 검색 끝  /admin/memList.jsp  */	
	
	
	/* 검색 결과를 유지한 리스트페이지 이동 시작 /admin/memList.jsp */
	$("#listBtn").click(function(){
		let param = $("#nowPage").val();
		let p3 = $("#pKeyField").val();  // p3 : keyField
	    let p4 = $("#pKeyWord").val();  // p4 : keyWord
	     
		let url = "/memList?nowPage=" + param;		    
		    url += "&keyField="+p3;
	     	url += "&keyWord="+p4; 
	     	url += "&gnbParam=adminPage"; 
		location.href=url;
	});
	/* 검색 결과를 유지한 리스트페이지 이동 끝 /admin/memList.jsp */
	
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
    
   		/*수정페이지에서 뒤로가기 이동 시작 */
	$("#backBtn").click(function(){
		 history.back();
	});
	/*수정페이지에서 뒤로가기 이동 끝 */

	function fnJoinSbm() {
		
		let uId = $("#uId").val();
		$("#uId").val(uId);
		let uName = $("#uName").val();
		$("#uName").val(uName);
		let uEmail_01 = $("#uEmail_01").val();
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
		}else if (uName == "") {
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
					
					$("#regFrm").attr("action", "/memMod");
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
	
	
	/*//////////   관리자계정으로 회원탈퇴 페이지로 이동  /////////*/
	$("#quitBtn").click(function(){
		let uId = $("#uId").val();
	
		location.href="/memQuit?uId="+uId;
	});
	
	/*//////////   관리자계정으로 회원탈퇴 버튼 전송 실행  /////////*/
	
	$("#memQuitBtn").click(function(){		
		fnQuitSbm();		
	});
	
	function fnQuitSbm(){
		let chkSbmQu = confirm("관리자 권한으로 회원 탈퇴하시겠습니까?");
		
		if(chkSbmQu){
			$("#memQuitFrm").attr("action","/memQuitProc");
			$("#memQuitFrm").submit();
		}
	
	};
	
	
});
	
	
/* 상세내용 보기 페이지 이동 시작 /admin/memList.jsp => memRead.jsp */
function read(p1, p2, p5) {

	// p1 : num (게시글의 고유번호, 고유값, 키값 : Key Value)
	// p2 : nowPage (현재페이지)
	// p5 : delCheck (삭제여부)
	
	if(p5 == 'Y'){
		return;
	}
	
    let p3 = $("#pKeyField").val().trim();  // p3 : keyField
    let p4 = $("#pKeyWord").val().trim();  // p4 : keyWord
	let param = "/memRead?num="+p1;
	     param += "&nowPage="+p2;
	     param += "&keyField="+p3;
	     param += "&keyWord="+p4; 
	     param += "&gnbParam=adminPage"; 
	     
	location.href=param;
}		
/* 상세내용 보기 페이지 이동 끝 /bbs/list.jsp => read.jsp  */



/* 리스트페이지 페이징 시작 /bbs/list.jsp */
function movePage(p1) {    // 페이지 이동
	
    let p3 = $("#pKeyField").val().trim();  // p3 : keyField
    let p4 = $("#pKeyWord").val().trim();  // p4 : keyWord

	let param = "/memList?nowPage="+p1;	    
	     param += "&keyField="+p3;
	     param += "&keyWord="+p4; 
	     param += "&gnbParam=adminPage"; 
	location.href= param;

}
/* 리스트페이지 페이징 끝 /bbs/list.jsp */


/* 리스트페이지 페이징 블럭이동 시작 /bbs/list.jsp */
function moveBlock(p1, p2, param3) {    
	                // 이전 블럭 또는 이후 블럭으로 이동  p1 => nowBlock-1  또는 nowBlock+1

	let moveBlock = parseInt(p1);    // 이전 블럭의 시작페이지로 이동에 사용하는 소
	let pagePerBlock = parseInt(p2);	
	//alert("p1(moveBlock) : " + p1 + "\np2(pagePerBlock) : " + p2);
	
    let p3 = $("#pKeyField").val().trim();  // p3 : keyField
    let p4 = $("#pKeyWord").val().trim();  // p4 : keyWord
	
	if (param3 == 'pb') {
		 param = "/memList?nowPage="+(moveBlock*pagePerBlock);
	                                                          // moveBlock : nowBlock - 1 
	     param += "&keyField="+p3;
	     param += "&keyWord="+p4;
	     param += "&gnbParam=adminPage";
	} else if (param3 == 'nb' ) {		
		 param = "/memList?nowPage="+(pagePerBlock*(moveBlock-1)+1);   
	                                                          // moveBlock : nowBlock + 1 
	     param += "&keyField="+p3;
	     param += "&keyWord="+p4;
	     param += "&gnbParam=adminPage";
	}

	location.href=param;
}
/* 리스트페이지 페이징 블럭이동 끝 /bbs/list.jsp */




