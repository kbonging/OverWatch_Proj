package pack.spring.project.noticeBoard;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
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

import pack.spring.project.admin.AdminService;
import pack.spring.project.board.UtilMgr;
import pack.spring.project.common.PageVO;
import pack.spring.project.member.MemberService;

@Controller
public class NoticeBoardController {
	// 집용
	private static final String SAVEFOLER = "C:/Users/apple/git/OverWatch_Proj/OverWatch/src/main/webapp/resources/NoticeFileUpload";
	
//	private static final String SAVEFOLER = "C:/Users/EZEN202/git/Project_OverWatch/Project_OverWatch/src/main/webapp/resources/NoticeFileUpload";
	// private static final String SAVEFOLER =
	// "C:/Users/User/git/Project_OverWatch/Project_OverWatch/src/main/webapp/resources/fileUpload";
	private static String encType = "UTF-8";
	private static int maxSize = 5 * 1024 * 1024;

	@Autowired
	NoticeBoardService noticeBoardService;

	// 게시글 목록 보기
	@RequestMapping(value = "/noticelist") // session 유지 ,
	public ModelAndView list(@RequestParam Map<String, Object> map, HttpSession session) {
		String sessionuId = (String) session.getAttribute("uId");
		System.out.println("/list - 시작 시 map : " + map.toString());
		/////////////////////// 페이징 관련 속성 값 시작///////////////////////////
		// 페이징(Paging) = 페이지 나누기를 의미함
		int totalRecord = 0; // 전체 데이터 수(DB에 저장된 row 개수)
		int numPerPage = 5; // 페이지당 출력하는 데이터 수(=게시글 숫자)
		int pagePerBlock = 5; // 블럭당 표시되는 페이지 수의 개수
		int totalPage = 0; // 전체 페이지 수
		int totalBlock = 0; // 전체 블록수

		/*
		 * 페이징 변수값의 이해 totalRecord=> 200 전체레코드 numPerPage => 10 pagePerBlock => 5
		 * totalPage => 20 totalBlock => 4 (20/5 => 4)
		 */

		int nowPage = 1; // 현재 (사용자가 보고 있는) 페이지 번호
		int nowBlock = 1; // 현재 (사용자가 보고 있는) 블럭

		int start = 0; // DB에서 데이터를 불러올 때 시작하는 인덱스 번호
		int end = 5; // 시작하는 인덱스 번호부터 반환하는(=출력하는) 데이터 개수
		// select * from T/N where... order by ... limit 5, 5;
		// 데이터가 6개 1~5
		// 인덱스번호 5이므로 6번 자료를 의미 5개

		int listSize = 0; // 1페이지에서 보여주는 데이터 수
		// 출력할 데이터의 개수 = 데이터 1개는 가로줄 1개

		// 게시판 검색 관련소스
		String keyField = ""; // DB의 컬럼명
		String keyWord = ""; // DB의 검색어
		List<Map<String, Object>> list = null;

		String nowPage2 = (String) map.get("nowPage");
		if (nowPage2 != null) {
			nowPage = Integer.parseInt(nowPage2);
			start = (nowPage * numPerPage) - numPerPage;
			end = numPerPage;
		}

		map.put("start", start);
		map.put("end", end);

		System.out.println("/noticelist - db 연결 전map : " + map.toString());

		if (map.get("keyWord") != null && !map.get("keyWord").toString().equals("")) { // 검색 keyWord가 있을 경우
			keyField = map.get("keyField").toString();
			keyWord = map.get("keyWord").toString();
			map.put("keyField", keyField);
			map.put("keyWord", keyWord);
			list = noticeBoardService.select_keyWord(map);
			totalRecord = noticeBoardService.select_countKey(map);

		} else { // 검색 keyWord가 없음 경우
			list = noticeBoardService.select_All(map);
			totalRecord = noticeBoardService.select_countAll(map);
		}

		for (int i = 0; i < list.size(); i++) {
			Map<String, Object> userMap = list.get(i);
			String regTM = userMap.get("regTM").toString();
			regTM = regTM.substring(0, 10) + " " + regTM.substring(11);
			userMap.put("regTM", regTM);
		}

		/*
		 * select * from tblBoard order by num desc limit 10, 10; 데이터가 100개 => num : 100
		 * 99 98 97 ... 91 | 90 .... 2 1 start, end : 0 1 2 3.... 9 10 페이지당 출력할 데이터 수
		 * 10개 현재 페이지 1페이지라면 => 1페이지의 출력결과 100 ~ 91 2페이지(= nowPage가 2라는 의미) 90~81 3페이지
		 * 80~71
		 */
		// 전체 데이터 수 반환

		totalPage = (int) Math.ceil((double) totalRecord / numPerPage);
		nowBlock = (int) Math.ceil((double) nowPage / pagePerBlock);
		totalBlock = (int) Math.ceil((double) totalPage / pagePerBlock);

		int pageStart = (nowBlock - 1) * pagePerBlock + 1;
		int pageEnd = (nowBlock < totalBlock) ? pageStart + pagePerBlock - 1 : totalPage;

		/////////////////////// 페이징 관련 속성 값 끝///////////////////////////

		PageVO pageVo = new PageVO(totalRecord, nowPage, totalPage, numPerPage, nowBlock, pagePerBlock, totalBlock,
				listSize, pageStart, pageEnd);

		System.out.println("/list - db 연결 후 list : " + list.toString());
		System.out.println("/list - db 연결 후 map : " + map.toString());
		System.out.println("/list - db 연결 후 pagaVo : " + pageVo.toString());

		ModelAndView mav = new ModelAndView();
		mav.addObject("sessionuId", sessionuId);
		mav.addObject("pageVo", pageVo);
		mav.addObject("list", list);
		mav.addObject("map", map);
		mav.setViewName("noticeBoard/list");
		return mav;
	} // 게시글 목록 보기 끝

