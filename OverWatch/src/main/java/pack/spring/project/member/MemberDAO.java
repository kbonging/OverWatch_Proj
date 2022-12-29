package pack.spring.project.member;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MemberDAO {

		@Autowired
		SqlSessionTemplate sqlSessionTemplate;
		
		//로그인
		public Map<String, Object> loginCheck(Map<String, Object> map) {
			return this.sqlSessionTemplate.selectOne("member.login", map);
		}
		
		//세션 아이디로 정보 가져오기
		public Map<String, Object> selectByUId(Map<String, Object> map){
			return this.sqlSessionTemplate.selectOne("member.selectBy_uId", map);
		}
		
		//회원정보 수정
		public int updateMem(Map<String, Object> map) {
			return this.sqlSessionTemplate.update("member.updateMem", map);
		}
		
		// 회원가입
		public int  insert(Map<String, Object> map) {
			return this.sqlSessionTemplate.insert("member.insert", map);
		}

		//아이디 중복 검사
		 public int idCheck(Map<String, Object> map) {
			 return  this.sqlSessionTemplate.selectOne("member.idCheck", map); 
		 }
		 
		 //로그인 사용자 이름 반환
		 public Map<String, Object> getMemberName(Map<String, Object> map){
			 return this.sqlSessionTemplate.selectOne("member.select_uName",map);
		 }
		 
		 //회원 탈퇴
		 public int delete_uId(String uId) {
			 return this.sqlSessionTemplate.delete("member.delete_uId", uId);
		 }
		
		 //회원 리스트 전체조회
		 public List<Map<String, Object>> select_All(Map<String, Object> map){
			return this.sqlSessionTemplate.selectList("member.select_All",map);
		}
	
		 // 회원 리스트 검색 조회
		public List<Map<String, Object>> select_keyWord(Map<String, Object> map) {
			return this.sqlSessionTemplate.selectList("member.select_keyWord", map);
		}
		
		// 회원 목록 리스트 전체 개수
		public int select_countAll(Map<String, Object> map) {
			return this.sqlSessionTemplate.selectOne("member.select_countAll", map);
		}
		
		// 회원 목록 리스트 keyWord 검색으로 개수 구하기
		public int select_countKey(Map<String, Object> map) {
			return this.sqlSessionTemplate.selectOne("member.select_countKey", map);
		}
		
		//회원 번호로
		public Map<String, Object> selectByNum(Map<String, Object> map) {
			return this.sqlSessionTemplate.selectOne("member.selectByNum", map);
		}
}
