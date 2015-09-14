<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="<%=request.getContextPath()%>/resources/js/admin/invoiceManagement.js"></script>
<script src="<%=request.getContextPath()%>/resources/js/admin/invoiceManagementExt.js"></script>

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
		<div style="height:130px; background:#fafafa;padding:10px;">

			<div id="invoiceManagement" class="easyui-panel" style="width:900px;height:130px;padding:10px;"
	
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
								<div name="customerName" iconCls="icon-ok">Customer Name</div>
								<div name="customerMob1">Customer Phone</div>
								<div name="doctorName">Doctor Name</div>
							</div>
						</td>
						<td>
							<a href="#" class="easyui-linkbutton addNewInvoice" iconCls="icon-add">Add New Invoice</a>
						</td>
					</tr>
				</table>
				<table width="50%">
					<tr id="invoiceDateCriteria" style="height:45px;">
							<td class="td-prop">Start Date </td>
							<td class="td-prop">
								<input name="startDate" class="datebox">
							</td>
							<td class="td-prop">End Date </td>
							<td class="td-prop">
								<input name="endDate" class="datebox">
							</td>
					</tr>
				</table>
			</div>
		
		</div>
	<div id="invoiceDiv" style="padding:10px;display:none;">
			<table id="invoiceListTable"></table>
	</div>
	<div id="paymentWindow" class="easyui-dialog" closed="true" modal="true"
		title="Payment Details" style="padding:20px;width:850px;height:600px;">
		<table>
			<tr>
				<td>Total Amount: </td>
				<td></td>
				<td>&nbsp;&nbsp;&nbsp;</td>
				<td></td>
				<td id="totalAmt"></td>
			</tr>
			<tr>
				<td>Vat(%): </td>
				<td id="vat"></td>
				<td>&nbsp;&nbsp;&nbsp;</td>
				<td>Vat Amount: </td>
				<td id="totalVat"></td>
			</tr>
			<tr>
				<td>Discount(%): </td>
				<td id="discount"></td>
				<td>&nbsp;&nbsp;&nbsp;</td>
				<td>Discount Amount: </td>
				<td id="totalDiscount"></td>
			</tr>
			<tr>
				<td>Gross Amount: </td>
				<td></td>
				<td>&nbsp;&nbsp;&nbsp;</td>
				<td></td>
				<td id="gross"></td>
			</tr>
			<tr>
				<td>Paid Amount: </td>
				<td></td>
				<td>&nbsp;&nbsp;&nbsp;</td>
				<td></td>
				<td id="paidAmt"></td>
			</tr>
			<tr>
				<td>Due Amount: </td>
				<td></td>
				<td>&nbsp;&nbsp;&nbsp;</td>
				<td></td>
				<td id="due"></td>
			</tr>
		</table>
		<div id="paymentDiv" style="padding:10px;">
				<table id="paymentTable"></table>
		</div>
	</div>
	<form id="hiddenFieldForm">
		<input type="hidden" name="invoiceId"/>
	</form>
	<div id="invoiceDisplayWindow" class="easyui-dialog" closed="true" modal="true"
		title="Create/View/Edit Invoice" style="padding:20px;width:1000px;height:600px;"
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
				</form>
				<table id="invoiceTable" style="width:900px;height:300px;padding:20px"
					title="Manage Invoice" iconCls="icon-edit" singleSelect="true"
					url="" fitColumns="true" rownumbers="true" class="easyui-datagrid">
					<thead valign="middle">
						<tr>
							<th field="invoiceDetailsId" width="1" hidden="true"></th>
							<th field="invoiceId" width="1" hidden="true"></th>
							<th field="medicineDetailsId" width="1" hidden="true"></th>
							<th field="companyId" width="1" hidden="true"></th>
							<th field="medicineId" width="1" hidden="true"></th>
							<th field="batchId" width="1" hidden="true"></th>
							<th field="stock" width="1" hidden="true"></th>
							<th field="availableStock" width="1" hidden="true"></th>
							<th field="purchaseDate" width="1" hidden="true"></th>
							<th field="unitPrice" width="1" hidden="true"></th>
							<th field="medDose" width="1" hidden="true">Medicine Dose</th>
							<th field="medWeight" width="1" hidden="true">Medicine Weight</th>
							<th field="medType" width="1" hidden="true">Medicine Type</th>
							<th field="soldoutStock" width="40" align="left">Qty.</th>
							<th field="medicineName" width="200">Medicine Name</th>
							<th field="batchName" width="90">Batch</th>
							<th field="companyName" width="120">Manufacturer</th>
							<th field="mfgDate" width="60" align="left">Mfg. Date</th>
							<th field="expDate" width="60" align="left">Exp. Date</th>
							<th field="schedule" width="100" align="left">Schedule</th>
							<th field="totalPrice" width="100" align="right">Total Price</th>
						</tr>
					</thead>
				</table>
			<form id="invoiceDisplayFormPart2">
				<table>
					<tr>
						<td>
							<table>
								<tr height="45">
									<td>Vat(%)</td>
									<td><input class="easyui-validatebox inp-form" type="text" required="true" name="vat" onkeyup="calculateTotal(this);"></td>
								</tr>
								<tr height="45">
									<td>Discount(%)</td>
									<td><input class="easyui-validatebox inp-form" type="text" required="true" name="discount" size="10" onkeyup="calculateGrandTotalAfterDiscount(this);"></td>
								</tr>
							</table>
						</td>
						<td width="15"></td>
						<td>
							<table>
								<tr height="45">
									<td>Total Amount</td>
									<td><input class="easyui-validatebox inp-form" type="text" readonly="readonly" name="totalAmt"></td>
								</tr>
								<tr height="45">
									<td>Payment Amount</td>
									<td><input class="easyui-validatebox inp-form" type="text" readonly="readonly" name="totalPaid" onkeyup="calculateDue(this);"></td>
								</tr>
							</table>
						</td>
						<td width="15"></td>
						<td>
							<table>
								<tr height="45">
									<td>Due Amount</td>
									<td><input class="easyui-validatebox inp-form" type="text" readonly="readonly" name="due"></td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
				<div id="dlg-buttons">  
					<a href="#" class="easyui-linkbutton payCreateEditInvoice" iconCls="icon-pay">Pay</a>
				    <a href="#" class="easyui-linkbutton submitCreateEditInvoice" iconCls="icon-ok">Save</a>
				    <a href="#" class="easyui-linkbutton printCreateEditInvoice" iconCls="icon-print">Print</a>
				    <a href="#" class="easyui-linkbutton deleteInvoice" iconCls="icon-remove">Delete</a>
				    <a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#invoiceDisplayWindow').dialog('close')">Close</a>  
				</div>
			</form>
		<div id="allHiddenFields" style="display:none;"></div>
		
		<div id="addMedicineIntoInvoiceWindow" class="easyui-window easyui-dialog" closed="true" modal="true"
		title="Create/View/Edit Customer" style="padding:20px;width:850px;height:600px;"
		buttons="#dlg-buttons">
		<div id="addMedicineIntoInvoiceDiv" style="padding:10px;display:none;">
				<form id="addMedicineIntoInvoiceForm" method="post" novalidate>
					<div>
						<table>
							<tr>
								<td>
									<table>
										<tr style="display:none;">
											<td class="td-prop"></td>
											<td class="td-prop">
												<input class="easyui-validatebox inp-form" type="text" name="medicineDetailsId">
											</td>
										</tr>
										<tr style="display:none;">
											<td class="td-prop"></td>
											<td class="td-prop">
												<input class="easyui-validatebox inp-form" type="text" name="invoiceId">
											</td>
										</tr>
										<tr style="display:none;">
											<td class="td-prop"></td>
											<td class="td-prop">
												<input class="easyui-validatebox inp-form" type="text" name="invoiceDetailsId">
											</td>
										</tr>
										<tr height="45" id="availableStockTr">
											<td>Max. Available Stock</td>
											<td><input class="easyui-validatebox inp-form" type="text" name="availableStock" readonly="readonly" disabled="disabled"/></td>
										</tr>
										<tr height="40">
											<td class="td-prop">Quantity:</td>
											<td class="td-prop"><input class="easyui-validatebox inp-form" type="text" required="true" name="soldoutStock"></td>
										</tr>
										<tr height="45">
											<td>Manufacturer:</td>
											<td>
												<input type="text" hidden='true' name="companyName">
												<select id="companyList" class="easyui-combobox" name="companyId" style="width:200px;" required="true">
													<s:iterator value="companyList">
														<option value='<s:property value="companyId"/>'><s:property value="companyName"/></option>
													</s:iterator>
												</select>
											</td>
										</tr>
										<tr height="45" id="medicineNameTr">
											<td class="td-prop" width="40%">Medicine Name:</td>
											<td class="td-prop" width="60%">
												<table width="100%">
													<tr height="45">
														<td width="100%">
															<select id="medicineNameList" name="medicineId" style="width:200px;" required="true">
															</select>
														</td>
													</tr>
													<tr style="display:none;" id="medicineNameListOther">
														<td width="100%">
															<table>
																<tr>
																	<td width="30%">
																		Other
																	</td>
																	<td width="10%">&nbsp;</td>
																	<td width="60%">
																		<input class="easyui-validatebox inp-form" type="text" required="true" name="medicineName">
																	</td>
																</tr>
															</table>
															
														</td>
													</tr>
												</table>
											</td>
										</tr>
										<tr height="45" id="medicineBatchListTr">
											<td class="td-prop" width="40%">Medicine Batch:</td>
											<td class="td-prop" width="60%">
												<table width="100%">
													<tr height="45">
														<td width="100%">
															<s:property value="medicineBatchList"/>
															<select id="medicineBatchList" class="easyui-combobox" name="batchId" style="width:200px;" required="true">
																<s:iterator value="batchList">
																	<option value='<s:property value="batchId"/>'><s:property value="batchName"/></option>
																</s:iterator>
															</select>
														</td>
													</tr>
													<tr style="display:none;" id="medicineBatchListOther">
														<td width="100%">
															<table>
																<tr>
																	<td width="30%">
																		Other
																	</td>
																	<td width="10%">&nbsp;</td>
																	<td width="60%">
																		<input class="easyui-validatebox inp-form" required="true" type="text" name="batchName">
																	</td>
																</tr>
															</table>
															
														</td>
													</tr>
												</table>
											</td>
										</tr>
										<tr height="45">
											<td>Medicine Dose:</td>
											<td><input class="easyui-validatebox inp-form" type="text" name="medDose"></td>
										</tr>
										<tr height="45">
											<td>Medicine Weight:</td>
											<td><input class="easyui-validatebox inp-form" type="text" name="medWeight"></td>
										</tr>
										<tr height="45">
											<td>Medicine Type:</td>
											<td>
												<select id="medicineTypeList" class="easyui-combobox" name="medType" style="width:200px;">
													<option value='Non-Liquid'>Non-Liquid</option>
													<option value='Liquid'>Liquid</option>
												</select>
											</td>
										</tr>
									</table>
								</td>
								<td width="20"></td>
								<td>
									<table>
										<tr height="45">
											<td>Mfg Date:</td>
											<td><input name="mfgDate" class="datebox"></td>
										</tr>
										<tr height="45">
											<td>Exp Date</td>
											<td><input class="datebox" name="expDate"></td>
										</tr>
										<tr height="40">
											<td>Schedule:</td>
											<td><textarea class="easyui-validatebox" style="height:100px;" name="schedule"></textarea></td>
										</tr>
										<tr height="45" style="display:none;" id="stockTr">
											<td>Stock</td>
											<td><input class="easyui-validatebox inp-form" type="text" name="stock" onkeyup="updateAvailableStock(this);"></td>
										</tr>
										<tr height="45" style="display:none;" id="purchaseDateTr">
											<td>Date of Purchase:</td>
											<td><input name="purchaseDate" class="datebox"></td>
										</tr>
										<tr height="45" style="display:none;" id="unitPriceTr">
											<td>Unit Price(Purchased)</td>
											<td><input class="easyui-validatebox inp-form" type="text" required="true" name="unitPrice"></td>
										</tr>
										<tr height="45">
											<td>Unit Price(Soldout)</td>
											<td><input class="easyui-validatebox inp-form" required="true" type="text" name="soldoutUnitPrice"></td>
										</tr>
									</table>
								</td>
							</tr>
							<tr>
								<!-- td><input type="button" class="form-submit submitCreateEditCustomer" value="Submit"></td>
								<td><input type="button" class="button blue deleteCustomer" value="Delete"></td-->
								<td colspan="2"></td>
								<td align="right">
									<div id="dlg-buttons">  
									    <a href="#" class="easyui-linkbutton submitMedicineIntoInvoice" iconCls="icon-ok">Save</a>
									    <a href="#" class="easyui-linkbutton deleteMedicineIntoInvoice" iconCls="icon-remove">Delete</a>
									    <a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#addMedicineIntoInvoiceWindow').dialog('close')">Close</a>  
									</div>
								</td>
							</tr>
						</table>
					</div>
			    </form>
			</div>
		<!--/div-->
	</div>
	</div>
								
		
	</td>
	<td>

	<%@include file="/resources/jsp/framework/quickLinks.jsp" %>
	<%@include file="/resources/jsp/admin/multipleMedicineListManagement.jsp" %>
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