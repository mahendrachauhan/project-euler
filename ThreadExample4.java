package caveofprogramming.threadexample;

/*
try not to use synchronized method, insted use synchronized block, if any section that can be done in parallel, it should not be in synchronized block
if you synchronized on object/lock , then they should be private and final , for safety and deterninistic output.
*/

class Worker_4 {
	private final Object lock_1 = new Object();
	private final Object lock_2 = new Object();
	
	public void step1() {
		synchronized(lock_1) {
			sleep();
		}
	}
	
	public void step2() {
	synchronized(lock_2) {
			sleep();
		}
	}
	
	public void process() {
		step1();
		step2();
	}
	
	public void sleep() {
		try {
			Thread.sleep(5);
		}catch(InterruptedException e) { }
	}
}

class WorkerThread_4 implements Runnable {
	private Worker_4 worker;
	public WorkerThread_4(Worker_4 worker) {
		this.worker = worker;
	}
	
	@Override
	public void run() {
		System.out.println(Thread.currentThread());
		for(int i=0; i<100;i++) {
			worker.process();
		}
	}
}


public class ThreadExample4 {

public static void main(String[] args) {
	WorkerThread_4 wt = new WorkerThread_4(new Worker_4());
	Thread t1 = new Thread(wt);
	Thread t2 = new Thread(wt);
	Thread t3 = new Thread(wt);
	Thread t4 = new Thread(wt);
	Thread t5 = new Thread(wt);
	Thread t6 = new Thread(wt);
	
	long t = -System.currentTimeMillis();
	t1.start();
	t2.start();
	t3.start();
	t4.start();
	t5.start();
	t6.start();
	
	try {
		t1.join();
		t2.join();
		t3.join();
		t4.join();
		t5.join();
		t6.join();
		
	}catch(InterruptedException e) {}
	
	System.out.println("Total time elapsed :  "+ (t +System.currentTimeMillis()));
	
}


}

