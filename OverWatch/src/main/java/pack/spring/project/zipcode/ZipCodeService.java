package pack.spring.project.zipcode;

import java.util.List;
import java.util.Map;

public interface ZipCodeService {
	
	List<Map<String, Object>> selectDong(Map<String , Object> map);
}
