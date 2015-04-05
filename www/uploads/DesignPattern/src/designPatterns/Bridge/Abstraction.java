package designPatterns.Bridge;

public class Abstraction {
	protected Implementor imp;
	
	public void operation() {
		imp.operationImp();
	}
}
