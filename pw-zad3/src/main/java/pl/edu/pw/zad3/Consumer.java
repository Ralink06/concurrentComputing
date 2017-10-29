package pl.edu.pw.zad3;

import pl.edu.pw.zad3.listener.BufferListener;

import java.util.LinkedList;
import java.util.List;

public class Consumer implements Runnable {

    private SecondBuffer buffer2;
    private List<BufferListener<Package>> listeners;
    private List<Package> packs;

    Consumer(SecondBuffer buffer2) {
        this.buffer2 = buffer2;
        packs = new LinkedList<>();
        listeners = new LinkedList<>();
    }

    @Override
    public void run() {
        try {
            while (true) {
                Package[] elements = buffer2.getAllElements();
                System.out.println("Consumer:");
                packs = new LinkedList<>();

                for (Package element : elements) {
                    System.out.println("\t" + element);
                    packs.add(element);
                }
                notifyListeners();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    void addListener(BufferListener<Package> listener) {
        listeners.add(listener);
    }

    private void notifyListeners() {
        for (BufferListener<Package> listener : listeners) {
            listener.onBufferChange(packs);
        }
    }
}