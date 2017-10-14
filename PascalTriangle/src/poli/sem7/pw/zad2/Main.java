package poli.sem7.pw.zad2;

import poli.sem7.pw.zad2.presentation.MainFrame;

public class Main {

    static MainFrame frame;

    public static void main(String[] args) {

        int POWER = 10;
        PascalTriangle pt = new PascalTriangle(POWER);
        frame = new MainFrame(POWER, pt);

        Thread t1 = new Thread(new CalcThread(pt, 1)); //green
        t1.start();
        Thread t2 = new Thread(new CalcThread(pt, 500)); //blue
        t2.start();
        Thread t3 = new Thread(new CalcThread(pt, 1000)); //yellow
        t3.start();
        Thread t4 = new Thread(new CalcThread(pt, 10000)); //pink
        t4.start();

    }

}
