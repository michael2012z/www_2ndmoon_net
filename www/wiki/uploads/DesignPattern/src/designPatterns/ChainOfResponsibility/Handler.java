package designPatterns.ChainOfResponsibility;

public abstract class Handler {
	
	private Handler successor = null;
	
	public void setSuccessor(Handler successor) {
		this.successor = successor;
	}
	
	public Handler getSuccessor() {
		return successor;
	}
	
	public abstract void handleRequest(Request request);
	
}
