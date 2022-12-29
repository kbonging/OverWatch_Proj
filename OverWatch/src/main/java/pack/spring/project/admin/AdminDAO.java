package pack.spring.project.admin;

import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AdminDAO {
	
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;
	
	//로그인
	public Map<String, Object> login(Map<String, Object> map) {
		return this.sqlSessionTemplate.selectOne("admin.login", map);
	}
	
	//아이디 유무 확인
	public int loginCheck(Map<String, Object> map) {
		return this.sqlSessionTemplate.selectOne("admin.loginCheck", map);
	}
	
	//세션 아디로 정보 가져오기
	public Map<String, Object> selectByUId(Map<String, Object> map){
		return this.sqlSessionTemplate.selectOne("admin.selectBy_uId", map);
	}
}
