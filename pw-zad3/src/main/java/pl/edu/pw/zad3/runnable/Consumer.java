package pl.edu.pw.zad3.runnable;

import lombok.extern.slf4j.Slf4j;
import pl.edu.pw.zad3.Package;
import pl.edu.pw.zad3.SecondBuffer;
import pl.edu.pw.zad3.listener.BufferListener;

import java.util.LinkedList;
import java.util.List;

@Slf4j
public class Consumer implements Runnable {

    private final SecondBuffer buffer2;
    private final List<BufferListener<Package>> listeners;
    private List<Package> packages;

    public Consumer(final SecondBuffer buffer2) {
        this.buffer2 = buffer2;
        this.packages = new LinkedList<>();
        this.listeners = new LinkedList<>();
    }

    @Override
    public void run() {
        try {
            while (true) {
                Package[] packages = buffer2.getAllElements();
                this.packages = new LinkedList<>();

                log.info("Consumer consumes <{}> packages", packages.length);

                for (Package pack : packages) {
                    log.info("Package size <{}>, with elements <{}>", pack.getSize(), pack);
                    this.packages.add(pack);
                }
                notifyListeners();
            }
        } catch (InterruptedException e) {
            log.error("{}", e);
        }
    }

    public void addListener(final BufferListener<Package> listener) {
        listeners.add(listener);
    }

    private void notifyListeners() {
        for (BufferListener<Package> listener : listeners) {
            listener.onBufferChange(packages);
        }
    }
}