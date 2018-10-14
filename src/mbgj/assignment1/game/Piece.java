package mbgj.assignment1.game;

import mbgj.assignment1.util.Coordinate;
import java.util.ArrayList;

public abstract class Piece {
    public String name;

    protected Coordinate cord;
    protected Flag flag;
    protected ArrayList<Coordinate> moves;

    public static Flag nFlag(Flag f) {
        return f == Flag.BLACK ? Flag.WHITE : Flag.BLACK;
    }

    public Piece(Coordinate cord, String name, Flag flag) {
        moves = new ArrayList<>();

        this.cord = cord;
        this.name = name;
        this.flag = flag;
    }

    public final Coordinate getCord() {
        return new Coordinate(cord.row, cord.col);
    }

    public final Flag getFlag() {
        return flag == Flag.BLACK ? Flag.BLACK : Flag.WHITE;
    }

    public abstract void calcMoves();

    protected boolean canMoveTo(Coordinate cord) {
        boolean canMove = false;

        for (Coordinate c : moves) {
            canMove = canMove || c.compareTo(cord) == -1 ? false : true;
        }

        return canMove;
    }

    public final boolean moveTo(Coordinate cord) {
        if (canMoveTo(cord)) {

            this.cord = cord;

            moves.clear();
            calcMoves();

            return true;
        }

        return false;
    }
}
