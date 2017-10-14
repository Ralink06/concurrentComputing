package poli.sem7.pw.zad2;

public class Cell {
    private long value;
    private boolean calculated;
    int color;

    Cell() {
        this.value = -1;
        this.calculated = false;
        color = 0;
    }

    Cell(long value) {
        this.value = value;
        this.calculated = true;
        if ("main".equals(Thread.currentThread().getName())) {
            color = 0;
        } else {
            color = Integer.parseInt(Thread.currentThread().getName().split("[-]")[1]);
        }
    }

    long getValue() {
        return value;
    }

    boolean isCalculated() {
        return calculated;
    }

    public String toString() {
        return "value: " + value + ", calculated: " + calculated;
    }
}
