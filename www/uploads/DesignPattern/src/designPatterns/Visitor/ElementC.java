package designPatterns.Visitor;

public class ElementC implements Element {
	public void accept(Visitor visitor) {
		visitor.visitC(this);
	}
	
	public void operationC1() {}
	public void operationC3() {}
}
