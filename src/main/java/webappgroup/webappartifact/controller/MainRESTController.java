package webappgroup.webappartifact.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import webappgroup.webappartifact.dao.QuestionService;
import webappgroup.webappartifact.entity.Question;

@RestController
@RequestMapping("/Restapi/*")
public class MainRESTController {

	@Autowired
	private QuestionService questionService;

	@RequestMapping("/rest")
	@ResponseBody
	public String welcome() {
		return "Welcome to RestTemplate Example.";
	}

	// URL:
	// http://localhost:8080/Restapi/questions
	// http://localhost:8080/Restapi/questions.xml
	// http://localhost:8080/Restapi/questions.json
	@RequestMapping(value = "/questions", //
			method = RequestMethod.GET, //
			produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	@ResponseBody
	public List<Question> getEmployees() {
		List<Question> list = questionService.getQuestions();
		List<Question> listQues = new ArrayList<Question>();
		Random rd = new Random();
		for (int i = 0; i < 20; i++) {
			int num = rd.nextInt(list.size() - 1) + 0;
			listQues.add(list.get(num));
			list.remove(num);
		}

		return listQues;
	}

}