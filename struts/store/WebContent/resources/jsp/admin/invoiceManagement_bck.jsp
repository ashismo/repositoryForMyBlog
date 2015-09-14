<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="<%=request.getContextPath()%>/resources/js/admin/invoiceManagement.js"></script>

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

			<div id="invoiceManagement" class="easyui-panel" style="width:900px;height:100px;padding:10px;"
	
					title="Search Invoice" 
	
					collapsible="true" minimizable="false"
	
					maximizable=true closable="false">
				<table width="100%">
					<tr>
						<td>
							<input id="invoiceSearch" class="easyui-searchbox"
								searcher="searchInvoice"
								prompt="Search Invoice" menu="#searchCriteria" style="width:400px"></input>
				
							<div id="searchCriteria" style="width:140px">
								<div name="invoiceName" iconCls="icon-ok">Invoice Name</div>
								<div name="pin">Invoice PIN</div>
								<div name="state">Invoice State</div>
							</div>
						</td>
						<td>
							<input type="button" value="Add New Invoice" name="addCoBtn" class="button black addNewInvoice" style="display:block"/>
						</td>
					</tr>
				</table>
			</div>
		
		</div>
	<div id="invoiceDiv" style="padding:10px;display:none;">
			<table id="invoiceListTable"></table>
	</div>
	<form id="hiddenFieldForm">
		<input type="hidden" name="invoiceId"/>
	</form>
	<div id="invoiceDisplayWindow" class="easyui-dialog" closed="true" modal="true"
		title="Create/View/Edit Invoice" style="padding:20px;width:1050px;height:650px;"
		buttons="#dlg-buttons">
		<form id="invoiceDisplayForm">
			<table>
							<tr>
								<td>
									<table>
										<tr height="45">
											<td>Date of Purchase:</td>
											<td><input required="true" name="purchaseDate" class="datebox"></td>
										</tr>
										<tr height="45" id="customerNameTr">
											<td class="td-prop" width="40%">Customer's Name:</td>
											<td class="td-prop" width="60%">
												<table width="100%">
													<tr height="45">
														<td width="100%">
															<select id="customerList" name="customerId" style="width:200px;" required="true">
															</select>
														</td>
													</tr>
													<tr style="display:none;" id="customerListOther">
														<td width="100%">
															<table>
																<tr>
																	<td width="30%">
																		Enter
																	</td>
																	<td width="10%">&nbsp;</td>
																	<td width="60%">
																		<input class="easyui-validatebox inp-form" type="text" required="true" name="customerName">
																	</td>
																</tr>
															</table>
															
														</td>
													</tr>
												</table>
												
												
											</td>
										</tr>
										<tr height="45">
											<td>Customer Address:</td>
											<td><input class="easyui-validatebox inp-form" required="true" type="text" name="customerAddr1"></td>
										</tr>
										<tr height="45">
											<td>Customer Contact:</td>
											<td><input class="easyui-validatebox inp-form" type="text" name="customerMob1"></td>
										</tr>
									</table>
								</td>
								<td width="35"></td>
								<td>
									<table>
										<tr height="45" id="doctorNameTr">
											<td class="td-prop" width="40%">Doctor's Name:</td>
											<td class="td-prop" width="60%">
												<table width="100%">
													<tr height="45">
														<td width="100%">
															<select id="doctorList" name="doctorId" style="width:200px;" required="true">
															</select>
														</td>
													</tr>
													<tr style="display:none;" id="doctorListOther">
														<td width="100%">
															<table>
																<tr>
																	<td width="30%">
																		Enter
																	</td>
																	<td width="10%">&nbsp;</td>
																	<td width="60%">
																		<input class="easyui-validatebox inp-form" type="text" required="true" name="doctorName">
																	</td>
																</tr>
															</table>
															
														</td>
													</tr>
												</table>
											</td>
										</tr>
										<tr height="45">
											<td>Doctor's Address:</td>
											<td><input class="easyui-validatebox inp-form" type="text" name="doctorAddr1"></td>
										</tr>
										<tr height="45">
											<td>Doctor's Contact:</td>
											<td><input class="easyui-validatebox inp-form" type="text" name="doctorMob1"></td>
										</tr>
									</table>
								</td>
							</tr>
						</table>
			<table id="invoiceTable" style="width:990px;height:350px;padding:20px;"
					title="Manage Invoice" class="easyui-datagrid" iconCls="icon-edit" singleSelect="true"
					 url="admin/viewInvoice" fitColumns="true" rownumbers="true" showFooter="true">
				<thead valign="middle">
					<tr>
						<th field="invoiceDetailsId" width="1" hidden="true"></th>
						<th field="soldoutStock" width="40" align="left" editor="{type:'numberbox',options:{required:true}}">Qty.</th>
						<th field="medicineName" width="200" formatter="medicineListFormatter" editor="{type:'combobox',
								options:{valueField:'medicineId',
										textField:'medicineName',
										data: allMedicines , 
										required:true,
											onChange: function(newValue,oldValue){
												loadMedicineBatchList(newValue);
												loadCompanyList();
											}
										}
										}">Medicine Name</th>
						<th field="batchName" width="150" formatter="batchListFormatter" editor="{type:'combobox',
								options:{valueField:'batchId',
										textField:'batchName',
										data: allBatches , 
										required:true,
										onChange: function(newValue,oldValue){
												loadMedicineDetailsByBatch(newValue);
											}
										}}">Batch</th>
						<!-- th field="companyName" width="120" align="left" editor="text">Manufacturer</th-->
						<th field="companyName" width="150" formatter="companyListFormatter" editor="{type:'combobox',
								options:{valueField:'companyId',
										textField:'companyName',
										data: allCompanies , 
										required:true
										}}">Manufacturer</th>
						<th field="mfgDate" width="80" align="left">Mfg. Date</th>
						<th field="expDate" width="80" align="left">Exp. Date</th>
						<th field="schedule" width="200" align="left" editor="text">Schedule</th>
						<th field="totalPrice" width="80" align="right" editor="text">Total</th>
					</tr>
				</thead>
			</table>
		</form>
		<div id="dlg-buttons">  
		    <a href="#" class="easyui-linkbutton submitCreateEditInvoice" iconCls="icon-ok">Save</a>
		    <a href="#" class="easyui-linkbutton deleteInvoice" iconCls="icon-remove">Delete</a>
		    <a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#invoiceDisplayWindow').dialog('close')">Cancel</a>  
		</div>
		<div id="allHiddenFields" style="display:none;"></div>
		<!--table id="invoiceTable" title="Invoice" style="width:950px;height:550px"  
		        toolbar="#toolbar"  
		        rownumbers="true" fitColumns="true" singleSelect="true">  
		    <thead>  
		        <tr>  
		            <th field="medicineList" formatter="productFormatter" width="50" editor="{type:'combobox',options:{required:true}}">Medicine Name</th>  
		            <th field="invoiceAddr1" width="50" editor="{type:'validatebox',options:{required:true}}">Last Name</th>  
		            <th field="phone1" width="50" editor="text">Phone</th>  
		            <th field="phone2" width="50" editor="{type:'validatebox',options:{validType:'email'}}">Email</th>  
		        </tr>  
		    </thead>  
		</table>  
		<div id="dlg-buttons">  
		    <a href="#" class="easyui-linkbutton submitCreateEditInvoice" iconCls="icon-ok">Save</a>
		    <a href="#" class="easyui-linkbutton deleteInvoice" iconCls="icon-remove">Delete</a>
		    <a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#invoiceDisplayWindow').dialog('close')">Cancel</a>  
		</div-->  
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