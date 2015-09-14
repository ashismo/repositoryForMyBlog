package com.ashish.medicine.admin.doctor;

import com.ashish.medicine.exception.AppException;

public class DoctorServiceImpl implements DoctorService {

	public void addOrUpdateDoctor(DoctorBean doctorBean) throws AppException {
		new DoctorServiceHelperImpl().addOrUpdateDoctor(doctorBean);
	}

	public void searchDoctor(DoctorBean doctorBean) throws AppException {
		new DoctorServiceHelperImpl().searchDoctor(doctorBean);
	}

	public void viewDoctor(DoctorBean doctorBean) throws AppException {
		new DoctorServiceHelperImpl().viewDoctor(doctorBean);
	}

	public void deleteDoctor(DoctorBean doctorBean) throws AppException {
		new DoctorServiceHelperImpl().deleteDoctor(doctorBean);
	}

	public void getAllDoctors(DoctorBean doctorBean) throws AppException {
		new DoctorServiceHelperImpl().getAllDoctors(doctorBean);
	}

	@Override
	public void getDoctorByDoctorId(DoctorBean doctorBean) throws AppException {
		new DoctorServiceHelperImpl().getDoctorByDoctorId(doctorBean);
	}
}
