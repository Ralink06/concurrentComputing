package pl.edu.pw.zad4.runnable;

import lombok.extern.slf4j.Slf4j;
import pl.edu.pw.zad4.buffer.SecondBuffer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Slf4j
public class Consumer implements Runnable {

    private final SecondBuffer secondBuffer;

    public Consumer(final SecondBuffer secondBuffer) {
        this.secondBuffer = secondBuffer;
    }

    @Override
    public void run() {
        Random rand = new Random(System.nanoTime());
        while (true) {
            try {
                if (secondBuffer.getBuffer().size() == secondBuffer.getMaxSize()) {
                    List<Integer> buffer = new ArrayList<>(secondBuffer.getBuffer());

                    log.info("{}", buffer);
                    secondBuffer.getBuffer().removeAll(buffer);
                    Thread.sleep(1000);
                } else {
                    log.info("second buffor is not full");
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
