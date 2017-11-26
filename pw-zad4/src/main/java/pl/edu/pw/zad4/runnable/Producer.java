package pl.edu.pw.zad4.runnable;

import lombok.extern.slf4j.Slf4j;
import pl.edu.pw.zad4.buffer.FirstBuffer;

import java.util.Random;

@Slf4j
public class Producer implements Runnable {

    private final FirstBuffer firstBuffer;
    private final int sleepTime;
    private final int producerNumber;

    public Producer(final FirstBuffer firstBuffer,
                    final int sleepTime,
                    final int producerNumber) {
        this.firstBuffer = firstBuffer;
        this.sleepTime = sleepTime;
        this.producerNumber = producerNumber;
    }

    @Override
    public void run() {
        Random rand = new Random(System.nanoTime());
        try {
            int i = 1;
            while (true) {
                log.info("Adding portion <{}> by producer <{}>", i, producerNumber);
                firstBuffer.getQueue().put(i);
                log.info("Portion <{}> added by producer <{}>.", i, producerNumber);
                Thread.sleep(100 + rand.nextInt(sleepTime));
                i++;
            }
        } catch (InterruptedException e) {
            log.error("{}", e);
        }
    }

}