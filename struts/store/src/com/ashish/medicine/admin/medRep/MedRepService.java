package com.ashish.medicine.admin.medRep;

import com.ashish.medicine.exception.AppException;

public interface MedRepService {
	public void addOrUpdateMedRep(MedRepBean medRepBean) throws AppException;
	public void searchMedRep(MedRepBean medRepBean) throws AppException;
	public void viewMedRep(MedRepBean medRepBean) throws AppException;
	public void deleteMedRep(MedRepBean medRepBean) throws AppException;
	public void getMedRepByWholeSellerId(MedRepBean medRepBean) throws AppException;
}
