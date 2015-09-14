package com.ashish.medicine.admin.doctor;

import com.ashish.medicine.exception.AppException;

public interface DoctorService {
	public void addOrUpdateDoctor(DoctorBean doctorBean) throws AppException;
	public void searchDoctor(DoctorBean doctorBean) throws AppException;
	public void viewDoctor(DoctorBean doctorBean) throws AppException;
	public void deleteDoctor(DoctorBean doctorBean) throws AppException;
	public void getAllDoctors(DoctorBean doctorBean) throws AppException;
	public void getDoctorByDoctorId(DoctorBean doctorBean) throws AppException;
}
