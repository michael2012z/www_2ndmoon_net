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
