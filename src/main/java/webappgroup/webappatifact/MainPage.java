package webappgroup.webappatifact;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class MainPage {

//	private String UPLOAD_FOLDER_PLACE = "C:\\Users\\USER\\Downloads\\tomcat\\server\\apache-tomcat-9.0.7\\tmpFiles\\";
	
//	@RequestMapping(name="/upload",method=RequestMethod.POST)
//	public String singleFileUpload(@RequestParam("file"),) {
//		
//		
//		
//		return null;
//	}
	
	
	@RequestMapping("/mainMenu")
	public String mainpage() {
		return "mainMenu";
	}

	
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
