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
        addMoveKing(new Coordinate(cord.x - 1, cord.y));
        addMoveKing(new Coordinate(cord.x + 1, cord.y));
        addMoveKing(new Coordinate(cord.x, cord.y - 1));
        addMoveKing(new Coordinate(cord.x, cord.y + 1));

        // Diagonal
        addMoveKing(new Coordinate(cord.x - 1, cord.y + 1));
        addMoveKing(new Coordinate(cord.x + 1, cord.y + 1));
        addMoveKing(new Coordinate(cord.x - 1, cord.y - 1));
        addMoveKing(new Coordinate(cord.x - 1, cord.y + 1));
    }

    private void addMoveKing(Coordinate cord) {
        PiecePack p = BoardManager.getPieceAt(cord);

        // Check if new cord has a enemy king near by
        if (p.id == PiecePackId.EMPTY) {
            PiecePack[] kPacks = new PiecePack[8];
            kPacks[0] = BoardManager.getPieceAt(new Coordinate(cord.x - 1, cord.y));
            kPacks[1] = BoardManager.getPieceAt(new Coordinate(cord.x + 1, cord.y));
            kPacks[2] = BoardManager.getPieceAt(new Coordinate(cord.x, cord.y - 1));
            kPacks[3] = BoardManager.getPieceAt(new Coordinate(cord.x, cord.y + 1));

            kPacks[4] = BoardManager.getPieceAt(new Coordinate(cord.x - 1, cord.y + 1));
            kPacks[5] = BoardManager.getPieceAt(new Coordinate(cord.x + 1, cord.y + 1));
            kPacks[6] = BoardManager.getPieceAt(new Coordinate(cord.x - 1, cord.y - 1));
            kPacks[7] = BoardManager.getPieceAt(new Coordinate(cord.x - 1, cord.y + 1));

            boolean canPlace = true;

            for (PiecePack kpk : kPacks) {
                if (kpk.id == PiecePackId.NORM) {
                    canPlace = canPlace && (!kpk.piece.name.equals("King") && kpk.piece.getFlag() != flag);
                }
            }

            // No enemy king thus placing
            if (canPlace) {
                moves.add(cord);
            }
        } else if (p.id == PiecePackId.NORM) {
            moves.add(cord);
        }
    }
}
