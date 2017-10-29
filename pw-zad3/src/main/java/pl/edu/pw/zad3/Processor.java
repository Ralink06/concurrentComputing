package pl.edu.pw.zad3;

import java.util.Random;

public class Processor implements Runnable {

    private FirstBuffer buffer1;
    private SecondBuffer buffer2;

    Processor(FirstBuffer buffer1, SecondBuffer buffer2) {
        this.buffer1 = buffer1;
        this.buffer2 = buffer2;
    }

    @Override
    public void run() {
        try {
            Random rand = new Random(System.nanoTime());
            while (true) {
                Portion[] elements = buffer1.getAllAvailableElements();
//				for (int i = 0; i < elements.length; i++) {
//					System.out.println("> "+elements[i]);
//				}
                Package pack = new Package(elements);
                buffer2.put(pack);
                Thread.sleep(500 + rand.nextInt(1500));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
