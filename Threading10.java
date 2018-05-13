package caveofprogramming;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Reentrant lock :
 * - Java provided a mechanism to acquire lock on a particular object.
 * - The beauty of reentrant lock is that each time we lock an object it keep track of the 
 * counter and need to unlock it the same number of time.
 * - each reentrantlock object returns condition object, this provides await() , signal() 
 * and signalall().
 * - we can all these methods only after acquiring the lock.
 * @author mahendra
 *
 */
public class Threading10 {
	
	public static void main(String args[]) {
	
		final ReentrantLockExample process = new ReentrantLockExample();
		
		long time = System.currentTimeMillis();
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					process.processOne();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					process.processTwo();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		
		t2.start();
		t1.start();
		
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
		}
		
		process.finished();
		
		System.out.println("Time : "+(System.currentTimeMillis() - time));
	}
}


class ReentrantLockExample {
	
	int count;
	Lock lock = new ReentrantLock();
	Condition cond = lock.newCondition();
	
	private void increment() {
		for(int i=0; i<1000000; i++) {
			count ++;
		}
	}
	
	public void processOne() throws InterruptedException {
		//Thread.sleep(2000);
		lock.lock();
		try {
			System.out.println("Waiting  on condition ....");
			
			cond.await();
			System.out.println("Woken up ...");
			
			increment();
		} finally {
			lock.unlock();
		}

	}
	
	public void processTwo() throws InterruptedException {
		Thread.sleep(2000);
		lock.lock();
		try {
			Thread.sleep(5000);
			System.out.println("Signaled process one ... ");
			increment();
			cond.signal();
		} finally {
			lock.unlock();
		}
	}
	
	public void finished() {
		System.out.println("Counter value : "+count);
	}
}

