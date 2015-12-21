<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>   
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>HelloWorld page</title>
</head>
<body>
    <form:form action="verifyJson" method="post" name="inputBean" commandName="inputBean">
    	<textarea  name="jsonInput" cols="50" rows="10">${inputBean.jsonInput}</textarea>
    	<br>
    	<textarea  name="jsonOutput" cols="50" rows="10">${inputBean.jsonOutput}</textarea>
    	<input type="submit" value="Submit">
    </form:form>
</body>
</html>
