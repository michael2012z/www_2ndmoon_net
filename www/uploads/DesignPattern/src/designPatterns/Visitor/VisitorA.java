package designPatterns.Visitor;

public class VisitorA implements Visitor {
	public void visitA(ElementA element) {
		element.operationA1();
	}
	
	public void visitB(ElementB element) {
		element.operationB1();
	}

	public void visitC(ElementC element) {
		element.operationC1();
	}

}
