package com.ashish.learning.expression.language;

public class Car {
	private String wheelType;
	private String make;
	private double randomSpeed; 
	private Wheels wheels;
	private CarFuel carFuel;
	
	public String getWheelType() {
		return wheelType;
	}
	public void setWheelType(String wheelType) {
		this.wheelType = wheelType;
	}
	public Wheels getWheels() {
		return wheels;
	}
	public void setWheels(Wheels wheels) {
		this.wheels = wheels;
	}
	public String getMake() {
		return make;
	}
	public void setMake(String make) {
		this.make = make;
	}
	public double getRandomSpeed() {
		return randomSpeed;
	}
	public void setRandomSpeed(double randomSpeed) {
		this.randomSpeed = randomSpeed;
	}
	public CarFuel getCarFuel() {
		return carFuel;
	}
	public void setCarFuel(CarFuel carFuel) {
		this.carFuel = carFuel;
	}
}
