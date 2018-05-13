package caveofprogramming.threadexample;
// Worker thread increment variable - dirty read
// then make it thread safe

class Worker {
	private int counter;
	public void increment() {
		synchronized(this) {
			counter++;
		}
	}
	public int getCounter() {
		return counter;
	}
}

class WorkerThread implements Runnable {
	private Worker worker;
	public WorkerThread (Worker worker) {
		this.worker = worker;
	}
	
	@Override
	public void run() {
		for(int i=0; i<1000;i++) {
			worker.increment();
		}
	}
}


public class ThreadExample3 {

public static void main(String[] args) {
	Worker w = new Worker();
	Thread t1 = new Thread(new WorkerThread(w));
	Thread t2 = new Thread(new WorkerThread(w));
	
	t1.start();
	t2.start();
	
	try {
		t1.join();
		t2.join();
	}catch(InterruptedException e) {}
	
	System.out.println("Counter = "+ w.getCounter());
	
}


}

