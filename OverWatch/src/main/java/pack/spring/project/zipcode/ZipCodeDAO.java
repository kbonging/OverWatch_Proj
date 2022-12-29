package pack.spring.project.zipcode;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ZipCodeDAO {
	
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;
	
	//우편번호 동으로 찾기
	public List<Map<String, Object>> selectDong(Map<String , Object> map){
		return this.sqlSessionTemplate.selectList("zipcode.selectDong", map);
	}
	
}
