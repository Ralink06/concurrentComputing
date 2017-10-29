package pl.edu.pw.zad3;

import pl.edu.pw.zad3.listener.BufferListener;

import java.util.LinkedList;
import java.util.List;

class SecondBuffer {

    private Queue<Package> queue;
    private List<BufferListener<Package>> listeners;

    SecondBuffer(int size) {
        queue = new Queue<>(size);
        listeners = new LinkedList<>();
    }

    synchronized void put(Package pack) throws InterruptedException {
        while (queue.isFull()) this.wait();
        queue.put(pack);
        notifyListeners();
        this.notifyAll();
    }

    synchronized Package[] getAllElements() throws InterruptedException {
        while (!queue.isFull()) this.wait();

        int size = queue.getCurrentSize();
        Package[] elements = new Package[size];
        for (int i = 0; i < size; i++) {
            elements[i] = queue.get();
        }
        notifyListeners();
        this.notifyAll();
        return elements;
    }

    void addListener(BufferListener<Package> listener) {
        listeners.add(listener);
    }

    private void notifyListeners() {
        for (BufferListener<Package> listener : listeners) {
            List<Package> portions = queue.toList();
            listener.onBufferChange(portions);
        }
    }
}