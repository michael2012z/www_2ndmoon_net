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
