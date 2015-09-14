<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="<%=request.getContextPath()%>/resources/js/admin/userManagement.js"></script>

<!-- Chart  ENDS -->
<script src="<%=request.getContextPath()%>/resources/js/Highcharts-2.2.5/js/highcharts.js"></script>
<script src="<%=request.getContextPath()%>/resources/js/Highcharts-2.2.5/js/modules/exporting.js"></script>
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

			<div id="userManagement" class="easyui-panel" style="width:900px;height:100px;padding:10px;"
	
					title="Search User" 
	
					collapsible="true" minimizable="false"
	
					maximizable=true closable="false">
				<table width="100%">
					<tr>
						<td>
							<input id="userSearch" class="easyui-searchbox"
								searcher="searchUser"
								prompt="Search User" menu="#searchCriteria" style="width:400px"></input>
				
							<div id="searchCriteria" style="width:140px">
								<div name="userName" iconCls="icon-ok">User Name</div>
								<div name="role">User Role</div>
							</div>
						</td>
						<td>
							<a href="#" class="easyui-linkbutton addNewUser" iconCls="icon-add">Add New User</a>
						</td>
					</tr>
				</table>
			</div>
		
		</div>
	<div id="userDiv" style="padding:10px;display:none;">
			<table id="userListTable"></table>
	</div>
	<form id="hiddenFieldForm">
		<input type="hidden" name="userId"/>
	</form>
	
	<div id="userDisplayWindow" class="easyui-window easyui-dialog" closed="true" modal="true"
		title="Create/View/Edit User" style="padding:20px;width:750px;height:550px;"
		buttons="#dlg-buttons">
		<div id="userDisplayDiv" style="padding:10px;display:none;">
			<!--div id="userDisplayPanel" class="easyui-panel small-form"
		
						title="View/Edit User" 
		
						collapsible="true" minimizable="false"
		
						maximizable=true closable="false"-->
				<form id="userDisplayForm" method="post" novalidate>
					<div>
						<input type="hidden" name="userId" class="easyui-validatebox inp-form"/>
						<table>
							<tr>
								<td>
									<table>
										<tr height="40">
											<td class="td-prop">User Name:</td>
											<td class="td-prop"><input class="easyui-validatebox inp-form" type="text" required="true" name="userName"></td>
										</tr>
										<tr height="40">
											<td class="td-prop">Name:</td>
											<td class="td-prop"><input class="easyui-validatebox inp-form" type="text" required="true" name="name"></td>
										</tr>
										<tr height="40">
											<td>Phone:</td>
											<td><input class="easyui-validatebox inp-form" name="phone"></td>
										</tr>
										<tr height="40">
											<td>Mobile:</td>
											<td><input class="easyui-validatebox inp-form" required="true" name="mobile"></td>
										</tr>
										<tr height="40">
											<td>Email:</td>
											<td><input class="easyui-validatebox inp-form" name="emailId" validType="email"></td>
										</tr>
										<tr height="40" id="userRoleTr">
											<td>Role:</td>
											<td>
												<select id="roleList" class="easyui-combobox" name="role" style="width:200px;" required="true">
													<option value='SuperAdmin'>Super Admin</option>
													<option value='Admin'>Admin</option>
													<option value='EndUser'>End User</option>
												</select>
											</td>
										</tr>
									</table>
								</td>
								<td>
									<table>
										<tr height="40">
											<td>Password:</td>
											<td><input class="easyui-validatebox inp-form" name="password" maxlength="30"></td>
										</tr>
										<tr height="40">
											<td>Confirm New Password:</td>
											<td><input class="easyui-validatebox inp-form" name="confirmPassword" maxlength="30"></td>
										</tr>
										<tr height="40">
											<td>Address:</td>
											<td><textarea class="easyui-validatebox" required="true" style="height:100px;" name="address"></textarea></td>
										</tr>
										<tr height="40">
											<td>Description:</td>
											<td><textarea class="easyui-validatebox" style="height:100px;" name="description"></textarea></td>
										</tr>
									</table>
								</td>
							</tr>
							<tr>
								<!-- td><input type="button" class="form-submit submitCreateEditUser" value="Submit"></td>
								<td><input type="button" class="button blue deleteUser" value="Delete"></td-->
								<td colspan="2"></td>
								<td align="right">
									<div id="dlg-buttons">  
									    <a href="#" class="easyui-linkbutton submitCreateEditUser" iconCls="icon-ok">Save</a>
									    <a href="#" class="easyui-linkbutton deleteUser" iconCls="icon-remove">Delete</a>
									    <a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#userDisplayWindow').dialog('close')">Cancel</a>  
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