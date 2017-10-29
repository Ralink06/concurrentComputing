package pl.edu.pw.zad3.runnable;

import pl.edu.pw.zad3.Package;
import pl.edu.pw.zad3.SecondBuffer;
import pl.edu.pw.zad3.listener.BufferListener;

import java.util.LinkedList;
import java.util.List;

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
                Package[] elements = buffer2.getAllElements();
                System.out.println("Consumer:");
                packages = new LinkedList<>();

                for (Package element : elements) {
                    System.out.println("\t" + element);
                    packages.add(element);
                }
                notifyListeners();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
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