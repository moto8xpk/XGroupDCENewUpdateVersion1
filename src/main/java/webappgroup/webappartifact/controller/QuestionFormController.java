package webappgroup.webappartifact.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

//import webappgroup.webappartifact.dao.ClassDAO;
import webappgroup.webappartifact.dao.QuestionService;
import webappgroup.webappartifact.entity.Question;
import webappgroup.webappartifact.service.XmlFormatter;

@Controller
@RequestMapping("/Question/*")
public class QuestionFormController {

//	@Autowired
//	 private ClassDAO classDAO;
	
	@Autowired
	 private QuestionService questionDAO ;
	
	@RequestMapping(value = "/redirect", method = RequestMethod.GET)
	public ModelAndView Redirect() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("redirect:Question/list");
		return modelAndView;
	}
	 @RequestMapping("/Question/list")
	 public String quesList(Model model) {

		 //Get list Deps from model to view
		 List<Question> listQuestion=questionDAO.getQuestions();

		 listQuestion=XmlFormatter.QuestionsFomatter(listQuestion);
		 
		 for(Question question: listQuestion)
		 {
			 System.out.print(question.getQuestionContent()+
					 			"-"+question.getAnswersA()+
					 			"-"+question.getAnswersB()+
					 			"-"+question.getAnswersC()+
					 			"-"+question.getAnswersD());
		 }
	     
		 //Add list Deps to view
		 model.addAttribute("questions", listQuestion);
	     
		 //come to view
		 return "listQuestions";
	 }

}
