package poli.sem7.pw.zad5;



public class Main {

	public static void main(String[] args) {
		Resources.add("testowy", "init");
		
		Thread t1 = new Thread(new Reader(25));
		Thread t2 = new Thread(new Reader(30));
		Thread t3 = new Thread(new Writer("first", 10));
		Thread t4 = new Thread(new Reader(20));
		Thread t5 = new Thread(new Reader(10));
		Thread t6 = new Thread(new Writer("second", 40));
		Thread t7 = new Thread(new Writer("third", 40));
		Thread t8 = new Thread(new Reader(10));
		try {
			Thread.sleep(500);
			t3.start();
			Thread.sleep(100);
			t1.start();			
			t2.start();
			t4.start();			
			t5.start();
			t6.start();	
			Thread.sleep(600);
			t7.start();
			t8.start();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	
	}

}
