package pl.edu.pw.zad3.listener;

import java.util.List;

public interface BufferListener<T> {

    void onBufferChange(List<T> elements);
}
