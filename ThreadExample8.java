package caveofprogramming.threadexample;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ThreadExample8 {

	public static void main(String[] args) {

		ExecutorService executor = Executors.newFixedThreadPool(1);
		Future<Integer> future = executor.submit(new Callable<Integer>() {
			@Override
			public Integer call() throws Exception {
				System.out.println("Entered call()");
				int value = new Random().nextInt(2000);
				System.out.println("sleeping for "+value);
				Thread.sleep(value);
				System.out.println("Returing the value ");
				return value;
			}
		});
		
		executor.shutdown();
		
		System.out.println("isDone() "+future.isDone());
		System.out.println("isCancelled() "+future.isCancelled());
		try {
			System.out.println("Returned value ==> "+future.get());
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		System.out.println("isDone() "+future.isDone());
		System.out.println("isCancelled() "+future.isCancelled());
	}

}
