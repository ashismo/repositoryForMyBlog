package com.dozerbean;

import java.util.List;

public class ParentBean {
	private int fId;
	private int mId;
	private String fName;
	private String mName;
	private int fAge;
	private int mAge;
	private List<ChildBean> child;
	public int getfId() {
		return fId;
	}
	public void setfId(int fId) {
		this.fId = fId;
	}
	public int getmId() {
		return mId;
	}
	public void setmId(int mId) {
		this.mId = mId;
	}
	public String getfName() {
		return fName;
	}
	public void setfName(String fName) {
		this.fName = fName;
	}
	public String getmName() {
		return mName;
	}
	public void setmName(String mName) {
		this.mName = mName;
	}
	public int getfAge() {
		return fAge;
	}
	public void setfAge(int fAge) {
		this.fAge = fAge;
	}
	public int getmAge() {
		return mAge;
	}
	public void setmAge(int mAge) {
		this.mAge = mAge;
	}
	public List<ChildBean> getChild() {
		return child;
	}
	public void setChild(List<ChildBean> child) {
		this.child = child;
	}
	@Override
	public String toString() {
		return "ParentBean [fId=" + fId + ", mId=" + mId + ", fName=" + fName
				+ ", mName=" + mName + ", fAge=" + fAge + ", mAge=" + mAge
				+ ", child=" + child + "]";
	}
	
	
}
