package pack.spring.project.noticeBoard;

import java.util.List;
import java.util.Map;

public interface NoticeBoardService {
	List<Map<String, Object>> select_All(Map<String, Object> map);
	
	List<Map<String, Object>> select_keyWord(Map<String, Object> map);
	
	int select_countAll(Map<String, Object> map);
	
	int select_countKey(Map<String, Object> map);
	
	int insert_NoticeBoard(Map<String, Object> map);
	
	Map<String, Object> select_maxNum();
	
	Map<String, Object> selectByNum(Map<String, Object> map);
	
	 int update_NoticeBoard(Map<String, Object> map);
	 
	 int upCount(Map<String, Object> map);
}
