package designPatterns.Visitor;

public interface Visitor {
	public void visitA(ElementA element);
	public void visitB(ElementB element);
	public void visitC(ElementC element);
}
