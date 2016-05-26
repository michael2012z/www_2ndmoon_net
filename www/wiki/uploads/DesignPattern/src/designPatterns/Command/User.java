package designPatterns.Command;

public class User {
	public static void main(String[] args) {
		Receiver receiver = new ConcreteReceiver();
		Deliverer deliverer = new Deliverer(receiver);
		deliverer.start();
		Invoker invoker = new Invoker(deliverer);
		invoker.act();
	}
}
