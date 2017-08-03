package com.ashish.learning;

import java.util.List;
import java.util.Map;

public class ShowRoom {
	private FourWheelers fourWheelers = null;
	private String greetings;
	private List<String> cars;
	private Map<String, Object> carsMap;
	
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

	public List<String> getCars() {
		return cars;
	}

	public void setCars(List<String> cars) {
		this.cars = cars;
	}

	public Map<String, Object> getCarsMap() {
		return carsMap;
	}

	public void setCarsMap(Map<String, Object> carsMap) {
		this.carsMap = carsMap;
	}
}
