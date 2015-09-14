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
		<div style="height:500px; background:#fafafa;padding:10px;">

			<div id="manageAccount" class="easyui-panel" style="width:900px;height:500px;padding:10px;"
					title="Store Details" 
					collapsible="true" minimizable="false"
					maximizable=true closable="false">
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
										<tr height="40">
											<td>Mobile1:</td>
											<td><input class="easyui-validatebox inp-form" required="true" name="mob1"></td>
										</tr>
									</table>
								</td>
								<td width="80"></td>
								<td>
									<table>
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
											<td>Shop Name:</td>
											<td><input class="easyui-validatebox inp-form" required="true" name="shopName"></td>
										</tr>
										<tr height="40">
											<td>Shop Number:</td>
											<td><input class="easyui-validatebox inp-form" required="true" name="shopNo"></td>
										</tr>
										<tr height="40">
											<td>License Number:</td>
											<td><input class="easyui-validatebox inp-form" required="true" name="licenceNo"></td>
										</tr>
										<tr height="40">
											<td>Baby Food License No:</td>
											<td><input class="easyui-validatebox inp-form" required="true" name="babyFoodLcNo"></td>
										</tr>
										<tr height="40">
											<td>Description:</td>
											<td><textarea class="easyui-validatebox" style="height:100px;" name="ownerDesc"></textarea></td>
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
									</div>
								</td>
							</tr>
						</table>
					</div>
			    </form>
			</div>
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