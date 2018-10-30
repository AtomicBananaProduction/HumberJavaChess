package mbgj.assignment1.game;

import java.io.*;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import mbgj.assignment1.game.Pieces.*;
import mbgj.assignment1.util.Coordinate;

public class BoardManager {

    private static Piece[][] board;
    private static HashMap<String, String> pieceMap;
    
    
    public static void init() {
        //Board init for New Game
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
        board[7][0] = new Rook(new Coordinate(7, 0), Flag.WHITE);
        board[7][1] = new Knight(new Coordinate(7, 1), Flag.WHITE);
        board[7][2] = new Bishop(new Coordinate(7, 2), Flag.WHITE);
        board[7][3] = new Queen(new Coordinate(7, 3), Flag.WHITE);
        board[7][4] = new King(new Coordinate(7, 4), Flag.WHITE);
        board[7][5] = new Bishop(new Coordinate(7, 5), Flag.WHITE);
        board[7][6] = new Knight(new Coordinate(7, 6), Flag.WHITE);
        board[7][7] = new Rook(new Coordinate(7, 7), Flag.WHITE);

        for (int i = 0; i < 8; i++) {
            board[6][i] = new Pawn(new Coordinate(6, i), Flag.WHITE);
        }
    }
        //Board Init for loading.
    private static void loadGameInit(){
            board = new Piece[8][8];
        
            pieceMap = new HashMap<>(); // Hashmap to translate computer id like Pawn to graphic value like P

            pieceMap.put("Pawn", "P"); // Pawn is the code id and P is the graphic representation
            pieceMap.put("Rook", "R"); 
            pieceMap.put("King", "K");
            pieceMap.put("Queen", "Q");
            pieceMap.put("Knight", "N");
            pieceMap.put("Bishop", "B");
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

            // Castle tester
            if ((oldCord.row == 0 || oldCord.row == 7) &&
                    (p.name.equals("King"))) {
                // King moves
                if (cord.compareTo(new Coordinate(oldCord.row, oldCord.col + 2)) == 0) {
                    forceMove(board[oldCord.row][7], new Coordinate(oldCord.row, 5));
                } else if (cord.compareTo(new Coordinate(oldCord.row, oldCord.col - 2)) == 0) {
                    forceMove(board[oldCord.row][0], new Coordinate(oldCord.row, 3));
                }
            }

            return true;
        }

