jQuery().ready(function(){
	var scheduleDate = jQuery('#scheduleDate').html();
	jQuery('input[name=scheduleDate]').val(scheduleDate);
	jQuery('.datebox').datebox({   
	    formatter:function(date) {
			return formatDate(date);
		}
	});
	createDynamicSchedule();	
});

function createDynamicSchedule() {
//	createDynamicLeftTable();
	createDynamicRightTable();
	var c = jQuery('.assigned');
	c.draggable({
		revert:true
	});
	dragLeft(jQuery('.left .item'));
	dragRight();
}
function dragLeft(elem) {
	jQuery('.left .item').draggable({   
		revert:true,   
		proxy:'clone'
	}); 
}

function dragRight() {
	jQuery('.right td.drop').droppable({
		onDragEnter:function(){
			jQuery(this).addClass('over');
		},
		onDragLeave:function(){
			jQuery(this).removeClass('over');
		},
		onDrop:function(e,source){
			jQuery(this).removeClass('over');
			if (jQuery(source).hasClass('assigned')){
				var tdElem = jQuery(this);
				var targetVal = (jQuery(this).find('input').val()) + '-' + jQuery(source).html();
				
				// if it is dragged from once cell to another
				var sourceInput = jQuery(source).parent('td').find('input');
				var sourceVal = sourceInput.val();
				if(sourceVal.toString().split('-').length > 2) {
					sourceVal = sourceVal.toString().split('-')[0] + '-' + sourceVal.toString().split('-')[1];
					sourceInput.val(sourceVal);
					sourceInput.removeAttr('name');
				}
				
				var targetElem = jQuery(this).find('input');
				targetElem.val(targetVal);
				targetElem.attr('name','assignedItems');
				tdElem.append(targetElem);
				
				jQuery(this).append(source);
			} else {
				var tdElem = jQuery(this);
				var targetVal = (jQuery(this).find('input').val());
				var target = jQuery(this).find('input');
				var c = jQuery(source).clone().addClass('assigned');
				jQuery(this).empty().append(c);
				var sourceVal=(jQuery(source).html());
				var targetHtml = target.val() + '-' + sourceVal;
				target.val(targetHtml);
				
				if(sourceVal == '&nbsp;') {
					target.removeAttr('name');
				} else {
					target.attr('name','assignedItems');
				}
				tdElem.append(target);
				//MEDICINE.XHR('saveSchedule.action?assignedItems='+targetHtml, 'json', 'scheduleCriteriaForm');
				c.draggable({
					revert:true
				});
			}
		}
	}); 
}

function setSchedule(source,element) {
	
}

