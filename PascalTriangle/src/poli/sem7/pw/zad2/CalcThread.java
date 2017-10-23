package poli.sem7.pw.zad2;

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
                p = pt.getFreeCell();
                if (p != null && p.getNum() >= 0) {
                    pt.getSemaphore().drainPermits();
                    if (pt.countFreeCells() != null) {
//                        System.out.println("Acquire lock: " + pt.getSemaphore().availablePermits());
                        pt.getSemaphore().tryAcquire(pt.countFreeCells().intValue());
//                        System.out.println("Available cells: " + pt.countFreeCells().intValue());
                    }
                    Thread.sleep(sleepTime);
                    long val = pt.cells[p.getRow() - 1][p.getNum() - 1].getValue()
                            + pt.cells[p.getRow() - 1][p.getNum()].getValue();
                    System.out.println("[" + Thread.currentThread().getName() + "] cell(" + p + ") = " + val);
                    pt.setValue(p, val);
                    pt.getSemaphore().release();
//                    System.out.println("Release lock: " + pt.getSemaphore().availablePermits());
                }
            } while (p != null);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        try {
//            Position p;
//            Cell cell = null;
//            do {
//                p = pt.getFreeCell();
//                if (p != null && p.getNum() >= 0) {
//                    cell = pt.cells[p.getRow()][p.getNum()];
//                    cell.getSemaphore().acquire();
//                    System.out.println(cell.getSemaphore().getQueueLength());
//                }
//
//                if (p != null) {
//                    if (p.getRow() >= 0) {
//                        Thread.sleep(sleepTime);
//                        long val = pt.cells[p.getRow() - 1][p.getNum() - 1].getValue()
//                                + pt.cells[p.getRow() - 1][p.getNum()].getValue();
//                        System.out.println("[" + Thread.currentThread().getName() + "] cell(" + p + ") = " + val);
//                        pt.setValue(p, val);
//                    } else {
//                    }
//                }
//                if (p != null && p.getNum() >= 0) {
//                    cell.getSemaphore().release();
//                }
//            } while (p != null);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }
}
