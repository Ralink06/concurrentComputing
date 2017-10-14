package poli.sem7.pw.zad2.presentation;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Polygon;

import javax.swing.JPanel;

import poli.sem7.pw.zad2.PascalTriangle;

public class TrianglePanel extends JPanel {

    private Color[] colors = {Color.white, Color.red, Color.green, Color.blue, Color.orange, Color.MAGENTA};

    private int numRows;
    private int lenght;
    private int lenght2;
    private int width;
    private int height;
    private PascalTriangle triangle;

    TrianglePanel(int rows, PascalTriangle pt) {
        numRows = rows;
        lenght = 20;
        lenght2 = (int) (1.4 * lenght);
        width = 2 * lenght2;
        height = (int) (lenght + lenght / Math.sqrt(2));
        triangle = pt;
        this.setPreferredSize(new Dimension(width * numRows + 100, height * numRows + 100));
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Polygon p = new Polygon();
        p.addPoint(-lenght2, -lenght / 2);
        p.addPoint(0, -(int) (lenght / 2 + lenght / Math.sqrt(2)));
        p.addPoint(lenght2, -lenght / 2);
        p.addPoint(lenght2, lenght / 2);
        p.addPoint(0, (int) (lenght / 2 + lenght / Math.sqrt(2)));
        p.addPoint(-lenght2, lenght / 2);
        p.translate(lenght2 * numRows + 50, 50 + lenght);

        int px = lenght2 * numRows + 50;
        int py = 50 + lenght;


        for (int r = 0; r < numRows; r++) {
            for (int c = 0; c <= r; c++) {
                Color color = Color.black;
                int elemColor = triangle.getColor(r, c);
                if (elemColor < colors.length) color = colors[elemColor];
                g.setColor(color);
                g.fillPolygon(p);
                g.setColor(Color.black);
                g.drawPolygon(p);
                if (triangle.isCalculated(r, c)) {
                    String text = "" + triangle.getValue(r, c);
                    int tl = text.length();
                    if (tl <= 7) g.drawString(text, px - ((int) (text.length() / 2.0 * 7)), py + 5);
                    else {
                        String text1 = text.substring(0, text.length() / 2);
                        String text2 = text.substring(text.length() / 2);
                        g.drawString(text1, px - ((int) (text1.length() / 2.0 * 7)), py - 2);
                        g.drawString(text2, px - ((int) (text2.length() / 2.0 * 7)), py + 10);
                    }
                }
                p.translate(width, 0);
                px += width;
            }
            p.translate(-width * (r + 1) - lenght2, height);
            px += -width * (r + 1) - lenght2;
            py += height;
        }
    }
}
