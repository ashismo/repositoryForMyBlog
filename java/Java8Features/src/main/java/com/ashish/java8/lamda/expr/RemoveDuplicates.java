package com.ashish.java8.lamda.expr;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class RemoveDuplicates {
	public static void main(String[] args) {
		String[] str = {"abc","def","abc","ade"};
		
		// Method 1: Remove duplicate using SET
		System.out.println("Method 1: Remove duplicate using SET");
		Set<String> strSet = new HashSet<String>();
		
		Stream.of(str).forEach(x-> strSet.add(x));
		
		System.out.print("Final array after removing duplicates: ");
		strSet.forEach(x->System.out.print(x + ","));
		
		// METHOD 2: Remove duplicate using List
		System.out.println("\n\n\nMethod 2: Remove duplicate using List");
		List<String> list = new ArrayList<String>();
		Stream
			.of(str)
			.forEach(x-> {
				if(list.contains(x)) {
					System.out.println("Duplicate record: " + x);
				} else {
					list.add(x);
				}
			});
		
		System.out.print("Final array after removing duplicates: ");
		list.forEach(x->System.out.print(x + ","));
		
		// Method 3: Remove duplicate without using Collections API
		System.out.println("\n\n\nMethod 3: Remove duplicate without using Collections API");
		IntStream.range(0, str.length)
				.filter(index->{
					String selectedVal = str[index];
					AtomicInteger count = new AtomicInteger(0);
					
					Stream.of(str)
						.forEach(x-> {
							if(x.equals(selectedVal)) {
								count.incrementAndGet();
							}
//							return true;
						});
					System.out.println(count.get());
					if(count.get() == 1) {
						return true;
					} else {
						System.out.println("Duplicate: " + selectedVal);
						return false;
					}
				})
				.forEach(index->System.out.print("Final:" + str[index] + ", "));
	}
}
