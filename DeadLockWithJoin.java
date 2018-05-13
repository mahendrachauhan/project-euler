package caveofprogramming.threadexample;

public class DeadLockWithJoin extends Thread {

	private DeadLockWithJoin other ;
	
	public DeadLockWithJoin(String name) {
		setName(name);
	}
	
	public void setOther(DeadLockWithJoin other) {
		this.other = other;
	}
	
	public void run() {
		System.out.println("Running thread : "+Thread.currentThread().getName());
		System.out.println("Waiting for : "+other.getName() +" to finish");
		try {
			other.join();	
		}catch(InterruptedException e) {
			
		}
		System.out.println(other.getName()+" finished proccessing");
		
	}
	
	
	
	public static void main(String args[])  {
		DeadLockWithJoin t1 = new DeadLockWithJoin("A");
		DeadLockWithJoin t2 = new DeadLockWithJoin("B");
		t1.setOther(t2);
		t2.setOther(t1);
		
		t1.start();
		t2.start();
		
		
	}
}
