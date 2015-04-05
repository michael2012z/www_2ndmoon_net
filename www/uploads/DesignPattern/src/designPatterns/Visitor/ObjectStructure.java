package designPatterns.Visitor;

import java.util.Enumeration;
import java.util.Vector;

public class ObjectStructure {
	private Vector elements = new Vector();
	
	public void addElement(Element e) {
		elements.addElement(e);
	}
	
	public void action(Visitor visitor) {
		for (Enumeration enu = elements.elements(); enu.hasMoreElements(); ) {
			Element e = (Element)enu.nextElement();
			e.accept(visitor);
		}
	}
}
