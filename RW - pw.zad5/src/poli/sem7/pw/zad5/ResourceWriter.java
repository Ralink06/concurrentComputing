package poli.sem7.pw.zad5;

public class ResourceWriter {
	
	Resource resource;
	boolean opened;
	
	public ResourceWriter(Resource resource){
		this.resource = resource;
		this.resource.openForWrite();
		opened = true;
	}
	
	void write(char c){
		if(!opened)throw new IllegalStateException("resource is closed");
		resource.write(c);
	}
	
	void clear(){
		if(!opened)throw new IllegalStateException("resource is closed");
		resource.clear();
	}

	void close(){
		resource.closeForWrite();
		opened = false;
	}
}
