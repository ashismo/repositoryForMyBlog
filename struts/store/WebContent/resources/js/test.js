
jQuery(document).delegate(".testAjax", "click", function(event) {	
		MEDICINE.XHR('friendsLink.action', 'html');
		jQuery("#dataTable").html(jQuery("#ajaxResponse").html());
		
		jQuery("#myDataTable").dataTable({
            "sPaginationType": "full_numbers",
            "bJQueryUI": true,
            "bProcessing" : true,
            "bScrollCollapse" : true,
            "iDisplayLength": 10,
            "sLoadingRecords" : "Loading...",
            "sScrollY" : "350px"
            //"sScrollX" : "100%",
            //"sScrollXInner" : "100%"


			
        });
		
		
	});

jQuery(document).delegate(".testAjaxFromXML", "click", function(event) {
	var theme = getTheme();
	// prepare the data
	var data = generatedata();
	var source =
	{
	    localdata: data,
	    datatype: "array"
	};
	jQuery("#dataTableFromXml").jqxGrid(
	{
	    width: 820,
	    source: source,
	    theme: theme,
	    columnsresize: true,
	    columns: [
	      { text: 'First Name', datafield: 'firstname', width: 100 },
	      { text: 'Last Name', datafield: 'lastname', width: 100 },
	      { text: 'Product', datafield: 'productname', width: 180 },
	      { text: 'Quantity', datafield: 'quantity', width: 80, cellsalign: 'right' },
	      { text: 'Unit Price', datafield: 'price', width: 90, cellsalign: 'right', cellsformat: 'c2' },
	      { text: 'Total', datafield: 'total', minwidth: 100, resizable: false, width: 'auto', cellsalign: 'right', cellsformat: 'c2' }
	    ]
	});
	// trigger the column resized event.
	jQuery("#dataTableFromXml").bind('columnresized', function (event) {
	    var column = event.args.columntext;
	    var newwidth = event.args.newwidth
	    var oldwidth = event.args.oldwidth;
	    jQuery("#eventlog").html("Column: " + column + ", " + "New Width: " + newwidth + ", Old Width: " + oldwidth);
	});
});

jQuery(document).delegate(".dataTableFromXML", "click", function(event) {
	var theme = getTheme();
	var url = "resources/js/jqwidgets/demos/sampledata/orders.xml";
	var parentsLength = $("#jqxWidget").parents().length;
	if (parentsLength > 3) {
	    url = 'resources/js/jqwidgets/demos/sampledata/orders.xml';
	}

	// prepare the data
	var source =
	{
	    datatype: "xml",
	    datafields: [
	        { name: 'ShippedDate', map: 'm\\:properties>d\\:ShippedDate', type: 'date' },
	        { name: 'Freight', map: 'm\\:properties>d\\:Freight', type: 'float' },
	        { name: 'ShipName', map: 'm\\:properties>d\\:ShipName' },
	        { name: 'ShipAddress', map: 'm\\:properties>d\\:ShipAddress' },
	        { name: 'ShipCity', map: 'm\\:properties>d\\:ShipCity' },
	        { name: 'ShipCountry', map: 'm\\:properties>d\\:ShipCountry' }
	    ],
	    root: "entry",
	    record: "content",
	    id: 'm\\:properties>d\\:OrderID',
	    url: url
	};
	$("#jqxgrid").jqxGrid(
	{
	    width: 820,
	    source: source,
	    theme: theme,
	    sortable: true,
	    pageable: true,
	    autoheight: true,
	    columns: [
	      { text: 'Ship Name', datafield: 'ShipName', width: 250 },
	      { text: 'Shipped Date', datafield: 'ShippedDate', width: 230, cellsformat: 'D' },
	      { text: 'Freight', datafield: 'Freight', width: 130, cellsformat: 'F2', cellsalign: 'right' },
	      { text: 'Ship Address', datafield: 'ShipAddress', width: 350 },
	      { text: 'Ship City', datafield: 'ShipCity', width: 100 },
	      { text: 'Ship Country', datafield: 'ShipCountry', width: 101 }
	    ]
	});
	$('#events').jqxPanel({ width: 300, height: 300, theme: theme });
	$("#jqxgrid").bind("pagechanged", function (event) {
	    $("#eventslog").css('display', 'block');
	    if ($("#events").find('.logged').length >= 5)
	    {
	        $("#events").jqxPanel('clearcontent');
	    }
	    var args = event.args;
	    var eventData = "pagechanged <div>Page:" + args.pagenum + ", Page Size: " + args.pagesize + "</div>";
	    $('#events').jqxPanel('prepend', '<div class="logged" style="margin-top: 5px;">' + eventData + '</div>');
	    // get page information.
	    var paginginformation = $("#jqxgrid").jqxGrid('getpaginginformation');
	    $('#paginginfo').html("<div style='margin-top: 5px;'>Page:" + paginginformation.pagenum + ", Page Size: " + paginginformation.pagesize + ", Pages Count: " + paginginformation.pagescount);
	});
	$("#jqxgrid").bind("pagesizechanged", function (event) {
	    $("#eventslog").css('display', 'block');
	    $("#events").jqxPanel('clearcontent');
	    var args = event.args;
	    var eventData = "pagesizechanged <div>Page:" + args.pagenum + ", Page Size: " + args.pagesize + ", Old Page Size: " + args.oldpagesize + "</div>";
	    $('#events').jqxPanel('prepend', '<div style="margin-top: 5px;">' + eventData + '</div>');
	    // get page information.

	  var paginginformation = $("#jqxgrid").jqxGrid('getpaginginformation');
	    $('#paginginfo').html("<div style='margin-top: 5px;'>Page:" + paginginformation.pagenum + ", Page Size: " + paginginformation.pagesize + ", Pages Count: " + paginginformation.pagescount);
	});
	
	// trigger the column resized event.
	jQuery("#jqxgrid").bind('columnresized', function (event) {
	    var column = event.args.columntext;
	    var newwidth = event.args.newwidth
	    var oldwidth = event.args.oldwidth;
	    jQuery("#eventlog").html("Column: " + column + ", " + "New Width: " + newwidth + ", Old Width: " + oldwidth);
	});
});