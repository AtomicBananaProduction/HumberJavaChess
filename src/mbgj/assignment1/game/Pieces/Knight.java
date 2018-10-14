package mbgj.assignment1.game.Pieces;

import mbgj.assignment1.game.*;
import mbgj.assignment1.util.Coordinate;

public class Knight extends Piece {

    public Knight(Coordinate cord, Flag flag) {
        super(cord, "Knight", flag);
    }

    @Override
    public void calcMoves() {
        addMoveKnight(new Coordinate(cord.row - 2, cord.col + 1));
        addMoveKnight(new Coordinate(cord.row - 2, cord.col - 1));

        addMoveKnight(new Coordinate(cord.row - 1, cord.col + 2));
        addMoveKnight(new Coordinate(cord.row - 1, cord.col - 2));

        addMoveKnight(new Coordinate(cord.row + 1, cord.col + 2));
        addMoveKnight(new Coordinate(cord.row + 1, cord.col - 2));

        addMoveKnight(new Coordinate(cord.row + 2, cord.col + 1));
        addMoveKnight(new Coordinate(cord.row + 2, cord.col - 1));
    }

    private void addMoveKnight(Coordinate cord) {
        PiecePack p = BoardManager.getPieceAt(cord);
        if (p.id == PiecePackId.EMPTY) {
            moves.add(cord);
        } else if (p.id == PiecePackId.NORM) {
            if (p.piece.getFlag() != flag) {
                moves.add(cord);
            }
        }
    }
}
