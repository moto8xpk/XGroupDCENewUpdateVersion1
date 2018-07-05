<%-- <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%> --%>
	<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Question list Exam</title>
<style type="text/css">
<%@ include file="css/table.css"%>
</style>
</head>
<body>
	<div class="limiter">
		<div class="container-table100">
			<div class="wrap-table100">
				<div class="table100 ver1 m-b-110">
					<div class="table100-head">
						<table>
							<thead>
								<tr class="row100 head">
									<th class="cell100 column1">Question</th>
									<th class="cell100 column2">Answer A</th>
									<th class="cell100 column3">Answer B</th>
									<th class="cell100 column4">Answer C</th>
									<th class="cell100 column5">Answer D</th>
									<th class="cell100 column6">Right Answer</th>
								</tr>
							</thead>
						</table>
					</div>

					<div class="table100-body js-pscroll">
						<table>
							<tbody>
								<c:forEach var="ques" items="${questions}">
									<tr class="row100 body">
										<td class="cell100 column1"><c:out value="${ques.questionContent}"/></td>
										<td class="cell100 column2">${ques.answersA }</td>
										<td class="cell100 column3">${ques.answersB }</td>
										<td class="cell100 column4">${ques.answersC }</td>
										<td class="cell100 column5">${ques.answersD }</td>
										<td class="cell100 column6">${ques.answer }</td>
									</tr>
								</c:forEach>
								<c:if test="${empty questions}">
									no users added yet.
								</c:if>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>