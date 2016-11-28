<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
<link rel="stylesheet" href="css/login.css">
<!-- <script src="js/jquery-3.1.1.min.js"></script> -->
<script src="js/jquery-1.12.4.js"
	integrity="sha256-Qw82+bXyGq6MydymqBxNPYTaUXXq7c8v3CwiYwLLNXU="
	crossorigin="anonymous"></script>
</head>
<body>
	<form action="<c:url value='/j_spring_security_check' />" method='POST'>
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
