package pl.edu.pw.zad4.buffer;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

@Slf4j
@Getter
public class FirstBuffer implements Runnable {

    private final BlockingQueue<Integer> queue;
    private final List<Integer> buffer;

    public FirstBuffer() {
        this.queue = new SynchronousQueue<>();
        this.buffer = new ArrayList<>();
    }

    @Override
    public void run() {
        while (true) {
            try {
                Integer portion = queue.take();
                buffer.add(portion);
                log.info("buffer contain {}", buffer);
            } catch (InterruptedException e) {
                log.info("try to take element");
                e.printStackTrace();
            }
        }
    }
}
