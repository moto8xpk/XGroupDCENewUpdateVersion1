	<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
	<html xmlns="http://www.w3.org/1999/xhtml">
	<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />

		<title>DCE Exam</title>

	<style>
		<%@ include file="css/style1.css"%>
	</style>
	</head>

	<body>
		<div class="countdown">
				<div id="demo"></div>
		</div>
		<div id="page-wrap">

			<p id="heading">Exam test</p>
			<br/>

			<form name="myForm" method="post" id="quiz">
	            <ol>
 				<c:forEach var="ques" items="${questions}">
	                <li>
	                    <h3><c:out value="${ques.questionContent}"/></h3>

	                    <div>
	                        <input type="radio" name="question1" id="question-1-answers-A" value="A" />
	                        <label for="question-1-answers-A">A) ${ques.answersA } </label>
	                    </div>

	                    <div>
	                        <input type="radio" name="question1" id="question-1-answers-B" value="B" />
	                        <label for="question-1-answers-B">B) ${ques.answersB }</label>
	                    </div>

	                    <div>
	                        <input type="radio" name="question1" id="question-1-answers-C" value="C" />
	                        <label for="question-1-answers-C">C) ${ques.answersC }</label>
	                    </div>

	                    <div>
	                        <input type="radio" name="question1" id="question-1-answers-D" value="D" />
	                        <label for="question-1-answers-D">D) ${ques.answersD }</label>
	                    </div>
	                </li>
	             </c:forEach>
	            </ol>

	            <input id="submitBtnForm" type="submit" onclick="TestLog()" value="Submit Quiz" />

			</form>

		</div>
		<script type="text/javascript">
			
		<%@ include file="js/index1.js"%>
			
		</script>
		
		<script type="text/javascript">
			
		<%@ include file="js/countdown.js"%>
			
		</script>
	</body>

	</html>
