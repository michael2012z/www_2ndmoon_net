package designPatterns.Visitor;

public class ElementB implements Element {
	public void accept(Visitor visitor) {
		visitor.visitB(this);
	}
	
	public void operationB1() {}
	public void operationB2() {}
}
