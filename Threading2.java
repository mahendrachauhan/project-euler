package caveofprogramming;

import java.util.Scanner;

/**
 * @author mahendra
 * 
 * Each thread has its local cache, using volatile variable ensures that its
 * not cached.
 * volatile does not ensures thread safety, dirty read or atomicity  
 * 
 *
 */

class Processor implements Runnable {

	private volatile boolean  running = true;
	@Override
	public void run() {
		
		int i = 1;
		while(running) {
			System.out.println("Running "+i++);
			
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}//end true
		
	}
	
	public void stopRunning() {
		running = false;
	}
}
public class Threading2 {

	public static void main(String args[]) {

		Processor p = new Processor();
		Thread t1 = new Thread(p);
		t1.start();
		
		new Scanner(System.in).nextLine();
		p.stopRunning();
		
		
	}
	
	
}
