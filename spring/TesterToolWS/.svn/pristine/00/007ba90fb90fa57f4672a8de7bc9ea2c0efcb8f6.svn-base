<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>   
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>HelloWorld page</title>
</head>
<body>
    <form:form action="restTester" method="post" name="inputBean" commandName="inputBean">
	    <table>
	    	<tr><td><input type="text"  name="url" size="100" value="${inputBean.url}"/></td></tr>
	    	<tr><td>
	    		<form:radiobutton path="requestMethod" value="GET" />GET 
	    		<form:radiobutton path="requestMethod" value="POST" />POST
	    		<form:radiobutton path="requestMethod" value="DELETE" />DELETE
	    		<form:radiobutton path="requestMethod" value="PUT" />PUT
			</td></tr>
			<tr><td><textarea  name="jsonInput" cols="50" rows="10">${inputBean.jsonInput}</textarea></td></tr>
	    	<tr><td><textarea  name="jsonOutput" cols="50" rows="10">${inputBean.jsonOutput}</textarea></td></tr>
	    	<tr><td><input type="submit" value="Submit"></td></tr>
	    </table>
    </form:form>
</body>
</html>
