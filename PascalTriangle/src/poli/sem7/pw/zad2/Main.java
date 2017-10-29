package poli.sem7.pw.zad2;

import poli.sem7.pw.zad2.presentation.MainFrame;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.Semaphore;

public class Main {

    static MainFrame frame;

    public static void main(String[] args) throws InterruptedException {

        int POWER = 14;
        Semaphore semaphore = new Semaphore(1);
        PascalTriangle pt = new PascalTriangle(POWER, semaphore);
        frame = new MainFrame(POWER, pt);

        LocalTime startTime = LocalTime.now();

        Thread t1 = new Thread(new CalcThread(pt, 1000)); //green
        t1.start();
        Thread t2 = new Thread(new CalcThread(pt, 800)); //blue
        t2.start();
        Thread t3 = new Thread(new CalcThread(pt, 600)); //yellow
        t3.start();
        Thread t4 = new Thread(new CalcThread(pt, 400)); //pink
        t4.start();
        Thread t5 = new Thread(new CalcThread(pt, 200)); //cyan
        t5.start();
        Thread t6 = new Thread(new CalcThread(pt, 1)); //darkgray
        t6.start();

        LocalTime end = null;
        while ((t6.isAlive() && t5.isAlive() && t4.isAlive() && t3.isAlive() && t2.isAlive() && t1.isAlive())) {
            end = LocalTime.now();
        }
        Thread.sleep(1000);
        System.out.println("Total time: " + ChronoUnit.MILLIS.between(startTime, end) + "ms");
    }

}
