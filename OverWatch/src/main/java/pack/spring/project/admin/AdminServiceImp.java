package pack.spring.project.admin;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImp implements AdminService {
	
	@Autowired
	AdminDAO adminDAO;
	
	@Override
	public Map<String, Object> login(Map<String, Object> map) {
		return this.adminDAO.login(map);
	}

	@Override
	public int loginCheck(Map<String, Object> map) {
		return this.adminDAO.loginCheck(map);
	}

	@Override
	public Map<String, Object> selectByUId(Map<String, Object> map) {
		return this.adminDAO.selectByUId(map);
	}
	
	

}
