<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="<%=request.getContextPath()%>/resources/js/admin/medicineManagement.js"></script>

<!-- Chart  ENDS -->
<script src="<%=request.getContextPath()%>/resources/js/Highcharts-2.2.5/js/highcharts.js"></script>
<script src="<%=request.getContextPath()%>/resources/js/Highcharts-2.2.5/js/modules/exporting.js"></script>
<!-- Chart  ENDS -->

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body>

<div id="manufacturerList" style="display:none;">
	<select id="cc" class="easyui-combobox" name="state" style="width:200px;" required="true">
		<s:iterator value="companyList">
			<option value='<s:property value="companyId"/>'>
				<s:property value="companyName"/>
			</option>
		</s:iterator>
	</select>
</div>
	
<table border="0" width="100%" cellpadding="0" cellspacing="0" id="content-table">

<tr height="45">
	<td id="tbl-border-left"></td>
	<td>
	<!--  start content-table-inner -->
	<div id="content-table-inner">
	
	<table border="0" width="100%" cellpadding="0" cellspacing="0">
	<tr valign="top">
	<td>
		<div style="height:160px; background:#fafafa;padding:10px;">

			<div id="medicineManagement" class="easyui-panel" style="width:900px;height:160px;padding:10px;"
	
					title="Search Medicine" 
	
					collapsible="true" minimizable="false"
	
					maximizable=true closable="false">
				<table width="100%">
					<tr height="45">
						<td>
							<input id="medicineSearch" class="easyui-searchbox"
								searcher="searchMedicine"
								prompt="Search Medicine" menu="#searchCriteria" style="width:400px"></input>
				
							<div id="searchCriteria" style="width:140px">
								<div name="medicineName" iconCls="icon-ok">Medicine Name</div>
								<div name="companyName">Manufacturer</div>
								<div name="batchName">Batch</div>
							</div>
						</td>
						<td>
							<a href="#" class="easyui-linkbutton addNewMedicine" iconCls="icon-add">Add New Medicine</a>
						</td>
					</tr>
				</table>
				<table width="100%" id="medicineSearchCriteria">
					<tr style="height:35px;">
							<td class="td-prop">
								<input name="status" type="radio" value="0" checked="checked"/>Not Expired
							</td>
							<td class="td-prop">
								<input name="status" type="radio" value="1"/>Expired
							</td>
							<td class="td-prop">
								<input name="status" type="radio" value="2"/>Both
							</td>
							<td class="td-prop expiringAfter" style="display:none;">Expiring After</td>
							<td class="td-prop expiringAfter" style="display:none;">
								<input name="expiringAfter" class="datebox">
							</td>
							<td class="td-prop expiringBefore" style="display:none;">Expiring Before</td>
							<td class="td-prop expiringBefore" style="display:none;">
								<input name="expiringBefore" class="datebox">
							</td>
							
							<td class="td-prop" colspan="3">Medicine Type:
								<select id="medicineTypeList" class="easyui-combobox" name="medType" style="width:200px;" required="true">
									<option value='All'>Liquid and Non-Liquid</option>
									<option value='Non-Liquid'>Non-Liquid</option>
									<option value='Liquid'>Liquid</option>
								</select>
							</td>
					</tr>
				</table>
				<table width="50%" id="medicineSearchCriteria1">
					<tr style="height:35px;">
							<td class="td-prop">Purchase Date Between </td>
							<td class="td-prop">
								<input name="startDate" class="datebox">
							</td>
							<td class="td-prop"> And </td>
							<td class="td-prop">
								<input name="endDate" class="datebox">
							</td>
					</tr>
				</table>
			</div>
		
		</div>
	<div id="medicineDiv" style="padding:10px;display:none;">
			<table id="medicineListTable"></table>
	</div>	
	<form id="hiddenFieldForm">
		<input type="hidden" name="medicineDetailsId"/>
	</form>
	<div id="medicineDisplayWindow" class="easyui-window easyui-dialog" closed="true" modal="true" 
		title="Create/View/Edit Medicine" style="padding:20px;width:950px;height:650px;" buttons="#dlg-buttons">
		<div id="medicineDisplayDiv" style="padding:10px;display:none;">
				<form id="medicineDisplayForm" method="post" novalidate>
					<input type="hidden" name="medicineDetailsId"/>
					<div>
						<table>
							<tr>
								<td>
									<table>
										<tr height="45">
											<td>Date of Purchase:</td>
											<td><input name="purchaseDate" class="datebox" required="true"></td>
										</tr>
										<tr height="45">
											<td>Medicine Type:</td>
											<td>
												<select id="medicineTypeList" class="easyui-combobox" name="medType" style="width:200px;" required="true">
													<option value='Non-Liquid'>Non-Liquid</option>
													<option value='Liquid'>Liquid</option>
												</select>
											</td>
										</tr>
										<tr height="45">
											<td>Manufacturer:</td>
											<td>
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
										<tr height="45">
											<td>Medicine Dose:</td>
											<td><input class="easyui-validatebox inp-form" required="true" type="text" name="medDose"></td>
										</tr>
										<tr height="45">
											<td>Medicine Weight:</td>
											<td><input class="easyui-validatebox inp-form" type="text" required="true" name="medWeight"></td>
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
											<td class="td-prop">Wholeseller</td>
											<td class="td-prop">
												<select id="wholesellerList" class="easyui-combobox" name="wholesellerId" style="width:200px;" required="true">
													<s:iterator value="wholesellerList">
														<option value='<s:property value="wholesellerId"/>'><s:property value="wholesellerName"/></option>
													</s:iterator>
												</select>
											</td>
										</tr>
										<tr height="45">
											<td class="td-prop">Medical Representative</td>
											<td class="td-prop">
												<select id="medRepList1" name="medRepId" style="width:200px;"  required="true" class="easyui-combobox medRepList">
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
											<td><input required="true" name="mfgDate" class="datebox" type="hidden"></td>
										</tr>
										<tr height="45">
											<td>Exp Date</td>
											<td><input class="datebox" required="true" name="expDate"></td>
										</tr>
										<tr height="45">
											<td>Stock</td>
											<td><input class="easyui-validatebox inp-form" required="true" type="text" name="stock"></td>
										</tr>
										<tr height="45" id="availableStockTr">
											<td>Available Stock</td>
											<td><input class="easyui-validatebox inp-form" required="true" type="text" name="availableStock" disabled="disabled"></td>
										</tr>
										<tr height="45">
											<td>Unit Price(Purchased)</td>
											<td><input class="easyui-validatebox inp-form" required="true" type="text" name="unitPrice"></td>
										</tr>
										<tr height="45">
											<td>Unit Price(Soldout)</td>
											<td><input class="easyui-validatebox inp-form" required="true" type="text" name="soldoutUnitPrice"></td>
										</tr>
										<tr height="45">
											<td>Description:</td>
											<td><textarea class="easyui-validatebox" style="height:100px;width:200px;" name="medicineDesc"></textarea></td>
										</tr>
									</table>
								</td>
							</tr>
							<tr>
								<td colspan="2"></td>
								<td align="right">
									<div id="dlg-buttons">  
								    <a href="#" class="easyui-linkbutton submitCreateEditMedicine" iconCls="icon-ok">Save</a>
								    <a href="#" class="easyui-linkbutton deleteMedicineDetails" iconCls="icon-remove">Delete</a>
								    <a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#medicineDisplayWindow').dialog('close')">Cancel</a>  
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
<tr height="45">
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
<tr height="45">
	<th class="sized bottomleft"></th>
	<td id="tbl-border-bottom">&nbsp;</td>
	<th class="sized bottomright"></th>
</tr>
</table>


</body>
</html>