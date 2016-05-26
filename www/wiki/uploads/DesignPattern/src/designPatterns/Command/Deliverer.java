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
