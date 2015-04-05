// Source code from file:  Element.java 

package designPatterns.Visitor;

public interface Element {
	public void accept(Visitor visitor);
}


// Source code from file:  ElementA.java 

package designPatterns.Visitor;

public class ElementA implements Element {
	public void accept(Visitor visitor) {
		visitor.visitA(this);
	}
	
	public void operationA1() {}
	
}


// Source code from file:  ElementB.java 

package designPatterns.Visitor;

public class ElementB implements Element {
	public void accept(Visitor visitor) {
		visitor.visitB(this);
	}
	
	public void operationB1() {}
	public void operationB2() {}
}


// Source code from file:  ElementC.java 

package designPatterns.Visitor;

public class ElementC implements Element {
	public void accept(Visitor visitor) {
		visitor.visitC(this);
	}
	
	public void operationC1() {}
	public void operationC3() {}
}


// Source code from file:  ObjectStructure.java 

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


// Source code from file:  User.java 

package designPatterns.Visitor;

public class User {
	public static void main(String[] args) {
		ObjectStructure obs = new ObjectStructure();
		obs.addElement(new ElementA());
		obs.addElement(new ElementB());
		obs.addElement(new ElementC());
		Visitor visitorA = new VisitorA();
		Visitor visitorB = new VisitorB();
		obs.action(visitorA);
		obs.action(visitorB);
	}
}


// Source code from file:  Visitor.java 

package designPatterns.Visitor;

public interface Visitor {
	public void visitA(ElementA element);
	public void visitB(ElementB element);
	public void visitC(ElementC element);
}


// Source code from file:  VisitorA.java 

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


// Source code from file:  VisitorB.java 

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


