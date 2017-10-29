package pl.edu.pw.zad3;

import pl.edu.pw.zad3.listener.BufferListener;

import java.util.LinkedList;
import java.util.List;

public class SecondBuffer {

    private Queue<Package> queue;
    private List<BufferListener<Package>> listeners;

    SecondBuffer(final int size) {
        queue = new Queue<>(size);
        listeners = new LinkedList<>();
    }

    public synchronized void put(final Package pack) throws InterruptedException {
        while (queue.isFull()) {
            wait();
        }

        queue.put(pack);
        notifyListeners();
        notifyAll();
    }

    public synchronized Package[] getAllElements() throws InterruptedException {
        while (!queue.isFull()) {
            wait();
        }

        int size = queue.getCurrentSize();
        Package[] elements = new Package[size];
        for (int i = 0; i < size; i++) {
            elements[i] = queue.get();
        }
        notifyListeners();
        notifyAll();

        return elements;
    }

    void addListener(final BufferListener<Package> listener) {
        listeners.add(listener);
    }

    private void notifyListeners() {
        for (BufferListener<Package> listener : listeners) {
            List<Package> portions = queue.toList();
            listener.onBufferChange(portions);
        }
    }
}