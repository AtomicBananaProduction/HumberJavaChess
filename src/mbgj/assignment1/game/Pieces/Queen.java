package mbgj.assignment1.game.Pieces;

import mbgj.assignment1.game.*;
import mbgj.assignment1.util.Coordinate;

public class Queen extends Piece {

    public Queen(Coordinate cord, Flag flag) {
        super(cord, "Queen", flag);
    }

    @Override
    public void calcMoves() {
        addMoveQueen(cord, 1, 0);
        addMoveQueen(cord, -1, 0);
        addMoveQueen(cord, 0, 1);
        addMoveQueen(cord, 0, -1);

        addMoveQueen(cord, 1, 1);
        addMoveQueen(cord, -1, -1);
        addMoveQueen(cord, -1, 1);
        addMoveQueen(cord, 1, -1);
    }

    private void addMoveQueen(Coordinate ambCord, int dirX, int dirY) {

        // Setup new cord with direction
        ambCord = new Coordinate(ambCord.x + dirX, ambCord.y + dirY);

        // Quit condition: out of bound
        if (BoardManager.getPieceAt(ambCord).id == PiecePackId.OUT_OF_BOUND) {
            return;
        }

        if (BoardManager.getPieceAt(ambCord).id == PiecePackId.EMPTY) { // Empty slot
            moves.add(ambCord);
            addMoveQueen(ambCord, dirX, dirY);
        } else if (BoardManager.getPieceAt(ambCord).piece.getFlag() != flag) { // Enemy
            moves.add(ambCord);
        }
    }
}
