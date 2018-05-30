package webappgroup.webappartifact.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "Question", uniqueConstraints = { @UniqueConstraint(columnNames = { "QuestionId" }) })

public class Question {

	private Integer questionId;
	private String questionContent;
	private String answersA;
	private String answersB;
	private String answersC;
	private String answersD;
	private String answer;
	private String classId;

	@Id
	@Column(name = "QuestionId")
	public Integer getQuestionId() {
		return questionId;
	}

	public void setQuestionId(Integer questionid) {
		questionId = questionid;
	}

	@Column(name = "QuestionContent")
	public String getQuestionContent() {
		return questionContent;
	}

	public void setQuestionContent(String questioncontent) {
		questionContent = questioncontent;
	}

	@Column(name = "AnswerA")
	public String getAnswersA() {
		return answersA;
	}

	public void setAnswersA(String answersa) {
		answersA = answersa;
	}

	@Column(name = "AnswerB")
	public String getAnswersB() {
		return answersB;
	}

	public void setAnswersB(String answersb) {
		answersB = answersb;
	}

	@Column(name = "AnswerC")
	public String getAnswersC() {
		return answersC;
	}

	public void setAnswersC(String answersc) {
		answersC = answersc;
	}

	@Column(name = "AnswerD")
	public String getAnswersD() {
		return answersD;
	}

	public void setAnswersD(String answersd) {
		answersD = answersd;
	}

	@Column(name = "RightAnswer")
	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer1) {
		answer = answer1;
	}

	@Column(name = "ClassId")
	public String getClassId() {
		return classId;
	}

	public void setClassId(String classid) {
		classId = classid;
	}

	public Question(Integer questionId, String questionContent, String answersA, String answersB, String answersC,
			String answersD, String answer, String classId) {
		super();
		this.questionId = questionId;
		this.questionContent = questionContent;
		this.answersA = answersA;
		this.answersB = answersB;
		this.answersC = answersC;
		this.answersD = answersD;
		this.answer = answer;
		this.classId = classId;
	}

	public Question() {
		super();
	}
}
