// Source code from file:  Facade.java 

package designPatterns.Facade;

public class Facade {
	private ServerA serverA = new ServerA();
	private ServerB serverB = new ServerB();
	private ServerC serverC = new ServerC();
	private ServerD serverD = new ServerD();
	
	public void service() {
		serverA.service();
		serverB.service();
		serverC.service();
		serverD.service();
	}
}


// Source code from file:  Server.java 

package designPatterns.Facade;

public interface Server {
	public void service();
}


// Source code from file:  ServerA.java 

package designPatterns.Facade;

public class ServerA implements Server {

	public void service() {
		System.out.println("Service of ServerA");
	}
}


// Source code from file:  ServerB.java 

package designPatterns.Facade;

public class ServerB implements Server {

	public void service() {
		System.out.println("Service of ServerB");
	}

}


// Source code from file:  ServerC.java 

package designPatterns.Facade;

public class ServerC implements Server {

	public void service() {
		System.out.println("Service of ServerC");
	}

}


// Source code from file:  ServerD.java 

package designPatterns.Facade;

public class ServerD implements Server {

	public void service() {
		System.out.println("Service of ServerD");
	}

}


// Source code from file:  User.java 

package designPatterns.Facade;

public class User {
	public static void main(String[] args) {
		Facade facade = new Facade();
		facade.service();
	}
}


