package com.dozerbean;

public class ChildBean {
	private int id;
	private int age;
	private String name;
	private int fId;
	private int mId;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
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
	@Override
	public String toString() {
		return "ChildBean [id=" + id + ", age=" + age + ", name=" + name
				+ ", fId=" + fId + ", mId=" + mId + "]";
	}
	
	
}
