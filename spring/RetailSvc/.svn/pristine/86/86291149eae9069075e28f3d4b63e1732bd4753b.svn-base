package com.org.coop.retail.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.org.coop.canonical.beans.UIModel;
import com.org.coop.retail.servicehelper.RetailBranchSetupServiceHelperImpl;
import com.org.coop.retail.servicehelper.RetailDocumentServiceHelperImpl;

@Service
public class RetailDocumentServiceImpl {

	private static final Logger log = Logger.getLogger(RetailDocumentServiceImpl.class); 
	
	@Autowired
	private RetailDocumentServiceHelperImpl retailDocumentServiceHelperImpl;
	
	
	public UIModel getDocumentByDocId(int documentId) {
		return retailDocumentServiceHelperImpl.getDocumentByDocId(documentId);
	}
	
	public UIModel saveDocument(UIModel uiModel) {
		uiModel = retailDocumentServiceHelperImpl.saveDocument(uiModel);
		if(uiModel.getErrorMsg() != null) {
			return uiModel;
		}
		int documentId = uiModel.getBranchBean().getDocuments().get(0).getDocId();
		return retailDocumentServiceHelperImpl.getDocumentByDocId(documentId);
	}
}
