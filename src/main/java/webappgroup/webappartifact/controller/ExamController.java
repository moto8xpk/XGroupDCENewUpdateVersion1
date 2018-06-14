package webappgroup.webappartifact.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import webappgroup.webappartifact.dao.QuestionService;
import webappgroup.webappartifact.entity.Question;
import webappgroup.webappartifact.service.XmlFormatter;

@Controller
@RequestMapping("/Exam/*")
public class ExamController {

	@Autowired
	private QuestionService questionDAO ;
	
	@RequestMapping(value = "/redirect", method = RequestMethod.GET)
	public ModelAndView Redirect() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("redirect:Exam/test");
		return modelAndView;
	}
	
	@RequestMapping("/Exam/test")
	private String testpage(Model model) {
		 //Get list Question from model to view
		 List<Question> listQuestion=questionDAO.getQuestions();

		 listQuestion=XmlFormatter.QuestionsFomatter(listQuestion);
		 List<Question> listQuesPost = new ArrayList<Question>();
		 for(Question question: listQuestion)
		 {
			 System.out.print(question.getQuestionContent()+
					 			"-"+question.getAnswersA()+
					 			"-"+question.getAnswersB()+
					 			"-"+question.getAnswersC()+
					 			"-"+question.getAnswersD());
		 }
		 Random rd = new Random();
			for (int i = 0; i < 20; i++) {
				int num = rd.nextInt(listQuestion.size() - 1) + 0;
				Question qs=new Question();
				
				qs.setQuestionContent(listQuestion.get(num).getQuestionContent());
				qs.setAnswersA(listQuestion.get(num).getAnswersA());
				qs.setAnswersB(listQuestion.get(num).getAnswersB());
				qs.setAnswersC(listQuestion.get(num).getAnswersC());
				qs.setAnswersD(listQuestion.get(num).getAnswersD());
				qs.setClassId(listQuestion.get(num).getClassId());
				
				listQuesPost.add(qs);
				listQuestion.remove(num);
			}
			
		 //Add list question to view
		 model.addAttribute("questions", listQuesPost);
	     
		 //come to view
		return "examTest";
	}
	
}
