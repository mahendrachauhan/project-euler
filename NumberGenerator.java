package caveofprogramming.threadexample;

public class NumberGenerator {

	
}

class OddGenerator implements Runnable {
	
	private Integer number;
	
	public OddGenerator(Integer number) {
		this.number = number;
	}
	
	public void run() {
		synchronized (number) {
			
			while(number % 2 != 0) {
				try {
					number.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				System.out.println("Number : "+number);
				number++;
				number.notify();
				if(number >=10) {
					break;
				}
			}
		}
	}
}

class EvenGenerator implements Runnable {
	private Integer number;
	
	public EvenGenerator(Integer number) {
		this.number = number;
		
	}
	
	public void run() {
		synchronized (number) {

			while(number % 2 == 0) {
				try {
					number.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				System.out.println("Number : "+number);
				number++;
				number.notify();
				if(number >=10) {
					break;
				}
			}
		
		}
	}
}