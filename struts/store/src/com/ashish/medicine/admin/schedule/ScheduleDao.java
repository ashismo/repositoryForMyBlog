package com.ashish.medicine.admin.schedule;

import com.ashish.medicine.entity.Schedule;
import com.ashish.medicine.exception.AppException;

public interface ScheduleDao {
	public void addOrUpdateSchedule(ScheduleBean scheduleBean) throws AppException;
	public void getAllCompanies(ScheduleBean scheduleBean) throws AppException;
	public void scheduleCriteriaForm(ScheduleBean scheduleBean) throws AppException;
	public void viewSchedule(ScheduleBean scheduleBean) throws AppException;
	public void deleteSchedule(ScheduleBean scheduleBean) throws AppException;
	public Schedule getScheduleByScheduleId(ScheduleBean scheduleBean) throws AppException;
}
