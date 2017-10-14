package poli.sem7.pw.zad2;

import poli.sem7.pw.zad2.presentation.MainFrame;

public class Main {

    static MainFrame frame;

    public static void main(String[] args) {

        int POWER = 100;
        PascalTriangle pt = new PascalTriangle(POWER);
        frame = new MainFrame(POWER, pt);

        Thread t1 = new Thread(new CalcThread(pt, 1));
        t1.start();
        Thread t2 = new Thread(new CalcThread(pt, 5));
        t2.start();
        Thread t3 = new Thread(new CalcThread(pt, 30));
        t3.start();
        Thread t4 = new Thread(new CalcThread(pt, 60));
        t4.start();

    }

}
