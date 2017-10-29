package pl.edu.pw.zad3.presentation;

import pl.edu.pw.zad3.Main;
import pl.edu.pw.zad3.Package;
import pl.edu.pw.zad3.Portion;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;
import java.util.List;

public class StatusPanel extends JPanel {

    private List<Portion> buffer1;
    private List<Package> buffer2;
    private List<Package> consumer;

    StatusPanel() {
        buffer1 = new LinkedList<>();
        buffer2 = new LinkedList<>();
        consumer = new LinkedList<>();
    }

    void setBuffer1(List<Portion> buffer1) {
        this.buffer1 = buffer1;
    }

    void setBuffer2(List<Package> buffer2) {
        this.buffer2 = buffer2;
    }

    void setConsumer(List<Package> consumer) {
        this.consumer = consumer;
    }


    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        List<Portion> b1 = buffer1;
        List<Package> b2 = buffer2;
        List<Package> b3 = consumer;

        //g.drawString(b1.size() + "/" + Main.BUF1SIZE, 10, 40);
        //g.drawString(b2.size() + "/" + Main.BUF2SIZE, 10, 80);

        int posX = 30;
        int posY = 30;

        graphics.setColor(Color.black);
        graphics.drawString("Pierwszy bufor (" + b1.size() + "/" + Main.BUF1SIZE + ")", posX + 20, posY);

        posY += 15;

        for (int i = 0; i < Main.BUF1SIZE; i++) {

            if (i < b1.size()) {
                Portion portion = b1.get(i);
                graphics.setColor(portion.getColor());
                graphics.fillRect(posX, posY + i * 30, 150, 30);
                graphics.setColor(Color.black);
                graphics.drawString(portion.toString(), posX + 60, posY + i * 30 + 20);
            } else {
                graphics.setColor(Color.white);
                graphics.fillRect(posX, posY + i * 30, 150, 30);
            }

            graphics.setColor(Color.black);
            graphics.drawRect(posX, posY + i * 30, 150, 30);
        }

        posX = 300;
        posY = 30;

        graphics.setColor(Color.black);
        graphics.drawString("Drugi bufor (" + b2.size() + "/" + Main.BUF2SIZE + ")", posX + 20, posY);

        posY += 15;

        for (int i = 0; i < Main.BUF2SIZE; i++) {

            if (i < b2.size()) {
                Package pack = b2.get(i);
                graphics.setColor(Color.orange);
                graphics.fillRect(posX, posY + i * 30, 150, 30);
                graphics.setColor(Color.black);
                graphics.drawString("Ilo�� porcji: " + pack.getSize(), posX + 40, posY
                        + i * 30 + 20);
            } else {
                graphics.setColor(Color.white);
                graphics.fillRect(posX, posY + i * 30, 150, 30);
            }

            graphics.setColor(Color.black);
            graphics.drawRect(posX, posY + i * 30, 150, 30);
        }

        // ///////////////////////////////////////////////////
        posX = 570;
        posY = 30;

        graphics.setColor(Color.black);
        graphics.drawString("Ostatnio zdj�te", posX + 30, posY);

        posY += 15;

        for (int i = 0; i < Main.BUF2SIZE; i++) {

            if (i < b3.size()) {
                Package pack = b3.get(i);
                graphics.setColor(Color.orange);
                graphics.fillRect(posX, posY + i * 30, 150, 30);
                graphics.setColor(Color.black);
                graphics.drawString("Ilo�� porcji: " + pack.getSize(), posX + 40, posY
                        + i * 30 + 20);
            } else {
                graphics.setColor(Color.white);
                graphics.fillRect(posX, posY + i * 30, 150, 30);
            }

            graphics.setColor(Color.black);
            graphics.drawRect(posX, posY + i * 30, 150, 30);
        }

    }
}
