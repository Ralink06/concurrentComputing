package pl.edu.pw.zad3.runnable;

import pl.edu.pw.zad3.FirstBuffer;
import pl.edu.pw.zad3.Package;
import pl.edu.pw.zad3.Portion;
import pl.edu.pw.zad3.SecondBuffer;

import java.util.Random;

public class Processor implements Runnable {

    private FirstBuffer buffer1;
    private SecondBuffer buffer2;

    public Processor(FirstBuffer buffer1, SecondBuffer buffer2) {
        this.buffer1 = buffer1;
        this.buffer2 = buffer2;
    }

    @Override
    public void run() {
        try {
            Random rand = new Random(System.nanoTime());
            while (true) {
                Portion[] elements = buffer1.getAllAvailableElements();
                Package pack = new Package(elements);
                buffer2.put(pack);
                Thread.sleep(500 + rand.nextInt(1500));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
