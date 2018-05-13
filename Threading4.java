package caveofprogramming;

/**
 * try not to use synchronized method but use synchronized block instead.
 * make sure you use proper monitor lock, if process are independent and
 * can be done in parallel use diff object lock for them
 * 
 * Design : lock should be private, final bcoz this provide safety.
 * 
 * @author mahendra
 *
 */

 class Worker_4 {
	
	private final Object lock1 = new Object();
	private final Object lock2 = new Object();
	public void stageOne() {
		synchronized (lock1) {
			sleep();
		}
		
	}
	
	
	public void stageTwo() {
		synchronized (lock2) {
			sleep();
		}
		
	}
	
	private void sleep() {
		try {
			Thread.sleep(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void process() {
		stageOne();
		stageTwo();
	}
	
	public void run() {
		long time = System.currentTimeMillis();
		Runnable r = new Runnable() {
			@Override
			public void run() {
				for(int i=0 ; i<100; i++) {
					process();
				}
			}
		};
		
		Thread t1 = new Thread(r);
		Thread t2 = new Thread(r);
		Thread t3 = new Thread(r);
		Thread t4 = new Thread(r);
		
		t1.start();
		t2.start();
		t3.start();
		t4.start();
		
		try {
			t1.join();
			t2.join();
			t3.join();
			t4.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("Time elapsed : "+(System.currentTimeMillis() - time));
	}
}

public class Threading4 {

	
	public static void main(String args[]) {
		
		Worker_4 w = new Worker_4();
		w.run();
	}
}
