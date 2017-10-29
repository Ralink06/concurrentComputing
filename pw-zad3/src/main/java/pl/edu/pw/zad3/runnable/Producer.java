package pl.edu.pw.zad3.runnable;

import lombok.extern.slf4j.Slf4j;
import pl.edu.pw.zad3.FirstBuffer;
import pl.edu.pw.zad3.Portion;

import java.util.Random;

@Slf4j
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
            int i = 1;
            while (true) {
                Portion p = new Portion(i);
                log.info("Adding portion with number <{}>", p);
                buffer.put(p);
                log.info("Portion with number <{}> was added.", p);
                Thread.sleep(100 + rand.nextInt(sleepTime));
                i++;
            }
        } catch (InterruptedException e) {
            log.error("", e);
        }
    }

}