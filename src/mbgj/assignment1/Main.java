package mbgj.assignment1;

import mbgj.assignment1.game.BoardManager;
import mbgj.assignment1.game.PiecePack;
import mbgj.assignment1.game.PiecePackId;
import mbgj.assignment1.util.Coordinate;

import java.util.HashMap;

public class Main {

    public static void main(String[] args) {

        HashMap<String, String> pieceMap = new HashMap<>(); // Hashmap to translate computer id like Pawn to graphic value like P
        pieceMap.put("Pawn", "P"); // Pawn is the code id and P is the graphic representation

        //Board initializing
        BoardManager.init();

        // Adding all possible pieces
        System.out.println("Board initialized\n");
      
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
        System.out.println("---------------------------------");
    
    }
}
