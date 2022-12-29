package pack.spring.project.board;

import java.util.List;
import java.util.Map;

public interface BoardService {
	
	List<Map<String, Object>> select_All(Map<String, Object> map);
	
	List<Map<String, Object>> select_keyWord(Map<String, Object> map);
	
	int select_countAll(Map<String, Object> map);
	
	int select_countKey(Map<String, Object> map);

	int insert_bbs(Map<String, Object> map);
	
	Map<String, Object> select_maxNum();
	
	Map<String, Object> selectByNum(Map<String, Object> map);
	
	int update_bbs(Map<String, Object> map);
	
	int delete_bbs(Map<String, Object> map);
	
	int replyUpBoard(Map<String, Object> map);
	
	int replyBoard(Map<String, Object> map);
	
	int upCount(Map<String, Object> map);
}
