<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="<%=request.getContextPath()%>/resources/js/admin/wholeSellerManagement.js"></script>

<!-- Chart  ENDS -->
<script src="<%=request.getContextPath()%>/resources/js/Highcharts-2.2.5/js/highcharts.js"></script>
<script src="<%=request.getContextPath()%>/resources/js/Highcharts-2.2.5/js/modules/exporting.js"></script>
<!-- Chart  ENDS -->

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body>

<table border="0" width="100%" cellpadding="0" cellspacing="0" id="content-table">
<!-- tr>
	<th rowspan="3" class="sized"><img src="<%=request.getContextPath()%>/resources/js/dashboard/images/shared/side_shadowleft.jpg" width="20" height="300" alt="" /></th>
	<th class="topleft"></th>
	<td id="tbl-border-top">&nbsp;</td>
	<th class="topright"></th>
	<th rowspan="3" class="sized"><img src="<%=request.getContextPath()%>/resources/js/dashboard/images/shared/side_shadowright.jpg" width="20" height="300" alt="" /></th>
</tr-->
<tr>
	<td id="tbl-border-left"></td>
	<td>
	<!--  start content-table-inner -->
	<div id="content-table-inner">
	
	<table border="0" width="100%" cellpadding="0" cellspacing="0">
	<tr valign="top">
	<td>
		<div style="height:100px; background:#fafafa;padding:10px;">

			<div id="wholesellerManagement" class="easyui-panel" style="width:900px;height:100px;padding:10px;"
	
					title="Search Whole Seller" 
	
					collapsible="true" minimizable="false"
	
					maximizable=true closable="false">
				<table width="100%">
					<tr>
						<td>
							<input id="wholesellerSearch" class="easyui-searchbox"
								searcher="searchWholeSeller"
								prompt="Search Whole Seller" menu="#searchCriteria" style="width:400px"></input>
				
							<div id="searchCriteria" style="width:140px">
								<div name="wholesellerName" iconCls="icon-ok">Whole Seller Name</div>
								<div name="pin">Whole Seller PIN</div>
								<div name="state">Whole Seller State</div>
							</div>
						</td>
						<td>
							<a href="#" class="easyui-linkbutton addNewWholeSeller" iconCls="icon-add">Add New Whole Seller</a>
						</td>
					</tr>
				</table>
			</div>
		
		</div>
	<div id="wholesellerDiv" style="padding:10px;display:none;">
			<table id="wholesellerListTable"></table>
	</div>
	<form id="hiddenFieldForm">
		<input type="hidden" name="wholesellerId"/>
	</form>
	
	<div id="wholesellerDisplayWindow" class="easyui-window easyui-dialog" closed="true" modal="true"
		title="Create/View/Edit Whole Seller" style="padding:20px;width:750px;height:550px;"
		buttons="#dlg-buttons">
		<div id="wholesellerDisplayDiv" style="padding:10px;display:none;">
			<!--div id="wholesellerDisplayPanel" class="easyui-panel small-form"
		
						title="View/Edit Whole Seller" 
		
						collapsible="true" minimizable="false"
		
						maximizable=true closable="false"-->
				<form id="wholesellerDisplayForm" method="post" novalidate>
					<div>
						<input type="hidden" name="wholesellerId" class="easyui-validatebox inp-form"/>
						<table>
							<tr>
								<td>
									<table>
										<tr height="40">
											<td class="td-prop">Whole Seller Name:</td>
											<td class="td-prop"><input class="easyui-validatebox inp-form" type="text" required="true" name="wholesellerName"></td>
										</tr>
										<tr height="40">
											<td class="td-prop">Whole Seller Address1:</td>
											<td class="td-prop"><input class="easyui-validatebox inp-form" required="true" type="text" name="wholesellerAddr1"></td>
										</tr>
										<tr height="40">
											<td class="td-prop">Whole Seller Address2:</td>
											<td class="td-prop"><input class="easyui-validatebox inp-form" type="text" name="wholesellerAddr2"></td>
										</tr>
										<tr height="40">
											<td>State:</td>
											<td>
												<s:include value="../common/States.jsp"></s:include>
											</td>
										</tr>
										<tr height="40">
											<td>PIN:</td>
											<td><input class="easyui-validatebox inp-form" required="true" name="pin"></td>
										</tr>
										<tr height="40">
											<td>Fax:</td>
											<td><input class="easyui-validatebox inp-form" name="fax"></td>
										</tr>
										<tr height="40">
											<td>Phone1:</td>
											<td><input class="easyui-validatebox inp-form" name="phone1" required="true"></td>
										</tr>
										<tr height="40">
											<td>Phone2:</td>
											<td><input class="easyui-validatebox inp-form" name="phone2"></td>
										</tr>
									</table>
								</td>
								<td width="20"></td>
								<td>
									<table>
										<tr height="40">
											<td>Mobile1:</td>
											<td><input class="easyui-validatebox inp-form" name="mob1" required="true"></td>
										</tr>
										<tr height="40">
											<td>Mobile2:</td>
											<td><input class="easyui-validatebox inp-form" name="mob2"></td>
										</tr>
										<tr height="40">
											<td>Website:</td>
											<td><input class="easyui-validatebox inp-form" name="website"></td>
										</tr>
										<tr height="40">
											<td>Email:</td>
											<td><input class="easyui-validatebox inp-form" name="emailId" validType="email"></td>
										</tr>
										<tr height="40">
											<td>Description:</td>
											<td><textarea class="easyui-validatebox" style="height:100px;" name="wholesellerDesc"></textarea></td>
										</tr>
									</table>
								</td>
							</tr>
							<tr>
								<!-- td><input type="button" class="form-submit submitCreateEditWholeSeller" value="Submit"></td>
								<td><input type="button" class="button blue deleteWholeSeller" value="Delete"></td-->
								<td colspan="2"></td>
								<td align="right">
									<div id="dlg-buttons">  
									    <a href="#" class="easyui-linkbutton submitCreateEditWholeSeller" iconCls="icon-ok">Save</a>
									    <a href="#" class="easyui-linkbutton deleteWholeSeller" iconCls="icon-remove">Delete</a>
									    <a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#wholesellerDisplayWindow').dialog('close')">Cancel</a>  
									</div>
								</td>
							</tr>
						</table>
					</div>
			    </form>
			</div>
		<!--/div-->
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