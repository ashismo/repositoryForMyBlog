package com.ashish.learning.v4;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ShowRoom {
	
	@Autowired
	private FourWheelers fourWheelers;
	
	private String greetings;
	private List<String> cars;
	private Map<String, Object> carsMap;
	
	//public ShowRoom() {}
	
	@Autowired // Constructor based autowiring
	public ShowRoom(FourWheelers fourWheeler) {
		System.out.println("\n>>>>>>>>>>>>>>>CONSTRUCTOR BASED DI STARTS>>>>>>>>>>>>>\n");
		this.fourWheelers = fourWheeler;
		fourWheelers.prepareFourwheelers();
		System.out.println("\n>>>>>>>>>>>>>>>CONSTRUCTOR BASED DI ENDS>>>>>>>>>>>>>\n");
	}
	
	@Autowired // Setter based auto wiring
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
