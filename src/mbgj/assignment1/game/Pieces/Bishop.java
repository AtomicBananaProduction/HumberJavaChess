package mbgj.assignment1.game.Pieces;

import mbgj.assignment1.game.*;
import mbgj.assignment1.util.Coordinate;

public class Bishop extends Piece {

    public Bishop(Coordinate cord, Flag flag) {
        super(cord, "Bishop", flag);
    }

    @Override
    public void calcMoves() {
        addMoveBishop(cord, 1, 1);
        addMoveBishop(cord, -1, -1);
        addMoveBishop(cord, -1, 1);
        addMoveBishop(cord, 1, -1);
    }

    private void addMoveBishop(Coordinate ambCord, int dirX, int dirY) {

        // Setup new cord with direction
        ambCord = new Coordinate(ambCord.row + dirY, ambCord.col + dirX);

        // Quit condition: out of bound
        if (BoardManager.getPieceAt(ambCord).id == PiecePackId.OUT_OF_BOUND) {
            return;
        }

        if (BoardManager.getPieceAt(ambCord).id == PiecePackId.EMPTY) { // Empty slot
            moves.add(ambCord);
            addMoveBishop(ambCord, dirX, dirY);
        } else if (BoardManager.getPieceAt(ambCord).piece.getFlag() != flag) { // Enemy
            moves.add(ambCord);
        }
    }

}
