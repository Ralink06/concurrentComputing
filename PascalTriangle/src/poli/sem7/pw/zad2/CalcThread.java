package poli.sem7.pw.zad2;

public class CalcThread implements Runnable {

    private static final Object waitObject = new Object();

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
                p = pt.getFreeCell();

                if (p != null) {
                    if (p.getRow() >= 0) {
                        Thread.sleep(sleepTime);
                        long val = pt.cells[p.getRow() - 1][p.getNum() - 1].getValue()
                                + pt.cells[p.getRow() - 1][p.getNum()].getValue();
                        System.out.println("[" + Thread.currentThread().getName() + "] cell(" + p + ") = " + val);
                        pt.setValue(p, val);
                        synchronized (waitObject) {
                            waitObject.notifyAll();
                        }
                    } else {
                        System.out.println("[" + Thread.currentThread().getName() + "] thread is going to sleep");
                        synchronized (waitObject) {
                            waitObject.wait();
                        }
                    }
                }

            } while (p != null);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
