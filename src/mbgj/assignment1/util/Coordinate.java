package mbgj.assignment1.util;

public final class Coordinate implements Comparable<Coordinate> {

    public int row, col;

    public Coordinate(int row, int col) {
        this.row = row;
        this.col = col;
    }

    @Override
    public int compareTo(Coordinate o) {
        return row == o.row && col == o.col ? 0 : -1;
    }
}
