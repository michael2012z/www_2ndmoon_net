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
