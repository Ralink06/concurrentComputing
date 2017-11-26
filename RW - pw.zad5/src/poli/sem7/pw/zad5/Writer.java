package poli.sem7.pw.zad5;

public class Writer implements Runnable {

	int timeout;
	String text;
	
	public Writer(String text, int timeout){
		this.timeout = timeout;
		this.text = text;
	}
	
	@Override
	public void run() {

		ResourceWriter writer = Resources.openForWrite("testowy");
		System.out.println(Thread.currentThread().getName() + " Writer start writing");
		for (int i = 0; i < text.length(); i++) {
			writer.write(text.charAt(i));
			try {
				Thread.sleep(timeout);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println(Thread.currentThread().getName() + " Writing finished writing!");
		writer.close();
	}

}
