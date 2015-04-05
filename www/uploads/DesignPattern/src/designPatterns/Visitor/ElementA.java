package designPatterns.Visitor;

public class ElementA implements Element {
	public void accept(Visitor visitor) {
		visitor.visitA(this);
	}
	
	public void operationA1() {}
	
}
