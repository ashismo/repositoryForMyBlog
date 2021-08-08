package com.ashish.java8.comparable.comparator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ObjectSort {
	public static void main(String[] args) {
		List<Laptop> laptops = new ArrayList<>();
		laptops.add(new Laptop("dell",160));
		laptops.add(new Laptop("Asus",8));
		laptops.add(new Laptop("Acer",12));
		System.out.println("\n\n Comparable before Soting: \n\n");
		
		laptops.stream().forEach(x->System.out.println(x.getBrand() + " " + x.getRam() + " GB"));
		
		Collections.sort(laptops);
		System.out.println("\n\nAfter <Comparable> Soting: \n\n");
		laptops.stream().forEach(x->System.out.println(x.getBrand() + " " + x.getRam() + " GB"));
		
		
		Comparator<Laptop2> comparator = new Comparator<Laptop2>() {

			@Override
			public int compare(Laptop2 o1, Laptop2 o2) {
				if(o1.getRam() > o2.getRam()) {
					return 1;
				} else {
					return -1;
				}
			}
		};
		
		
		System.out.println("\n\nComparator before Soting: \n\n");
		List<Laptop2> laptops2 = new ArrayList<>();
		laptops2.add(new Laptop2("Lenovo",16));
		laptops2.add(new Laptop2("Apple",8));
		laptops2.add(new Laptop2("Acer",12));
		
		laptops.stream().forEach(x->System.out.println(x.getBrand() + " " + x.getRam() + " GB"));
		
		System.out.println("\n\nAfter <Comparator> Soting: \n\n");
		
		Collections.sort(laptops2, comparator);
		
		laptops.stream().forEach(x->System.out.println(x.getBrand() + " " + x.getRam() + " GB"));
	}
}
