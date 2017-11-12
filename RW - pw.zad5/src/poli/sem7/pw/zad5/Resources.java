package poli.sem7.pw.zad5;

import java.util.HashMap;

public class Resources {
	
	static HashMap<String, Resource> resources = new HashMap<String, Resource>();
	
	static ResourceReader openForRead(String name){
		Resource r = resources.get(name);
		if(r == null)return null;
		return new ResourceReader(r);
	}
	
	static ResourceWriter openForWrite(String name){
		Resource r = resources.get(name);
		if(r == null)return null;
		return new ResourceWriter(r);		
	}

	static void add(String name, String content){
		resources.put(name, new Resource(content));
	}
}
