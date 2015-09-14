<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="<%=request.getContextPath()%>/resources/js/admin/multipleMedicineListManagement.js"></script>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body>

	<div id="multipleMedicineDisplayWindow1" class="easyui-window" closed="true" modal="true" 
		title="Select Medicine Details" style="display:none;padding:20px;width:750px;height:550px;">
			<table id="multipleMedicineListTable1"></table>
	</div>	
	<div id="multipleMedicineDisplayWindow">  
		<table id="multipleMedicineListTable"></table>
	</div>
	<form id="hiddenFieldForm">
		<input type="hidden" name="medicineDetailsId"/>
	</form>
	
</body>
</html>