package pack.spring.project.index;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {
	
	@Autowired
	IndexService indexService;
	
	//////////////////// index 화면 보기 /////////////////////////
	@RequestMapping(value = "/" )
	public ModelAndView index() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/index");
		return mav;
	}
	
}