	@Autowired
	MemberService memberService;

	@Autowired
	AdminService adminService;
	
	// 글쓰기 페이지 보여주기
	@RequestMapping(value = "/noticeWrite", method = RequestMethod.GET)
	public ModelAndView bbsWrite(HttpServletRequest request, HttpSession session) {
		String uId = (String) session.getAttribute("uId");

		System.out.println("/noticeWrite - request nowPage : " + request.getParameter("nowPage").toString());
		System.out.println("/noticeWrite - request keyField: " + request.getParameter("keyField").toString());
		System.out.println("/noticeWrite - request keyWord: " + request.getParameter("keyWord").toString());
		System.out.println("/noticeWrite - request gnbParam: " + request.getParameter("gnbParam").toString());

		String nowPage = request.getParameter("nowPage").toString();
		String keyField = request.getParameter("keyField").toString();
		String keyWord = request.getParameter("keyWord").toString();
		String gnbParam =  request.getParameter("gnbParam").toString();
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("uId", uId);
		map.put("nowPage", nowPage);
		map.put("keyField", keyField);
		map.put("keyWord", keyWord);
		map.put("gnbParam", gnbParam);
		
		Map<String, Object> userMap = adminService.selectByUId(map);
		
		System.out.println("/noticeWrite - userMap : "+userMap.toString());
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("map", map);
		mav.addObject("data", userMap);
		mav.setViewName("/noticeBoard/write");

		return mav;

	} // 글쓰기 페이지 보여주기 끝

	// 글 쓰기 처리
	@RequestMapping(value = "/noticeWrite", method = RequestMethod.POST)
	public ModelAndView bbsWrite_post(@RequestParam Map<String, Object> map, HttpSession session,
			HttpServletRequest request) throws IOException {

		// System.out.println("request : "+request.getParameterNames());
		// String ip = (String)request.getParameter("ip");
		MultipartRequest multi = null;
		int fileSize = 0;
		String fileName = null;

		System.out.println("/noticeWrite map : " + map.toString());
		// 사용자 입력 값은 request 가 다 받아서 현재 map에는 아무것도 없음

		Map<String, Object> maxMap = noticeBoardService.select_maxNum();

		System.out.println(maxMap.toString());
		int ref = 1;
		int maxNum = Integer.parseInt(maxMap.get("max(num)").toString());
		if (maxNum > 0) {
			ref = maxNum + 1;
		}
		System.out.println("ref = " + ref);

		File file = new File(SAVEFOLER);

		System.out.println("/noticeWrite file : " + file.toString());

		if (!file.exists()) {
			file.mkdir();
		}

		multi = new MultipartRequest(request, SAVEFOLER, maxSize, encType, new DefaultFileRenamePolicy());

		if (multi.getFilesystemName("fileName") != null) {
			fileName = multi.getFilesystemName("fileName");
			fileSize = (int) multi.getFile("fileName").length();
		}

		String uId = multi.getParameter("uId");
		String uName = multi.getParameter("uName");
		String subject = multi.getParameter("subject");
		String content = multi.getParameter("content");

		if (multi.getParameter("contentType").equalsIgnoreCase("TEXT")) {
			content = UtilMgr.replace(content, "<", "&lt;");
		}

		map.put("uId", uId);
		map.put("uName", uName);
		map.put("subject", subject);
		map.put("content", content);
		map.put("fileName", fileName);
		map.put("fileSize", fileSize);
		/*
		 * Map<String, Object> maxMap = boardService.select_maxNum();
		 * System.out.println(maxMap.toString()); if(!maxMap.isEmpty()) { int num =
		 * (int) maxMap.get("num"); ref = num +1; }
		 */

		System.out.println("인서트 전 map : " + map.toString());
		int bbsNum = noticeBoardService.insert_NoticeBoard(map);
		System.out.println(bbsNum);

		String msg = "공지글 쓰기 실패", url = "/noticeWrite";
		if (bbsNum > 0) {
			msg = "쓰기 성공";
			url = "/noticelist?gnbParam=bbs";
		}

		ModelAndView mav = new ModelAndView();
		mav.addObject("msg", msg);
		mav.addObject("url", url);
		mav.setViewName("/common/message");
		return mav;
	}// 글 쓰기 처리 끝
	
