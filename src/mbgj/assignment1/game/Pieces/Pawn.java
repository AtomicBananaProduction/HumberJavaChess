package mbgj.assignment1.game.Pieces;

import mbgj.assignment1.game.*;
import mbgj.assignment1.util.Coordinate;

public class Pawn extends Piece {

    public Pawn(Coordinate cord, Flag flag) {
        super(cord, "Pawn", flag);
    }

    @Override
    public void calcMoves() {
        // Initial 2 move case
        if (flag == Flag.WHITE && cord.y == 1) { // White pawn in starting location
            moves.add(new Coordinate(cord.x, cord.y + 2));
        } else if (flag == Flag.BLACK && cord.y == 6) { // Black pawn in starting location
            moves.add(new Coordinate(cord.x, cord.y - 2));
        }

        PiecePack left = null;
        PiecePack right = null;

        // Enemy on diagonal case
        if (flag == Flag.WHITE) { // White piece
            left = BoardManager.getPieceAt(new Coordinate(cord.x - 1, cord.y + 1));
            right = BoardManager.getPieceAt(new Coordinate(cord.x + 1, cord.y + 1));
        } else if (flag == Flag.BLACK) { // Black piece
            left = BoardManager.getPieceAt(new Coordinate(cord.x - 1, cord.y - 1));
            right = BoardManager.getPieceAt(new Coordinate(cord.x + 1, cord.y - 1));
        }

        // Add kill moves if there is any
        if (left.id == PiecePackId.NORM) {
            if (left.piece.getFlag() != flag) {
                moves.add(left.piece.getCord());
            }
        }
        if (right.id == PiecePackId.NORM) {
            if (right.piece.getFlag() != flag) {
                moves.add(right.piece.getCord());
            }
        }

        // Standard move
        PiecePack front;
        Coordinate frontCord;
        if (flag == Flag.WHITE) {
            frontCord = new Coordinate(cord.x, cord.y + 1);
            front = BoardManager.getPieceAt(frontCord);
        } else {
            frontCord = new Coordinate(cord.x, cord.y - 1);
            front = BoardManager.getPieceAt(frontCord);
        }

        // If nothing in front add move
        if (front.id == PiecePackId.EMPTY) {
            moves.add(frontCord);
        }
    }
}
