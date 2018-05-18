package webappgroup.webappatifact;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/Questionlist")
public class QuestionFormController {

//	@RequestMapping("/Questionlist")
//	public String listQues() {
//		System.out.println("Fuck you!");
//		return "jsp/listQuestions";
//	}
	@RequestMapping("/Questionlist")
	public String listQues() {
		System.out.println("Fuck you!");
		return "jsp/listQuestions";
	}

	@RequestMapping(value = "/redirect", method = RequestMethod.GET)
	public ModelAndView Redirect() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("redirect:Questionlist");
		return modelAndView;
	}
}
