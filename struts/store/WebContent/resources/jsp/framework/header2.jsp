<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<%@page import="com.ashish.medicine.login.UserBean"%>
<%@page import="com.ashish.medicine.util.MedicineConstants"%><html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title></title>
<!-- Panel  STARTS -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/js/jquery-easyui-1.2.6/themes/default/easyui.css">

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/js/jquery-easyui-1.2.6/themes/icon.css">

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/js/jquery-easyui-1.2.6/demo/demo.css">

<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery-easyui-1.2.6/jquery.easyui.min.js"></script>

<!-- Panel  ENDS -->
<!-- script src="<%=request.getContextPath()%>/resources/js/jquery/jquery.edatagrid.js"></script-->
<script src="<%=request.getContextPath()%>/resources/js/jquery.alerts-1.1/jquery.alerts.js" type="text/javascript"></script>
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/js/jquery.alerts-1.1/jquery.alerts.css" type="text/css" media="screen" title="default" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/js/dashboard/css/screen.css" type="text/css" media="screen" title="default" />
<script src="<%=request.getContextPath()%>/resources/js/common/header.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/resources/js/jquery/json2.js" type="text/javascript"></script>
</head>
<body> 
<!-- Start: page-top-outer -->
<div id="page-top-outer">    

<!-- Start: page-top -->
<div id="page-top">

	<!-- start logo -->
	<div id="logo">
	<a href=""><img src="<%=request.getContextPath()%>/resources/js/dashboard/images/shared/logo.png" width="156" height="40" alt="" /></a>
	</div>
	<!-- end logo -->
	
 	<div class="clear"></div>

</div>
<!-- End: page-top -->

</div>
<!-- End: page-top-outer -->
	
<div class="clear">&nbsp;</div>
 
<!--  start nav-outer-repeat................................................................................................. START -->
<div class="nav-outer-repeat"> 
<!--  start nav-outer -->
<%
	UserBean userBean = (UserBean)session.getAttribute(MedicineConstants.USER_PROFILE);
	String role = null;
	if(userBean != null && userBean.getUser() != null) {
		role = userBean.getUser().getRole();
	}
%>
<div class="nav-outer"> 

		<!-- start nav-right -->
		<div id="nav-right">
			<%if(MedicineConstants.SUPER_ADMIN.equals(role)) {%>
				<div class="showhide-account">
					<a href="<%=request.getContextPath()%>/admin/userManagement.action">
						<img src="<%=request.getContextPath()%>/resources/js/dashboard/images/shared/nav/nav_userdetails.gif" width="93" height="19" alt="" />
					</a>
				</div>
				<div class="nav-divider">&nbsp;</div>
				<div class="showhide-account">
					<a href="<%=request.getContextPath()%>/admin/account.action">
						<img src="<%=request.getContextPath()%>/resources/js/dashboard/images/shared/nav/nav_shopdetails.gif" width="93" height="19" alt="" />
					</a>
				</div>
				<div class="nav-divider">&nbsp;</div>
			<%} %>
			<div class="showhide-account">
				<a href="<%=request.getContextPath()%>/admin/useraccount.action">
					<img src="<%=request.getContextPath()%>/resources/js/dashboard/images/shared/nav/nav_myaccount.gif" width="93" height="14" alt="" />
				</a>
			</div>
			<div class="nav-divider">&nbsp;</div>
			<a href="logout.action" id="logout"><img src="<%=request.getContextPath()%>/resources/js/dashboard/images/shared/nav/nav_logout.gif" width="64" height="14" alt="" /></a>
			<div class="clear">&nbsp;</div>
		
			<!--  start account-content -->	
			<div class="account-content">
			<div class="account-drop-inner">
				<a href="" id="acc-settings">Settings</a>
				<div class="clear">&nbsp;</div>
				<div class="acc-line">&nbsp;</div>
				<a href="" id="acc-details">Personal details </a>
				<div class="clear">&nbsp;</div>
				<div class="acc-line">&nbsp;</div>
				<a href="" id="acc-project">Project details</a>
				<div class="clear">&nbsp;</div>
				<div class="acc-line">&nbsp;</div>
				<a href="" id="acc-inbox">Inbox</a>
				<div class="clear">&nbsp;</div>
				<div class="acc-line">&nbsp;</div>
				<a href="" id="acc-stats">Statistics</a> 
			</div>
			</div>
			<!--  end account-content -->
		
		</div>
		<!-- end nav-right -->


		<!--  start nav -->
		<div class="nav">
		<div class="table">
		
		
		
		<div class="nav-divider">&nbsp;</div>
		                    
		<ul class="select"><li><a href="<%=request.getContextPath()%>/admin/home.action"><b>Medicine Management</b><!--[if IE 7]><!--></a><!--<![endif]-->
		<!--[if lte IE 6]><table><tr><td><![endif]-->
		<div class="select_sub">
			<ul class="sub">
				<!-- li><a href="<%=request.getContextPath()%>/poc/getPOCPage.action">All POC</a></li-->
				<li><a href="<%=request.getContextPath()%>/admin/companyManagementDetails.action">Manufacturer</a></li>
				<li><a href="<%=request.getContextPath()%>/admin/wholesellerManagementDetails.action">Whole Seller</a></li>
				<li><a href="<%=request.getContextPath()%>/admin/medRepManagementDetails.action">MR's</a></li>
				<li><a href="<%=request.getContextPath()%>/admin/medicineManagementDetails.action">Medicine</a></li>
				<li><a href="<%=request.getContextPath()%>/admin/customerManagementDetails.action">Customer</a></li>
				<li><a href="<%=request.getContextPath()%>/admin/doctorManagementDetails.action">Doctor's</a></li>
				<!--li><a href="<%=request.getContextPath()%>/admin/buyManagementDetails.action">Buy</a></li-->
				<li><a href="<%=request.getContextPath()%>/admin/invoiceManagementDetails.action">Invoice</a></li>
				<!--li><a href="#nogo">Profit/Loss Analysis</a></li-->
				<li><a href="<%=request.getContextPath()%>/admin/orderManagementDetails.action">Order</a></li>
				<%if(MedicineConstants.SUPER_ADMIN.equals(role)) {%>
					<li><a href="<%=request.getContextPath()%>/admin/transactionManagementDetails.action">Transactions</a></li>
				<%} %>
				<li><a target="_block" href="<%=request.getContextPath()%>/resources/html/Medicine%20Management%20User%20Guide.html">Resources</a></li>
			</ul>
		</div>
		<!--[if lte IE 6]></td></tr></table></a><![endif]-->
		</li>
		</ul>
		
		<div class="nav-divider">&nbsp;</div>
		
		
		<div class="clear"></div>
		</div>
		<div class="clear"></div>
		</div>
		<!--  start nav -->

</div>
<div class="clear"></div>
<!--  start nav-outer -->
</div>
<!--  start nav-outer-repeat................................................... END -->
</body>
</html>