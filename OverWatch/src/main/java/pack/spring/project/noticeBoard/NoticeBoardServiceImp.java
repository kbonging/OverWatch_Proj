package pack.spring.project.noticeBoard;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NoticeBoardServiceImp implements NoticeBoardService {
	
	@Autowired
	NoticeBoardDAO noticeBoardDAO;
	
	@Override
	public List<Map<String, Object>> select_All(Map<String, Object> map) {
		return this.noticeBoardDAO.select_All(map);
	}

	@Override
	public List<Map<String, Object>> select_keyWord(Map<String, Object> map) {
		return this.noticeBoardDAO.select_keyWord(map);
	}

	@Override
	public int select_countAll(Map<String, Object> map) {
		return this.noticeBoardDAO.select_countAll(map);
	}

	@Override
	public int select_countKey(Map<String, Object> map) {
		return this.noticeBoardDAO.select_countKey(map);
	}

	@Override
	public int insert_NoticeBoard(Map<String, Object> map) {
		return this.noticeBoardDAO.insert_NoticeBoard(map);
	}

	@Override
	public Map<String, Object> select_maxNum() {
		return this.noticeBoardDAO.select_maxNum();
	}

	@Override
	public Map<String, Object> selectByNum(Map<String, Object> map) {		
		return this.noticeBoardDAO.selectByNum(map);
	}

	@Override
	public int update_NoticeBoard(Map<String, Object> map) {
		return this.noticeBoardDAO.update_NoticeBoard(map);
	}

	@Override
	public int upCount(Map<String, Object> map) {
		return this.noticeBoardDAO.upCount(map);
	}

}
