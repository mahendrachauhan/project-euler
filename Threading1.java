package caveofprogramming;

public class Threading1 {

	public static void main(String args[]) {
		
		Runnable r = new Runnable() {
			
			@Override		
			public void run() {
				
				
				try {
					Thread.sleep(300);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				System.out.println("Running ...");
			}
		};
		
		Thread t1 = new Thread(r);
		Thread t2 = new Thread(r);
		
		t1.start();
		t2.start();
	}
}
