package caveofprogramming;

/**
 * Use of synchronized block
 * - lost update 
 * - atomicity
 * 
 * @author mahendra
 *
 */

class Worker {
private int count;
	
	private void increment() {
		synchronized (this) {
			count++;
		}
		
	}
	public void run() {
		
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
			
				for(int i=0; i<10000; i++) {
					increment(); 
				}
			}
		});
		
		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
			
				for(int i=0; i<10000; i++) {
					increment();
				}
			}
		});
		
		t1.start();
		t2.start();

		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
		}
		
		System.out.println("Counter : "+count);
		
	}
}

public class Threading3 {

	public static void main(String args[]) {
		Worker worker = new Worker();
		worker.run();
	}
}
