<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="<%=request.getContextPath()%>/resources/js/admin/transactionManagement.js"></script>

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
		<div style="height:160px; background:#fafafa;padding:10px;">

			<div id="transactionDetailsManagement" class="easyui-panel" style="width:900px;height:160px;padding:10px;"
	
					title="Search Transaction Details" 
	
					collapsible="true" minimizable="false"
	
					maximizable=true closable="false">
					<form id="transactionDetailsSearchForm" method="post" novalidate>
						<table width="100%">
							<tr>
								<td>
									<input id="transactionDetailsSearch" class="easyui-searchbox"
										searcher="searchTransactionDetails"
										prompt="Search Transaction Details" menu="#searchCriteria" style="width:400px"></input>
						
									<div id="searchCriteria" style="width:140px">
										<div name="income" iconCls="icon-ok">Income</div>
										<div name="expenditure">Expenditure</div>
										<div name="revenue">Revenue</div>
									</div>
								</td>
								<td>
									<a href="#" class="easyui-linkbutton addNewTransactionPurchase" iconCls="icon-add">Add New Transaction - Purchase</a>
								</td>
							</tr>
							</table>
							<table width="50%">
								<tr id="transactionDateCriteria" style="height:45px;">
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
							<table width="50%">
								<tr id="transactionDistributorCriteria" style="display:none;height:45px;">
										<td class="td-prop">Whole Seller </td>
										<td class="td-prop">
											<select id="wholesellerList1" class="easyui-combobox" name="wholesellerId" style="width:120px;">
												<s:iterator value="wholesellerList">
													<option value='<s:property value="wholesellerId"/>'><s:property value="wholesellerName"/></option>
												</s:iterator>
											</select>
										</td>
										<td class="td-prop">Medical Representative </td>
										<td class="td-prop">
											<select id="medRepList1" name="medRepId" style="width:120px;"  class="easyui-combobox medRepList">
											</select>
										</td>
										<td class="td-prop">Payment By</td>
										<td class="td-prop">
											<select id="paymentMode" class="easyui-combobox" name="paymentMode" style="width:120px;">
												<option value=''>All</option>
												<option value='Cash'>Cash</option>
												<option value='CreditCard'>Credit Card</option>
												<option value='DebitCard'>Debit Card</option>
												<option value='Cheque'>Cheque</option>
											</select>
										</td>
								</tr>
							</table>
							<table width="50%">
								<tr id="transactionCustomerCriteria" style="display:block;height:45px;">
										<td class="td-prop">Customer Name </td>
										<td class="td-prop">
											<input type="text" class="easyui-validatebox inp-form" name="customerName">
										</td>
										<td class="td-prop">Mobile </td>
										<td class="td-prop">
											<input type="text" class="easyui-validatebox inp-form" name="mob1">
										</td>
										<td class="td-prop">Payment By</td>
										<td class="td-prop">
											<select id="paymentMode" class="easyui-combobox" name="paymentMode" style="width:120px;">
												<option value=''>All</option>
												<option value='Cash'>Cash</option>
												<option value='CreditCard'>Credit Card</option>
												<option value='DebitCard'>Debit Card</option>
												<option value='Cheque'>Cheque</option>
											</select>
										</td>
								</tr>
						</table>
						<table width="50%">
								<tr id="revenueCriteria" style="display:none;height:45px;">
										<td class="td-prop">User Name </td>
										<td class="td-prop">
											<select id="userNameList" name="usersId" style="width:120px;"  class="easyui-combobox userNameList">
												<s:iterator value="usersList">
													<option value='<s:property value="userId"/>'><s:property value="userName"/></option>
												</s:iterator>
											</select>
										</td>
										<td class="td-prop">Payment By</td>
										<td class="td-prop">
											<select id="paymentMode" class="easyui-combobox" name="paymentMode" style="width:120px;">
												<option value=''>All</option>
												<option value='Cash'>Cash</option>
												<option value='CreditCard'>Credit Card</option>
												<option value='DebitCard'>Debit Card</option>
												<option value='Cheque'>Cheque</option>
											</select>
										</td>
								</tr>
						</table>
					</form>
			</div>
		
		</div>
	<div id="transactionDetailsDiv" style="padding:10px;display:none;">
			<table id="transactionDetailsListTable"></table>
	</div>
	
	<div id="transactionDetailsDisplayWindow" class="easyui-window easyui-dialog" closed="true" modal="true"
		title="Create/View/Edit Transaction Details" style="padding:20px;width:850px;height:450px;"
		buttons="#dlg-buttons">
		<div id="transactionDetailsDisplayDiv" style="padding:10px;display:none;">
				<form id="transactionDetailsDisplayForm" method="post" novalidate>
					<div>
						<input type="hidden" name="transactionDetailsId" class="easyui-validatebox inp-form"/>
						<table>
							<tr>
								<td>
									<table>
										<tr style="display:none;">
											<td class="td-prop">Total Paid Amount</td>
											<td class="td-prop"><input type="text" name="totalPaidAmount"></td>
										</tr>
										<tr height="40">
											<td class="td-prop">Payment Date</td>
											<td class="td-prop"><input class="datebox" required="true" name="paymentDate"></td>
										</tr>
										<tr height="40">
											<td class="td-prop">Whole Seller</td>
											<td class="td-prop">
												<select id="wholesellerList" class="easyui-combobox" name="wholesellerId" style="width:200px;" required="true">
													<s:iterator value="wholesellerList">
														<option value='<s:property value="wholesellerId"/>'><s:property value="wholesellerName"/></option>
													</s:iterator>
												</select>
											</td>
										</tr>
										<tr height="40">
											<td class="td-prop">Medical Representative</td>
											<td class="td-prop" id="medRepListAdd" style="display:none;">
												<select id="medRepList" name="medRepId" style="width:200px;" class="easyui-combobox medRepList">
												</select>
											</td>
											<td class="td-prop" id="medRepListModify" style="display:none;">
												<select id="medRepList2" name="medRepName" style="width:200px;"  class="easyui-combobox medRepList">
												</select>
											</td>
										</tr>
										<tr height="40">
											<td class="td-prop">Total Amount</td>
											<td class="td-prop"><input class="easyui-validatebox inp-form" type="text" required="true" name="totalAmt" onkeyup="calculateDueForPurchase(jQuery(this).val(), jQuery('input[name=totalPaid]').val());"></td>
										</tr>
										<tr height="40">
											<td class="td-prop">Paid Amount</td>
											<td class="td-prop"><input class="easyui-validatebox inp-form" type="text" required="true" name="totalPaid" onkeyup="calculateDueForPurchase(jQuery('input[name=totalAmt]').val(),jQuery(this).val());"></td>
										</tr>
										<tr height="40">
											<td class="td-prop">Total Due Amount</td>
											<td class="td-prop"><input class="easyui-validatebox inp-form" type="text" name="totalDueAmount" readonly="readonly"></td>
										</tr>
									</table>
								</td>
								<td width="20"></td>
								<td>
									<table>
										<tr height="40">
											<td>Mode of Payment</td>
											<td>
												<select id="paymentMode" class="easyui-combobox" name="paymentMode" style="width:200px;" required="true">
													<option value='Cash'>Cash</option>
													<option value='CreditCard'>Credit Card</option>
													<option value='DebitCard'>Debit Card</option>
													<option value='Cheque'>Cheque</option>
												</select>
											</td>
										</tr>
										<tr height="40">
											<td>Card/Cheque number</td>
											<td><input class="easyui-validatebox inp-form" name="cardNumber"></td>
										</tr>
										<tr height="40">
											<td>Transaction Remarks</td>
											<td><textarea class="easyui-validatebox" style="height:100px;width:200px;" name="transactionDesc"></textarea></td>
										</tr>
									</table>
								</td>
							</tr>
							<tr>
								<td colspan="2"></td>
								<td align="right">
									<div id="dlg-buttons">  
										<a href="#" class="easyui-linkbutton attachBillDetails" iconCls="icon-add">Attach Bills</a>
									    <a href="#" class="easyui-linkbutton submitCreateEditTransactionDetails" iconCls="icon-ok">Save</a>
									    <a href="#" class="easyui-linkbutton deleteTransactionDetails" iconCls="icon-remove">Delete</a>
									    <a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#transactionDetailsDisplayWindow').dialog('close')">Cancel</a>  
									</div>
								</td>
							</tr>
						</table>
					</div>
			    </form>
			</div>
		<!--/div-->
	</div>
	
	<div id="attachedBillDetailsDisplayWindow" class="easyui-window easyui-dialog" closed="true" modal="true"
		title="Attach Bills" style="padding:20px;width:850px;height:550px;">
		<table id="attachedBillDetailsListTable"></table>

	</div>
	<div id="attachedBillDetailsDiv" class="easyui-window easyui-dialog" closed="true" modal="true"
		title="Attach Bills" style="padding:20px;width:550px;height:300px;">
		<s:form action="attachFileDetails" namespace="/admin/" 
				method="POST" enctype="multipart/form-data">
				<s:actionerror />
				<input type="hidden" name="transactionDetailsId" value=""/>
				<s:file name="fileUpload" label="Select a File to upload" size="40" />
				<br/>
				Enter Description
				<input type="text" name="attachmentDesc" value="" size="100"/>
				<s:submit value="submit" name="submit" />
				 
			</s:form>
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