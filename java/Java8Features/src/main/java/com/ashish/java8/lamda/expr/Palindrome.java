package com.ashish.java8.lamda.expr;

import java.util.stream.Stream;

public class Palindrome {
	public static void main(String[] args) {
		String[] str = {"abc","aba","cdc","cda"};
		Stream
			.of(str)
			.filter(x-> x.equals(new StringBuilder(x).reverse().toString()))
			.forEach(x->System.out.println(x + " is palindrome"));
	}
}