        return false;
    }

    public static void forceMove(Piece p, Coordinate cord) {
        board[p.cord.row][p.cord.col] = null;
        board[cord.row][cord.col] = p;
    }

    public static void promotePawn(Piece p, int id) {
        Piece newPiece = null;
        Flag flag = p.getFlag();
        Coordinate cord = p.cord;

        switch (id) {
            case 0:
                newPiece = new Knight(cord, flag);
                break;
            case 1:
                newPiece = new Bishop(cord, flag);
                break;
            case 2:
                newPiece = new Rook(cord, flag);
                break;
            case 3:
                newPiece = new Queen(cord, flag);
                break;
        }

        board[cord.row][cord.col] = newPiece;
    }
    
    public static void RenderBoard(){

        System.out.print("      0   1   2   3   4   5   6   7 ");

        for (int row = 0; row < 8; row++)
        {
          System.out.println("");
          System.out.println("    ---------------------------------");

            System.out.print("  " + row + " ");
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
        System.out.println("    ---------------------------------");
        System.out.println("      0   1   2   3   4   5   6   7 \n");
    }
    
    //Save Game function
    public static void saveGame(int currentPlayer){
        //Creates a buffered writer with the append set to false, this way the file is going to always be overwritten
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("SavedGame.txt", false))){
            //First line is the current player turn. If 0 = WHITE , if 1 = BLACK
            bw.write(String.valueOf(currentPlayer));
            bw.newLine();
            //Each new line contains the coordinates            
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (board[i][j] != null) {
                        
                        bw.write(String.valueOf(i) + "-");
                        bw.write(String.valueOf(j) + "-");
                        //Then the piece name                                                
                        switch(board[i][j].name){
                            case "Pawn":
                                bw.write("Pawn" + "-");
                                break;
                            case "Rook":
                                bw.write("Rook" + "-");
                                break;
                            case "Knight":
                                bw.write("Knight" + "-");
                                break;
                            case "Bishop":
                                bw.write("Bishop" + "-");
                                break;
                            case "Queen":
                                bw.write("Queen" + "-");
                                break;
                            case "King":
                                bw.write("King" + "-");
                                break;
                        }
                        //Then which flag is correspondent to that piece
                        switch(board[i][j].getFlag()){
                            case WHITE:
                                bw.write(0 + "");
                                break;
                            case BLACK:
                                bw.write(1 + "");
                                break;
                        }
                        //Then jump a new line
                        bw.newLine();
                    }
                 }
            }
        } catch (IOException e) {
            e.getMessage();
        }    
       
    }
    
    public static int loadGame(String file){
            //VERY IMPORTANT
            //This initializes the board automatically for the game to be loaded
            
            loadGameInit();
            
            int currentPlayer = 0;
            
            //Saved File Reading
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String line = br.readLine();

                if(line != null){
                    currentPlayer = Integer.parseInt(line);
                }

                line = br.readLine();

                while(line != null){
                    StringTokenizer st = new StringTokenizer(line, "-");

                    while(st.hasMoreTokens()){
                        String coordI = st.nextToken();
                        String coordJ = st.nextToken();
                        String pieceName = st.nextToken();
                        String pieceFlag = st.nextToken();
                        
                        switch(Integer.parseInt(pieceFlag)){
                            case 0:
                                switch(pieceName){
                                    case "Pawn":
                                        board[Integer.parseInt(coordI)][Integer.parseInt(coordJ)] = new Pawn(new Coordinate(Integer.parseInt(coordI), Integer.parseInt(coordJ)), Flag.WHITE);
                                        break;
                                    case "Rook":
                                        board[Integer.parseInt(coordI)][Integer.parseInt(coordJ)] = new Rook(new Coordinate(Integer.parseInt(coordI), Integer.parseInt(coordJ)), Flag.WHITE);
                                        break;
                                    case "Knight":
                                        board[Integer.parseInt(coordI)][Integer.parseInt(coordJ)] = new Knight(new Coordinate(Integer.parseInt(coordI), Integer.parseInt(coordJ)), Flag.WHITE);
                                        break;
                                    case "Bishop":
                                        board[Integer.parseInt(coordI)][Integer.parseInt(coordJ)] = new Bishop(new Coordinate(Integer.parseInt(coordI), Integer.parseInt(coordJ)), Flag.WHITE);
                                        break;
                                    case "Queen":
                                        board[Integer.parseInt(coordI)][Integer.parseInt(coordJ)] = new Queen(new Coordinate(Integer.parseInt(coordI), Integer.parseInt(coordJ)), Flag.WHITE);
                                        break;
                                    case "King":
                                        board[Integer.parseInt(coordI)][Integer.parseInt(coordJ)] = new King(new Coordinate(Integer.parseInt(coordI), Integer.parseInt(coordJ)), Flag.WHITE);
                                        break;
                                }
                                break;
                            case 1:
                                switch(pieceName){
                                    case "Pawn":
                                        board[Integer.parseInt(coordI)][Integer.parseInt(coordJ)] = new Pawn(new Coordinate(Integer.parseInt(coordI), Integer.parseInt(coordJ)), Flag.BLACK);
                                        break;
                                    case "Rook":
                                        board[Integer.parseInt(coordI)][Integer.parseInt(coordJ)] = new Rook(new Coordinate(Integer.parseInt(coordI), Integer.parseInt(coordJ)), Flag.BLACK);
                                        break;
                                    case "Knight":
                                        board[Integer.parseInt(coordI)][Integer.parseInt(coordJ)] = new Knight(new Coordinate(Integer.parseInt(coordI), Integer.parseInt(coordJ)), Flag.BLACK);
                                        break;
                                    case "Bishop":
                                        board[Integer.parseInt(coordI)][Integer.parseInt(coordJ)] = new Bishop(new Coordinate(Integer.parseInt(coordI), Integer.parseInt(coordJ)), Flag.BLACK);
                                        break;
                                    case "Queen":
                                        board[Integer.parseInt(coordI)][Integer.parseInt(coordJ)] = new Queen(new Coordinate(Integer.parseInt(coordI), Integer.parseInt(coordJ)), Flag.BLACK);
                                        break;
                                    case "King":
                                        board[Integer.parseInt(coordI)][Integer.parseInt(coordJ)] = new King(new Coordinate(Integer.parseInt(coordI), Integer.parseInt(coordJ)), Flag.BLACK);
                                        break;
                                }
                                break;
                        }
                    }
                    line = br.readLine();
            }           
         }
            catch (FileNotFoundException e){
                e.getMessage();
            }
            catch (IOException e){
                e.getMessage();
            }
        return currentPlayer;
    }

}

