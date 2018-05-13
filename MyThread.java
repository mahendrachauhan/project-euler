package caveofprogramming.threadexample;

public class MyThread extends Thread {

	/**
	 * Three different ways you can create thread are
	 * 1-extending Thread class
	 * 2-implementing Runnable interface.
	 * 3-using executorframework - thread pool
	 * 
	 */
	private String name;
	
	public MyThread(String name) {
		this.name = name;
	}
	
	public void run() {
		System.out.println("Entering run");
		System.out.println("Thread name:"+name);
		System.out.println("Leaving run");
	}
	
	public static void main(String args[]) {

		Thread t1 = new MyThread("first");
		Thread t2 = new MyThread("second");
		t1.start();
		t2.start();
		
		try {
			t1.join();
			t2.join();
		}catch(InterruptedException e) {
			
		}
		
		System.out.println("Done");
		
	}
}
