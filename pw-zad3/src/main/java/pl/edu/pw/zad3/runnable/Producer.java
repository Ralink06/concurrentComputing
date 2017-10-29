package pl.edu.pw.zad3.runnable;

import pl.edu.pw.zad3.FirstBuffer;
import pl.edu.pw.zad3.Portion;

import java.util.Random;

public class Producer implements Runnable {

    private FirstBuffer buffer;
    private int sleepTime;

    public Producer(final FirstBuffer buffer, final int sleepTime) {
        this.buffer = buffer;
        this.sleepTime = sleepTime;
    }

    @Override
    public void run() {
        Random rand = new Random(System.nanoTime());
        try {
            int i = 0;
            while (true) {
                Portion p = new Portion("porcja  " + i);
                System.out.println("adding: " + p);
                buffer.put(p);
                System.out.println(p + " was added");
                Thread.sleep(100 + rand.nextInt(sleepTime));
                i++;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}