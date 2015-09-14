package com.ashish.medicine.admin.medicine;

import com.ashish.medicine.exception.AppException;

public interface MedicineServiceHelper {
	public void addOrUpdateMedicine(MedicineBean medicineBean) throws AppException;
	public void getAllMedicines(MedicineBean medicineBean) throws AppException;
	public void searchMedicineDetails(MedicineBean medicineBean) throws AppException;
	public void viewMedicineDetails(MedicineBean medicineBean) throws AppException;
	public void deleteMedicineDetails(MedicineBean medicineBean) throws AppException;
	public void getAllMedicineBatches(MedicineBean medicineBean) throws AppException;
	public void getMedicineDetailsByMedicineId(MedicineBean medicineBean) throws AppException;
	public void getMedicineByBatchAndMedicineId(MedicineBean medicineBean) throws AppException;
	public void getMedicinesByCompanyId(MedicineBean medicineBean) throws AppException;
	public void saveMedicineIntoInvoice(MedicineBean medicineBean) throws AppException;
}
