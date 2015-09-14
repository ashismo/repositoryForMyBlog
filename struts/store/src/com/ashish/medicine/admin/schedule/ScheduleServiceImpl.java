package com.ashish.medicine.admin.schedule;

import com.ashish.medicine.exception.AppException;

public class ScheduleServiceImpl implements ScheduleService {

	public void addOrUpdateSchedule(ScheduleBean scheduleBean) throws AppException {
		new ScheduleServiceHelperImpl().addOrUpdateSchedule(scheduleBean);
	}

	public void scheduleCriteriaForm(ScheduleBean scheduleBean) throws AppException {
		new ScheduleServiceHelperImpl().scheduleCriteriaForm(scheduleBean);
	}

	public void viewSchedule(ScheduleBean scheduleBean) throws AppException {
		new ScheduleServiceHelperImpl().viewSchedule(scheduleBean);
	}

	public void deleteSchedule(ScheduleBean scheduleBean) throws AppException {
		new ScheduleServiceHelperImpl().deleteSchedule(scheduleBean);
	}

	public void getAllCompanies(ScheduleBean scheduleBean) throws AppException {
		new ScheduleServiceHelperImpl().getAllCompanies(scheduleBean);
	}

}
