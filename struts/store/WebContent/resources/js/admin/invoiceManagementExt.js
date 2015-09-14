function editPayment(index){  
	jQuery('#paymentTable').datagrid('beginEdit', index);  
}  
function deletePayment(index){  
	jQuery.messager.confirm('Confirm','Delete? Are you sure?',function(r){  
        if (r){  
        	var selectedRow = jQuery('#paymentTable').datagrid('getSelected');
        	if(selectedRow) {
        		transactionDetailsId = selectedRow.transactionDetailsId;
        		if(transactionDetailsId == null || transactionDetailsId == undefined || transactionDetailsId == '') {
        			deleteSelectedRow();
        			MEDICINE.errMessage("Please Note: This Transaction is found. Deleting from table");
            		return;
        		}
        		url = 'deleteTransactionDetails.action?transactionDetailsId=' + transactionDetailsId;
        	}
        	
        	MEDICINE.XHR(url, 'json', '');
        	var jsonString = jQuery("#ajaxJsonResponse").html();
        	var json = JSON.parse(jsonString);
        	if(json.orderBean && json.orderBean.errorMsg != null) {
        		jQuery('#paymentTable').datagrid('cancelEdit',index);
        		MEDICINE.errMessage(json.orderBean.errorMsg);
        		return;
        	} else {
        		deleteSelectedRow();
        		MEDICINE.operationMessage(json.orderBean.msg);
        	}
        }  
    });  
}  
function savePayment(index){  
	jQuery('#paymentTable').datagrid('endEdit', index); 
	
	var selectedRow = jQuery('#paymentTable').datagrid('getSelected');
	var transactionDetailsId=0;
//	var invoiceId=jQuery('#hiddenFieldForm input[name=invoiceId]').val();
	var invoiceId = currentInvoiceId;
	var paymentDate='';
	var totalPaid = '';
	var paymentMode = '';
	var medDose = '';
	var url = 'addOrUpdateTransactionDetails.action?invoiceId=0';
	if(selectedRow) {
		transactionDetailsId = selectedRow.transactionDetailsId;
//		invoiceId = selectedRow.invoiceId;
		paymentDate = selectedRow.paymentDate;
		totalPaid = selectedRow.totalPaid;
		paymentMode = selectedRow.paymentMode;
		cardNumber = selectedRow.cardNumber;
		transactionDesc = selectedRow.transactionDesc;
		if(transactionDetailsId == null || transactionDetailsId == undefined || transactionDetailsId == '') {
			transactionDetailsId = 0;
		}
		if(invoiceId == null || invoiceId == undefined || invoiceId == '') {
			invoiceId = 0;
		}
		url = 'addOrUpdateTransactionDetails.action?invoiceId=' + invoiceId + 
					'&transactionDetailsId=' + transactionDetailsId + '&paymentDate=' + paymentDate +
					'&totalPaid=' + totalPaid + '&paymentMode=' + paymentMode + 
					'&cardNumber=' + cardNumber + '&transactionDesc=' + transactionDesc;
	}
	
	MEDICINE.XHR(url, 'json', '');
	var jsonString = jQuery("#ajaxJsonResponse").html();
	var json = JSON.parse(jsonString);
	if(json.transactionDetailsBean.errorMsg != null) {
		jQuery('#paymentTable').datagrid('cancelEdit',index);
		MEDICINE.errMessage(json.transactionDetailsBean.errorMsg);
		return;
	} else {
		//selectedRow.orderId = json.orderBean.orderId;
		var rows = jQuery('#paymentTable').datagrid('getRows');
		rows[index].transactionDetailsId = json.transactionDetailsBean.transactionDetailsId;
		jQuery('#paymentTable').datagrid('load',rows);
		MEDICINE.operationMessage(json.transactionDetailsBean.msg);
	}
}  

function deleteSelectedRow() {
	var row = jQuery('#paymentTable').datagrid('getSelected');
	if (row){
		var index = jQuery('#paymentTable').datagrid('getRowIndex', row);
		jQuery('#paymentTable').datagrid('deleteRow', index);
		jQuery('#paymentTable').datagrid('load',rows);
	}
}

function cancelEdit(index){  
	jQuery('#paymentTable').datagrid('cancelEdit', index);  
}

function updateActions(){  
    var rowcount = jQuery('#paymentTable').datagrid('getRows').length;  
    for(var i=0; i<rowcount; i++){  
    	jQuery('#paymentTable').datagrid('updateRow',{  
            index:i,  
            row:{action:''}  
        });  
    }  
} 

//function updatePayment() {
//	var rows = jQuery('#paymentTable').datagrid('getFooterRows');
//	var paidAmt = rows[0]['totalPaid'];
//	updatePaymentDetails(paidAmt);
//}

function updatePaymentDetails(paidAmt) {
	rows = jQuery('#invoiceTable').datagrid('getFooterRows');
	var totalBill = rows[0]['totalPrice'];
	var vat = jQuery('#invoiceDisplayFormPart2 input[name=vat]').val();
	var discount = jQuery('#invoiceDisplayFormPart2 input[name=discount]').val();
	var totalVat = totalBill*vat/100;
	var totalDiscount = totalBill*discount/100;
	var totalAmt = totalBill + totalVat;
	
	var diff = vat - discount;
	var finalPrice = 0;
//	if(diff >= 0) {
//		finalPrice = totalBill - (totalBill*diff)/100;
//	} else {
//		finalPrice = totalBill + (totalBill*diff)/100;
//	}
	finalPrice = totalBill + (totalBill*diff)/100;
	var due = finalPrice - paidAmt;
	
	jQuery('#totalAmt').html('<label>' + totalBill.toFixed(2) + '</label>');
	jQuery('#vat').html('<label>' + vat + '</label>');
	jQuery('#totalVat').html('<label>' + totalVat.toFixed(2) + '</label>');
	jQuery('#discount').html('<label>' + discount + '</label>');
	jQuery('#totalDiscount').html('<label>' + totalDiscount.toFixed(2) + '</label>');
	jQuery('#gross').html('<label>' + finalPrice.toFixed(2) + '</label>');
	jQuery('#paidAmt').html('<label>' + paidAmt.toFixed(2)	 + '</label>');
	jQuery('#due').html('<label>' + due.toFixed(2) + '</label>');
	jQuery('#invoiceDisplayFormPart2 input[numberboxname=totalPaid]').val(paidAmt.toFixed(2));
	jQuery('#invoiceDisplayFormPart2 input[name=due]').val(due.toFixed(2));
	jQuery('#invoiceDisplayFormPart2 input[name=totalAmt]').val(totalBill.toFixed(2));
}