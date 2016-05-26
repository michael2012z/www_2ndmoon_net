package designPatterns.State;

public class User {
	public static void main(String[] args) {
		Context c = new Context();
		c.operation();
		c.switchState();
		c.operation();
		c.switchState();
		c.operation();
	}
}
