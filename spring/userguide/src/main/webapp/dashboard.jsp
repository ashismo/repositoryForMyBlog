<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>

<head>
	<meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Manage your project Data</title>
  <link rel="stylesheet" href="static/js/jquery-ui-1.12.1/jquery-ui.css">
  <link rel="stylesheet" href="static/css/styles.css">
  <link rel="stylesheet" href="static/css/jquery.dataTables.min.css">
  <link rel="stylesheet" href="static/css/dataTables.jqueryui.min.css">
  <script src="static/js/jquery-1.12.4.js"
	integrity="sha256-Qw82+bXyGq6MydymqBxNPYTaUXXq7c8v3CwiYwLLNXU="
	crossorigin="anonymous"></script>
  <script src="static/js/jquery-ui-1.12.1/jquery-ui.js"></script>
  <script src="static/js/jquery.validate.js"></script>
  <script src="static/js/global.js"></script>
  <script src="static/js/jquery.dataTables.min.js"></script>
	
  <script>
  $( window ).on("load",function() {
	  $( "#accordion" ).accordion({
	      collapsible: true,
	      heightStyle: "content"
	    });	  
  });
  $( document ).ready(function() {
	  controlAccess();
	  refreshModuleEnvData(true);
	  
	  $( "#login" ).click(function() {
		  window.location.href = "login.jsp";
	  });
	  
	  $( ".section" ).click(function() {
		  controlAccess();
		  
		  var environment = $(this).hasClass('environment');
		  if(environment) {
			  refreshEnvData(false);
		  }
		  
		  var module = $(this).hasClass('module');
		  if(module) {
			  refreshModuleData(false);
		  }
		  
		  var moduleEnv = $(this).hasClass('moduleEnv');
		  if(moduleEnv) {
			  refreshModuleEnvData(false);
		  }
		  
		  var searchData = $(this).hasClass('search');
		  if(searchData) {
			  refreshModuleEnvData(true);
		  }
		  
		  var users = $(this).hasClass('users');
		  if(users) {
			  refreshUserData();
		  }
		  
		});
	  
	  // Add new Environment
	  $( ".masterData #searchEnv #newEnv" ).click(function() {
			openEnvDialog();
		});
	  
	  // Add new Module
	  $( ".masterData #newModule" ).click(function() {
			openModuleDialog();
		});
	  
	// Add new Module
	  $( ".masterData #newModuleEnv" ).click(function() {
			openModuleEnvDialog();
		});
	  
	
	// Add new User
	  $( ".masterData #newUser" ).click(function() {
			openUserDialog();
		});
	  
	  function controlAccess() {
		  var adminUser = $.ajax({
			    type: "GET",
			    url: "rest/svc/guide/isAdminUser",
			    async: false
			}).responseText;
		  
		  if(adminUser != "true") {
			  $(".masterData").remove();
		  } else {
			  $("#loginOption").hide();
		  }
		  if(adminUser != "true") {
			  var generalUser = $.ajax({
				    type: "GET",
				    url: "rest/svc/guide/isGeneralUser",
				    async: false
				}).responseText;
			 
			  if(generalUser != "true") {
				  //$(".search").remove();
				  $("#loginOption").show();
				  $("#logout").hide();
			  } else {
				  $("#loginOption").hide();
			  }
		  }
	  };
  
  });
  
  </script>

</head>

<body>
	<%-- <a href="<c:url value="login.jsp?logout=true" />" > Logout</a> --%>
	
	<form action="<c:url value='/j_spring_security_logout' />" method="POST">
	<div style="text-align: right">
		<input type="submit" class="buttonRed" id="logout"
				value="Logout">
	</div>
	</form>
	<div id="loginOption" style="text-align: right">
		<input type="button" class="buttonGreen" id="login"
				value="Login">
	</div>
	
	

<div id="accordion">
  <h3 class="section search">Search</h3>
  <div class="searchData">
    <jsp:include page="search.jsp" />
  </div>
  <h3 class="section masterData users">Create User</h3>
  <div class="masterData">
    <jsp:include page="users.jsp" />
  </div>
  <h3 class="section masterData moduleEnv">URL Management</h3>
  <div class="masterData">
    <jsp:include page="url.jsp" />
  </div>
  <h3 class="section masterData module">Project Management</h3>
  <div class="masterData">
    <jsp:include page="projects.jsp" />
  </div>
  <h3 class="section masterData environment">Environment Management</h3>
  <div class="masterData">
    <jsp:include page="env.jsp" />
  </div>
</div>
 


</body>
</html>
