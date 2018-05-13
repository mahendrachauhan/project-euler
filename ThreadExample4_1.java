package caveofprogramming.threadexample;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ThreadExample4_1 {

static class Worker {
	
	private final Object lock1 = new Object();
	private final Object lock2 = new Object();
	
	private List<Integer> list1 = new ArrayList<Integer>();
	private List<Integer> list2 = new ArrayList<Integer>();
	private Random random = new Random();
	
	public void stageOne() {
		synchronized(lock1) {
			sleep(1);
			list1.add(random.nextInt(100));
		}
	}
	
	public synchronized void stageTwo() {
		synchronized(lock2) {
			sleep(1);
			list2.add(random.nextInt(100));
		}
	}
	
	public void process() {
		for(int i=0; i<1000; i++) {
			stageOne();
			stageTwo();
		}
	}
	
	public void main() {
		//create two thread and count total elements and time taken
		Thread t1 = new Thread(new Runnable() {
			public void run() { process(); }
		});
		Thread t2 = new Thread(new Runnable() {
			public void run() { process(); }
		});
		long start = System.currentTimeMillis();
		t1.start();
		t2.start();
		
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		print("Time taken = "+( System.currentTimeMillis() - start));
		print("list1 = "+list1.size() + ", list2 = "+list2.size());
		
	}
	private void sleep(long time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	public void print(String printSting) {
		System.out.println(printSting);
	}
}

public static void main(String[] args) {
	new Worker().main();
}


}