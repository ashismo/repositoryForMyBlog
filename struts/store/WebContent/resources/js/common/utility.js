var MEDICINE = {};

MEDICINE.XHR = function(url, dataType, formId){
    // setup some local variables
    var $form = jQuery('#' + formId),
        // let's select and cache all the fields
        $inputs = $form.find("input, select, button, textarea"),
        // serialize the data in the form
        serializedData = $form.serialize();
    // let's disable the inputs for the duration of the ajax request
    $inputs.attr("disabled", "disabled");

    // fire off the request to /form.php
    jQuery.ajax({
        url: url,
        type: "post",
        async: false,
        data: serializedData,
        dataType: dataType,
        // callback handler that will be called on success
        success: function(response, textStatus, jqXHR){
    		var resp = response;
    		if(dataType == 'json') {
    			var json = JSON.stringify(response);
    			jQuery("#ajaxJsonResponse").html(json);
	    		if(response.msg != null && response.msg != undefined ) {
	    			resp = response.msg;
	    		}
    		}
        	jQuery("#ajaxResponse").html(resp);
        },
        // callback handler that will be called on error
        error: function(jqXHR, textStatus, errorThrown){
            // log the error to the console
            console.log(
                "The following error occured: "+
                textStatus, errorThrown
            );
            jQuery("#ajaxResponse").html(errorThrown);
        },
        // callback handler that will be called on completion
        // which means, either on success or error
        complete: function(){
            // enable the inputs
            $inputs.removeAttr("disabled");
        }
    });

    // prevent default posting of form
    //event.preventDefault();
};

MEDICINE.XHR_JSON = function(url, dataType, formId){
	// setup some local variables
	var $form = jQuery('#' + formId),
	// let's select and cache all the fields
	$inputs = $form.find("input, select, button, textarea"),
	// serialize the data in the form
	serializedData = $form.serialize();
	// let's disable the inputs for the duration of the ajax request
	$inputs.attr("disabled", "disabled");
	alert(url + '?' + serializedData);
	jQuery.getJSON(url + '?' + serializedData, function(data) {
        alert(data); //uncomment this for debug
        //alert (data.item1+" "+data.item2+" "+data.item3); //further debug
        //$('#showdata').html("<p>item1="+data.item1+" item2="+data.item2+" item3="+data.item3+"</p>");
    });
};

MEDICINE.enableForm = function(formId) {
	var $form = jQuery('#' + formId),
	$inputs = $form.find("input, select, button, textarea");
	// let's enable the inputs for the duration of the ajax request
	$inputs.removeAttr("disabled");
};

MEDICINE.disableForm = function(formId) {
	var $form = jQuery('#' + formId),
	$inputs = $form.find("input, select, button, textarea");
	// let's enable the inputs for the duration of the ajax request
	$inputs.attr("disabled", "disabled");
};

MEDICINE.readonlyForm = function(formId) {
	var $form = jQuery('#' + formId),
	$inputs = $form.find("input, select, textarea");
	// let's enable the inputs for the duration of the ajax request
	$inputs.attr("readonly","true");
};

MEDICINE.operationMessage = function(operationMsg) {
	
	if(operationMsg != undefined && operationMsg != null && operationMsg != '') {
		jQuery.messager.alert('Confirmed!!',operationMsg, 'Confirmed!!');
		if(operationMsg.toString().length > 10) {
			var len = (operationMsg.toString().length * 10);
			var wWidth = len + 'px';
			var hWidth = (len-1) + 'px';
			var bWidth = (len-20) + 'px';
			jQuery('.messager-window').css('width',wWidth);
			jQuery('.messager-window .panel-header').css('width',hWidth);
			jQuery('.messager-window .messager-body').css('width',bWidth);
			jQuery('.window-shadow').css('width',wWidth);
		}
	} else {
		jQuery.messager.alert('Warning','Operation failed', 'Warning');
	}
};

