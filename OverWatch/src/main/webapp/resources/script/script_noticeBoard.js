/**
 * 
 */
$(function(){
	
	/* 파일 다운로드 */
	$('#downloadFile').click(function(){
		
		let p1 = $("#num").val(); 
		let p2 = $("#hiddenFname").val();
		
		param = "/noticedownload?num="+p1;
		param += "&fileName="+p2;
	
		location.href=param;
	});
	
	
	/* 리스트 페이지 글쓰기 버튼 시작 /noticeBoard/list.jsp */	
	$("#loginAlertBtn").click(function(){		
		alert("로그인 후 게시글을 작성하실 수 있습니다.");
	});	
	
	$("#writeBtn").click(function(){		
		let p3 = $("#pKeyField").val().trim();  // p3 : keyField
	    let p4 = $("#pKeyWord").val().trim();  // p4 : keyWord
		let param = $("#nowPage").val();
	     
		let url = "/noticeWrite?nowPage=" + param;		    
		    url += "&keyField="+p3;
	     	url += "&keyWord="+p4; 
	     	url += "&gnbParam=bbs"; 
		location.href=url;
		
		
		
	});
	/* 리스트 페이지 글쓰기 버튼 끝 /noticeBoard/list.jsp */
	
	
	/* 글쓰기 페이지 게시글 등록 시작 /noticeBoard/write.jsp */
	$("#regBtn").click(function(){
		let subject = $("#subject").val().trim();		
		
		 if (subject == "") {
			alert("제목은 필수입력입니다.");
			$("#subject").focus();
		} else {
			$("#writeFrm").attr("action", "/noticeWrite");
			$("#writeFrm").submit();
		}
	
	});	
	
	/* 글쓰기 페이지 게시글 등록 끝 /noticeBoard/write.jsp */
		
	/* 게시글 내용보기페이지에서 수정버튼 시작 /noticeBoard/read.jsp */
	$("td.read>button#modBtn").click(function(){
	
		let nowPage = $("input#nowPage").val().trim();
		let num = $("input#num").val().trim();
				
		let p3 = $("#pKeyField").val().trim();  // p3 : keyField
	    let p4 = $("#pKeyWord").val().trim();  // p4 : keyWord
	
		let url = "/noticemodify?";
			url += "num="+num;
			url += "&nowPage="+nowPage;
			url += "&keyField="+p3;
	     	url += "&keyWord="+p4; 
	     	url += "&gnbParam=bbs"; 
		location.href=url;
	});
	
	/* 게시글 내용보기페이지에서 수정버튼 끝 /noticeBoard/read.jsp */
	
	
	/* 게시글 수정페이지에서 수정내용 전송 시작 /noticeBoard/modify.jsp */
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
	/* 게시글 수정페이지에서 수정내용 전송 끝 /noticeBoard/modify.jsp */
	
	
	/* 리스트페이지 검색 시작 /noticeBoard/list.jsp */	
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
	/* 리스트페이지 검색 끝 /noticeBoard/list.jsp */	
	
	
	/* 검색 결과를 유지한 리스트페이지 이동 시작 /noticeBoard/read.jsp */
	$("#listBtn").click(function(){
		let param = $("#nowPage").val();
		let p3 = $("#pKeyField").val();  // p3 : keyField
	    let p4 = $("#pKeyWord").val();  // p4 : keyWord
	     
		let url = "/noticelist?nowPage=" + param;		    
		    url += "&keyField="+p3;
	     	url += "&keyWord="+p4; 
	     	url += "&gnbParam=bbs"; 
		location.href=url;
	});
	/* 검색 결과를 유지한 리스트페이지 이동 끝 /noticeBoard/read.jsp */
	
		/*수정페이지에서 뒤로가기 이동 시작 */
	$("#backBtn").click(function(){
		 history.back();
	});
	/*수정페이지에서 뒤로가기 이동 끝 */
	
});
	
	
/* 상세내용 보기 페이지 이동 시작 /noticeBoard/list.jsp => read.jsp */
function read(p1, p2, p5) {

	// p1 : num (게시글의 고유번호, 고유값, 키값 : Key Value)
	// p2 : nowPage (현재페이지)
	// p5 : delCheck (삭제여부)
	
	if(p5 == 'Y'){
		return;
	}
	
    let p3 = $("#pKeyField").val().trim();  // p3 : keyField
    let p4 = $("#pKeyWord").val().trim();  // p4 : keyWord
	let param = "/noticeread?num="+p1;
	     param += "&nowPage="+p2;
	     param += "&keyField="+p3;
	     param += "&keyWord="+p4; 
	     param += "&gnbParam=bbs"; 
	location.href=param;
}		
/* 상세내용 보기 페이지 이동 끝 /noticeBoard/list.jsp => read.jsp  */



/* 리스트페이지 페이징 시작 /noticeBoard/list.jsp */
function movePage(p1) {    // 페이지 이동
	
    let p3 = $("#pKeyField").val().trim();  // p3 : keyField
    let p4 = $("#pKeyWord").val().trim();  // p4 : keyWord

	let param = "/noticelist?nowPage="+p1;	    
	     param += "&keyField="+p3;
	     param += "&keyWord="+p4; 
	     param += "&gnbParam=bbs"; 
	location.href= param;

}
/* 리스트페이지 페이징 끝 /noticeBoard/list.jsp */


/* 리스트페이지 페이징 블럭이동 시작 /noticeBoard/list.jsp */
function moveBlock(p1, p2, param3) {    
	                // 이전 블럭 또는 이후 블럭으로 이동  p1 => nowBlock-1  또는 nowBlock+1

	let moveBlock = parseInt(p1);    // 이전 블럭의 시작페이지로 이동에 사용하는 소
	let pagePerBlock = parseInt(p2);	
	//alert("p1(moveBlock) : " + p1 + "\np2(pagePerBlock) : " + p2);
	
    let p3 = $("#pKeyField").val().trim();  // p3 : keyField
    let p4 = $("#pKeyWord").val().trim();  // p4 : keyWord
	
	if (param3 == 'pb') {
		 param = "/noticelist?nowPage="+(moveBlock*pagePerBlock);
	                                                          // moveBlock : nowBlock - 1 
	     param += "&keyField="+p3;
	     param += "&keyWord="+p4 ;
	} else if (param3 == 'nb' ) {		
		 param = "/noticelist?nowPage="+(pagePerBlock*(moveBlock-1)+1);   
	                                                          // moveBlock : nowBlock + 1 
	     param += "&keyField="+p3;
	     param += "&keyWord="+p4 ;
	}

	location.href=param;
}
/* 리스트페이지 페이징 블럭이동 끝 /noticeBoard/list.jsp */




