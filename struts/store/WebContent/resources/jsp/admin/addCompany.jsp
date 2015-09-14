<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="<%=request.getContextPath()%>/resources/js/admin/addCompany.js"></script>
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
	
	
		<!--  start step-holder -->
		<div id="addCompanySteps">
			<div class="step-no">1</div>
			<div class="step-dark-left"><a href="#">Add Company details</a></div>
			<div class="step-dark-right noClear">&nbsp;</div>
			<div class="step-no-off">2</div>
			<div class="step-light-left"><a href="#">Preview</a></div>
			<div class="step-light-round">&nbsp;</div>
			<div class="clear"></div>
		</div>
		<!--  end step-holder -->
	
		<!-- start id-form -->
		<s:form action="admin/addCompanyDetails.action">
			<div id="companyDetails">
			<table border="0" cellpadding="0" cellspacing="0"  id="id-form">
			<tr>
				<th valign="top">Company Name:</th>
				<td><s:textfield name="companyName" cssClass="inp-form"/></td>
				<td></td>
			</tr>
			<tr>
				<th valign="top">Company Address1:</th>
				<!--td><input type="text" class="inp-form-error" /></td-->
				<td><s:textfield name="companyAddr1" cssClass="inp-form"></s:textfield></td>
				<td>
				<!-- span class="error-left"></span>
				<span class="error-inner">This field is required.</span-->
				</td>
			</tr>
			<tr>
				<th valign="top">Company Address2:</th>
				<!--td><input type="text" class="inp-form-error" /></td-->
				<td><s:textfield name="companyAddr2" cssClass="inp-form"></s:textfield></td>
				<td>
				<!-- span class="error-left"></span>
				<span class="error-inner">This field is required.</span-->
				</td>
			</tr>
			<tr>
			<th valign="top">State:</th>
			<td>
				<s:select name="state" list="states" 
					cssClass="styledselect_form_1" listKey="id" 
					listValue="name" headerKey="-1" headerValue="Select States">
					
				</s:select>
			</td>
			<td></td>
			</tr>
			<tr>
				<th valign="top">PIN:</th>
				<td><s:textfield name="pin" cssClass="inp-form"></s:textfield></td>
				<td></td>
			</tr>
			<tr>
				<th valign="top">Mobile1:</th>
				<td><s:textfield name="mobile1" cssClass="inp-form"/></td>
				<td></td>
			</tr>
			<tr>
				<th valign="top">Mobile2:</th>
				<td><s:textfield name="mobile2" cssClass="inp-form"/></td>
				<td></td>
			</tr>
			<tr>
				<th valign="top">Phone1:</th>
				<td><s:textfield name="phone1" cssClass="inp-form"/></td>
				<td></td>
			</tr>
			<tr>
				<th valign="top">Phone2:</th>
				<td><s:textfield name="phone2" cssClass="inp-form"/></td>
				<td></td>
			</tr>
			<tr>
				<th valign="top">Fax:</th>
				<td><s:textfield name="fax" cssClass="inp-form"/></td>
				<td></td>
			</tr>
		</table>
		</div>
		<table>
			<tr>
				<th width="43%">&nbsp;</th>
				<td valign="top">
					<s:hidden name="isCompanyDetails" id="isCompanyDetails"  value="1"/>
					<input type="button" value="" name="submitBtn" class="form-submit submitNewCompany" style="display:none"/> &nbsp;
					<input type="button" value="Next" name="nextBtn" class="button black addCompany" style="display:block"/> &nbsp;
					<input type="reset" value="" name="resetBtn" class="form-reset" style="display:block"/> &nbsp;
					<input type="button" value="Back" name="backBtn" class="button black backToDetails" style="display:none"/>
				</td>
				<td></td>
			</tr>
		</table>
	</s:form>
	<!-- end id-form  -->

	</td>
	<td>

	<!--  start related-activities -->
	<div id="related-activities">
		
		<!--  start related-act-top -->
		<div id="related-act-top">
		<img src="<%=request.getContextPath()%>/resources/js/dashboard/images/forms/header_related_act.gif" width="271" height="43" alt="" />
		</div>
		<!-- end related-act-top -->
		
		<!--  start related-act-bottom -->
		<div id="related-act-bottom">
		
			<!--  start related-act-inner -->
			<div id="related-act-inner">
			
				<div class="left"><a href=""><img src="<%=request.getContextPath()%>/resources/js/dashboard/images/forms/icon_plus.gif" width="21" height="21" alt="" /></a></div>
				<div class="right">
					<h5>Quick Links</h5>
					<ul class="greyarrow">
						<li><a href="/admin/companyManagementDetails.action">Click here to visit</a></li> 
						<li><a href="">Click here to visit</a> </li>
					</ul>
				</div>
				
				<div class="clear"></div>
				<div class="lines-dotted-short"></div>
				
				<div class="left"><a href=""><img src="<%=request.getContextPath()%>/resources/js/dashboard/images/forms/icon_minus.gif" width="21" height="21" alt="" /></a></div>
				<div class="right">
					<h5>Delete products</h5>
					Lorem ipsum dolor sit amet consectetur
					adipisicing elitsed do eiusmod tempor.
					<ul class="greyarrow">
						<li><a href="">Click here to visit</a></li> 
						<li><a href="">Click here to visit</a> </li>
					</ul>
				</div>
				
				<div class="clear"></div>
				<div class="lines-dotted-short"></div>
				
				<div class="left"><a href=""><img src="<%=request.getContextPath()%>/resources/js/dashboard/images/forms/icon_edit.gif" width="21" height="21" alt="" /></a></div>
				<div class="right">
					<h5>Edit categories</h5>
					Lorem ipsum dolor sit amet consectetur
					adipisicing elitsed do eiusmod tempor.
					<ul class="greyarrow">
						<li><a href="">Click here to visit</a></li> 
						<li><a href="">Click here to visit</a> </li>
					</ul>
				</div>
				<div class="clear"></div>
				
			</div>
			<!-- end related-act-inner -->
			<div class="clear"></div>
		
		</div>
		<!-- end related-act-bottom -->
	
	</div>
	<!-- end related-activities -->

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