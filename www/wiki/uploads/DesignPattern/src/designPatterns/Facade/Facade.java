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
