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
        if (flag == Flag.WHITE && cord.row == 6) { // White pawn in starting location
            moves.add(new Coordinate(cord.row - 2, cord.col));
        } else if (flag == Flag.BLACK && cord.row == 1) { // Black pawn in starting location
            moves.add(new Coordinate(cord.row + 2, cord.col));
        }

        PiecePack left = null;
        PiecePack right = null;

        // Enemy on diagonal case
        if (flag == Flag.WHITE) { // White piece
            left = BoardManager.getPieceAt(new Coordinate(cord.row - 1, cord.col - 1));
            right = BoardManager.getPieceAt(new Coordinate(cord.row - 1, cord.col + 1));
        } else if (flag == Flag.BLACK) { // Black piece
            left = BoardManager.getPieceAt(new Coordinate(cord.row + 1, cord.col - 1));
            right = BoardManager.getPieceAt(new Coordinate(cord.row + 1, cord.col + 1));
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
            frontCord = new Coordinate(cord.row - 1, cord.col);
            front = BoardManager.getPieceAt(frontCord);
        } else {
            frontCord = new Coordinate(cord.row + 1, cord.col);
            front = BoardManager.getPieceAt(frontCord);
        }

        // If nothing in front add move
        if (front.id == PiecePackId.EMPTY) {
            moves.add(frontCord);
        }
    }
}
