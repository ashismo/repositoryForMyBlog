<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Security Question</title>
</head>
<body>
	<form:form action="verifySecurityQuestion" method="post"
		name="userProfile" commandName="userProfile" modelAttribute="userProfile">
		<form:errors path="errorMsg" cssStyle="color: #ff0000;"/>
		<table>
		    <tr>
		        <th>SL No.</th>
		        <th>Question</th>
		        <th>Answer</th>
		    </tr>
		    <c:forEach items="${userProfile.securityQnAList}" var="qna" varStatus="status">
		        <tr>
		            <td align="center">${status.count}</td>
		            <td>
		            	<%-- <form:input type="hidden" path="securityQnAList[${status.index}].question" value="${qna.question}"/> --%>
		            	<input type="hidden" id="securityQnAList${status.index}.question" name="securityQnAList[${status.index}].question" value="${qna.question}"/>
		            	${qna.question}?
		            </td>
		            <td><input type="text" id="securityQnAList${status.index}.answer" name="securityQnAList[${status.index}].answer" autocomplete="off" value=""/></td>
		        </tr>
		    </c:forEach>
		</table>  
		<br>
		<input type="submit" value="Submit">
	</form:form>
</body>
</html>