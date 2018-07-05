package webappgroup.webappartifact.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.annotation.JsonView;

import webappgroup.webappartfact.web.jsonview.Views;
import webappgroup.webappartifact.dao.QuestionService;
import webappgroup.webappartifact.entity.Question;
import webappgroup.webappartifact.model.AjaxResponseBody;
import webappgroup.webappartifact.model.Answer;
import webappgroup.webappartifact.model.Answer2;
import webappgroup.webappartifact.model.Answers;
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
			 System.out.print(question.getQuestionId()+
					 			"-"+question.getQuestionContent()+
					 			"-"+question.getAnswersA()+
					 			"-"+question.getAnswersB()+
					 			"-"+question.getAnswersC()+
					 			"-"+question.getAnswersD());
		 }
		 Random rd = new Random();
		 //add random question to list 
			for (int i = 0; i < 20; i++) {
				int num = rd.nextInt(listQuestion.size() - 1) + 0;
				Question qs=new Question();
				qs.setQuestionId(listQuestion.get(num).getQuestionId());
				qs.setQuestionContent(listQuestion.get(num).getQuestionContent());
				qs.setAnswersA(listQuestion.get(num).getAnswersA());
				qs.setAnswersB(listQuestion.get(num).getAnswersB());
				qs.setAnswersC(listQuestion.get(num).getAnswersC());
				qs.setAnswersD(listQuestion.get(num).getAnswersD());
				qs.setClassId(listQuestion.get(num).getClassId());
				
				
				listQuesPost.add(qs);
				//remove question in old list
				listQuestion.remove(num);
			}
			
		 //Add list question to view
		 model.addAttribute("questions", listQuesPost);
	     
		 //come to view
		return "examTest";
	}
	
	@JsonView(Views.Public.class)
	@RequestMapping(value="/Exam/getAnswerList")
	public @ResponseBody String getAnswerListData(@RequestBody Answer[] answers)
	{
		AjaxResponseBody mv=new AjaxResponseBody();
		
		Integer result=0;
		for (Answer answer : answers) {
			String answerRight= questionDAO.getQuestionAnswerContent(answer.getQuestionid());
			
			if(answer.getAnswerques().equals(answerRight)) {
				result=result+1;
			}
		}
		System.out.println(result);
		String strResult=result.toString();
		mv.setMsg(strResult);
		return strResult;
	}
//	 @RequestMapping("/Exam/Result")
//	 public String getResult(ModelAndView model) {
//	     
//		 //come to view
//		 return "resultExam";
//	 }
	
}
