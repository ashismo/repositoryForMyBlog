package com.ashish.java8.threads;


// There are two threads called Consumer and Producer. The inter thread communication will lock on inventory object 
// to make sure the consumer consumes when the stock is positive.
public class InterthreadCommunication {
	public static void main(String[] args) throws InterruptedException {
		Inventory inventory = new Inventory();
		Thread producer = new Thread(new Producer(inventory));
		Thread consumer = new Thread(new Consumer(inventory));
		
		producer.start();
		consumer.start();
		
		producer.join();
		consumer.join();
	}
	
}

class Producer implements Runnable {

	Inventory inventory;
	
	Producer(Inventory inventory) {
		this.inventory = inventory;
	}
	@Override
	public void run() {
		
		synchronized (inventory) {
			for(int i = 0; i < 6; i++) {
				inventory.addProduceNum();
				if(inventory.getProduceNum() <= 0) {
					try {
						System.out.println("Producer is waiting");
						inventory.wait();
					} catch (InterruptedException e) {
						System.out.println("Error in producer:");
						e.printStackTrace();
					}
				} else {
					inventory.notify();
					System.out.println("Consumer is notified");
				}
				System.out.println("Producer: " + inventory.getProduceNum());
			}
		}
	}
	
}

class Consumer implements Runnable {

	Inventory inventory;
	
	Consumer(Inventory inventory) {
		this.inventory = inventory;
	}
	@Override
	public void run() {
		
		synchronized (inventory) {
			for(int i = 0; i < 10; i++) {
				inventory.removeProduceNum();
				
				if(inventory.getProduceNum() <= 0) {
					try {
						System.out.println("Consumer is waiting. Current inventory is: " + inventory.getProduceNum());
						inventory.wait();
					} catch (InterruptedException e) {
						System.out.println("Error in consumer:");
						e.printStackTrace();
					}
				} else {
					inventory.notify();
					System.out.println("Producer is notified. Current inventory is: " + inventory.getProduceNum());
				}
				
				
				System.out.println("Consumer: " + inventory.getProduceNum());
			}
		}
	}
	
}

class Inventory {
	int produceNum;
	
	public int getProduceNum() {
		return produceNum;
	}
	public void addProduceNum() {
		this.produceNum++;
	}
	public void removeProduceNum() {
		this.produceNum--;
	}
}
