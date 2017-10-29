package pl.edu.pw.zad3.presentation;

import pl.edu.pw.zad3.Package;
import pl.edu.pw.zad3.Portion;

import javax.swing.*;
import java.util.List;

public class MainFrame extends JFrame {

    private final StatusPanel panel;

    public MainFrame() {
        panel = new StatusPanel();
        add(panel);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(800, 600);
        setVisible(true);
        setTitle("Zad 3 - Monitory");
    }

    public void updateBuffer1(List<Portion> elements) {
        panel.setBuffer1(elements);
        panel.repaint();
    }

    public void updateBuffer2(final List<Package> elements) {
        panel.setBuffer2(elements);
        panel.repaint();
    }

    public void updateConsumer(List<Package> elements) {
        panel.setConsumer(elements);
        panel.repaint();
    }
}