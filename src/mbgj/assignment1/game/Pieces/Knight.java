package mbgj.assignment1.game.Pieces;

import mbgj.assignment1.game.*;
import mbgj.assignment1.util.Coordinate;

public class Knight extends Piece {

    public Knight(Coordinate cord, Flag flag) {
        super(cord, "Knight", flag);
    }

    @Override
    protected void calcMoves() {
        addMoveKnight(new Coordinate(cord.x - 2, cord.y + 1));
        addMoveKnight(new Coordinate(cord.x - 2, cord.y - 1));

        addMoveKnight(new Coordinate(cord.x - 1, cord.y + 2));
        addMoveKnight(new Coordinate(cord.x - 1, cord.y - 2));

        addMoveKnight(new Coordinate(cord.x + 1, cord.y + 2));
        addMoveKnight(new Coordinate(cord.x + 1, cord.y - 2));

        addMoveKnight(new Coordinate(cord.x + 2, cord.y + 1));
        addMoveKnight(new Coordinate(cord.x + 2, cord.y - 1));
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
