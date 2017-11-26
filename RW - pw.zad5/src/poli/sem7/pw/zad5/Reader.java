package poli.sem7.pw.zad5;

public class Reader implements Runnable {

	int timeout;
	
	public Reader(int timeout){
		this.timeout = timeout;
	}
	
	@Override
	public void run() {
		
		ResourceReader reader = Resources.openForRead("testowy");

		int c;
		System.out.println(Thread.currentThread().getName() + " Reader start reading");
		while((c = reader.read())>=0){
			try {
				Thread.sleep(timeout);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println(Thread.currentThread().getName() + " Reader end reading");
		reader.close();
	}

}
