package pack.spring.project.board;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import pack.spring.project.comments.CommentsService;
import pack.spring.project.common.PageVO;
import pack.spring.project.member.MemberService;

@Controller
public class BoardController {
	//pc
//	private static final String SAVEFOLER = "C:/Users/apple/git/OverWatch_Proj/OverWatch/src/main/webapp/resources/fileUpload"; 
	
	//laptop
	private static final String SAVEFOLER = "C:/Users/bong/git/OverWatch_Proj/OverWatch/src/main/webapp/resources/fileUpload";
//	private static final String SAVEFOLER = "C:/Users/EZEN202/git/Project_OverWatch/Project_OverWatch/src/main/webapp/resources/fileUpload";
	//	private static final String SAVEFOLER = "C:/Users/User/git/Project_OverWatch/Project_OverWatch/src/main/webapp/resources/fileUpload";
	private static String encType = "UTF-8";
	private static int maxSize = 5 * 1024 * 1024;
	
	private static Date currentDate = new Date();
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	
	@Autowired
	BoardService boardService;
	
	
	// ????????? ?????? ??????
	@RequestMapping(value = "/list") // session ?????? ,
	public ModelAndView list(@RequestParam Map<String, Object> map, HttpSession session) {
		String sessionuId = (String) session.getAttribute("uId");
		System.out.println("/list - ?????? ??? map : "+map.toString());
		/////////////////////// ????????? ?????? ?????? ??? ??????///////////////////////////
		// ?????????(Paging) = ????????? ???????????? ?????????
		int totalRecord = 0; // ?????? ????????? ???(DB??? ????????? row ??????)
		int numPerPage = 5; // ???????????? ???????????? ????????? ???(=????????? ??????)
		int pagePerBlock = 5; // ????????? ???????????? ????????? ?????? ??????
		int totalPage = 0; // ?????? ????????? ???
		int totalBlock = 0; // ?????? ?????????

		/*
		 * ????????? ???????????? ?????? totalRecord=> 200 ??????????????? numPerPage => 10 pagePerBlock => 5
		 * totalPage => 20 totalBlock => 4 (20/5 => 4)
		 */

		int nowPage = 1; // ?????? (???????????? ?????? ??????) ????????? ??????
		int nowBlock = 1; // ?????? (???????????? ?????? ??????) ??????

		int start = 0; // DB?????? ???????????? ????????? ??? ???????????? ????????? ??????
		int end = 5; // ???????????? ????????? ???????????? ????????????(=????????????) ????????? ??????
		// select * from T/N where... order by ... limit 5, 5;
		// ???????????? 6??? 1~5
		// ??????????????? 5????????? 6??? ????????? ?????? 5???

		int listSize = 0; // 1??????????????? ???????????? ????????? ???
		// ????????? ???????????? ?????? = ????????? 1?????? ????????? 1???

		// ????????? ?????? ????????????
		String keyField = ""; // DB??? ?????????
		String keyWord = ""; // DB??? ?????????
		List<Map<String, Object>> list = null;

		String nowPage2 = (String)map.get("nowPage");
		if(nowPage2 != null ) {
			nowPage = Integer.parseInt(nowPage2);
			start = (nowPage * numPerPage ) - numPerPage;
			end = numPerPage;
		}

		map.put("start", start);
		map.put("end", end);

		System.out.println("/list - db ?????? ???map : " + map.toString());

		if (map.get("keyWord") != null && !map.get("keyWord").toString().equals("")) { // ?????? keyWord??? ?????? ??????
			keyField = map.get("keyField").toString();
			keyWord = map.get("keyWord").toString();
			map.put("keyField", keyField);
			map.put("keyWord", keyWord);
			list = boardService.select_keyWord(map);
			totalRecord = boardService.select_countKey(map);

		} else { // ?????? keyWord??? ?????? ??????
			list = boardService.select_All(map);
			totalRecord = boardService.select_countAll(map);
		}

		Date date = new Date();
		System.out.println(date);
		
		
		
		
		for (int i = 0; i < list.size(); i++) {
			Map<String, Object> userMap = list.get(i);
			String regTM = userMap.get("regTM").toString();
			System.out.println("substring ?????? ??? DB?????? ????????? ??????"+regTM);
			
			regTM = regTM.substring(0,10)+" "+regTM.substring(11);
//			userMap.put("regTM", regTM);
			
			
			
//			regTM =  regTM.substring(0, 10);
			
			try {
				Date d1 = sdf.parse(regTM); // String ????????? ???????????? Date ??? ??? ??????
				System.out.println("????????? : "+d1);
			  	long diff =  (currentDate.getTime() - d1.getTime()) / (1000*60*60*24); // ?????? ?????????????????? ????????? ?????? ???????????? ????????? ????????? ?????? ??????
			  	
			  	String daysAgo = diff+"??????";
			  	
			  	userMap.put("regTM", daysAgo);
			  	
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
			
			int boardNo = (int) userMap.get("num");
			userMap.put("boardNo", boardNo);
			
			
			if(commentsService.select_comCountAll(userMap) > 0) {
				int comCount = commentsService.select_comCountAll(userMap);
				userMap.put("comCount", comCount);
			}
		}//for
		
		
		
		/*
		 * select * from tblBoard order by num desc limit 10, 10; ???????????? 100??? => num : 100
		 * 99 98 97 ... 91 | 90 .... 2 1 start, end : 0 1 2 3.... 9 10 ???????????? ????????? ????????? ???
		 * 10??? ?????? ????????? 1??????????????? => 1???????????? ???????????? 100 ~ 91 2?????????(= nowPage??? 2?????? ??????) 90~81 3?????????
		 * 80~71
		 */
		// ?????? ????????? ??? ??????

		totalPage = (int) Math.ceil((double) totalRecord / numPerPage);
		nowBlock = (int) Math.ceil((double) nowPage / pagePerBlock);
		totalBlock = (int) Math.ceil((double) totalPage / pagePerBlock);
		
		int pageStart = (nowBlock - 1) * pagePerBlock + 1;
		int pageEnd = (nowBlock < totalBlock) ? pageStart + pagePerBlock -1 : totalPage;

		/////////////////////// ????????? ?????? ?????? ??? ???///////////////////////////

		PageVO pageVo = new PageVO(totalRecord, nowPage, totalPage, numPerPage, nowBlock, pagePerBlock, totalBlock, listSize, pageStart, pageEnd);

		System.out.println("/list - db ?????? ??? list : " +list.toString());
		System.out.println("/list - db ?????? ??? map : "+map.toString());
		System.out.println("/list - db ?????? ??? pagaVo : "+pageVo.toString());
		
		
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("sessionuId" , sessionuId);
		mav.addObject("pageVo", pageVo);
		mav.addObject("list", list);
		mav.addObject("map", map);
		mav.setViewName("bbs/list");
		
		return mav;
	} // ????????? ?????? ?????? ???

	@Autowired
	MemberService memberService;

	// ????????? ????????? ????????????
	@RequestMapping(value = "/bbsWrite", method = RequestMethod.GET)
	public ModelAndView bbsWrite(HttpServletRequest request, HttpSession session) {
		String uId = (String) session.getAttribute("uId");
		String ip = request.getRemoteAddr();
		
		System.out.println("/bbsWrite - request : "+ request.getParameter("nowPage").toString());
		System.out.println("/bbsWrite - request : "+ request.getParameter("keyField").toString());
		System.out.println("/bbsWrite - request : "+ request.getParameter("keyWord").toString());
		System.out.println("/bbsWrite - request : "+ request.getParameter("gnbParam").toString());
		
		String nowPage = request.getParameter("nowPage").toString();
		String keyField =request.getParameter("keyField").toString();
		String keyWord =request.getParameter("keyWord").toString();
		String gnbParam=request.getParameter("gnbParam").toString();
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("uId", uId);
		map.put("nowPage", nowPage);
		map.put("keyField", keyField);
		map.put("keyWord", keyWord);
		map.put("gnbParam", gnbParam);
		
		
		Map<String, Object> userMap = memberService.selectByUId(map);
		userMap.put("ip", ip);
		
		System.out.println("/bbsWrite - map : "+map.toString());
		System.out.println("/bbsWrite - userMap : "+userMap.toString());
		ModelAndView mav = new ModelAndView();
		mav.addObject("data", userMap);
		mav.addObject("map", map);
		mav.setViewName("/bbs/write");

		return mav;

	} // ????????? ????????? ???????????? ???

	// ??? ?????? ??????
	@RequestMapping(value = "/bbsWrite", method = RequestMethod.POST)
	public ModelAndView bbsWrite_post(@RequestParam Map<String, Object> map, HttpSession session, HttpServletRequest request) throws IOException {
		
//		System.out.println("request : "+request.getParameterNames());
//		String ip = (String)request.getParameter("ip");
		MultipartRequest multi = null;
		int fileSize = 0;
		String fileName =null;
		
		System.out.println("/bbsWrite map : " + map.toString()); 
		// ????????? ?????? ?????? request ??? ??? ????????? ?????? map?????? ???????????? ??????

		Map<String, Object> maxMap =  boardService.select_maxNum();
		
		
		System.out.println(maxMap.toString());
		int ref = 1;
		int maxNum = Integer.parseInt(maxMap.get("max(num)").toString()) ;
		if(maxNum > 0) {
			ref = maxNum +1;
		}
		System.out.println("ref = " + ref);
		
		File file = new File(SAVEFOLER);
		
		System.out.println("/bbsWrite file : "+file.toString());
		
		if(!file.exists()) {
			file.mkdir();
		}
		
			multi = new MultipartRequest(request, SAVEFOLER, maxSize, encType, new DefaultFileRenamePolicy());
			
			if (multi.getFilesystemName("fileName") != null) {
				fileName = multi.getFilesystemName("fileName");
				fileSize = (int) multi.getFile("fileName").length();
			}
			
			String uId = multi.getParameter("uId");
			String uName = multi.getParameter("uName");
			String subject= multi.getParameter("subject");
			String content = multi.getParameter("content");
			String ip = multi.getParameter("ip");
			
			if (multi.getParameter("contentType").equalsIgnoreCase("TEXT")) {
				content = UtilMgr.replace(content, "<", "&lt;");
			}
			
			map.put("uId", uId);
			map.put("uName", uName);
			map.put("subject", subject);
			map.put("content", content);
			map.put("fileName", fileName);
			map.put("fileSize", fileSize);
			map.put("ref", ref);
			map.put("ip", ip);
			
			
		
		/*
		 * Map<String, Object> maxMap = boardService.select_maxNum();
		 * System.out.println(maxMap.toString()); if(!maxMap.isEmpty()) { int num =
		 * (int) maxMap.get("num"); ref = num +1; }
		 */

		System.out.println("????????? ??? map : "+map.toString());
		int bbsNum = boardService.insert_bbs(map);
		System.out.println(bbsNum);

		String msg = "??? ?????? ??????", url = "/bbsWrite";
		if (bbsNum > 0) {
			msg = "?????? ??????";
			url = "/list?gnbParam=bbs";
		}

		ModelAndView mav = new ModelAndView();
		mav.addObject("msg", msg);
		mav.addObject("url", url);
		mav.setViewName("/common/message");
		return mav;
	}// ??? ?????? ?????? ???
	
	
	@Autowired
	CommentsService commentsService;
	
	// ??? ?????? ????????????
	@RequestMapping(value = "/read", method = RequestMethod.GET)
	public ModelAndView read(@RequestParam Map<String, Object> map, HttpSession session) {
		String sessionuId = (String) session.getAttribute("uId");
		
		//????????? ??????
		boardService.upCount(map);
		
		System.out.println("/read  ??? : "+map.toString());
		Map<String, Object> userMap = boardService.selectByNum(map);
		userMap.put("sessionuId", sessionuId);
		
		System.out.println("/read ?????? ??? : "+userMap.toString());
		
		String regTM = userMap.get("regTM").toString();
		regTM = regTM.substring(0,10)+" "+regTM.substring(11);
		userMap.put("regTM", regTM);
		
		int fileSize = 0;
		String mapFileSize = (String) map.get("fileSize");
		
		if (mapFileSize != null) {
			fileSize = Integer.parseInt(mapFileSize);
		}

		map.put("fileSize", fileSize);

		String fUnit = "Bytes";
		if (fileSize > 1024) {
			fileSize /= 1024;
			fUnit = "KBytes";
		}
		
		//////////////////////////////
		
		String boardNo =  map.get("num").toString();
		int kind = 0;
		
		map.put("boardNo", boardNo);
		map.put("kind", kind);
		
		List<Map<String, Object>> commentMapList =  commentsService.select_comments(map);
		
		System.out.println("commentMap : " +commentMapList.toString());
		
		//////////////////////////////////
		
		ModelAndView mav = new ModelAndView();
		if(commentMapList != null) {
			
			for (int i = 0; i < commentMapList.size(); i++) {
				Map<String, Object> commentsMap = commentMapList.get(i);
				String regdate = commentsMap.get("regdate").toString();
				regdate = regdate.substring(0,10)+" "+regdate.substring(11);
				commentsMap.put("regdate", regdate);
			}
			
			mav.addObject("commentMapList", commentMapList);
		}
		
		mav.addObject("fUnit", fUnit);
		mav.addObject("map", map);
		mav.addObject("data", userMap);
		mav.setViewName("/bbs/read");

		return mav;

	}// ??? ?????? ???????????? ???

	@RequestMapping(value = "/modify", method = RequestMethod.GET)
	public ModelAndView modify(@RequestParam Map<String, Object> map) {

		Map<String, Object> userMap = boardService.selectByNum(map);

		System.out.println("modify : " + map.toString());
		System.out.println("modify : " + userMap.toString());
		ModelAndView mav = new ModelAndView();
		mav.addObject("data", userMap);
		mav.addObject("map", map);

		mav.setViewName("/bbs/modify");

		return mav;

	}

	@RequestMapping(value = "/modifyProc", method = RequestMethod.GET)
	public ModelAndView modifyProc(@RequestParam Map<String, Object> map) {
		
		System.out.println("/modifyProc = map : "+map.toString());
		int num = Integer.parseInt(map.get("num").toString());
		
		String nowPage = map.get("nowPage").toString();
		String keyField = map.get("keyField").toString();
		String keyWord = map.get("keyWord").toString();
		
		try {
			keyWord = URLEncoder.encode(keyWord, "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		int board_num = boardService.update_bbs(map);
		System.out.println("board_num : "+board_num);
		ModelAndView mav = new ModelAndView();
		if (board_num > 0) {
			System.out.println("?????? ??????");
			System.out.println(map.get("num").toString());
			
			mav.setViewName("redirect:/read?num="+num+"&nowPage="+nowPage+"&keyField="+keyField+"&keyWord="+keyWord+"&gnbParam=bbs");
		} else {
			System.out.println(map.get("num").toString());
			System.out.println("?????? ??????");

			mav.setViewName("redirect:/modify?num="+num+"&nowPage="+nowPage+"&keyField="+keyField+"&keyWord="+keyWord+"&gnbParam=bbs");
		}

		return mav;
	}

	@RequestMapping(value = "/deleteProc", method = RequestMethod.GET)
	public ModelAndView deleteProc(@RequestParam Map<String, Object> map) {

		int exeCnt = boardService.delete_bbs(map);

		ModelAndView mav = new ModelAndView();
		System.out.println("deleteProc = " + map.toString());

		if (exeCnt > 0) {
			mav.addObject("map", map);
			mav.setViewName("redirect:/list");
		} else {
			mav.addObject("map", map);
			mav.setViewName("redirect:/read");
		}

		return mav;
	}

	@RequestMapping(value = "/reply", method = RequestMethod.GET)
	public ModelAndView reply(@RequestParam Map<String, Object> map, HttpSession session) {
		
		System.out.println("/reply - map :"+map.toString());
		String uId = (String) session.getAttribute("uId"); // ????????? ????????? ?????????
		map.put("uId", uId);

		Map<String, Object> temp = memberService.getMemberName(map); // ????????? ????????? ????????????
		String replyName = (String) temp.get("uName");

		Map<String, Object> newMap = boardService.selectByNum(map); // ????????? ????????????
		newMap.put("replyName", replyName);

		System.out.println("reply map = " + map.toString());
		System.out.println("reply new map = " + newMap.toString());

		ModelAndView mav = new ModelAndView();
		mav.addObject("map",map);
		mav.addObject("data", newMap);
		mav.setViewName("/bbs/reply");

		return mav;
	}

	@RequestMapping(value = "/replyProc", method = RequestMethod.GET)
	public ModelAndView replyProc(@RequestParam Map<String, Object> map) {
		
		System.out.println("/replyProc - map :"+map.toString());
		int repUpCnt = 0;
		
		if (boardService.replyUpBoard(map) != 0) {
			repUpCnt = boardService.replyUpBoard(map);
		}
		; // ????????????
		
		int cnt  = boardService.replyUpBoard(map);
		
		if(cnt>0) {
			repUpCnt = cnt;
		}
		
		int repInsCnt = boardService.replyBoard(map); // ?????? ????????? insert

		map.put("repUpCnt", repUpCnt);
		map.put("repInsCnt", repInsCnt);

		String nowPage= map.get("nowPage").toString();
		String keyField= map.get("keyField").toString();
		String keyWord= map.get("keyWord").toString();
		
		String url = "/list?nowPage="+nowPage+"&keyField="+keyField+"&keyWord="+keyWord+"&gnbParam=bbs";
		String msg = "";

		msg = "????????? ????????? ????????? ??????????????????.\n";
		msg += "?????? ??????????????????\n";
		msg += "????????? ???????????? ??????????????? ??????????????????.";

		System.out.println("replyProc map = " + map.toString());
		ModelAndView mav = new ModelAndView();
		mav.addObject("url", url);
		mav.addObject("msg", msg);
		mav.addObject("data", map);
		System.out.println();
		mav.setViewName("/bbs/replyProc");

		return mav;

	}
	
	//?????? ???????????? ??????
	@RequestMapping(value = "/download", method = RequestMethod.GET)
	public ModelAndView download(@RequestParam Map<String, Object> map, HttpServletResponse response) throws IOException {
		System.out.println("/dowmload ?????? ="+map.toString());
		String fileName =(String) map.get("fileName");
		
		File file = new File(SAVEFOLER, fileName);
		
		response.setContentType("application/octet-stream");
		response.setHeader("Content-disposition", "attachment;filename="
				+fileName);
		
		OutputStream os =  response.getOutputStream();
		FileInputStream fis = null;
		
		fis = new FileInputStream(file);
		FileCopyUtils.copy(fis, os);
		
		if(fis!=null) {
			fis.close();
		}
		
		return new ModelAndView("");
	}
	
	//?????? ???????????? ???

}
