package webappgroup.model;

public class Question {
private Integer QuestionId;
private String QuestionContent;
private String AnswersA;
private String AnswersB;
private String AnswersC;
private String AnswersD;
public Integer getQuestionId() {
	return QuestionId;
}
public void setQuestionId(Integer questionId) {
	QuestionId = questionId;
}
public String getQuestionContent() {
	return QuestionContent;
}
public void setQuestionContent(String questionContent) {
	QuestionContent = questionContent;
}
public String getAnswersA() {
	return AnswersA;
}
public void setAnswersA(String answersA) {
	AnswersA = answersA;
}
public String getAnswersB() {
	return AnswersB;
}
public void setAnswersB(String answersB) {
	AnswersB = answersB;
}
public String getAnswersC() {
	return AnswersC;
}
public void setAnswersC(String answersC) {
	AnswersC = answersC;
}
public String getAnswersD() {
	return AnswersD;
}
public void setAnswersD(String answersD) {
	AnswersD = answersD;
}
public Question(Integer questionId, String questionContent, String answersA, String answersB, String answersC,
		String answersD) {
	super();
	QuestionId = questionId;
	QuestionContent = questionContent;
	AnswersA = answersA;
	AnswersB = answersB;
	AnswersC = answersC;
	AnswersD = answersD;
}
public Question() {
	super();
}
}
