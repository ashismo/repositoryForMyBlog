<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="<%=request.getContextPath()%>/resources/js/admin/doctorManagement.js"></script>

<!-- Chart  ENDS -->
<script src="<%=request.getContextPath()%>/resources/js/Highcharts-2.2.5/js/highcharts.js"></script>
<script src="<%=request.getContextPath()%>/resources/js/Highcharts-2.2.5/js/modules/exporting.js"></script>
<!-- Chart  ENDS -->

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body>

<table border="0" width="100%" cellpadding="0" cellspacing="0" id="content-table">
<!--tr>
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

			<div id="doctorManagement" class="easyui-panel" style="width:900px;height:100px;padding:10px;"
	
					title="Search Doctor" 
	
					collapsible="true" minimizable="false"
	
					maximizable=true closable="false">
				<table width="100%">
					<tr>
						<td>
							<input id="doctorSearch" class="easyui-searchbox"
								searcher="searchDoctor"
								prompt="Search Doctor" menu="#searchCriteria" style="width:400px"></input>
				
							<div id="searchCriteria" style="width:140px">
								<div name="doctorName" iconCls="icon-ok">Doctor Name</div>
							</div>
						</td>
						<td>
							<a href="#" class="easyui-linkbutton addNewDoctor" iconCls="icon-add">Add New Doctor</a>
						</td>
					</tr>
				</table>
			</div>
		
		</div>
	<div id="doctorDiv" style="padding:10px;display:none;">
			<table id="doctorListTable"></table>
	</div>
	<form id="hiddenFieldForm">
		<input type="hidden" name="doctorId"/>
	</form>
	
	<div id="doctorDisplayWindow" class="easyui-window easyui-dialog" closed="true" modal="true"
		title="Create/View/Edit Doctor" style="padding:20px;width:750px;height:630px;"
		buttons="#dlg-buttons">
		<div id="doctorDisplayDiv" style="padding:10px;display:none;">
				<form id="doctorDisplayForm" method="post" novalidate>
					<div>
						<input type="hidden" name="doctorId" value="0" class="easyui-validatebox inp-form"/>
						<table>
							<tr>
								<td>
									<table>
										<tr height="40">
											<td class="td-prop">Doctor Name:*</td>
											<td class="td-prop"><input class="easyui-validatebox inp-form" type="text" required="true" name="doctorName"></td>
										</tr>
										<tr height="40">
											<td class="td-prop">Doctor Address1:*</td>
											<td class="td-prop"><input class="easyui-validatebox inp-form" required="true" type="text" name="doctorAddr1"></td>
										</tr>
										<tr height="40">
											<td class="td-prop">Doctor Address2:</td>
											<td class="td-prop"><input class="easyui-validatebox inp-form" type="text" name="doctorAddr2"></td>
										</tr>
										<tr height="40">
											<td>State:*</td>
											<td>
												<s:include value="../common/States.jsp"></s:include>
											</td>
										</tr>
										<tr height="40">
											<td>PIN:</td>
											<td><input class="easyui-validatebox inp-form" name="pin"></td>
										</tr>
										<tr height="40">
											<td>Fax:</td>
											<td><input class="easyui-validatebox inp-form" name="fax"></td>
										</tr>
										<tr height="40">
											<td>Phone1:*</td>
											<td><input class="easyui-validatebox inp-form" name="phone1"></td>
										</tr>
										<tr height="40">
											<td>Phone2:</td>
											<td><input class="easyui-validatebox inp-form" name="phone2"></td>
										</tr>
										<tr height="40">
											<td>Mobile1:</td>
											<td><input class="easyui-validatebox inp-form" name="mob1" required="true"></td>
										</tr>
										<tr height="40">
											<td>Mobile2:</td>
											<td><input class="easyui-validatebox inp-form" name="mob2"></td>
										</tr>
									</table>
								</td>
								<td width="20"></td>
								<td>
									<table>
										<tr height="40" class="td-prop">
											<td>Association Date:*</td>
											<td><input name="dateOfAssociation" class="datebox"></td>
										</tr>
										<tr height="40" class="td-prop">
											<td>Release Date:</td>
											<td><input name="dateOfRelease" class="datebox"></td>
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
											<td>Qualification:*</td>
											<td><input class="easyui-validatebox inp-form" name="qualification" required="true"></td>
										</tr>
										<tr height="40">
											<td>Speciality:*</td>
											<td><input class="easyui-validatebox inp-form" name="speciality" required="true"></td>
										</tr>
										<tr height="40">
											<td>Description:</td>
											<td><textarea class="easyui-validatebox" style="height:100px;" name="doctorDesc"></textarea></td>
										</tr>
									</table>
								</td>
							</tr>
							<tr>
								<td colspan="2"></td>
								<td align="right">
									<div id="dlg-buttons">  
									    <a href="#" class="easyui-linkbutton submitCreateEditDoctor" iconCls="icon-ok">Save</a>
									    <a href="#" class="easyui-linkbutton deleteDoctor" iconCls="icon-remove">Delete</a>
									    <a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#doctorDisplayWindow').dialog('close')">Cancel</a>  
									</div>
								</td>
							</tr>
						</table>
					</div>
			    </form>
			</div>
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