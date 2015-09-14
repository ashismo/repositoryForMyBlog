<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="<%=request.getContextPath()%>/resources/js/admin/medRepManagement.js"></script>

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

			<div id="medRepManagement" class="easyui-panel" style="width:900px;height:100px;padding:10px;"
	
					title="Search Medical Representative" 
	
					collapsible="true" minimizable="false"
	
					maximizable=true closable="false">
				<table width="100%">
					<tr>
						<td>
							<input id="medRepSearch" class="easyui-searchbox"
								searcher="searchMedRep"
								prompt="Search Medical Representative" menu="#searchCriteria" style="width:400px"></input>
				
							<div id="searchCriteria" style="width:140px">
								<div name="medRepName" iconCls="icon-ok">MR Name</div>
								<div name="wholesellerName">Wholeseller</div>
							</div>
						</td>
						<td>
							<a href="#" class="easyui-linkbutton addNewMedRep" iconCls="icon-add">Add New Medical Representative</a>
						</td>
					</tr>
				</table>
			</div>
		
		</div>
	<div id="medRepDiv" style="padding:10px;display:none;">
			<table id="medRepListTable"></table>
	</div>
	<form id="hiddenFieldForm">
		<input type="hidden" name="medRepId"/>
	</form>
	
	<div id="medRepDisplayWindow" class="easyui-window easyui-dialog" closed="true" modal="true"
		title="Create/View/Edit Medical Representative" style="padding:20px;width:850px;height:550px;"
		buttons="#dlg-buttons">
		<div id="medRepDisplayDiv" style="padding:10px;display:none;">
			<!--div id="medRepDisplayPanel" class="easyui-panel small-form"
		
						title="View/Edit Medical Representative" 
		
						collapsible="true" minimizable="false"
		
						maximizable=true closable="false"-->
				<form id="medRepDisplayForm" method="post" novalidate>
					<div>
						<input type="hidden" name="medRepId" class="easyui-validatebox inp-form"/>
						<table>
							<tr>
								<td>
									<table>
										<tr height="40">
											<td class="td-prop">Medical Representative Name:</td>
											<td class="td-prop"><input class="easyui-validatebox inp-form" type="text" required="true" name="medRepName"></td>
										</tr>
										<tr height="45">
											<td>Date of Association:</td>
											<td><input required="true" name="dateOfAssociation" class="datebox" type="hidden"></td>
										</tr>
										<tr height="45">
											<td>Wholeseller's Name:</td>
											<td>
												<select id="wholesellerList" class="easyui-combobox" name="wholesellerId" style="width:200px;" required="true">
													<s:iterator value="wholesellerList">
														<option value='<s:property value="wholesellerId"/>'><s:property value="wholesellerName"/></option>
													</s:iterator>
												</select>
											</td>
										</tr>
										<tr height="40">
											<td class="td-prop">Medical Representative Address1:</td>
											<td class="td-prop"><input class="easyui-validatebox inp-form" required="true" type="text" name="medRepAddr1"></td>
										</tr>
										<tr height="40">
											<td class="td-prop">Medical Representative Address2:</td>
											<td class="td-prop"><input class="easyui-validatebox inp-form" type="text" name="medRepAddr2"></td>
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
											<td>Phone1:</td>
											<td><input class="easyui-validatebox inp-form" name="phone1" required="true"></td>
										</tr>
										<tr height="40">
											<td>Phone2:</td>
											<td><input class="easyui-validatebox inp-form" name="phone2"></td>
										</tr>
										<tr height="40">
											<td>PAN:</td>
											<td><input class="easyui-validatebox inp-form" name="panNo"></td>
										</tr>
										<tr height="40">
											<td>Voter Id card:</td>
											<td><input class="easyui-validatebox inp-form" name="voterIdNo" required="true"></td>
										</tr>
										<tr height="40">
											<td>Email:</td>
											<td><input class="easyui-validatebox inp-form" name="email" validType="email"></td>
										</tr>
										<tr height="40">
											<td>Description:</td>
											<td><textarea class="easyui-validatebox" style="height:100px;" name="medRepDesc"></textarea></td>
										</tr>
									</table>
								</td>
							</tr>
							<tr>
								<!-- td><input type="button" class="form-submit submitCreateEditMedRep" value="Submit"></td>
								<td><input type="button" class="button blue deleteMedRep" value="Delete"></td-->
								<td colspan="2"></td>
								<td align="right">
									<div id="dlg-buttons">  
									    <a href="#" class="easyui-linkbutton submitCreateEditMedRep" iconCls="icon-ok">Save</a>
									    <a href="#" class="easyui-linkbutton deleteMedRep" iconCls="icon-remove">Delete</a>
									    <a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#medRepDisplayWindow').dialog('close')">Cancel</a>  
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