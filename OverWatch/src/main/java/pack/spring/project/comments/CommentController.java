package pack.spring.project.comments;

import java.util.Map;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CommentController {

	@Autowired
	CommentsService commentsService;

	//댓글 등록 처리 시작
	@RequestMapping(value = "/comments", method = RequestMethod.POST)
	@ResponseBody
	public String insert_comments(@RequestParam Map<String, Object> map) {
		System.out.println("/comments_post map : "+map.toString());

		String uId = (String) map.get("sessionuId");
		map.put("uId", uId);

		Map<String, Object> maxMap =  commentsService.select_maxNum();

		System.out.println(maxMap.toString());
		int ref = 1;
		int maxNum = Integer.parseInt(maxMap.get("max(num)").toString()) ;
		if(maxNum > 0) {
			ref = maxNum +1;
		}
		System.out.println("ref = " + ref);

		map.put("ref", ref);
		int cmNum = commentsService.insert_comments(map);
		
		JSONObject jsonObject = new JSONObject();
		
		return jsonObject.toString();
	}
	//댓글 등록 처리 끝
	
	
	//댓글 삭제 처리 시작
	@RequestMapping(value = "/deleteCProc", method = RequestMethod.GET)
	@ResponseBody
	public String delete_comments(@RequestParam Map<String, Object> map) {
		System.out.println("/deleteCProc map :  "+map.toString());
		
		String num = map.get("commentNo").toString();
		map.put("num", num);
		
		int cnt = commentsService.delete_comments(map);
		
		JSONObject jsonObject = new JSONObject();
		
		return jsonObject.toString();
	}
	
	//댓글 삭제 처리 끝

	// 대댓글 등록 시작
	@RequestMapping(value = "/CommentReplyProc", method = RequestMethod.POST)
	@ResponseBody
	public String reply_commnets(@RequestParam Map<String, Object> map) {
		System.out.println("/CommentReplyProc map : "+map.toString());
		
		  int repUpCnt = 0;
		  
		  if(commentsService.update_replyUpComments(map) !=0) {
			  repUpCnt = commentsService.update_replyUpComments(map); 
			  }   //끼어들기
		 
		  int cnt =commentsService.insert_relpyComments(map);
		  
		  
		JSONObject jsonObject = new JSONObject();
		
		return jsonObject.toString();
	}
	
	
	// 대댓글 등록 끝
		
}
