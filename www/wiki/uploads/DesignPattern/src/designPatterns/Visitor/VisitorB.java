package designPatterns.Visitor;

public class VisitorB implements Visitor {
	public void visitA(ElementA element) {}
	
	public void visitB(ElementB element) {
		element.operationB2();
	}

	public void visitC(ElementC element) {
		element.operationC3();
	}
	
}
