package mbgj.assignment1.util;

public final class Coordinate implements Comparable<Coordinate> {

    public int x, y;

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public int compareTo(Coordinate o) {
        return x == o.x && y == o.y ? 0 : -1;
    }
}
