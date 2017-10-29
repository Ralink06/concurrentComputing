package pl.edu.pw.zad3.listener;

import pl.edu.pw.zad3.Main;
import pl.edu.pw.zad3.Package;

import java.util.List;

public class ConsumerListener implements BufferListener<Package> {

    @Override
    public void onBufferChange(final List<Package> elements) {
        Main.frame.updateConsumer(elements);
    }
}


