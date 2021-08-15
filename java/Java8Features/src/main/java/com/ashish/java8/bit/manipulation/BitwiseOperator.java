package com.ashish.java8.bit.manipulation;

public class BitwiseOperator {
	public static void main(String[] args) {
		int a = 15;
		int b = 27;
		
		String binary1 = Integer.toBinaryString(a);
		String binary2 = Integer.toBinaryString(b);
		
		System.out.println("Binary of " + a + " is " + binary1);
		System.out.println("Binary of " + b + " is " + binary2);
		// Binary AND
		int and = a & b;
		String binary = Integer.toBinaryString(and);
		System.out.println("Binary AND: " + binary);
		
		
		// Binary One Complement
		int comp = ~a;
		String complement = Integer.toBinaryString(comp);
		System.out.println("Binary One complement of a: " + complement);
		
		// Left Shift 1 to 3rd place
		int leftShift = (1 << 3);
		String leftShiftPlace = Integer.toBinaryString(leftShift);
		System.out.println("Shift 1 by 3 places: " + leftShiftPlace);
		
		
		// Right Shift 1 to 3rd place
		int rightShift = (1 >> 3);
		String rightShiftPlace = Integer.toBinaryString(rightShift);
		System.out.println("Shift 1 by 3 places: " + rightShiftPlace);
	}
}
