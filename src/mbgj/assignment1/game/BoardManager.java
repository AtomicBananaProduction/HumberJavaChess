package mbgj.assignment1.game;

import mbgj.assignment1.game.Pieces.*;
import mbgj.assignment1.util.Coordinate;

public class BoardManager {

    private static Piece[][] board;

    public static void init() {
        board = new Piece[8][8];

        // Black
        board[0][0] = new Rook(new Coordinate(0, 0), Flag.BLACK);
        board[0][1] = new Knight(new Coordinate(0, 1), Flag.BLACK);
        board[0][2] = new Bishop(new Coordinate(0, 2), Flag.BLACK);
        board[0][3] = new Queen(new Coordinate(0, 3), Flag.BLACK);
        board[0][4] = new King(new Coordinate(0, 4), Flag.BLACK);
        board[0][5] = new Bishop(new Coordinate(0, 5), Flag.BLACK);
        board[0][6] = new Knight(new Coordinate(0, 6), Flag.BLACK);
        board[0][7] = new Rook(new Coordinate(0, 7), Flag.BLACK);

        for (int i = 0; i < 8; i++) {
            board[1][i] = new Pawn(new Coordinate(1, i), Flag.BLACK);
        }

        // White
        board[7][0] = new Rook(new Coordinate(0, 0), Flag.WHITE);
        board[7][1] = new Knight(new Coordinate(0, 1), Flag.WHITE);
        board[7][2] = new Bishop(new Coordinate(0, 2), Flag.WHITE);
        board[7][3] = new Queen(new Coordinate(0, 3), Flag.WHITE);
        board[7][4] = new King(new Coordinate(0, 4), Flag.WHITE);
        board[7][5] = new Bishop(new Coordinate(0, 5), Flag.WHITE);
        board[7][6] = new Knight(new Coordinate(0, 6), Flag.WHITE);
        board[7][7] = new Rook(new Coordinate(0, 7), Flag.WHITE);

        for (int i = 0; i < 8; i++) {
            board[6][i] = new Pawn(new Coordinate(6, i), Flag.WHITE);
        }

        UpdateAllMove();
    }

    private static void UpdateAllMove() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[i][j] != null) {
                    board[i][j].calcMoves();
                }
            }
        }
    }

    public static PiecePack getPieceAt(Coordinate cord) {
        PiecePackId id;

        if (cord.x < 0 || cord.x > 7 || cord.y < 0 || cord.y >7) {
            id = PiecePackId.OUT_OF_BOUND;
            return new PiecePack(null, id);
        } else if (board[cord.x][cord.y] == null) {
            id = PiecePackId.EMPTY;
        } else {
            id = PiecePackId.NORM;
        }

        return new PiecePack(board[cord.x][cord.y], id);
    }

    public static boolean requestMove(Piece p, Coordinate cord) {
        Coordinate oldCord = new Coordinate(p.cord.x, p.cord.y);

        if (p.moveTo(cord)) {
            board[oldCord.x][oldCord.y] = null;
            board[cord.x][cord.y] = p;

            return true;
        }

        return false;
    }
}