	// 글 목록 상세보기
		@RequestMapping(value = "/noticeread", method = RequestMethod.GET)
		public ModelAndView read(@RequestParam Map<String, Object> map, HttpSession session) {
			String sessionuId = (String) session.getAttribute("uId");
			
			//조회수 증가
			noticeBoardService.upCount(map);
			
			System.out.println("/noticeread  맵 : "+map.toString());
			Map<String, Object> userMap = noticeBoardService.selectByNum(map);
			userMap.put("sessionuId", sessionuId);
			
			System.out.println("/noticeread 유저 맵 : "+userMap.toString());
			
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

			ModelAndView mav = new ModelAndView();
			mav.addObject("fUnit", fUnit);
			mav.addObject("map", map);
			mav.addObject("data", userMap);
			mav.setViewName("/noticeBoard/read");

			return mav;

		}// 글 목록 상세보기 끝
		
		@RequestMapping(value = "/noticemodify", method = RequestMethod.GET)
		public ModelAndView modify(@RequestParam Map<String, Object> map) {

			Map<String, Object> userMap = noticeBoardService.selectByNum(map);

			System.out.println("noticemodify : " + map.toString());
			System.out.println("noticemodify : " + userMap.toString());
			ModelAndView mav = new ModelAndView();
			mav.addObject("data", userMap);
			mav.addObject("map", map);

			mav.setViewName("/noticeBoard/modify");

			return mav;

		}
		
		@RequestMapping(value = "/noticemodifyProc", method = RequestMethod.GET)
		public ModelAndView modifyProc(@RequestParam Map<String, Object> map) {
			
			System.out.println("/noticemodifyProc = map : "+map.toString());
			int num = Integer.parseInt(map.get("num").toString());
			
			String nowPage = map.get("nowPage").toString();
			String keyField = map.get("keyField").toString();
			String keyWord = map.get("keyWord").toString();
			
			try {
				keyWord = URLEncoder.encode(keyWord, "utf-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			int board_num = noticeBoardService.update_NoticeBoard(map);
			System.out.println("noticeboard_num : "+board_num);
			ModelAndView mav = new ModelAndView();
			if (board_num > 0) {
				System.out.println("수정 성공");
				System.out.println(map.get("num").toString());
				
				mav.setViewName("redirect:/noticeread?num="+num+"&nowPage="+nowPage+"&keyField="+keyField+"&keyWord="+keyWord+"&gnbParam=bbs");
			} else {
				System.out.println(map.get("num").toString());
				System.out.println("수정 실패");

				mav.setViewName("redirect:/noticemodify?num="+num+"&nowPage="+nowPage+"&keyField="+keyField+"&keyWord="+keyWord+"&gnbParam=bbs");
			}

			return mav;
		}
		
		//파일 다운로드 시작
		@RequestMapping(value = "/noticedownload", method = RequestMethod.GET)
		public ModelAndView download(@RequestParam Map<String, Object> map, HttpServletResponse response) throws IOException {
			System.out.println("/dowmload 매핑 ="+map.toString());
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
		
		//파일 다운로드 끝
}
