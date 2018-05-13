package caveofprogramming.threadexample;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


class Processor6 implements Runnable {
	
	private CountDownLatch latch;
	private Random r;
	public Processor6(CountDownLatch latch) {
		this.latch = latch;
		this.r = new Random();
	}
	public void run() {
		//do some work, when done countdown the latch;
		System.out.println("Startinig the work ...");
		sleep(r.nextInt(5000));
		System.out.println("Done with work, counting down ");
		latch.countDown();
		System.out.println("Current latch count: "+latch.getCount());
	}
	
	public void sleep(long time) {
		try {
			Thread.sleep(time);
		}catch(InterruptedException e) { }
	}
}

public class ThreadExample6 {
	public static void main(String[] args) {
		
		ExecutorService executorService = Executors.newFixedThreadPool(2);
		CountDownLatch latch = new CountDownLatch(6);
		for(int i=0; i<6; i++) {
			executorService.submit(new Processor6(latch));
		}
		executorService.shutdown(); // no more threads would be assigeed to the executor service , this lock any more job submissions.
		//wait till all the 6 threads are done
		try {
			latch.await();
			System.out.println("All threads done...");
		}catch(InterruptedException e) { e.printStackTrace();}
	}

}

