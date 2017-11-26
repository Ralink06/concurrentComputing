package pl.edu.pw.zad4.buffer;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

@Slf4j
@Getter
public class SecondBuffer implements Runnable {

    private final BlockingQueue<Integer> queue;
    private final List<Integer> buffer;
    private final int maxSize;

    public SecondBuffer(final int maxSize) {
        this.queue = new SynchronousQueue<>();
        this.buffer = new ArrayList<>();
        this.maxSize = maxSize;
    }

    @Override
    public void run() {
        Random rand = new Random(System.nanoTime());
        while (true) {
            try {
                if (buffer.size() < maxSize) {
                    Integer packageValue = queue.take();
                    buffer.add(packageValue);
                    log.info("Add package <{}> to buffer <{}>", packageValue, buffer);
                    Thread.sleep(500 + rand.nextInt(200));
                } else {
                    Thread.sleep(500 + rand.nextInt(200));
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
