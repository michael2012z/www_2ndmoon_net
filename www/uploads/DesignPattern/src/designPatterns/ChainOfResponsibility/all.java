// Source code from file:  ConcreteHandlerA.java 

package designPatterns.ChainOfResponsibility;

public class ConcreteHandlerA extends Handler {
	public void handleRequest(Request request) {
		if (request.data < 0)
			System.out.println("ConcreteHandlerA.handleRequest() as data < 0");
		else {
			Handler successor = getSuccessor();
			if (null != successor)
				successor.handleRequest(request);
		}
	}
}


// Source code from file:  ConcreteHandlerB.java 

package designPatterns.ChainOfResponsibility;

public class ConcreteHandlerB extends Handler {
	public void handleRequest(Request request) {
		if (request.data >= 0)
			System.out.println("ConcreteHandlerB.handleRequest() as data >= 0");
		else {
			Handler successor = getSuccessor();
			if (null != successor)
				successor.handleRequest(request);
		}
	}

}


// Source code from file:  Handler.java 

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


// Source code from file:  Request.java 

package designPatterns.ChainOfResponsibility;

public class Request {
	int data;
	public Request(int data) {
		this.data = data;
	}
}


// Source code from file:  User.java 

package designPatterns.ChainOfResponsibility;

public class User {
	public static void main(String[] args) {
		Handler handler = new ConcreteHandlerA();
		handler.setSuccessor(new ConcreteHandlerB());
		handler.handleRequest(new Request(1));
	}
}