MEDICINE.errMessage = function(operationMsg) {
		jQuery.messager.alert('Warning',operationMsg, 'Warning');
		if(operationMsg.toString().length > 10) {
			var len = (operationMsg.toString().length * 10);
			var wWidth = len + 'px';
			var hWidth = (len-1) + 'px';
			var bWidth = (len-20) + 'px';
			jQuery('.messager-window').css('width',wWidth);
			jQuery('.messager-window .panel-header').css('width',hWidth);
			jQuery('.messager-window .messager-body').css('width',bWidth);
			jQuery('.window-shadow').css('width',wWidth);
		}
};

MEDICINE.selectSearchCriteria = function(selectedDiv) {
	jQuery('#searchCriteria').children('div').each(function(){
		var currentCriteriaHtml = jQuery(this).html();
		currentCriteriaHtml = currentCriteriaHtml.replace('<div class="menu-icon icon-ok"></div>','');
		jQuery(this).html(currentCriteriaHtml);
	});
	jQuery('#searchCriteria .menu-icon').each(function(){
		jQuery(this).removeClass('icon-ok');
	});
	jQuery(selectedDiv).attr('iconCls', 'icon-ok');
	jQuery(selectedDiv).append('<div class="menu-icon icon-ok"></div>');
	jQuery('.searchbox .l-btn-text').addClass('icon-ok');
	jQuery('.l-btn-text').css('padding-left','20px');
//	jQuery('.searchbox').css('width', '400px');
};

function getAttrNameOfSelectedCriteria() {
	jQuery('#searchCriteria').children('div').each(function(){
		if(jQuery(this).attr('iconCls') == 'icon-ok') {
			param = jQuery(this).attr('name');
		}
	});
	return param;
};

// This method formats date for combobox
function formatDate(date) {
	var y = date.getFullYear();
	var m = date.getMonth() + 1;
	var d = date.getDate();
	return( (d < 10 ? '0' + d : d) + '/' + (m < 10 ? '0' + m : m) + '/' + y);
}

// This method formats date coming from database
function getDateFromString(value) {
	if(value == null) return value;
	value = value.substring(0,10);
	var d = value.split('-')[2];
	var m = value.split('-')[1];
	var y = value.split('-')[0];
    return d + '/' + m + '/' + y;
}

function getMfgAndExpDateFromString(value) {
	if(value == null) return value;
	value = value.substring(0,10);
	var d = value.split('-')[2];
	var m = value.split('-')[1];
	var y = value.split('-')[0];
    return m + '/' + y;
}

/**
 * This method compares two dates. If date1 > date2, returns 1, if date1 < date2 then returns -1. If date1 = date2 then return 0
 * @param dt1
 * @param dt2
 * @return
 */
function compareDates(dt1, dt2) {
	dt1 = convertIntoMMddyyyyFormat(dt1);
	dt2 = convertIntoMMddyyyyFormat(dt2);
	var date1 = Date.parse(dt1);
	var date2 = Date.parse(dt2);
	if(date1 == date2)
		return 0;
	else if(date1 > date2)
		return 1;
	else if(date1 < date2)
		return -1;
}

function convertIntoMMddyyyyFormat(date) {
	var dd = date.substring(0,2);
	var mm = date.substring(3,5);
	var yyyy = date.substring(6,10);
	if(dd < 10 && dd.toString().length < 2) dd = '0' + dd;
	if(mm < 10 && mm.toString().length < 2) mm = '0' + mm;
	return mm + '/' + dd + '/' + yyyy;
}
/**
 * This method clears all non required fields common to all screens
 */
MEDICINE.clear = function() {
	jQuery('.validatebox-tip').css('display', 'none');
};

MEDICINE.resetForm = function(formId) {
	jQuery('#'+formId).form('clear'); 
};

MEDICINE.disableFields = function(parentElem, elemNamesToBeDisabled) {
	jQuery.each(elemNamesToBeDisabled, function(index, value)
	{ 
		$inputs = jQuery('input[name=' + value + ']'),
//		$inputs = $form.find("input, select, button, textarea");
		// let's enable the inputs for the duration of the ajax request
		$inputs.attr("disabled", "disabled");
		//jQuery('input[name=' + value + ']').attr('disabled','disabled'); 
		//alert(jQuery('input[name=' + value + ']').val());
	});
}