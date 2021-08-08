package com.ashish.java8.comparable.comparator;

public class Laptop implements Comparable<Laptop> {
	private String brand;
	private int ram;
	
	public Laptop(String brand, int ram) {
		this.brand = brand;
		this.ram = ram;
	}
	
	public int compareTo(Laptop l) {
		if(this.ram > l.getRam()) {
			return 1;
		} else {
			return -1;
		}
	}
	
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public int getRam() {
		return ram;
	}
	public void setRam(int ram) {
		this.ram = ram;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((brand == null) ? 0 : brand.hashCode());
		result = prime * result + ram;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Laptop other = (Laptop) obj;
		if (brand == null) {
			if (other.brand != null)
				return false;
		} else if (!brand.equals(other.brand))
			return false;
		if (ram != other.ram)
			return false;
		return true;
	}
}
