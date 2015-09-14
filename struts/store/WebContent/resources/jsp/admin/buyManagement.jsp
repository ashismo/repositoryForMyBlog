<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="<%=request.getContextPath()%>/resources/js/admin/buyManagement.js"></script>

<!-- Chart  ENDS -->
<script src="<%=request.getContextPath()%>/resources/js/Highcharts-2.2.5/js/highcharts.js"></script>
<script src="<%=request.getContextPath()%>/resources/js/Highcharts-2.2.5/js/modules/exporting.js"></script>
<!-- Chart  ENDS -->

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body>

	
<table bbuy="0" width="100%" cellpadding="0" cellspacing="0" id="content-table">
<tr>
	<th rowspan="3" class="sized"><img src="<%=request.getContextPath()%>/resources/js/dashboard/images/shared/side_shadowleft.jpg" width="20" height="300" alt="" /></th>
	<th class="topleft"></th>
	<td id="tbl-bbuy-top">&nbsp;</td>
	<th class="topright"></th>
	<th rowspan="3" class="sized"><img src="<%=request.getContextPath()%>/resources/js/dashboard/images/shared/side_shadowright.jpg" width="20" height="300" alt="" /></th>
</tr>
<tr>
	<td id="tbl-bbuy-left"></td>
	<td>
	<!--  start content-table-inner -->
	<div id="content-table-inner">
	
	<table bbuy="0" width="100%" cellpadding="0" cellspacing="0">
	<tr valign="top">
	<td>
		<div style="height:100px; background:#fafafa;padding:10px;">

			<div id="buyManagement" class="easyui-panel" style="width:900px;height:100px;padding:10px;"
	
					title="Search Buy" 
	
					collapsible="true" minimizable="false"
	
					maximizable=true closable="false">
				<table width="100%">
					<tr>
						<td>
							<input id="buySearch" class="easyui-searchbox"
								searcher="searchBuy"
								prompt="Search Buy" menu="#searchCriteria" style="width:400px"></input>
				
							<div id="searchCriteria" style="width:140px">
								<div name="buyName" iconCls="icon-ok">Buy Name</div>
								<div name="buyPIN">Buy PIN</div>
							</div>
						</td>
						<td>
							<input type="button" value="Add New Buy" name="addCoBtn" class="button black addNewBuy" style="display:block"/>
						</td>
					</tr>
				</table>
			</div>
		
		</div>
	<div id="buyDiv" style="padding:10px;display:none;">
			<table id="buyListTable"></table>
	</div>	
	<div id="buyDisplayWindow" class="easyui-window" closed="true" modal="true" 
		title="Create/View/Edit Buy" style="padding:20px;width:600px;height:550px;">
		<div id="buyDisplayDiv" style="padding:10px;display:none;">
			<!--div id="buyDisplayPanel" class="easyui-panel small-form"
		
						title="View/Edit Buy" 
		
						collapsible="true" minimizable="false"
		
						maximizable=true closable="false"-->
				<form id="buyDisplayForm" method="post" novalidate>
					<div>
						<input type="hidden" name="buyId" class="easyui-validatebox inp-form"/>
						<table>
							<tr>
								<td class="td-prop">Buy Name:</td>
								<td class="td-prop"><input class="easyui-validatebox inp-form" type="text" required="true" name="buyName"></td>
							</tr>
							<tr>
								<td class="td-prop">Buy Address1:</td>
								<td class="td-prop"><input class="easyui-validatebox inp-form" required="true" type="text" name="buyAddr1"></td>
							</tr>
							<tr>
								<td class="td-prop">Buy Address2:</td>
								<td class="td-prop"><input class="easyui-validatebox inp-form" type="text" name="buyAddr1"></td>
							</tr>
							<tr>
								<td>State:</td>
								<td>
									<div>
										<input class="easyui-combobox" 
												name="language"
												url="getStates.action" 
												valueField="id" 
												textField="text" 
												panelHeight="auto">
							        </div>
								</td>
							</tr>
							<tr>
								<td>PIN:</td>
								<td><input class="easyui-validatebox inp-form" required="true" name="pin"></td>
							</tr>
							<tr>
								<td>Fax:</td>
								<td><input class="easyui-validatebox inp-form" name="fax"></td>
							</tr>
							<tr>
								<td>Phone1:</td>
								<td><input class="easyui-validatebox inp-form" name="phone1"></td>
							</tr>
							<tr>
								<td>Phone2:</td>
								<td><input class="easyui-validatebox inp-form" name="phone2"></td>
							</tr>
							<tr>
								<td>Mobile1:</td>
								<td><input class="easyui-validatebox inp-form" name="mob1"></td>
							</tr>
							<tr>
								<td>Mobile2:</td>
								<td><input class="easyui-validatebox inp-form" name="mob2"></td>
							</tr>
							<tr>
								<td>Description:</td>
								<td><textarea class="easyui-validatebox" required="true" style="height:100px;"></textarea></td>
							</tr>
							<tr>
								<td colspan="2"><input type="button" class="form-submit submitCreateEditBuy" value="Submit"></td>
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
<td id="tbl-bbuy-right"></td>
</tr>
<tr>
	<th class="sized bottomleft"></th>
	<td id="tbl-bbuy-bottom">&nbsp;</td>
	<th class="sized bottomright"></th>
</tr>
</table>


</body>
</html>