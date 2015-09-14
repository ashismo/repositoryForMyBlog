package com.ashish.medicine.admin.base;

import java.sql.Timestamp;

public class MedicineBaseBean {
	protected int userId;
	protected Timestamp dbAddTs;
	protected int dbAddUser;
	protected Timestamp dbUpdTs;
	protected int dbUpdUser;
	public Timestamp getDbAddTs() {
		return dbAddTs;
	}
	public void setDbAddTs(Timestamp dbAddTs) {
		this.dbAddTs = dbAddTs;
	}
	public int getDbAddUser() {
		return dbAddUser;
	}
	public void setDbAddUser(int dbAddUser) {
		this.dbAddUser = dbAddUser;
	}
	public Timestamp getDbUpdTs() {
		return dbUpdTs;
	}
	public void setDbUpdTs(Timestamp dbUpdTs) {
		this.dbUpdTs = dbUpdTs;
	}
	public int getDbUpdUser() {
		return dbUpdUser;
	}
	public void setDbUpdUser(int dbUpdUser) {
		this.dbUpdUser = dbUpdUser;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
}
