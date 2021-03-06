package mbgj.assignment1.game.Pieces;

import mbgj.assignment1.game.*;
import mbgj.assignment1.util.Coordinate;

public class Rook extends Piece {
    public Rook(Coordinate cord, Flag flag) {
        super(cord, "Rook", flag);
    }

    @Override
    public void calcMoves() {
        addMoveRook(cord, 1, 0);
        addMoveRook(cord, -1, 0);
        addMoveRook(cord, 0, 1);
        addMoveRook(cord, 0, -1);
    }

    private void addMoveRook(Coordinate ambCord, int dirX, int dirY) {

        // Setup new cord with direction
        ambCord = new Coordinate(ambCord.row + dirY, ambCord.col + dirX);

        // Quit condition: out of bound
        if (BoardManager.getPieceAt(ambCord).id == PiecePackId.OUT_OF_BOUND) {
            return;
        }

        if (BoardManager.getPieceAt(ambCord).id == PiecePackId.EMPTY) { // Empty slot
            moves.add(ambCord);
            addMoveRook(ambCord, dirX, dirY);
        } else if (BoardManager.getPieceAt(ambCord).piece.getFlag() != flag) { // Enemy
            moves.add(ambCord);
        }
    }
}
