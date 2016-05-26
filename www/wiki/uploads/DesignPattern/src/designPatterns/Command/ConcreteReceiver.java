package designPatterns.Command;

public class ConcreteReceiver implements Receiver {
	public void handle(Event e) {
		System.out.println("ConcreteReceiver.handle()");
	}
}