function createDynamicLeftTable() {
	jQuery('div.left').html('');
//	MEDICINE.XHR('scheduleCriteriaForm.action', 'json', 'scheduleCriteriaForm');
//	var operationMsg = jQuery("#ajaxResponse").html();
//	var jsonString = jQuery("#ajaxJsonResponse").html();
//	var data = JSON.parse(jsonString);
////	var leftItem = new Array('Sampad Baral','Ashish Mondal');
//	var leftItem = new Array();
//	for(var count = 0; count < data.scheduleBean.scheduleList.length; count++) {
//		leftItem[count] =  data.scheduleBean.scheduleList[count].doctorName;
//	}
////	var leftItem = data.scheduleBean.scheduleList;
	var leftItem = new Array('Sampad Baral','Ashish Mondal');
	var innerTr = '';
	jQuery.each(leftItem, function(index, item) { 
		innerTr = innerTr + '<tr><td><div class="item">' + item + '</div></td></tr>';
	});
	innerTr = innerTr + '<tr><td><div class="item">&nbsp</div></td></tr>';
	jQuery('div.left').html('<table>' + innerTr + '</table>');
}
function createDynamicRightTable() {
	var time = new Array('0600','0630','0700','0730','0800','0830',
			'0900','0930','1000','1030','1100','1130',
			'1200','1230','1300','1330','1400','1430','1500','1530',
			'1600','1630','1700','1730','1800','1830','1900','1930',
			'2000','2030','2100','2130','2200','2230','2300','2330','2400');
	
//	var time = new Array('0600','0700','0800','0900','1000','1100',
//			'1200','1300','1400','1500',
//			'1600','1700','1800','1900',
//			'2000','2100','2200','2300','2400');
//	var day = new Array('Sunday','Monday','Tuesday','Wednesday','Thursday', 'Friday','Saturday');
	
//	MEDICINE.XHR('dayAndDates.action', 'json', 'scheduleCriteriaForm');
//	var day = new Array()[7];
	
	MEDICINE.XHR('dayAndDates.action', 'json', 'scheduleCriteriaForm');
	var operationMsg = jQuery("#ajaxResponse").html();
	var jsonString = jQuery("#ajaxJsonResponse").html();
	var json = JSON.parse(jsonString);
	//alert(json.dayAndDatesList[0]);
//	for(var i = 0; i < 7; i++) {
//		day[i] = json.dayAndDatesList[i];
//	}
	var day = json.dayAndDatesList;
	var innerTr = '';
	var innerTdDay = '';
	jQuery.each(time, function(index, t) { 
		innerTdDay = '';
		var innerTd = '<td class="time">' + t + '</td>';
		jQuery.each(day, function(i, d) { 
			var dayOnly = d.split('-')[0];
			 var value = '';
			 var classname = t + '-' + dayOnly;
			 if( jQuery("#scheduleData ." + classname).val() != undefined ) {
				 value = jQuery("#scheduleData ." + classname).val();
				 innerTd = innerTd + '<td class="drop"><div style="POSITION: static;" class="item assigned">' + 
		 					value + '</div>' +
		 					'<input type="hidden" name="assignedItems" value="' + t + '-' + d + '-' + value + '"/>' +
		 					'</td>';
			 } else {
			 	innerTd = innerTd + '<td class="drop">' + 
			 				'<div class="item" style="position: static; left: 371px; top: 289px;"></div>' +
			 				'<input type="hidden" value="' + t + '-' + d + '"/>' + 
			 				'</td>';
			 }
			 innerTdDay = innerTdDay + '<td class="title">' +  d + '</td>';
		});
		innerTr = innerTr + '<tr>' + innerTd + '</tr>';
	});
	innerTr = '<table>' + '<tr><td class="blank"></td>' + innerTdDay + '</tr>' + innerTr + '</table>';
	jQuery('div.right').html(innerTr);	
}
function submitData() {
	
}

jQuery(document).delegate(".assigned", "dblclick", function(event) {
	var val = jQuery(this).html();
	if(val.toString().trim() !='' && val.toString().split(',').length == 2) {
		var doctorId = val.toString().split(',')[0];
		displayDoctor(doctorId, 'viewDoctor.action?doctorId=' + doctorId);
		jQuery('#doctorDisplayWindow').dialog('setTitle','Doctor Detail');
	}
});
jQuery(document).delegate(".addNewSchedule", "click", function(event) {
	jQuery('#scheduleCriteriaForm').attr('action','scheduleManagementDetails.action');
	jQuery('#scheduleCriteriaForm').submit();
});

jQuery(document).delegate(".viewSchedule", "click", function(event) {
	jQuery('#scheduleCriteriaForm').attr('action','scheduleManagementDetails.action');
	jQuery('#scheduleCriteriaForm').submit();
	//createDynamicSchedule();
	//jQuery('#scheduleDisplayWindow').css('display','block');
});

jQuery(document).delegate(".saveSchedule", "click", function(event) {
	MEDICINE.XHR('saveSchedule.action?', 'json', 'scheduleCriteriaForm');
	MEDICINE.operationMessage("Saved successfully");
});

jQuery(document).delegate(".deleteSchedule", "click", function(event) {
	MEDICINE.XHR('deleteSchedule.action?', 'json', 'scheduleCriteriaForm');
	jQuery('#scheduleCriteriaForm').attr('action','scheduleManagementDetails.action');
	jQuery('#scheduleCriteriaForm').submit();
	MEDICINE.operationMessage("Deleted successfully");
});
