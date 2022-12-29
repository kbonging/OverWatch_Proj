package pack.spring.project.noticeBoard;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class NoticeBoardDAO {

	@Autowired
	SqlSessionTemplate sqlSessionTemplate;

	// 공지게시판 리스트 전체 조회
	public List<Map<String, Object>> select_All(Map<String, Object> map) {
		return this.sqlSessionTemplate.selectList("NoticeBoard.select_All", map);
	}

	// 공지게시판 리스트 검색 조회
	public List<Map<String, Object>> select_keyWord(Map<String, Object> map) {
		return this.sqlSessionTemplate.selectList("NoticeBoard.select_keyWord", map);
	}

	// 공지게시판 목록 리스트 전체 개수
	public int select_countAll(Map<String, Object> map) {
		return this.sqlSessionTemplate.selectOne("NoticeBoard.select_countAll", map);
	}

	// 공지게시판 목록 리스트 keyWord 검색으로 개수 구하기
	public int select_countKey(Map<String, Object> map) {
		return this.sqlSessionTemplate.selectOne("NoticeBoard.select_countKey", map);
	}

	// 공지게시판 글쓰기
	public int insert_NoticeBoard(Map<String, Object> map) {
		return this.sqlSessionTemplate.insert("NoticeBoard.insert_NoticeBoard", map);
	}

	// maxNum 가져오기
	public Map<String, Object> select_maxNum() {
		return this.sqlSessionTemplate.selectOne("NoticeBoard.select_maxNum");
	}

	// num 로 게시판 상세내용 구하기
	public Map<String, Object> selectByNum(Map<String, Object> map) {
		return this.sqlSessionTemplate.selectOne("NoticeBoard.selectByNum", map);
	}

	// 게시판 업데이트
	public int update_NoticeBoard(Map<String, Object> map) {
		return this.sqlSessionTemplate.update("NoticeBoard.update_NoticeBoard", map);
	}

	// 조회수
	public int upCount(Map<String, Object> map) {
		return this.sqlSessionTemplate.update("NoticeBoard.upCount", map);
	}
}
