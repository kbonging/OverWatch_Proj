/**
 * 
 */
 $(function(){
 	
 	$(".reply").hide();
 	
 	$(".replyBtn").click(function(){
 		 	$(this).parent().next(".reply").slideToggle(".reply");
 	});
 	
 	/* 글 상세 목록에서 댓글등록 버튼 클릭 시작 */
	$("#Cbtn").click(function(){
		let content =  $("#content").val();
		
		let sessionuId = $("#sessionuId").val();
		
		if (sessionuId == null || sessionuId == "" ) {
			alert("로그인 후에 이용해주세요");
			return;
		} else if(content == null || content==""){
			alert("내용을 입력하세요.");
			$("#content").focus();
			return;
		}else{
		
			$.ajax({
          
             type:"POST",
             url:"/comments",
             data:{
                "boardNo":$("#boardNo").val().trim(),
                "content":$("#content").val().trim(),
                "sessionuId":$("#sessionuId").val().trim()
             },
             dataType:"JSON",
             
             success:function(data) {
                     location.reload();
              },
                error:function(request,status,error){
                 alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
                }
                
          }); //ajax 끝
			
		};
	
	});
	/* 글 상세 목록에서 댓글등록 버튼 클릭 끝 */
	
	
	
	
		
		
 }); // $(function() 끝
 
	/* 글 상세 목록에서 댓글삭제 버튼 시작 */	
function del(param){
	let delChk =  confirm("댓글을 삭제하시겠습니까?");
		
		if (delChk) {
			$.ajax({
          
             type:"GET",
             url:"/deleteCProc",
             data:{
                "commentNo":param
             },
             dataType:"JSON",
             
             success:function(data) {
                     location.reload();
              },
                error:function(request,status,error){
                 alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
                }
                
          }); //ajax 끝
		
		}else{
		
			
		};
}
	/* 글 상세 목록에서 댓글삭제 버튼 끝 */	
	
	
	/* 글 상세 목록에서 답변등록 버튼 시작 */	
	function Commentreply(p1, p2, p3, p4, p5){
		
		
		let content = $('.commentReply'+p1).val();
		let sessionuId = $("#sessionuId").val();
		
		 if (sessionuId == null || sessionuId == "" ) {
			alert("로그인 후에 이용해주세요");
			event.preventDefault();
		} else if(content == null || content==""){
			alert("내용을 입력하세요.");
			$('.commentReply'+p1).focus();
			event.preventDefault();
		}else{
			$.ajax({
		          
		             type:"POST",
		             url:"/CommentReplyProc",
		             data:{
		                "content":$('.commentReply'+p1).val().trim(),
		                 "boardNo":$("#boardNo").val().trim(),
		                 "num":p1,
		                 "pos":p2,
		                 "ref":p3,
		                 "depth":p4,
		                 "uId":p5
		             },
		             dataType:"JSON",
		             
		             success:function(data) {
		                     location.reload();
		              },
		                error:function(request,status,error){
		                 alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
		                }
		                
	          }); //ajax 끝
		} //else
	}
	/* 글 상세 목록에서 답변등록 버튼 끝 */	










