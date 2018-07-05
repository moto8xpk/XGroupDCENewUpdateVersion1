package webappgroup.webappartifact.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonView;



public class Answers {

	
	List<Answer> answerList;

	public List<Answer> getAnswerList() {
		return answerList;
	}

	public void setAnswerList(List<Answer> answerList) {
		this.answerList = answerList;
	}

	public Answers(List<Answer> answerList) {
		super();
		this.answerList = answerList;
	}

	public Answers() {
		super();
	}
	
}
