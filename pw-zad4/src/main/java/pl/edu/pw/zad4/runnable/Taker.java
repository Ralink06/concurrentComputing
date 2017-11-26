package pl.edu.pw.zad4.runnable;

import lombok.extern.slf4j.Slf4j;
import pl.edu.pw.zad4.buffer.FirstBuffer;
import pl.edu.pw.zad4.buffer.SecondBuffer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Slf4j
public class Taker implements Runnable {

    private final FirstBuffer firstBuffer;
    private final SecondBuffer secondBuffer;
    private final int sleepTime;

    public Taker(final FirstBuffer firstBuffer,
                 final SecondBuffer secondBuffer,
                 final int sleepTime) {
        this.firstBuffer = firstBuffer;
        this.secondBuffer = secondBuffer;
        this.sleepTime = sleepTime;
    }

    @Override
    public void run() {
        Random rand = new Random(System.nanoTime());
        try {
            while (true) {
                log.info("Try to retrieve portion from first buffer");

                List<Integer> buffer = new ArrayList<>(firstBuffer.getBuffer());
                log.info("{}", buffer);

                int packageFromBuffer = ((int) buffer.stream().mapToLong(value -> value).sum());

                log.info("{}", packageFromBuffer);

                secondBuffer.getQueue().put(packageFromBuffer);
                firstBuffer.getBuffer().clear();
                Thread.sleep(500 + rand.nextInt(sleepTime));
            }
        } catch (InterruptedException e) {
            log.error("{}", e);
        }
    }
}
