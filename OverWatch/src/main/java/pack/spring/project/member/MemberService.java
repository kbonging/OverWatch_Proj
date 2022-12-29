package pack.spring.project.member;

import java.util.List;
import java.util.Map;

public interface MemberService {
	
	//로그인 체크
	Map<String, Object> login(Map<String, Object> map);
	
	//세션 uId로 정보 가져오기
	Map<String, Object> selectByUId(Map<String, Object> map);
	
	//회원정보 수정
	int updateMem(Map<String, Object> map);
	
	//아이디 중복 체크
	int idCheck(Map<String, Object> map);
	
	 //회원가입 Service
	String insert(Map<String,Object> map);
	
	//로그인 사용자 이름 반환
	Map<String, Object> getMemberName(Map<String, Object> map);
	
	//회원 탈퇴
	int delete_uId(String uId);
	
	 //회원 리스트 전체조회
	 List<Map<String, Object>> select_All(Map<String, Object> map);

	 // 회원 리스트 검색 조회
	List<Map<String, Object>> select_keyWord(Map<String, Object> map);
	
	// 회원 목록 리스트 전체 개수
	int select_countAll(Map<String, Object> map);
	
	// 회원 목록 리스트 keyWord 검색으로 개수 구하기
	int select_countKey(Map<String, Object> map);
	
	//회원 번호로 정보 가져오기
	Map<String, Object> selectByNum(Map<String, Object> map);
}
