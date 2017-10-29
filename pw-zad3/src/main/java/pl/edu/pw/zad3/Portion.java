package pl.edu.pw.zad3;

import java.awt.*;

public class Portion {
    private String text;
    private int color;

    public Portion(final String text) {
        this.text = text;
        String name = Thread.currentThread().getName();
        color = Integer.parseInt(name.substring(name.indexOf("-") + 1));
    }

    public String toString() {
        return text;
    }

    public Color getColor() {
        switch (color) {
            case 1:
                return Color.magenta;
            case 2:
                return Color.cyan;
            case 3:
                return Color.YELLOW;
        }
        return Color.pink;
    }
}
