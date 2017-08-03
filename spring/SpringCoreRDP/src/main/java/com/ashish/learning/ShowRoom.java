package com.ashish.learning;

public class ShowRoom {
	private FourWheelers fourWheelers = null;
	private String greetings;
	
	public ShowRoom() {}
	
	public ShowRoom(FourWheelers fourWheeler) {
		this.fourWheelers = fourWheeler;
	}
	
	public void setFourWheelers(FourWheelers fourWheelers) {
		this.fourWheelers = fourWheelers;
	}

	public FourWheelers getFourWheelers() {
		return fourWheelers;
	}

	public void getCar() {
		fourWheelers.prepareFourwheelers();
	}
	
	public void getCustomCar(String msg) {
		System.out.println(msg);
	}

	public String getGreetings() {
		return greetings;
	}

	public void setGreetings(String greetings) {
		this.greetings = greetings;
	}
}
