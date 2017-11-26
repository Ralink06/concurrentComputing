package pl.edu.pw.zad4.runnable;

import lombok.extern.slf4j.Slf4j;
import pl.edu.pw.zad4.buffer.SecondBuffer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Slf4j
public class Consumer implements Runnable {

    private final SecondBuffer secondBuffer;
    private final int sleepTime;

    public Consumer(final SecondBuffer secondBuffer,
                    final int sleepTime) {
        this.secondBuffer = secondBuffer;
        this.sleepTime = sleepTime;
    }

    @Override
    public void run() {
        Random rand = new Random(System.nanoTime());
        while (true) {
            try {
                if (secondBuffer.getBuffer().size() >= secondBuffer.getMaxSize()) {
                    List<Integer> buffer = new ArrayList<>(secondBuffer.getBuffer());

                    log.info("Consumer consume <{}> from second buffer.", buffer);
                    secondBuffer.getBuffer().removeAll(buffer);
                    Thread.sleep(500 + rand.nextInt(sleepTime));
                } else {
                    Thread.sleep(500 + rand.nextInt(sleepTime));
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
