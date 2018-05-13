package caveofprogramming.threadexample;

public class TrickyThread extends Base implements Runnable {

	
	public static void main(String args[]) {
		Thread t1 = new Thread(new TrickyThread());
		Thread t2 = new Thread(new TrickyThread());
		
		
		t1.start();
		t2.start();
		
		try {
			t1.join();
			t2.join();
		}catch(InterruptedException e) {
			
		}
		
		System.out.println("Done");
	}
	
}


class Base {
	
	public void run() {
		System.out.println("Entering run");
		System.out.println("Leaving run");
		
	}
}