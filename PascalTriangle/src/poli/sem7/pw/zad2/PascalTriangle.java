package poli.sem7.pw.zad2;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class PascalTriangle {

    Cell cells[][];
    private List<Position> skipped;
    private Position pointer;
    private Semaphore semaphore;

    PascalTriangle(int num, Semaphore semaphore) {
        this.semaphore = semaphore;
        assert (num > 0);
        cells = new Cell[num][];
        cells[0] = createRow(1);

        skipped = new LinkedList<>();
        if (num >= 2) {
            cells[1] = createRow(2);
        }
    /*if (num >= 3) {
        cells[2] = createRow(3);
	    pointer = new Position(2, 1);
	}*/
        pointer = new Position(1, 2);
    }

    // creates empty row with '1' in first and last cell
    private Cell[] createRow(int rowNum) {
        Cell cells[] = new Cell[rowNum];
        cells[0] = new Cell(1);
        cells[0].color = 0;
        cells[rowNum - 1] = new Cell(1);
        cells[rowNum - 1].color = 0;
        for (int i = 1; i < rowNum - 1; i++) {
            cells[i] = new Cell();
        }
        return cells;
    }

    private boolean canBeCalculated(Position pos) {
        return cells[pos.getRow() - 1][pos.getNum() - 1].isCalculated()
                && cells[pos.getRow() - 1][pos.getNum()].isCalculated();
    }

    private boolean canCalculateNextRow(int row) {
        for (int i = 0; i < cells[row].length - 1; i++) {
            if (cells[row][i].isCalculated()
                    && cells[row][i + 1].isCalculated()) {
                return true;
            }
        }
        return false;
    }

    // gets first cell witch can be calculated
    public Position getFreeCell() throws InterruptedException {
        if (pointer == null) {
            return null;
        }

        for (Iterator<Position> iterator = skipped.iterator(); iterator
                .hasNext();) {
            Position pos = iterator.next();
            if (canBeCalculated(pos)) {
                iterator.remove();
                return pos;
            }
        }

        int row = pointer.getRow();
        for (int i = pointer.getNum(); i < cells[row].length; i++) {
            if (!cells[row][i].isCalculated()) {
                if (canBeCalculated(new Position(row, i))) {
                    // can add
                    pointer = new Position(row, i + 1);
                    return new Position(row, i);
                } else {
                    // can't add... cell value isn't known
                    skipped.add(new Position(row, i));
                    pointer = new Position(row, i + 1);
                }
            }
        }

        // everything is calculated
        if (row + 1 >= cells.length && skipped.size() == 0) {
            return null;
        }

        // go to next row
        if (row + 1 < cells.length && canCalculateNextRow(row)) {
            cells[row + 1] = createRow(row + 2);
            pointer = new Position(row + 1, 1);
            return getFreeCell();
        }

        // nothing to do
        return new Position(-1, -1);


    }

    // sets cell value
    void setValue(Position pointer, long value) {
        cells[pointer.getRow()][pointer.getNum()] = new Cell(value);
        Main.frame.repaint();
    }

    public long getValue(int row, int col) {
        if (cells[row] != null && cells[row][col] != null && cells[row][col].isCalculated())
            return cells[row][col].getValue();
        return -1;
    }

    public boolean isCalculated(int row, int col) {
        return cells[row] != null && cells[row][col] != null && cells[row][col].isCalculated();
    }

    public int getColor(int row, int col) {
        if (cells[row] != null && cells[row][col] != null && cells[row][col].isCalculated())
            return cells[row][col].color;
        return 0;
    }

    public Semaphore getSemaphore() {
        return this.semaphore;
    }
}
