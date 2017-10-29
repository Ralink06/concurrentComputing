package poli.sem7.pw.zad2;

import java.util.concurrent.Semaphore;

public class CalcThread implements Runnable {

    private PascalTriangle pt;
    private long sleepTime;

    CalcThread(PascalTriangle pt, long sleepTime) {
        this.pt = pt;
        this.sleepTime = sleepTime;
    }

    public void run() {

        try {
            Position p;
            do {
                pt.getSemaphore().acquire();
//                System.out.println(pt.getSemaphore().getQueueLength() + " threads waiting...");
                p = pt.getFreeCell();
                pt.getSemaphore().release();
                if (p != null && p.getNum() >= 0) {
                    Thread.sleep(sleepTime);
                    long val = pt.cells[p.getRow() - 1][p.getNum() - 1].getValue()
                            + pt.cells[p.getRow() - 1][p.getNum()].getValue();
                    System.out.println("[" + Thread.currentThread().getName() + "] cell(" + p + ") = " + val);
                    pt.setValue(p, val);
                }
            } while (p != null);
//            pt.getSemaphore().release(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
