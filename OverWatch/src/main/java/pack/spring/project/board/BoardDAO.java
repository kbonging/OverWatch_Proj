package pack.spring.project.board;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BoardDAO {
	
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;
	
	//게시판 리스트 전체 조회
	public List<Map<String, Object>> select_All(Map<String, Object> map){
		return this.sqlSessionTemplate.selectList("tblBoard.select_All",map );
	}
	
	// 게시판 리스트 검색 조회
	public List<Map<String, Object>> select_keyWord(Map<String, Object> map) {
		return this.sqlSessionTemplate.selectList("tblBoard.select_keyWord", map);
	}
	
	// 게시판 목록 리스트 전체 개수
	public int select_countAll(Map<String, Object> map) {
		return this.sqlSessionTemplate.selectOne("tblBoard.select_countAll", map);
	}
	
	// 게시판 목록 리스트 keyWord 검색으로 개수 구하기
	public int select_countKey(Map<String, Object> map) {
		return this.sqlSessionTemplate.selectOne("tblBoard.select_countKey", map);
	}
	
	// 게시판 글쓰기
	public int insert_bbs(Map<String, Object> map) {
		return this.sqlSessionTemplate.insert("tblBoard.insert_bbs", map);
	}
	
	// maxNum 가져오기
	public Map<String, Object> select_maxNum() {
		return this.sqlSessionTemplate.selectOne("tblBoard.select_maxNum");
	}
	
	// num 로 게시판 상세내용 구하기
	public Map<String, Object> selectByNum(Map<String, Object> map){
		return this.sqlSessionTemplate.selectOne("tblBoard.selectByNum", map);
	}
	
	// 게시판 업데이트
	public int update_bbs(Map<String, Object> map) {
		return this.sqlSessionTemplate.update("tblBoard.update_bbs", map);
	}
	
	//게시판 삭제
	public int delete_bbs(Map<String, Object> map) {
		return this.sqlSessionTemplate.delete("tblBoard.delete_bbs", map);
	}
	
	//답변글 끼어들기
	public int replyUpBoard(Map<String, Object> map) {
		return this.sqlSessionTemplate.update("tblBoard.replyUpBoard", map);
	}
	
	//게시글 답변 페이지 시작
	public int replyBoard(Map<String, Object> map){
		return this.sqlSessionTemplate.insert("tblBoard.replyBoard",map);
	}
	
	//조회수
	public int upCount(Map<String, Object> map) {
		return this.sqlSessionTemplate.update("tblBoard.upCount", map);
	}
}
