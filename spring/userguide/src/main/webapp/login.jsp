<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
<link rel="stylesheet" href="static/css/login.css">
<link rel="stylesheet" href="static/css/styles.css">
<!-- <script src="js/jquery-3.1.1.min.js"></script> -->
<script src="static/js/jquery-1.12.4.js"
	integrity="sha256-Qw82+bXyGq6MydymqBxNPYTaUXXq7c8v3CwiYwLLNXU="
	crossorigin="anonymous"></script>
</head>
<body>
	<form action="<c:url value='/j_spring_security_check' />" method='POST'>
		
			<%if ("true".equals(request.getParameter("error"))) { %>
			  	<div class="errorMsg">
			   		Username or password is incorrect.
			   	</div>
			<% }	%>
			<%if ("true".equals(request.getParameter("logout"))) { 
				session = request.getSession(false);
				
				if(session != null) {
				    session.invalidate();
				}
				//request.getRequestDispatcher("/login.jsp").forward(request,response);
			%>
			  	<div class="errorMsg">
			   		User is logged out. <%=session %>
			   	</div>
			<% }	%>
		
		<div class="container">
			<label><b>Username</b></label> <input type="text"
				placeholder="Enter Username" id="username" name="username" required>
			<label><b>Password</b></label> <input type="password"
				placeholder="Enter Password" id="password" name="password" required>
			<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" /> <input type="submit" class="button"
				value="Login"> <input type="button" class="cancelbtn"
				value="Cancel">
		</div>
	</form>
</body>
</html>