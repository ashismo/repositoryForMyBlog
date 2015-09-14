<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="<%=request.getContextPath()%>/resources/js/admin/scheduleManagement.js"></script>

<!-- Chart  ENDS -->
<script src="<%=request.getContextPath()%>/resources/js/Highcharts-2.2.5/js/highcharts.js"></script>
<script src="<%=request.getContextPath()%>/resources/js/Highcharts-2.2.5/js/modules/exporting.js"></script>
<!-- Chart  ENDS -->

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<style type="text/css">
		.left{
			width:120px;
			float:left;
		}
		.left table{
			background:#E0ECFF;
		}
		.left td{
			background:#eee;
		}
		.right{
			float:right;
			width:570px;
		}
		.right table{
			background:#E0ECFF;
			width:100%;
		}
		.right td{
			background:#fafafa;
			text-align:center;
			padding:7px;
		}
		.right td{
			background:#E0ECFF;
		}
		.right td.drop{
			background:#fafafa;
			width:120px;
		}
		.right td.over{
			background:#FBEC88;
		}
		.item{
			text-align:center;
			border:1px solid #499B33;
			background:#fafafa;
			width:125px;
		}
		.assigned{
			border:1px solid #BC2A4D;
		}
		
	</style>
</head>
<body>

<table border="0" width="1100px" cellpadding="0" cellspacing="0" id="content-table">
<tr>
	<td id="tbl-border-left"></td>
	<td>
	<!--  start content-table-inner -->
	<div id="content-table-inner">
	<table border="0" width="100%" cellpadding="0" cellspacing="0">
	<tr valign="top">
	<td>
	<form id="scheduleCriteriaForm" method="post" novalidate>
		<table>
			<tr height="45">
				<td>Schedule Date:</td>
				<td><span style="display:none;" id="scheduleDate"><s:date name="scheduleDateActual" format="dd/MM/yyyy"/></span>
					<input name="scheduleDate" class="datebox"/>
				</td>
				<td width="10%"></td>
				<td width="150">Schedule For:</td>
				<td>
					<select id="scheduleTypeList" class="easyui-combobox" name="scheduleLookupId" style="width:200px;">
						<option value='1'>Doctor</option>
					</select>
				</td>
			</tr>
			<tr height="45">
				<td>
					<a href="#" class="easyui-linkbutton addNewSchedule" iconCls="icon-add">Add New Schedule</a>
				</td>
				<td>
					<a href="#" class="easyui-linkbutton viewSchedule" iconCls="icon-add">View Schedule</a>
				</td>
				<td width="10%"></td>
				<td>
					<a href="#" class="easyui-linkbutton saveSchedule" iconCls="icon-save">Save Schedule</a>
				</td>
				<td>
					<a href="#" class="easyui-linkbutton deleteSchedule" iconCls="icon-remove">Delete Schedule</a>
				</td>
			</tr>
		</table>
	
	<div style="hight:80;">&nbsp;</div>
	<div id="scheduleDisplayWindow" style="width:730px;display:block;">
		<div class="left">
			<table>
				<s:iterator value="scheduleListLeft">
					<tr>
						<td><div class="item"><s:property value="doctorId"/>,<s:property value="leftItemName"/></div></td>
					</tr>
				</s:iterator>
				<tr>
					<td><div class="item">&nbsp;</div></td>
				</tr>
			</table>
		</div>
		
		<div class="right">
			<table>
				<tr>
					<td class="blank"></td>
					<td class="title">Sunday</td>
					<td class="title">Monday</td>
					<td class="title">Tuesday</td>
					<td class="title">Wednesday</td>
					<td class="title">Thursday</td>
					<td class="title">Friday</td>
					<td class="title">Saturday</td>
				</tr>
				<tr>
					<td class="time">08:00</td>
					<td class="drop"></td>
					<td class="drop"></td>
					<td class="drop"></td>
					<td class="drop"></td>
					<td class="drop"></td>
					<td class="drop"></td>
					<td class="drop"></td>
				</tr>
		</div>
	</div>
	
	<div id="scheduleData" style="display:none;">
		<s:iterator value="scheduleListRight">
			<input type="text" class="<s:property value='rightItemClass'/>" value="<s:property value='scheduleValue'/>"></input>
		</s:iterator>
		<!--input type="text" class="0800-Monday" value="6,Deepanjan Paine"></input>
		<input type="text" class="0830-Wednesday" value="6,Deepanjan Paine"></input-->
	</div>
	</form>	
	<ul id="doctorDisplayWindow" class="easyui-dialog" closed="true" modal="true"
		title="Create/View/Edit Doctor" style="padding:20px;width:400px;height:200px;">
		<ul id="doctorDisplayDiv" style="padding:10px;display:none;">
			Doctor Name: <input class="easyui-validatebox" type="text" name="doctorName">
			<div style="height:12px;"></div>
			Qualification: <input class="easyui-validatebox" type="text" name="qualification">
			<div style="height:12px;"></div>
			Specilization: <input class="easyui-validatebox" type="text" name="speciality">
			<div style="height:12px;"></div>
			Phone: <input class="easyui-validatebox" type="text" name="phone1">
			<div style="height:12px;"></div>
			Mobile: <input class="easyui-validatebox" type="text" name="mob1">
		</ul>
	</ul>
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