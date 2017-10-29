package pl.edu.pw.zad3;

import pl.edu.pw.zad3.listener.BufferListener;

import java.util.LinkedList;
import java.util.List;

public class FirstBuffer {

    private Queue<Portion> queue;
    private List<BufferListener<Portion>> listeners;

    FirstBuffer(final int size) {
        queue = new Queue<>(size);
        listeners = new LinkedList<>();
    }

    public synchronized void put(final Portion portion) throws InterruptedException {
        while (queue.isFull()) {
            wait();
        }

        queue.put(portion);
        notifyListeners();
        notifyAll();
    }

    public synchronized Portion[] getAllAvailableElements() throws InterruptedException {
        while (queue.isEmpty()) {
            wait();
        }

        int size = queue.getCurrentSize();
        Portion[] elements = new Portion[size];
        for (int i = 0; i < size; i++) {
            elements[i] = queue.get();
        }
        notifyListeners();
        notifyAll();

        return elements;
    }

    void addListener(final BufferListener<Portion> listener) {
        listeners.add(listener);
    }

    private void notifyListeners() {
        for (BufferListener<Portion> listener : listeners) {
            List<Portion> portions = queue.toList();
            listener.onBufferChange(portions);
        }
    }
}
