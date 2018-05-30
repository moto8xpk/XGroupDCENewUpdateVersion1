<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Document Converter Exam</title>
</head>
<body>
	<style>
		<%@ include file="css/style.css"%>
	</style>
	<div id="txt1">
		<h1>Please upload your the Exam Document</h1>
	</div>
	<div id="containner">
		<div id="tag1">
			<div id="questionListData">
			<!-- Show Question List page -->
			<b>
			<a id="btnListQuestion" href="${pageContext.request.contextPath}/Question/list">Questions Page</a>
			</b>
			</div>
			<div id="exportXmlData">
				<!-- Export xml file -->
			 	<b><a href="${pageContext.request.contextPath}/Questionlist" id="btnListQuestion" id="listQuestions">Export XML File</a></b>
			 </div>
		</div>
		<div style="clear: both;"></div>
		<div id="tag2"></div>
	</div>
	 <form:form modelAttribute="myUploadForm" method="POST" 
                        action="" enctype="multipart/form-data">
		Description:
        <br>
        <form:input path="description" style="width:300px;"/>                
        <br/><br/>    
 	<div class="file-upload">
		<div class="file-select"> 
			<!-- Upload file -->
		 	<div class="file-select-button" id="fileName">Choose File</div>
			<div class="file-select-name" id="noFile">No file chosen...</div> 
			<!-- <input type="file" name="chooseFile" id="chooseFile"> -->
			<form:input path="fileDatas" type="file" name="chooseFile" id="chooseFile"/>
 		</div>
	</div> 
	 <div id="submit-item"> 
	<input class="submitButton" type="submit" value="Upload">
	 </div> 
	</form:form> 
	<div class="copyright">
		<c:if test="${2>1}">Document Converter Exam</c:if>
	</div>
</body>
<script type="text/javascript"
	src="//code.jquery.com/jquery-1.10.2.min.js"></script>


<script type="text/javascript">
	
<%@ include file="js/index.js"%>
	
</script>
</html>
