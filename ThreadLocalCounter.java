package caveofprogramming.threadexample;

public class ThreadLocalCounter {
	
	public class Counter {
		private int counter;
		public Counter(int counter) {
			this.counter=counter;
		}
		public int getCount() {
			return counter;
		}
		
		public void increment() {
			counter = counter + 1;
		}
	}
	
	public ThreadLocal<Counter> localCounter = new ThreadLocal<Counter>() {
		protected Counter initialValue() {
			return new Counter(100);
		}
	};
	
	public void increment() {
		localCounter.get().increment();
	}
}

