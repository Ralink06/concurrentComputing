package poli.sem7.pw.zad2.presentation;

import javax.swing.*;

import poli.sem7.pw.zad2.PascalTriangle;

public class MainFrame extends JFrame {

    private TrianglePanel tpanel;

    public MainFrame(int numRows, PascalTriangle pt) {
        tpanel = new TrianglePanel(numRows, pt);
        //tpanel.setPreferredSize(new Dimension(1000,1000));
        JScrollPane sp = new JScrollPane(tpanel);
        this.add(sp);

        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setSize(800, 600);
        this.setVisible(true);

        JScrollBar sb = sp.getHorizontalScrollBar();
        System.out.println(sb.getMaximum() + ", " + sb.getMinimum());
        sb.setValue((sb.getMaximum() - sb.getMinimum() - sb.getVisibleAmount()) / 2);
        System.out.println(sb.getVisibleAmount());
        System.out.println(tpanel.getPreferredSize());
    }

}
