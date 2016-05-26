package designPatterns.Composite.Type2;

import java.util.Enumeration;

public interface Component {

	public void sampleOperation();
	public Composite getComposite();
	public void addChild(Component component);
	public void removeChild(Component component);
	@SuppressWarnings("unchecked")
	public Enumeration getChilds();
}
