package com.ashish.medicine.admin.medicine;

import javax.persistence.Transient;

import com.ashish.medicine.exception.AppException;

public class MedicineServiceHelperImpl implements MedicineServiceHelper {

	public void addOrUpdateMedicine(MedicineBean medicineBean) throws AppException {
		new MedicineDaoImpl().addOrUpdateMedicine(medicineBean);
	}

	public void searchMedicineDetails(MedicineBean medicineBean) throws AppException {
		new MedicineDaoImpl().searchMedicineDetails(medicineBean);
	}

	public void viewMedicineDetails(MedicineBean medicineBean) throws AppException {
		new MedicineDaoImpl().viewMedicineDetails(medicineBean);
	}

	public void deleteMedicineDetails(MedicineBean medicineBean) throws AppException {
		new MedicineDaoImpl().deleteMedicineDetails(medicineBean);		
	}

	public void getAllMedicines(MedicineBean medicineBean) throws AppException {
		new MedicineDaoImpl().getAllMedicines(medicineBean);		
	}

	public void getAllMedicineBatches(MedicineBean medicineBean)
			throws AppException {
		new MedicineDaoImpl().getAllMedicineBatches(medicineBean);
	}

	@Override
	public void getMedicinesByCompanyId(MedicineBean medicineBean)
			throws AppException {
		new MedicineDaoImpl().getMedicinesByCompanyId(medicineBean);		
	}

	public void getMedicineDetailsByMedicineId(MedicineBean medicineBean)
			throws AppException {
		new MedicineDaoImpl().getMedicineDetailsByMedicineId(medicineBean);
	}

	public void getMedicineByBatchAndMedicineId(MedicineBean medicineBean)
			throws AppException {
		new MedicineDaoImpl().getMedicineByBatchAndMedicineId(medicineBean);
		
	}
	
	public void saveMedicineIntoInvoice(MedicineBean medicineBean)
		throws AppException {
		new MedicineDaoImpl().saveMedicineIntoInvoice(medicineBean);
	}
}
