package caveofprogramming.threadexample;

public final class SingletonThreadsafe {

	//static instance
	private static SingletonThreadsafe instance = null;
	//private constructor
	private SingletonThreadsafe() { 
	}
	
	//expose public static method 
	public static SingletonThreadsafe getInstance() {
		
		if(instance == null) {
			synchronized (instance) {
				if(instance == null)
					instance = new SingletonThreadsafe();
			}
		}
		return instance;
	}
	

}
