package caveofprogramming.threadexample;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;


//Producer consumer using blocking queue;

class ProducerConsumer {
	private BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(10);
	private Random random = new Random();
	public void produce() throws InterruptedException {
		while(true) {
			queue.put(random.nextInt(100));
		}
	}
	
	public void consume() throws InterruptedException{
		while(true) {
			sleep(100);
			if(random.nextInt(10) == 0) {
				int value = queue.take();
				System.out.println("Taken value="+value+ " Size = "+queue.size());
			}
		}
	}
	public void sleep(long time) {
		try {
			Thread.sleep(time);
		}catch(InterruptedException e) { }
	}
}

public class ThreadExample7 {
	public static void main(String[] args) {
		final ProducerConsumer pc = new ProducerConsumer();
		
		Thread t1 = new Thread(new Runnable() {
			public void run() {
				try {
					pc.produce();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	
		Thread t2 = new Thread(new Runnable() {
			public void run() {
				try {
					pc.consume();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		t1.start();
		t2.start();
		
		
	}
	

}
