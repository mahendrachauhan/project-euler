package caveofprogramming;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;


/**
 * Producer Consumer using Blocking queue. 
 * @author mahendra
 *
 */
public class Threading7 {

	private static BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(10);
	
	public static void producer() throws InterruptedException {
		
		Random random = new Random();
		while(true) {
			queue.put(random.nextInt(100));
			
		}
	}
	
	public static void consumer() throws InterruptedException {
		Random random = new Random();
		
		while(true) {
			Thread.sleep(100);
			
			if(random.nextInt(10) == 0) {
				Integer data = queue.take();
				System.out.println("Taken value="+data +", size = "+queue.size());
			}
		}
	}
	
	public static void main(String args[]) throws InterruptedException {
		
		Thread t1 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					producer();
				} catch (InterruptedException e) {
				}
			}
		});
		
		Thread t2 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					consumer();
				} catch (InterruptedException e) {
				}
			}
		});
		
		t1.start();
		t2.start();
		
	}
}
