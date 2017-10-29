package pl.edu.pw.zad3;

import java.util.Random;

public class Producer implements Runnable {

    private FirstBuffer buffer;
    private int sleepTime;

    Producer(FirstBuffer buffer, int sleepTime) {
        this.buffer = buffer;
        this.sleepTime = sleepTime;
    }

    @Override
    public void run() {
        Random rand = new Random(System.nanoTime());
        try {
            String name = Thread.currentThread().getName();
            int i = 0;
            while (true) {
                Portion p = new Portion(/*"watek" + num + "_porcja"*/"porcja  " + i);
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