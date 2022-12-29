package pack.spring.project.admin;

import java.util.Map;

public interface AdminService {
	Map<String, Object> login(Map<String, Object> map);
	
	int loginCheck(Map<String, Object> map);
	
	Map<String, Object> selectByUId(Map<String, Object> map);
}
