package mbgj.assignment1.game;

import java.util.HashMap;
import mbgj.assignment1.game.Pieces.*;
import mbgj.assignment1.util.Coordinate;

public class BoardManager {

    private static Piece[][] board;
    private static HashMap<String, String> pieceMap;

    public static void init() {
        board = new Piece[8][8];
        
        pieceMap = new HashMap<>(); // Hashmap to translate computer id like Pawn to graphic value like P
        
        pieceMap.put("Pawn", "P"); // Pawn is the code id and P is the graphic representation
        pieceMap.put("Rook", "R"); 
        pieceMap.put("King", "K");
        pieceMap.put("Queen", "Q");
        pieceMap.put("Knight", "N");
        pieceMap.put("Bishop", "B");

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

        if (cord.row < 0 || cord.row > 7 || cord.col < 0 || cord.col >7) {
            id = PiecePackId.OUT_OF_BOUND;
            return new PiecePack(null, id);
        } else if (board[cord.row][cord.col] == null) {
            id = PiecePackId.EMPTY;
        } else {
            id = PiecePackId.NORM;
        }

        return new PiecePack(board[cord.row][cord.col], id);
    }

    public static boolean requestMove(Piece p, Coordinate cord) {
        p.calcMoves();
        Coordinate oldCord = new Coordinate(p.cord.row, p.cord.col);

        if (p.moveTo(cord)) {
            board[oldCord.row][oldCord.col] = null;
            board[cord.row][cord.col] = p;

            return true;
        }

        return false;
    }
    
    public static void RenderBoard(){
        
        for (int row = 0; row < 8; row++)
        {
          System.out.println("");
          System.out.println("---------------------------------");

          for (int column = 0; column < 8; column++)
          {
              PiecePack piece = BoardManager.getPieceAt(new Coordinate(row, column));
              if (piece.id == PiecePackId.EMPTY) { // No piece on cord
                  System.out.print("| " + " " + " ");
              } else if (piece.id == PiecePackId.NORM) { // A piece on cord
                  String value = pieceMap.get(piece.piece.name); // Get the graphic value

                  if (value != null) { // if we have setup all items in the map
                      System.out.print("| " + value + " ");
                  }
              }
          }           
        }
        System.out.println("");
        System.out.println("---------------------------------\n");
    }
}
