<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Hello World title</title>
</head>
<body>
	<a href="<c:url value="logout" />" > Logout</a>
	<center>
		<h2>Hello World</h2>
		<h3>
			${message} ${name}
		</h3>
	</center>
</body>
</html>