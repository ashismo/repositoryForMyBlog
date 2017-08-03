package com.ashish.learning;

public class ShowRoom {
	private FourWheelers fourWheelers = null;
	public ShowRoom(FourWheelers fourWheeler) {
		this.fourWheelers = fourWheeler;
	}
	
	public void getCar() {
		fourWheelers.prepareFourwheelers();
	}
}
