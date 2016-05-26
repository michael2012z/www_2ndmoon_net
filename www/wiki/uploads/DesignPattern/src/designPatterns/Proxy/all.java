// Source code from file:  ProxySubject.java 

package designPatterns.Proxy;

public class ProxySubject implements Subject {
	private RealSubject realSubject = null;
	
	public ProxySubject() {
		realSubject = new RealSubject();
	}
	
	public void request() {
		System.out.println("ProxySubject.request()");
		preRequest();
		realSubject.request();
		postRequest();
	}
	
	public void preRequest() {
		System.out.println("ProxySubject.preRequest()");
	}
	
	public void postRequest() {
		System.out.println("ProxySubject.postRequest()");
	}
}


// Source code from file:  RealSubject.java 

package designPatterns.Proxy;

public class RealSubject implements Subject {
	public void request() {
		System.out.println("RealSubject.request()");
	}
}


// Source code from file:  Subject.java 

package designPatterns.Proxy;

public interface Subject {
	public void request();
}


