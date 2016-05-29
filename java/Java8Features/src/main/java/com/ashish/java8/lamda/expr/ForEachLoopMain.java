package com.ashish.java8.lamda.expr;

import java.util.ArrayList;
import java.util.List;

public class ForEachLoopMain {
	public static void main(String args[]) {
		List<Integer> list = new ArrayList<Integer>();
		list.add(10);
		list.add(20);
		list.add(30);
		
		System.out.println("Traditional Loop START");
		for(int i : list) {
			System.out.println(i);
		}
		System.out.println("Traditional Loop END");
		
		System.out.println("ForEach Loop START");
		
		list.forEach(n -> {System.out.println(n);});
		
		System.out.println("ForEach Loop END");
		
		System.out.println("Another way to write ForEach Loop START");
		
		list.forEach(System.out::println);
		
		System.out.println("Another way to write ForEach Loop END");
	}
}
