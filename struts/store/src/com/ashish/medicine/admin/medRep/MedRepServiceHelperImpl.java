package com.ashish.medicine.admin.medRep;

import com.ashish.medicine.exception.AppException;

public class MedRepServiceHelperImpl implements MedRepServiceHelper {

	public void addOrUpdateMedRep(MedRepBean medRepBean) throws AppException {
		new MedRepDaoImpl().addOrUpdateMedRep(medRepBean);
	}

	public void searchMedRep(MedRepBean medRepBean) throws AppException {
		new MedRepDaoImpl().searchMedRep(medRepBean);
	}

	public void viewMedRep(MedRepBean medRepBean) throws AppException {
		new MedRepDaoImpl().viewMedRep(medRepBean);
	}

	public void deleteMedRep(MedRepBean medRepBean) throws AppException {
		new MedRepDaoImpl().deleteMedRep(medRepBean);		
	}

	@Override
	public void getMedRepByWholeSellerId(MedRepBean medRepBean)
			throws AppException {
		new MedRepDaoImpl().getMedRepByWholeSellerId(medRepBean);
		
	}
}
