package com.ashish.medicine.admin.medRep;

import com.ashish.medicine.exception.AppException;

public class MedRepServiceImpl implements MedRepService {

	public void addOrUpdateMedRep(MedRepBean medRepBean) throws AppException {
		new MedRepServiceHelperImpl().addOrUpdateMedRep(medRepBean);
	}

	public void searchMedRep(MedRepBean medRepBean) throws AppException {
		new MedRepServiceHelperImpl().searchMedRep(medRepBean);
	}

	public void viewMedRep(MedRepBean medRepBean) throws AppException {
		new MedRepServiceHelperImpl().viewMedRep(medRepBean);
	}

	public void deleteMedRep(MedRepBean medRepBean) throws AppException {
		new MedRepServiceHelperImpl().deleteMedRep(medRepBean);
	}

	public void getMedRepByWholeSellerId(MedRepBean medRepBean)
			throws AppException {
		new MedRepServiceHelperImpl().getMedRepByWholeSellerId(medRepBean);
		
	}

}
