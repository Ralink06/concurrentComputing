package pl.edu.pw.zad3;

import java.awt.*;

public class Portion {
    private String text;
    private int color;

    Portion(String text) {
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
                return Color.red;
            case 2:
                return Color.green;
            case 3:
                return Color.blue;
        }
        return Color.magenta;
    }
}
