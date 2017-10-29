package pl.edu.pw.zad3.listener;

import pl.edu.pw.zad3.Main;
import pl.edu.pw.zad3.Portion;

import java.util.List;

public class FirstBufferListener implements BufferListener<Portion> {

    @Override
    public void onBufferChange(final List<Portion> elements) {
        Main.frame.updateBuffer1(elements);
    }
}
