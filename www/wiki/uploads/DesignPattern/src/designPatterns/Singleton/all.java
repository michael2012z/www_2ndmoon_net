// Source code from file:  Singleton.java 

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


// Source code from file:  User.java 

package designPatterns.Singleton;

public class User {

	private void testSingleton() {
		Singleton s1 = Singleton.getInstance();
		Singleton s2 = Singleton.getInstance();
		System.out.println("s1 = " + s1);
		System.out.println("s2 = " + s2);
	}
	
	public static void main(String [] args) {
		User user = new User();
		user.testSingleton();
	}
}


