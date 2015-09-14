<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<script src="<%=request.getContextPath()%>/resources/js/admin/manageAccount.js"></script>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body>
	<div id="manageAccountDisplayWindow" class="easyui-window easyui-dialog" closed="true" modal="true"
		title="Create/View/Edit Manufacturer" style="padding:20px;width:750px;height:550px;"
		buttons="#dlg-buttons">
		<div id="manageAccountDisplayDiv" style="padding:10px;">
			<form id="manageAccountDisplayForm" method="post" novalidate>
				<div>
					<input type="hidden" name="manageAccountId" class="easyui-validatebox inp-form"/>
					<table>
						<tr>
							<td>
								<table>
									<tr height="40">
										<td class="td-prop">Owner Name:</td>
										<td class="td-prop"><input class="easyui-validatebox inp-form" type="text" required="true" name="ownerName"></td>
									</tr>
									<tr height="40">
										<td class="td-prop">Address1:</td>
										<td class="td-prop"><input class="easyui-validatebox inp-form" required="true" type="text" name="ownerAddr1"></td>
									</tr>
									<tr height="40">
										<td class="td-prop">Address2:</td>
										<td class="td-prop"><input class="easyui-validatebox inp-form" type="text" name="ownerAddr2"></td>
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
										<td><input class="easyui-validatebox inp-form" required="true" name="phone1"></td>
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
										<td><input class="easyui-validatebox inp-form" required="true" name="mob1"></td>
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
										<td>Medicine Shop Name:</td>
										<td><textarea class="easyui-validatebox" required="true" style="height:100px;" name="ownerDesc"></textarea></td>
									</tr>
								</table>
							</td>
						</tr>
						<tr>
							<!-- td><input type="button" class="form-submit submitCreateEditManageAccount" value="Submit"></td>
							<td><input type="button" class="button blue deleteManageAccount" value="Delete"></td-->
							<td colspan="2"></td>
							<td align="right">
								<div id="dlg-buttons">  
								    <a href="#" class="easyui-linkbutton submitCreateEditManageAccount" iconCls="icon-ok">Update</a>
								    <a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#manageAccountDisplayWindow').dialog('close')">Cancel</a>  
								</div>
							</td>
						</tr>
					</table>
				</div>
		    </form>
		</div>
	</div>

</body>
</html>