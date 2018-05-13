package caveofprogramming.threadexample;

import java.util.HashMap;
import java.util.Map;

public class RaceCounitionCounter {

	public static void main(String args[]) {
		//Counter counter = new Counter();
		Counter counter = new CounterPerThread();
		CounterThread ct = new CounterThread(counter);
		
		Thread t1 = new Thread(ct,"Thread-1");
		Thread t2 = new Thread(ct,"Thread-2");
		Thread t3 = new Thread(ct,"Thread-3");
		
		t1.start();
		t2.start();
		t3.start();
	}
}


class Counter {
	private Integer counter = Integer.valueOf(0);
	protected Map<String,Integer> counterMap = new HashMap<String,Integer>();
	public Integer getCount(String name) {
		return counterMap.get(name);
	}
	
	public  void increment() {
		
		counter = counter + 1;
		try {
			Thread.sleep(400);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		String tname = Thread.currentThread().getName();
		counterMap.put(tname, counter);
		
	}
}

class CounterPerThread extends Counter {
	
	@Override
	public void increment() {
		String tname = Thread.currentThread().getName();
		if(counterMap.containsKey(tname)) {
			Integer count = counterMap.get(tname);
			count = count + 1;
			counterMap.put(tname, count);
		}else {
			counterMap.put(tname, Integer.valueOf(1));
		}
		
	}
}

class CounterThread implements Runnable {
	
	private Counter counter ;
	public CounterThread(Counter counter) {
		this.counter = counter;
	}
	
	public void run() {
		
		counter.increment();
		String tname = Thread.currentThread().getName();
		Integer count = counter.getCount(tname);
		System.out.println(tname+" = "+count);
		
	}
}




