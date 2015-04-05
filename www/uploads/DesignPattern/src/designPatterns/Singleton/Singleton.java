package designPatterns.Singleton;

public class Singleton {

	private static Singleton instance = null;
	
	private Singleton() {}
	
	synchronized public static Singleton getInstance() {
		if (null == instance)
			instance = new Singleton();
		return instance;
	}
}
