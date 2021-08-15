package com.ashish.java8.threads;


// There are two threads called Consumer and Producer. The inter thread communication will lock on inventory object 
// to make sure the consumer consumes only after producer produces. If producer is faster than consumer then Producer will wait
// until consumer consumers (after 2 secs)
public class InterthreadCommunication {
	public static void main(String[] args) throws InterruptedException {
		Inventory inventory = new Inventory();
		new Producer(inventory);
		new Consumer(inventory);
	}
	
}

class Producer implements Runnable {

	Inventory inventory;
	
	Producer(Inventory inventory) {
		this.inventory = inventory;
		new Thread(this, "Producer").start();
	}
	@Override
	public void run() {
		while(true) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			inventory.addProduceNum();
		}
	}
	
}

class Consumer implements Runnable {

	Inventory inventory;
	
	Consumer(Inventory inventory) {
		this.inventory = inventory;
		new Thread(this, "Consumer").start();
	}
	@Override
	public void run() {
		while(true) {
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			inventory.getProduceNum();
		}
	}
	
}

class Inventory {
	int produceNum;
	
	boolean isProduced = false;
	
	public synchronized int getProduceNum() {
		while(!isProduced) {
			try {
				wait();
			} catch (Exception e) {
				
			}
		}
		System.out.println("Consumed: " + produceNum);
		isProduced = false;
		notify();
		return this.produceNum;
	}
	public synchronized void addProduceNum() {
		while(isProduced) {
			try {
				wait();
			} catch (Exception e) {
				
			}
		}
		isProduced = true;
		this.produceNum++;
		System.out.println("Produced: " + produceNum);
		notify();
	}
}
