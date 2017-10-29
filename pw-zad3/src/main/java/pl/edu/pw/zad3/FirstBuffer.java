package pl.edu.pw.zad3;

import pl.edu.pw.zad3.listener.BufferListener;

import java.util.LinkedList;
import java.util.List;

class FirstBuffer {

    private Queue<Portion> queue;
    private List<BufferListener<Portion>> listeners;

    FirstBuffer(int size) {
        queue = new Queue<>(size);
        listeners = new LinkedList<>();
    }

    synchronized void put(Portion portion) throws InterruptedException {
        while (queue.isFull()) {
            wait();
        }

        queue.put(portion);
        notifyListeners();
        notifyAll();
    }

    synchronized Portion[] getAllAvailableElements() throws InterruptedException {
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

    void addListener(BufferListener<Portion> listener) {
        listeners.add(listener);
    }

    private void notifyListeners() {
        for (BufferListener<Portion> listener : listeners) {
            List<Portion> portions = queue.toList();
            listener.onBufferChange(portions);
        }
    }
}
