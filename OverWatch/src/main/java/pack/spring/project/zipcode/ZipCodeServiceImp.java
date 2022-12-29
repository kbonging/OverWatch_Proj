package pack.spring.project.zipcode;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ZipCodeServiceImp implements ZipCodeService {

	@Autowired
	ZipCodeDAO zipCodeDao;
		
	@Override
	public List<Map<String, Object>> selectDong(Map<String, Object> map) {
		return zipCodeDao.selectDong(map);
	}

}
