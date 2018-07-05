package webappgroup.webappartifact.model;

public class Answer {

	String questionid;
	String answerques;
	
	
	public String getQuestionid() {
		return questionid;
	}


	public void setQuestionid(String questionid) {
		this.questionid = questionid;
	}


	public String getAnswerques() {
		return answerques;
	}


	public void setAnswerques(String answerques) {
		this.answerques = answerques;
	}


	@Override
	public String toString() {
		return "Answer [questionid=" + questionid + ", answerques=" + answerques + "]";
	}
}
