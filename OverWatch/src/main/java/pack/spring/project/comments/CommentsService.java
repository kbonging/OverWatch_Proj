package pack.spring.project.comments;

import java.util.List;
import java.util.Map;

public interface CommentsService {
	 
	int insert_comments(Map<String, Object> map);
	 
	 Map<String, Object> select_maxNum();
	 
	 List<Map<String, Object>> select_comments(Map<String, Object> map);
	 
	 int delete_comments(Map<String, Object> map);
	 
	 int update_replyUpComments(Map<String, Object> map);
	 
	 int insert_relpyComments(Map<String, Object> map);
	 
	 int select_comCountAll(Map<String, Object> map);
}
