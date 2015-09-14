<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="com.ashish.medicine.util.MedicineConstants"%>
<%@page import="com.ashish.medicine.login.UserBean"%><html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<%
	UserBean userBean = (UserBean)session.getAttribute(MedicineConstants.USER_PROFILE);
	String role = null;
	if(userBean.getUser() != null) {
		role = userBean.getUser().getRole();
	}
%>

<body>
	<!--  start related-activities -->
	<div id="related-activities">
		
		<!--  start related-act-top -->
		<div id="related-act-top">
		<img src="<%=request.getContextPath()%>/resources/js/dashboard/images/forms/header_related_act.gif" width="271" height="43" alt="" />
		</div>
		<!-- end related-act-top -->
		
		<!--  start related-act-bottom -->
		<div id="related-act-bottom">
		
			<!--  start related-act-inner -->
			<div id="related-act-inner">
			
				<div class="left"><a href=""><img src="<%=request.getContextPath()%>/resources/js/dashboard/images/forms/icon_plus.gif" width="21" height="21" alt="" /></a></div>
				<div class="right">
					<h5>Manage Billers</h5>
					<ul class="greyarrow">
						<li><a href="<%=request.getContextPath()%>/admin/companyManagementDetails.action">Manufacturer</a></li>
						<li><a href="<%=request.getContextPath()%>/admin/wholesellerManagementDetails.action">Whole Seller</a></li>
						<li><a href="<%=request.getContextPath()%>/admin/medRepManagementDetails.action">MR's</a></li>
						<li><a href="<%=request.getContextPath()%>/admin/customerManagementDetails.action">Customer</a></li>
						<li><a href="<%=request.getContextPath()%>/admin/doctorManagementDetails.action">Doctor's</a></li>
						<!--li><a href="#nogo">Profit/Loss Analysis</a></li-->
					</ul>
				</div>
				
				<div class="clear"></div>
				<div class="lines-dotted-short"></div>
				
				<div class="left"><a href=""><img src="<%=request.getContextPath()%>/resources/js/dashboard/images/forms/icon_plus.gif" width="21" height="21" alt="" /></a></div>
				<div class="right">
					<h5>Manage Invoice and Medicines</h5>
					<ul class="greyarrow">
						<li><a href="<%=request.getContextPath()%>/admin/medicineManagementDetails.action">Medicine</a></li>
						<li><a href="<%=request.getContextPath()%>/admin/invoiceManagementDetails.action">Invoice</a></li>
					</ul>
				</div>
				
				<div class="clear"></div>
				<div class="lines-dotted-short"></div>
				
				<div class="left"><a href=""><img src="<%=request.getContextPath()%>/resources/js/dashboard/images/forms/icon_edit.gif" width="21" height="21" alt="" /></a></div>
				<div class="right">
					<h5>Manage Transactions</h5>
					<ul class="greyarrow">
						<li><a href="<%=request.getContextPath()%>/admin/orderManagementDetails.action">Order</a></li>
						<%if(MedicineConstants.SUPER_ADMIN.equals(role)) {%>
						<li><a href="<%=request.getContextPath()%>/admin/transactionManagementDetails.action">Transactions</a></li>
						<%} %>
					</ul>
				</div>

				<div class="clear"></div>
				<div class="lines-dotted-short"></div>

				<div class="left"><a href=""><img src="<%=request.getContextPath()%>/resources/js/dashboard/images/forms/icon_edit.gif" width="21" height="21" alt="" /></a></div>
				<div class="right">
					<h5>Emergency Contacts</h5>
					<ul class="greyarrow">
						<li><a href="<%=request.getContextPath()%>/admin/contactsManagementDetails.action">Contacts</a></li>
					</ul>
				</div>

				<div class="clear"></div>
				<div class="lines-dotted-short"></div>
								
				<!-- div class="left"><a href=""><img src="<%=request.getContextPath()%>/resources/js/dashboard/images/forms/icon_plus.gif" width="21" height="21" alt="" /></a></div>
				<div class="right">
					<h5>Schedule</h5>
					<ul class="greyarrow">
						<li><a href="<%=request.getContextPath()%>/admin/scheduleManagementDetails.action">Schedule</a></li>
					</ul>
				</div>
												
				<div class="clear"></div>
				<div class="lines-dotted-short"></div-->
				
				<div class="left"><a href=""><img src="<%=request.getContextPath()%>/resources/js/dashboard/images/forms/icon_plus.gif" width="21" height="21" alt="" /></a></div>
				<div class="right">
					<h5>View Resources</h5>
					<ul class="greyarrow">
						<li><a target="_block" href="<%=request.getContextPath()%>/resources/html/Medicine%20Management%20User%20Guide.html">Resources</a></li>
					</ul>
				</div>
				
				<div class="clear"></div>
				<div class="lines-dotted-short"></div>
				
			</div>
			<!-- end related-act-inner -->
			<div class="clear"></div>
		
		</div>
		<!-- end related-act-bottom -->
	
	</div>
	<!-- end related-activities -->
</body>
</html>