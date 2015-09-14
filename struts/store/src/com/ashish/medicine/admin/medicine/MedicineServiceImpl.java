package com.ashish.medicine.admin.medicine;

import com.ashish.medicine.exception.AppException;

public class MedicineServiceImpl implements MedicineService {

	public void addOrUpdateMedicine(MedicineBean medicineBean) throws AppException {
		new MedicineServiceHelperImpl().addOrUpdateMedicine(medicineBean);
	}

	public void searchMedicineDetails(MedicineBean medicineBean) throws AppException {
		new MedicineServiceHelperImpl().searchMedicineDetails(medicineBean);
	}

	public void viewMedicineDetails(MedicineBean medicineBean) throws AppException {
		new MedicineServiceHelperImpl().viewMedicineDetails(medicineBean);
	}

	public void deleteMedicineDetails(MedicineBean medicineBean) throws AppException {
		new MedicineServiceHelperImpl().deleteMedicineDetails(medicineBean);
	}

	public void getAllMedicines(MedicineBean medicineBean) throws AppException {
		new MedicineServiceHelperImpl().getAllMedicines(medicineBean);
	}

	public void getAllMedicineBatches(MedicineBean medicineBean)
			throws AppException {
		new MedicineServiceHelperImpl().getAllMedicineBatches(medicineBean);		
	}

	@Override
	public void getMedicinesByCompanyId(MedicineBean medicineBean)
			throws AppException {
		new MedicineServiceHelperImpl().getMedicinesByCompanyId(medicineBean);		
	}

	public void getMedicineDetailsByMedicineId(MedicineBean medicineBean)
			throws AppException {
		
		new MedicineServiceHelperImpl().getMedicineDetailsByMedicineId(medicineBean);
	}

	public void getMedicineByBatchAndMedicineId(MedicineBean medicineBean)
			throws AppException {
		new MedicineServiceHelperImpl().getMedicineByBatchAndMedicineId(medicineBean);
		
	}

	public void saveMedicineIntoInvoice(MedicineBean medicineBean)
			throws AppException {
		new MedicineServiceHelperImpl().saveMedicineIntoInvoice(medicineBean);
	}

}
