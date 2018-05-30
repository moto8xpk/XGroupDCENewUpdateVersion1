package webappgroup.webappartifact.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import webappgroup.webappartifact.dao.impl.QuestionDAO;
import webappgroup.webappartifact.entity.Question;

@Service
public class QuestionService {

	@Autowired
    private QuestionDAO questionDAOImpl;
	
	public List<Question> getQuestions() {
		return questionDAOImpl.getQuestionList();
	}


}