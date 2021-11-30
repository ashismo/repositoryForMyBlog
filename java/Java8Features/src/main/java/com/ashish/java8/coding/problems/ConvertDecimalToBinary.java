package com.ashish.java8.coding.problems;

public class ConvertDecimalToBinary {
	public static void main(String[] args) {
		int decimal = 13;
		int input = decimal;
		String binary = Integer.toBinaryString(13);
		
		System.out.println(input + " to Binary " + binary);
		
		String binaryStr = "";
		while(true) {
			binaryStr = binaryStr + (decimal % 2);
			decimal = decimal / 2;
			
			if(decimal == 0) {
				break;
			}
		}
		
		System.out.println(input + " to Binary " + new StringBuilder(binaryStr).reverse().toString());
	}
}
