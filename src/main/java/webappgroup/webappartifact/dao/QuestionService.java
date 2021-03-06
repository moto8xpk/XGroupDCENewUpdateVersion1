package webappgroup.webappartifact.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import webappgroup.webappartifact.dao.impl.QuestionDAO;
import webappgroup.webappartifact.entity.Question;
import webappgroup.webappartifact.model.Answer;
import webappgroup.webappartifact.model.Answer2;

@Service
public class QuestionService {

	@Autowired
    private QuestionDAO questionDAOImpl;
	
	public List<Question> getQuestions() {
		return questionDAOImpl.getQuestionList();
	}
	public String getQuestionAnswerContent(String quesId) {
		return questionDAOImpl.getQuestionAnswer(quesId);
	}
	
	public String getQuestionNumber() {
		return questionDAOImpl.getQuestionCount();
	}
	
	public void save(List<Question> questionList) {
		questionDAOImpl.save(questionList);
	}
	
	public void delete(Integer questionId) {
		questionDAOImpl.delete(questionId);
	}

}
