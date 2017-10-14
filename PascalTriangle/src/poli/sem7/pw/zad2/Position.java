package poli.sem7.pw.zad2;

public class Position {
    private int row;
    private int num;

    Position(int row, int num) {
        this.row = row;
        this.num = num;
    }

    int getRow() {
        return row;
    }

    int getNum() {
        return num;
    }

    public String toString() {
        return row + ", " + num;
    }
}
