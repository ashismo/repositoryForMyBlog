package com.ashish.medicine.admin.schedule;

import com.ashish.medicine.exception.AppException;

public class ScheduleServiceHelperImpl implements ScheduleServiceHelper {

	public void addOrUpdateSchedule(ScheduleBean scheduleBean) throws AppException {
		new ScheduleDaoImpl().addOrUpdateSchedule(scheduleBean);
	}

	public void scheduleCriteriaForm(ScheduleBean scheduleBean) throws AppException {
		new ScheduleDaoImpl().scheduleCriteriaForm(scheduleBean);
	}

	public void viewSchedule(ScheduleBean scheduleBean) throws AppException {
		new ScheduleDaoImpl().viewSchedule(scheduleBean);
	}

	public void deleteSchedule(ScheduleBean scheduleBean) throws AppException {
		new ScheduleDaoImpl().deleteSchedule(scheduleBean);		
	}

	public void getAllCompanies(ScheduleBean scheduleBean) throws AppException {
		new ScheduleDaoImpl().getAllCompanies(scheduleBean);		
	}
}
