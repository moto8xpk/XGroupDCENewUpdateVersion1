package webappgroup.webappartifact.service;

import java.util.List;

import webappgroup.webappartifact.entity.Question;

public class XmlFormatter {

	public static List<Question> QuestionsFomatter(List<Question> questions) {
		String tmpStr="";
		for (Question question : questions) {
			tmpStr=question.getAnswersA();
			question.setAnswersA(StringFomatter(tmpStr));
			tmpStr=question.getAnswersB();
			question.setAnswersB(StringFomatter(tmpStr));
			tmpStr=question.getAnswersC();
			question.setAnswersC(StringFomatter(tmpStr));
			tmpStr=question.getAnswersD();
			question.setAnswersD(StringFomatter(tmpStr));
		}
		return questions;
	}
	public static String StringFomatter(String answerString) {
		String tmpStr="";
		
		if(answerString.contains("<") || answerString.contains(">") || answerString.contains("\"")) {
			if(answerString.contains("<"))
			{
				answerString=answerString.replace("<", "&lt;");
			}
			if(answerString.contains(">"))
			{
				answerString=answerString.replace(">", "&gt;");
			}
			if(answerString.contains("\""))
			{
				answerString=answerString.replace("\"", "&quot;");
			}
			return answerString;
		}
		return answerString;
	}
}
