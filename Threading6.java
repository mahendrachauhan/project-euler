package caveofprogramming;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * CountDownLatch is use for thread communication.
 * 
 * latch.wait() -->  blocking call waits till the count is zero.
 * Usage : 
 * 1) A thread can create latch and wait for other threads to finish 
 * particular task and communicate back to that thread and he can continue(many-1)
 * 
 * 2) Many threads can wait for a specific thread to finish some task, ( 1- many)
 * 
 *  
 *  
 * @author mahendra
 *
 */
class Processor6 implements Runnable {
	
	private CountDownLatch latch;
	
	public Processor6(CountDownLatch latch) {
		this.latch = latch;
	}
	
	
	@Override
	public void run() {
		System.out.println("Starting ...."+latch.getCount());
		
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("Decrementing latch : ");
		latch.countDown();
		System.out.println("latch : "+latch.getCount());
		
	}
}



public class Threading6 {

	public static void main(String args[]) {
		CountDownLatch latch = new CountDownLatch(5);
		
		ExecutorService executor = Executors.newFixedThreadPool(2);
		for(int i= 0; i<5; i++) {
			executor.submit(new Processor6(latch));
		}
		
		executor.shutdown();

		System.out.println("Wating on countdown latch");
		try {
			latch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("========= > Finished ");
	}
}
