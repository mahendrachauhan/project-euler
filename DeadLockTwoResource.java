package caveofprogramming.threadexample;

public class DeadLockTwoResource implements Runnable {

	static Object lock1 = new Object();
	static Object lock2 = new Object();
	
	public void method1() {
		synchronized (lock1) {
			System.out.println("Acquired lock1");
			delay(500);
			System.out.println("Waiting for lock2");
			synchronized (lock2) {
				System.out.println("method1 done");
			}
		}
	}
	
	public void method2() {
		synchronized (lock2) {
			System.out.println("Acquired lock2");
			delay(500);
			System.out.println("Waiting for lock1");
			synchronized (lock1) {
				System.out.println("method2 done");
			}
		}
	}
	
	public void delay(long time) {
		try {
			Thread.sleep(time);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	public void run() {
		method1();
		method2();
	}
	
	
	public static void main(String args[]) {
		
		Thread t1 = new Thread(new DeadLockTwoResource());
		Thread t2 = new Thread(new DeadLockTwoResource());
		
		t1.start();
		t2.start();
		
	}
}
