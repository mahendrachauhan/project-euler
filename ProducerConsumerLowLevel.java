package caveofprogramming.threadexample;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class ProducerConsumerLowLevel {

	static class ProducerConsumer {
		
		private LinkedList<Integer> list = new LinkedList<>();
		private Random random = new Random();
		
		public void producer() throws InterruptedException {
			while(true) {
				synchronized(list) {
					while(list.size() == 10) {
						list.wait();
					}
					
					if(list.size() < 10) {
						list.add(random.nextInt(100));
						//System.out.println("producer() size = "+list.size());
					}
					list.notify();
				}
			}
		}
		
		public void consumer() throws InterruptedException {
			while(true) {
				synchronized(list) {
					while(list.size() == 0) {
						list.wait();
					}
					if(list.size() > 0 ) {
						int v = list.removeFirst();
						System.out.println("Taken value = "+v +", size = "+list.size());
					}
					list.notify();
				}
				sleep(random.nextInt(500));
			}
		}
		private void sleep(long time) {
			try {
				Thread.sleep(time);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		final ProducerConsumer pc = new ProducerConsumer();
		Thread t1 = new Thread(new Runnable() {
			public void run() { try {
				pc.producer();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}}
		});
		Thread t2 = new Thread(new Runnable() {
			public void run() { try {
				pc.consumer();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}}
		});
		
		t1.start();
		t2.start();
		
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
