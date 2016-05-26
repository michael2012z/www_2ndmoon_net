package designPatterns.Bridge;

public class User {
	public static void main(String[] args) {
		Abstraction absA = new RefinedAbstraction("SystemA");
		absA.operation();
		Abstraction absB = new RefinedAbstraction("SystemB");
		absB.operation();
	}
}
