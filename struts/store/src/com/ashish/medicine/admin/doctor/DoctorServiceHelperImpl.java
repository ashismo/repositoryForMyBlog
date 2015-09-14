package com.ashish.medicine.admin.doctor;

import com.ashish.medicine.exception.AppException;

public class DoctorServiceHelperImpl implements DoctorServiceHelper {

	public void addOrUpdateDoctor(DoctorBean doctorBean) throws AppException {
		new DoctorDaoImpl().addOrUpdateDoctor(doctorBean);
	}

	public void searchDoctor(DoctorBean doctorBean) throws AppException {
		new DoctorDaoImpl().searchDoctor(doctorBean);
	}

	public void viewDoctor(DoctorBean doctorBean) throws AppException {
		new DoctorDaoImpl().viewDoctor(doctorBean);
	}

	public void deleteDoctor(DoctorBean doctorBean) throws AppException {
		new DoctorDaoImpl().deleteDoctor(doctorBean);		
	}
	
	public void getAllDoctors(DoctorBean doctorBean) throws AppException {
		new DoctorDaoImpl().getAllDoctors(doctorBean);		
	}

	@Override
	public void getDoctorByDoctorId(DoctorBean doctorBean) throws AppException {
		new DoctorDaoImpl().getDoctorByDoctorId(doctorBean);	
	}
}
