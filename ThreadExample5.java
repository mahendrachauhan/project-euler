package caveofprogramming.threadexample;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


class Processor_5 implements Runnable {

	private int id;
	public Processor_5(int id) {
		this.id = id;
	}
	@Override
	public void run() {
		System.out.println("Woring on "+id);
		sleep(5000);	
		System.out.println("Done with "+id);
	}
	
	public void sleep(long time) {
		try {
			Thread.sleep(time);
		}catch(InterruptedException e) { }
	}
}



public class ThreadExample5 {

public static void main(String[] args) {
	
	ExecutorService executorService = Executors.newFixedThreadPool(3);
	for(int i=0; i<10; i++) {
		executorService.submit(new Processor_5(i));
	}
	executorService.shutdown();// after this no new tasks are accepted., required for orderly shutdown
	
	try {
		executorService.awaitTermination(3,TimeUnit.SECONDS);
	} catch (InterruptedException e) {
		e.printStackTrace();
	}
	
	System.out.println("All TASKS DONE ..... ");
}

}






