package pack.spring.project.zipcode;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ZipCodeController {
	
	@Autowired
	ZipCodeService zipCodeService;
	
	// 우편번호 팝업 창
	@RequestMapping(value = "/zipCheck" , method = RequestMethod.GET)
	public ModelAndView zipCode() {
		return new ModelAndView("/member/zipCheck");
	}
	
	// 우편번호 찾기 동 이름
	@RequestMapping(value = "/zipCheck", method = RequestMethod.POST)
	public ModelAndView zipCodePost(@RequestParam Map<String, Object> map) {
		String area3 = map.get("area3").toString();
		
		List<Map<String, Object>> list =  zipCodeService.selectDong(map);
		
		for(int i=0; i<list.size(); i++) {
			String area = "";
			Map<String, Object> zipMap = list.get(i);
			area += zipMap.get("zipcode").toString()+ " ";
			area += zipMap.get("area1").toString()+ " ";
			area += zipMap.get("area2").toString()+ " ";
			area += zipMap.get("area3").toString()+ " ";
			area += zipMap.get("area4").toString();
			
			zipMap.put("area", area);
			
		}
		
		
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("data", list);
		mav.addObject("area3", area3);
		mav.setViewName("/member/zipCheck");
		
		return mav;
		
	}
	
}
