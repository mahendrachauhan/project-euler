package caveofprogramming;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class Processor2 implements Runnable {
	private int id ;
	
	Processor2(int id) {
		this.id = id;
	}
	
	@Override
	public void run() {
		System.out.println("Started running "+ id);
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("Completed running "+id);
	}
}

public class Threading5 {

	public static void main(String args[]) {
		
		ExecutorService executor = Executors.newFixedThreadPool(3);
		
		for(int i=0 ; i<5 ; i++) {
			executor.submit(new Processor2(i));
		}
		
		executor.shutdown();
		
		try {
			executor.awaitTermination(1, TimeUnit.DAYS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("All Task completed ... ");
		
	}
}
