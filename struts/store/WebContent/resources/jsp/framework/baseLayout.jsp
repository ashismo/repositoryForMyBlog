<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%String contextPath = request.getContextPath();%>
<html>
    <head>
    	<script src="<%=contextPath%>/resources/js/jquery/jquery-1.7.2.min.js"></script>
    	<script src="<%=contextPath%>/resources/js/common/utility.js"></script>
    	<script src="<%=contextPath%>/resources/js/common/common.js"></script>
    	<script src="<%=contextPath%>/resources/js/IdleTimer/jquery-ui.js"></script>
		<script src="<%=contextPath%>/resources/js/IdleTimer/jquery.idle-timer.js"></script>
    	<!--script type="text/javascript" src="<%=contextPath%>/resources/js/jquery/prototype.js"></script-->
		<!--script type="text/javascript" src="<%=contextPath%>/resources/js/jquery/jquery-1.5.2.min.js"></script-->
		<!-- script type="text/javascript">jQuery.noConflict();</script-->
		
		<link href="<%=request.getContextPath()%>/resources/css/datatable/media/dataTables/demo_page.css" rel="stylesheet" type="text/css" />
        <link href="<%=contextPath%>/resources/css/datatable/media/dataTables/demo_table.css" rel="stylesheet" type="text/css" />
        <link href="<%=contextPath%>/resources/css/datatable/media/dataTables/demo_table_jui.css" rel="stylesheet" type="text/css" />
        <link href="<%=contextPath%>/resources/css/datatable/media/themes/base/jquery-ui.css" rel="stylesheet" type="text/css" media="all" />
        <link href="<%=contextPath%>/resources/css/datatable/media/themes/smoothness/jquery-ui-1.7.2.custom.css" rel="stylesheet" type="text/css" media="all" />
		
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <title><tiles:insertAttribute name="title" ignore="true" /></title>
    </head>
    <body>
        <table border="1" cellpadding="2" cellspacing="2" align="center">
            <tr>
                <td height="30" width="100%" colspan="2">
                    <tiles:insertAttribute name="header" />
                </td>
            </tr>
            <tr style="display: none;">
                <td width="100%">
                    <tiles:insertAttribute name="idleTimer" />
                </td>
            </tr> 
            <tr>
                <!--td height="350">
                    <tiles:insertAttribute name="menu" />
                </td-->
                <td width="95%">
                    <tiles:insertAttribute name="body" />
                </td>
            </tr>
            <tr>
                <td height="5" colspan="2">
                    <tiles:insertAttribute name="footer" />
                </td>
            </tr>
        </table>
        <div id="ajaxResponse" style="display:none;">
        </div>
        <div id="ajaxJsonResponse" style="display:none;"></div>
    </body>
</html>
