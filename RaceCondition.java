package caveofprogramming.threadexample;

import java.util.Random;

//dirty read
public class RaceCondition {

	private int counter = 0;
	
	public void increment() {
		counter ++;
	}
	
	
	int getCounter() {
		//race 
		return counter;
	}
	
	public static void main(String args[]) {
		RaceCondition rc = new RaceCondition();
		Thread[] threadPool = new Thread[3];
		
		for(int i=0; i< threadPool.length; i++) {
			threadPool[i] = new Thread(new MyThreadCounter(rc));
			threadPool[i].start();
		}
		
		for(int i=0; i< threadPool.length; i++) {
			try {
				threadPool[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		for(int i=0; i< threadPool.length; i++) {
			System.out.println("Thread ["+ i +"] state : "+threadPool[i].getState());
		}
		
		System.out.println("Couter ==> "+rc.getCounter());
		
	}
}

class MyThreadCounter implements Runnable {
	
	final private RaceCondition rc; 
	
	public MyThreadCounter(RaceCondition rc) {
		this.rc = rc;
	}
	
	public void run() {
		Random random = new Random();
		
		for(int i=0; i<500; i++) {
			
			rc.increment();
			try {
				Thread.sleep(random.nextInt(20));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
	}
	
}

