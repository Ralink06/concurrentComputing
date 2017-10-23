package poli.sem7.pw.zad2;

import poli.sem7.pw.zad2.presentation.MainFrame;

import java.util.concurrent.Semaphore;

public class Main {

    static MainFrame frame;

    public static void main(String[] args) {

        int POWER = 14;
        Semaphore semaphore = new Semaphore(1);
        PascalTriangle pt = new PascalTriangle(POWER, semaphore);
        frame = new MainFrame(POWER, pt);

        Thread t1 = new Thread(new CalcThread(pt, 600)); //green
        t1.start();
        Thread t2 = new Thread(new CalcThread(pt, 400)); //blue
        t2.start();
        Thread t3 = new Thread(new CalcThread(pt, 200)); //yellow
        t3.start();
        Thread t4 = new Thread(new CalcThread(pt, 100)); //pink
        t4.start();
    }

}
