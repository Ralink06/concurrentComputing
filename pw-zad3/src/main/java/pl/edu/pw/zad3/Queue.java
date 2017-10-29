package pl.edu.pw.zad3;

import java.util.LinkedList;
import java.util.List;

class Queue<T> {

    private T[] buffer;
    private int startPtr, endPtr, cnt;

    Queue(int size) {
        buffer = (T[]) new Object[size];
        startPtr = 0;
        endPtr = 0;
        cnt = 0;
    }

    void put(T element) {
        if (cnt == buffer.length) throw new RuntimeException("Queue overflow");
        buffer[endPtr] = element;
        endPtr++;
        endPtr %= buffer.length;
        cnt++;
    }

    T get() {
        if (cnt == 0) throw new RuntimeException("Queue is empty");
        T element = buffer[startPtr];
        startPtr++;
        startPtr %= buffer.length;
        cnt--;
        return element;
    }

    boolean isEmpty() {
        return cnt == 0;
    }

    boolean isFull() {
        return cnt == buffer.length;
    }

    int getCurrentSize() {
        return cnt;
    }

    int getSize() {
        return buffer.length;
    }

    List<T> toList() {
        List<T> list = new LinkedList<T>();
        for (int i = 0; i < cnt; i++) {
            list.add(buffer[(startPtr + i) % buffer.length]);
        }
        return list;
    }
}