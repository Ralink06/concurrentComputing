package poli.sem7.pw.zad2.presentation;

import javax.swing.*;

import poli.sem7.pw.zad2.PascalTriangle;

public class MainFrame extends JFrame {

    private TrianglePanel tpanel;

    public MainFrame(int numRows, PascalTriangle pt) {
        tpanel = new TrianglePanel(numRows, pt);
        JScrollPane sp = new JScrollPane(tpanel);
        this.add(sp);

        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setSize(800, 600);
        this.setVisible(true);

        JScrollBar sb = sp.getHorizontalScrollBar();
        sb.setValue((sb.getMaximum() - sb.getMinimum() - sb.getVisibleAmount()) / 2);
    }

}
