package caveofprogramming.threadexample;

//final class no subclass
public final class Singleton {

	//static instance
	private static Singleton instance = null;
	//private constructor
	private Singleton() { 
	}
	
	//expose public static method 
	public static Singleton getInstance() {
		
		if(instance == null) {
			instance = new Singleton();
		}
			
		return instance;
	}
	
}
