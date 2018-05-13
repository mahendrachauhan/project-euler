package caveofprogramming.threadexample;

public class DeadLockWithoutSleep {
	
	private final Object lock1 = new Object();
	private final Object lock2 = new Object();
	
	public void methodOne() throws InterruptedException {
		
		System.out.println("Waiting for lock1");
		synchronized (lock1) {
			System.out.println("Acquired lock1");
			System.out.println("Releasing lock1");
			lock1.wait();
			System.out.println("Acquired lock1");
			System.out.println("Waiting for lock2");
			synchronized (lock2) {
				lock2.wait();
			}
			lock1.notify();
		}
	}
	
	
	
	
	public void methodTwo() throws InterruptedException {
		
		System.out.println("Waiting for lock2");
		synchronized (lock2) {
			System.out.println("Acquired lock2");
			System.out.println("Releasing lock2");
			lock2.wait();
			System.out.println("Acquired lock2");
			System.out.println("Waiting for lock1");
			synchronized (lock1) {
				lock1.notify();
			}
			lock2.notify();
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		final DeadLockWithoutSleep deadlock = new DeadLockWithoutSleep();
		Thread t1 = new Thread(new Runnable() {
			public void run() {
				try {
					deadlock.methodOne();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		Thread t2 = new Thread(new Runnable() {
			public void run() {
				try {
					deadlock.methodTwo();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});

		t1.start();
		t2.start();
		
		t1.join();
		t2.join();
	}

}
