package designPatterns.ChainOfResponsibility;

public class User {
	public static void main(String[] args) {
		Handler handler = new ConcreteHandlerA();
		handler.setSuccessor(new ConcreteHandlerB());
		handler.handleRequest(new Request(1));
	}
}
