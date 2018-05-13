package caveofprogramming.threadexample;

import java.util.Scanner;

class Processor implements Runnable {

private volatile boolean running = true; // its not cached

@Override
public void run() {
	int i = 1;
	while(running) {
		try {
			Thread.sleep(500);
		}catch(InterruptedException e) { }
		System.out.println("Running "+ i++);
	}
	
}

public void stopThread() {
	this.running = false;
}

}



public class ThreadExample2 {

public static void main(String[] args) {
	Processor p = new Processor();
	Thread t1 = new Thread(p);
	
	t1.start();
	
	new Scanner(System.in).nextLine();
	p.stopThread();
}

}