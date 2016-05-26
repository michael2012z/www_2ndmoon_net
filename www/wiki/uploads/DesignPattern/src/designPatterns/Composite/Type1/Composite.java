package designPatterns.Composite.Type1;

import java.util.*;;

public class Composite implements Component {

	@SuppressWarnings("unchecked")
	private Vector componentVector = new Vector();
	
	@SuppressWarnings("unchecked")
	public void sampleOperation() {
		System.out.println("Composite.sampleOperation()");
		Enumeration enu = getChilds();
		
		while(enu.hasMoreElements()) {
			((Component)enu.nextElement()).sampleOperation();
		}
	}

	public Composite getComposite() {
		return this;
	}
	
	@SuppressWarnings("unchecked")
	public void addChild(Component component) {
		componentVector.addElement(component);
	}
	
	public void removeChild(Component component) {
		componentVector.removeElement(component);
	}
	
	@SuppressWarnings("unchecked")
	public Enumeration getChilds() {
		return componentVector.elements();
	}
}
