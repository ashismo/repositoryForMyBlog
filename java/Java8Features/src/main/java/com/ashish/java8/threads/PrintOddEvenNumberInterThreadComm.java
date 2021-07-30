package com.ashish.java8.threads;

import java.util.stream.IntStream;
import java.util.stream.Stream;

public class PrintOddEvenNumberInterThreadComm {
	public static void main(String[] args) {
		OddEvenHelperSharedResource oddEvenHelperSharedResource = new OddEvenHelperSharedResource();
		Thread odd = new OddThread(oddEvenHelperSharedResource);
		Thread even = new EvenThread(oddEvenHelperSharedResource);
		
		odd.start();
		even.start();
	}
}

class OddEvenHelperSharedResource {
	private boolean isOdd = true;
	
	public void printOddNumber(int number) throws InterruptedException {
		synchronized(this) {
			if(!isOdd) {
				this.wait();
			}
			System.out.println("Odd: " + number);
			isOdd = false;
			this.notify();
			
		}
	}
	
	public void printEvenNumber(int number) throws InterruptedException {
		synchronized(this) {
			if(isOdd) {
				this.wait();
			}
			System.out.println("Even: " + number);
			isOdd = true;
			this.notify();
			
		}
	}
	
	public boolean isOdd() {
		return isOdd;
	}

	public void setOdd(boolean isOdd) {
		this.isOdd = isOdd;
	}
	
}
class EvenThread extends Thread {
	OddEvenHelperSharedResource oddEvenHelperSharedResource;
	EvenThread(OddEvenHelperSharedResource oddEvenHelperSharedResource) {
		this.oddEvenHelperSharedResource = oddEvenHelperSharedResource;
	}
	
	public void run() {
		IntStream
			.range(1, 100)
			.filter(x -> (x % 2) == 0)
			.forEach(x->{
				try {
					this.oddEvenHelperSharedResource.printEvenNumber(x);
				} catch (Exception e) {
					System.out.println("Error while calling print even number method");
					e.printStackTrace();
				}
			});
	}
}


class OddThread extends Thread {
	OddEvenHelperSharedResource oddEvenHelperSharedResource;
	OddThread(OddEvenHelperSharedResource oddEvenHelperSharedResource) {
		this.oddEvenHelperSharedResource = oddEvenHelperSharedResource;
	}
	
	public void run() {
		
		IntStream
			.range(1, 100)
			.filter(x -> (x % 2) != 0)
			.forEach(x->{
				try {
					this.oddEvenHelperSharedResource.printOddNumber(x);
				} catch (Exception e) {
					System.out.println("Error while calling print odd number method");
					e.printStackTrace();
				}
			});
	}
}