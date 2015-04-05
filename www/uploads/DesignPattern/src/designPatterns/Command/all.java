// Source code from file:  ConcreteReceiver.java 

package designPatterns.Command;

public class ConcreteReceiver implements Receiver {
	public void handle(Event e) {
		System.out.println("ConcreteReceiver.handle()");
	}
}


// Source code from file:  Deliverer.java 

package designPatterns.Command;

public class Deliverer extends Thread {
	
	// This simulate a event queue, the length of the queue is 1 here.
	private Event queue = null;	
	private Receiver receiver = null;
	
	public Deliverer(Receiver receiver) {
		this.receiver = receiver;
	}

	public void deliver(Event e) {
		queue = e;
	}
	
	public void distribute(Event e) {
		receiver.handle(e);
	}

	public void run() {
		int count = 3;
		while (count-- > 0) {
			try {
				sleep(1000);
				if (null != queue) {
					distribute(queue);
					queue = null;
				}
			} catch (Exception e) {}
		}
	}
}


// Source code from file:  Event.java 

package designPatterns.Command;

public class Event {
	int type;
	int parameterA;
	int parameterB;
}


// Source code from file:  Invoker.java 

package designPatterns.Command;

public class Invoker {
	
	Deliverer deliverer = null;
	
	public Invoker(Deliverer deliverer) {
		this.deliverer = deliverer;
	}
	
	public void act() {
		Event e = new Event();
		deliverer.deliver(e);
	}
}


// Source code from file:  Receiver.java 

package designPatterns.Command;

public interface Receiver {
	public void handle(Event e);
}


// Source code from file:  User.java 

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


