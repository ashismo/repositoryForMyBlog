<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="<%=request.getContextPath()%>/resources/js/admin/pocManagement.js"></script>

<!-- Chart  ENDS -->
<script src="<%=request.getContextPath()%>/resources/js/Highcharts-2.2.5/js/highcharts.js"></script>
<script src="<%=request.getContextPath()%>/resources/js/Highcharts-2.2.5/js/modules/exporting.js"></script>

<!--  Google chart -->
<script type="text/javascript" src="https://www.google.com/jsapi"></script> 
 <script type="text/javascript">
// Load the Visualization API and the piechart package.       
	google.load('visualization', '1.0', {'packages':['corechart']});        
	// Set a callback to run when the Google Visualization API is loaded.       
	google.setOnLoadCallback(drawChart);        
	</script>
<!-- Chart  ENDS -->

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body>

	
<table border="0" width="100%" cellpadding="0" cellspacing="0" id="content-table">
<tr>
	<th rowspan="3" class="sized"><img src="<%=request.getContextPath()%>/resources/js/dashboard/images/shared/side_shadowleft.jpg" width="20" height="300" alt="" /></th>
	<th class="topleft"></th>
	<td id="tbl-border-top">&nbsp;</td>
	<th class="topright"></th>
	<th rowspan="3" class="sized"><img src="<%=request.getContextPath()%>/resources/js/dashboard/images/shared/side_shadowright.jpg" width="20" height="300" alt="" /></th>
</tr>
<tr>
	<td id="tbl-border-left"></td>
	<td>
	<!--  start content-table-inner -->
	<div id="content-table-inner">
	
	<table border="0" width="100%" cellpadding="0" cellspacing="0">
	<tr valign="top">
	<td>
		<div style="height:100px; background:#fafafa;padding:10px;">

			<div id="companyManagement" class="easyui-panel" style="width:900px;height:100px;padding:10px;"
	
					title="Search Company" 
	
					collapsible="true" minimizable="false"
	
					maximizable=true closable="false">
				<table width="100%">
					<tr>
						<td>
							<input id="companySearch" class="easyui-searchbox"
								searcher="searchCompany"
								prompt="Search Company" menu="#searchCriteria" style="width:400px"></input>
				
							<div id="searchCriteria" style="width:140px">
								<div name="companyName" iconCls="icon-ok">Company Name</div>
								<div name="companyPIN">Company PIN</div>
							</div>
						</td>
						<td>
							<input type="button" value="Add New Company" name="addCoBtn" class="button black addNewCompany" style="display:block"/>
						</td>
					</tr>
				</table>
			</div>
		
		</div>
	<div id="companyDiv" style="padding:10px;display:none;">
			<table id="companyListTable"></table>
	</div>	
	<div id="companyDisplayWindow" class="easyui-window" closed="true" modal="true" 
		title="Create/View/Edit Company" style="width:600px;height:400px;">
		<div id="companyDisplayDiv" style="padding:10px;display:none;">
			<!--div id="companyDisplayPanel" class="easyui-panel small-form"
		
						title="View/Edit Company" 
		
						collapsible="true" minimizable="false"
		
						maximizable=true closable="false"-->
				<form id="companyDisplayForm" method="post" novalidate>
					<div>
						<input type="hidden" name="companyId" class="easyui-validatebox inp-form"/>
						<table>
							<tr>
								<td class="td-prop">Company Name:</td>
								<td class="td-prop"><input class="easyui-validatebox inp-form" type="text" required="true" name="companyName" validType="length[1,3]"></td>
							</tr>
							<tr>
								<td class="td-prop">Company Address1:</td>
								<td class="td-prop"><input class="easyui-validatebox inp-form" required="true" type="text" name="companyAddr1"></td>
							</tr>
							<tr>
								<td class="td-prop">Company Address2:</td>
								<td class="td-prop"><input class="easyui-validatebox inp-form" type="text" name="companyAddr1"></td>
							</tr>
							<tr>
								<td>Phone1:</td>
								<td><input class="easyui-validatebox inp-form" required="true" name="phone1" validType="url"></td>
							</tr>
							<tr>
								<td>Description:</td>
								<td><textarea class="easyui-validatebox" required="true" style="height:100px;"></textarea></td>
							</tr>
							<tr>
								<td colspan="2"><input type="button" class="form-submit submitCreateEditCompany" value="Submit"></td>
							</tr>
						</table>
					</div>
			    </form>
			</div>
		<!--/div-->
	</div>
	
	<!-- Graphs -->
	<div>
	<input type="button" value="Display Graph" name="displayGraph" class="button black displayGraph" style="display:block"/>
		<div id="chartsDisplayPanel" class="easyui-panel small-form"
							style="height:400px; background:#fafafa;padding:10px;"
							title="Chart Display" 
			
							collapsible="true" minimizable="false"
			
							maximizable=true closable="false">
		</div>
		</div>
	<!-- Pie Chart -->
	<div>
	<input type="button" value="Display Pie Chart" name="displayPieGraph" class="button displayPieGraph" style="display:block"/>
		<div id="pieChartsDisplayPanel" class="easyui-panel small-form"
							style="height:400px; background:#fafafa;padding:10px;"
							title="Chart Display" 
			
							collapsible="true" minimizable="false"
			
							maximizable=true closable="false">
		</div>
		</div>
		
		<!--  PDF Generator -->
		<div>
			<input type="button" value="PDF" name="displayPDF" class="button displayPDF" style="display:block"/>
		</div>
	</td>
	<td>

	<%@include file="/resources/jsp/framework/quickLinks.jsp" %>
</td>
</tr>
<tr>
<td><img src="<%=request.getContextPath()%>/resources/js/dashboard/images/shared/blank.gif" width="695" height="1" alt="blank" /></td>
<td></td>
</tr>
</table>
 
<div class="clear"></div>
 

</div>
<!--  end content-table-inner  -->
</td>
<td id="tbl-border-right"></td>
</tr>
<tr>
	<th class="sized bottomleft"></th>
	<td id="tbl-border-bottom">&nbsp;</td>
	<th class="sized bottomright"></th>
</tr>
</table>


</body>
</html>