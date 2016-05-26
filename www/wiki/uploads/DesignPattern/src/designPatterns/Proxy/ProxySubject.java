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
