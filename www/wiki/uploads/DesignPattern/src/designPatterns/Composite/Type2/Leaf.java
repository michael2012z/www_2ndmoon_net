package designPatterns.Composite.Type2;

import java.util.Enumeration;

public class Leaf implements Component {
	
	@Override
	public void sampleOperation() {
		System.out.println("Leaf.sampleOperation()");
	}
	
	@Override
	public Composite getComposite() {
		return null;
	}
	
	@Override
	public void addChild(Component component) {
		// Do nothing
	}
	
	@Override
	public void removeChild(Component component) {
		// Do nothing
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Enumeration getChilds() {
		return null;
	}

}
