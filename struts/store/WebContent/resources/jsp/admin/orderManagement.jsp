<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="<%=request.getContextPath()%>/resources/js/admin/orderManagement.js"></script>

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

			<div id="orderManagement" class="easyui-panel" style="width:900px;height:140px;padding:10px;"
	
					title="Search Order" 
	
					collapsible="true" minimizable="false"
	
					maximizable=true closable="false">
				<table width="100%">
					<tr style="height:45px;">
						<td>
							<input id="orderSearch" class="easyui-searchbox"
								searcher="searchOrder"
								prompt="Search Order" menu="#searchCriteria" style="width:400px"></input>
				
							<div id="searchCriteria" style="width:140px">
								<div name="lowStock" iconCls="icon-ok">Low Stocks</div>
								<div name="recentNotes">Recent Notes</div>
								<div name="executedOrder">Executed Orders</div>
							</div>
						</td>
					</tr>
					<tr id="lowStockMedicineTypeCriteria" style="display:block;height:45px;">
							<td class="td-prop" colspan="3">Medicine Type:
								<select id="medicineTypeList" class="easyui-combobox" name="medType" style="width:200px;" required="true">
									<option value='All'>Liquid and Non-Liquid</option>
									<option value='Non-Liquid'>Non-Liquid</option>
									<option value='Liquid'>Liquid</option>
								</select>
							</td>
					</tr>
					<tr id="lowStockCriteria" style="display:none;height:45px;">
							<td class="td-prop">Start Date: </td>
							<td class="td-prop">
								<input name="startDate" class="datebox">
							</td>
							<td class="td-prop">End Date: </td>
							<td class="td-prop">
								<input name="endDate" class="datebox">
							</td>
					</tr>
					
					<tr id="recentNotesTr" style="display:none;height:45px;">
							<td class="td-prop">Select Notes Criteria</td>
							<td class="td-prop">
								<select id="notesCriteria" class="easyui-combobox" name="notesCriteria" style="width:200px;" required="true">
									<option value="0" style="height:10px;";>All</option>
									<option value="1" style="height:10px;";>To Be Executed</option>
									<option value="2" style="height:10px;";>Executed</option>
								</select>
							</td>
					</tr>
				</table>
			</div>
		
		</div>
	<div id="orderDiv" style="padding:10px;display:none;">
			<table id="orderListTable"></table>
	</div>	
	<div id="orderDisplayWindow" class="easyui-window" closed="true" modal="true" 
		title="Create/View/Edit Order" style="padding:20px;width:600px;height:550px;">
		<div id="orderDisplayDiv" style="padding:10px;display:none;">
			<!--div id="orderDisplayPanel" class="easyui-panel small-form"
		
						title="View/Edit Order" 
		
						collapsible="true" minimizable="false"
		
						maximizable=true closable="false"-->
				<form id="orderDisplayForm" method="post" novalidate>
					<div>
						<input type="hidden" name="orderId" class="easyui-validatebox inp-form"/>
						<table>
							<tr>
								<td class="td-prop">Order Name:</td>
								<td class="td-prop"><input class="easyui-validatebox inp-form" type="text" required="true" name="orderName"></td>
							</tr>
							<tr>
								<td class="td-prop">Order Address1:</td>
								<td class="td-prop"><input class="easyui-validatebox inp-form" required="true" type="text" name="orderAddr1"></td>
							</tr>
							<tr>
								<td class="td-prop">Order Address2:</td>
								<td class="td-prop"><input class="easyui-validatebox inp-form" type="text" name="orderAddr1"></td>
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
								<td colspan="2"><input type="button" class="form-submit submitCreateEditOrder" value="Submit"></td>
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