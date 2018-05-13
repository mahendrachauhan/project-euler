package caveofprogramming.threadexample;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ParallelArraySum {

	static class ParrallelSum implements Callable<Integer> {
		
		private final int[] array;
		private final int startIndex;
		private final int endIndex;
		
		public ParrallelSum(int[] array, int startIndex, int endIndex) {
			this.array = array;
			this.startIndex = startIndex;
			this.endIndex = endIndex;
		}
		
		@Override
		public Integer call() throws Exception {
			int sum = 0;
			for(int i=startIndex; i <= endIndex; i++) {
				sleep(1);
				sum += array[i];
			}
			return sum;
		}
	}
	
	public static int getSum(int[] array) {
		int sum = 0;
		for(int data : array) {
			sleep(1);
			sum += data;
		}
		return sum;
	}
	
	public static int[] initialize(int arraySize) {
		int[] array = new int[arraySize];
		Random random = new Random();
		for(int i=0; i<arraySize; i++) {
			array[i] = random.nextInt(arraySize);
		}
		return array;
	}
	public static void sleep(long time) {
		try {
			Thread.sleep(time);
		}catch(InterruptedException e) { }
	}
	public static void main(String[] args) {
		
		
		int maxThread = 10;
		ExecutorService executor = Executors.newFixedThreadPool(maxThread);
		int[] array = initialize(9999);
		
		List<Future<Integer>> futureList = new ArrayList<>();
		int perThread = array.length / maxThread;
		int start = 0, end = 0;
		
		long s1 = System.currentTimeMillis();
		int sum = getSum(array);
		long s2 = System.currentTimeMillis();
		System.out.println(" Sequential SUM = "+sum + ", Time taken = "+(s2-s1));
		
		s1 = System.currentTimeMillis();
		while(start < array.length ) {
			end = Math.min(end+perThread, array.length-1);
			System.out.println("	array=" + array.length + ", start="+start+ ", end="+end);
			futureList.add(executor.submit(new ParrallelSum(array, start, end)));
			start = end+1;
			
		}
		executor.shutdown();
		
		sum = 0;
		for(Future<Integer> future : futureList) {
			try {
				sum += future.get();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
		}
		s2 = System.currentTimeMillis();
		System.out.println(" Parallel SUM = "+sum + ", Time taken = "+(s2-s1));
	}

}
