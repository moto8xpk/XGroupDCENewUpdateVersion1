<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Document Converter Exam</title>
</head>
<body>
	<style>
		<%@ include file="jsp/css/style.css"%>
	</style>
	<div id="txt1">
		<h1>Please upload your the Exam Document</h1>
	</div>
	<div id="containner">
		<div id="tag1">
			<div style="float: left;">
			<form:form method="GET" action="/webappatifact/Questionlist">
				<b><button type="submit" id="btnListQuestion">Questions Page</button></b>
			</form:form>
			</div>
			<div style="float: right;">
				<b><button id="btnListQuestion" id="listQuestions">Export XML File</button></b>
			</div>
		</div>
		<div style="clear: both;"></div>
		<div id="tag2"></div>
	</div>

	<div class="file-upload">
		<div class="file-select">
		<%-- <form method="POST" action="${pageContext.request.contextPath}/upload" enctype="multipart/form-data">
   		<input type="file" name="file" /><br/>
    <input type="submit" value="Submit" />
		</form> --%>
			<div class="file-select-button" id="fileName">Choose File</div>
			<div class="file-select-name" id="noFile">No file chosen...</div>
			<input type="file" name="chooseFile" id="chooseFile">
		</div>
	</div>
	<div class="copyright">
		<c:if test="${2>1}">Document Converter Exam</c:if>
	</div>
</body>
<script type="text/javascript"
	src="//code.jquery.com/jquery-1.10.2.min.js"></script>


<script type="text/javascript">
	
<%@ include file="jsp/js/index.js"%>
	
</script>
</html>