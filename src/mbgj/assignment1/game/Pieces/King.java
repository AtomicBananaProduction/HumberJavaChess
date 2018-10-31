package mbgj.assignment1.game.Pieces;

import mbgj.assignment1.game.*;
import mbgj.assignment1.util.Coordinate;

public class King extends Piece {

    public King(Coordinate cord, Flag flag) {
        super(cord, "King", flag);
    }

    @Override
    public void calcMoves() {
        // Horizontal and vertical
        addMoveKing(new Coordinate(cord.row - 1, cord.col));
        addMoveKing(new Coordinate(cord.row + 1, cord.col));
        addMoveKing(new Coordinate(cord.row, cord.col - 1));
        addMoveKing(new Coordinate(cord.row, cord.col + 1));

        // Diagonal
        addMoveKing(new Coordinate(cord.row - 1, cord.col + 1));
        addMoveKing(new Coordinate(cord.row + 1, cord.col + 1));
        addMoveKing(new Coordinate(cord.row - 1, cord.col - 1));
        addMoveKing(new Coordinate(cord.row - 1, cord.col + 1));

        if (cord.row == 0 || cord.row == 7) {
            addMoveCastle();
        }
    }

    private void addMoveCastle() {
        // Rhs
        if (BoardManager.getPieceAt(new Coordinate(cord.row, cord.col + 1)).id == PiecePackId.EMPTY &&
                BoardManager.getPieceAt(new Coordinate(cord.row, cord.col + 2)).id == PiecePackId.EMPTY &&
                BoardManager.getPieceAt(new Coordinate(cord.row, cord.col + 3)).id == PiecePackId.NORM) {

            Piece piece = BoardManager.getPieceAt((new Coordinate(cord.row, cord.col + 3))).piece;
            if (piece.name.equals("Rook") && piece.getFlag() == flag) {

                // Move the king
                addMoveKing(new Coordinate(cord.row, cord.col + 2));
            }
        }

        // Lhs
        if (BoardManager.getPieceAt(new Coordinate(cord.row, cord.col - 1)).id == PiecePackId.EMPTY &&
                BoardManager.getPieceAt(new Coordinate(cord.row, cord.col - 2)).id == PiecePackId.EMPTY &&
                BoardManager.getPieceAt(new Coordinate(cord.row, cord.col - 3)).id == PiecePackId.EMPTY &&
                BoardManager.getPieceAt(new Coordinate(cord.row, cord.col - 4)).id == PiecePackId.NORM) {

            Piece piece = BoardManager.getPieceAt((new Coordinate(cord.row, cord.col - 4))).piece;
            if (piece.name.equals("Rook") && piece.getFlag() == flag) {

                // Move the king
                addMoveKing(new Coordinate(cord.row, cord.col - 2));
            }
        }
    }

    private boolean isNotEnemyKingNear(Coordinate cord) {
        PiecePack[] kPacks = new PiecePack[8];
        kPacks[0] = BoardManager.getPieceAt(new Coordinate(cord.row - 1, cord.col));
        kPacks[1] = BoardManager.getPieceAt(new Coordinate(cord.row + 1, cord.col));
        kPacks[2] = BoardManager.getPieceAt(new Coordinate(cord.row, cord.col - 1));
        kPacks[3] = BoardManager.getPieceAt(new Coordinate(cord.row, cord.col + 1));

        kPacks[4] = BoardManager.getPieceAt(new Coordinate(cord.row - 1, cord.col + 1));
        kPacks[5] = BoardManager.getPieceAt(new Coordinate(cord.row + 1, cord.col + 1));
        kPacks[6] = BoardManager.getPieceAt(new Coordinate(cord.row - 1, cord.col - 1));
        kPacks[7] = BoardManager.getPieceAt(new Coordinate(cord.row - 1, cord.col + 1));

        boolean canPlace = true;

        for (PiecePack kpk : kPacks) {
            if (kpk.id == PiecePackId.NORM) {
                canPlace = canPlace && !(kpk.piece.name.equals("King") && kpk.piece.getFlag() != flag);
            }
        }

        return canPlace;
    }

    private void addMoveKing(Coordinate cord) {
        PiecePack p = BoardManager.getPieceAt(cord);

        // Check if new cord has a enemy king near by
        if (p.id == PiecePackId.EMPTY) {

            // No enemy king thus placing
            if (isNotEnemyKingNear(cord)) {
                moves.add(cord);
            }
        } else if (p.id == PiecePackId.NORM) {
            if (p.piece.getFlag() != flag) {
                moves.add(cord);
            }
        }
    }
}
