// Source code from file:  User.java 

package designPatterns.Iterator;

import java.util.*;

public class User {

	public Iterator getIteratorFromHashMap() {
		HashMap hashMap = new HashMap();
        hashMap.put("One", new Integer(1));
        hashMap.put("Two", new Integer(2));        
        hashMap.put("Three", new Integer(3));
        return hashMap.keySet().iterator();
	}
	
	public Iterator getIteratorFromList() {
		List list = new ArrayList();
		list.add("One");
		list.add("Two");
		list.add("Three");
		return list.iterator();
	}
	
	public static void main(String[] args) {
		User user = new User();
		Iterator iteratorFromHashMap = user.getIteratorFromHashMap();
		Iterator iteratorFromList = user.getIteratorFromList();
		while (iteratorFromHashMap.hasNext()){
			System.out.println((String)iteratorFromHashMap.next());
		}
		while (iteratorFromList.hasNext()){
			System.out.println((String)iteratorFromList.next());
		}
	}

}


