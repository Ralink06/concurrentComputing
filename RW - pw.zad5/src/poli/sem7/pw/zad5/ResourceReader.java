package poli.sem7.pw.zad5;

public class ResourceReader {
	
	Resource resource;
	boolean opened;
	int ptr;
	
	public ResourceReader(Resource resource){
		this.resource = resource;
		this.ptr = 0;
		this.resource.openForRead();
		opened = true;
	}
	
	int read(){
		if(!opened)throw new IllegalStateException("resource is closed");
		return resource.read(ptr++);
	}

	void close(){
		resource.closeForRead();
		opened = false;
	}
}
