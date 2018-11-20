package mbgj.assignment1;

import java.util.Scanner;
import javax.swing.*;
import mbgj.assignment1.game.*;
import mbgj.assignment1.util.ChessUI;
import mbgj.assignment1.util.Coordinate;

public class Main {

    public static void main(String[] args) {
        
        //Debug/Test
        ChessUI frameWindow = new ChessUI();
        frameWindow.setVisible(true);
        frameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameWindow.setBounds(200, 200, 600, 600);

        //End of Debug
        
        Scanner sc = new Scanner(System.in);
        Flag currentFlag = Flag.WHITE;
        int currentPlayer;
        
        //Start Game asking if want to load a Saved Game
        System.out.println("Welcome to the Chess Game. Do you want to load a game or start a new one?\n");
        System.out.println("When quitting, type end to quit type save to save and quit!\n");
        System.out.println("Type load or new , for whichever option");
 
        String option = sc.next();
        
        switch(option){
            case "load":
                //Assigns the current player to be equal to the last player's turn when the game was saved.
                currentPlayer = BoardManager.loadGame("SavedGame.txt");
                currentFlag = currentPlayer == 0 ? Flag.WHITE : Flag.BLACK;
                System.out.println("Board re-initialized\n");
                break;
            case "new":
                //Assigns the board to initialize normally and the current player will be WHITE.
                BoardManager.init();
                System.out.println("Board initialized\n");
                break;
        }        
        
        //Board Render (First Time)        
        BoardManager.RenderBoard();
        
        //Input play
        //This part is still in development, this placeholder is just a test to see if pieces are moving
        System.out.println("Enter your move, start with the coordinates the piece you want to move and where you want it to move.\n First numbers for row and column of origin and the next ones for destination (ex: 1 4 2 4): \n");
        
        while(true){
            String firstInput = sc.next();
            if (firstInput.equals("save")) {
                currentPlayer = currentFlag == Flag.WHITE ? 0 : 1;
                BoardManager.saveGame(currentPlayer);
                break;
            } else if (firstInput.equals("end"))
            {
                break;
            }
            
            int coordinateX = Integer.parseInt(firstInput);
            int coordinateY = sc.nextInt();
            int destinationX = sc.nextInt();
            int destinationY = sc.nextInt();
            
            Coordinate currentCoordinate = new Coordinate(coordinateX, coordinateY);
            Coordinate destinationCoordinate = new Coordinate(destinationX, destinationY);
            PiecePack currentPiece = BoardManager.getPieceAt(currentCoordinate);

            // If player try enter dumb coordinate
            if (currentPiece.id == PiecePackId.EMPTY) {
                System.out.println("Empty location!");
                System.out.println();
                continue;
            } else if (currentPiece.id == PiecePackId.OUT_OF_BOUND) {
                System.out.println("Location outside board!");
                System.out.println();
                continue;
            }

            // Wrong player
            if (currentPiece.piece.getFlag() != currentFlag) {
                String id = null;

                if (currentFlag == Flag.WHITE) {
                    id = "White";
                } else {
                    id = "Black";
                }

                System.out.println("Wrong player! The current player should be using " + id + " pieces!");
                continue;
            }

            // Debug print all moves
            System.out.println("All available moves:");
            currentPiece.piece.calcMoves();
            currentPiece.piece.printMoves();
            System.out.println();
            
            if(BoardManager.requestMove(currentPiece.piece, destinationCoordinate)) {

                // Test for pawn's promotion
                if ((destinationCoordinate.row == 0 || destinationCoordinate.row == 7) && currentPiece.piece.name.equals("Pawn")) {
                    System.out.println("Your pawn has a promotion!!!");
                    System.out.println("0 - Knight");
                    System.out.println("1 - Bishop");
                    System.out.println("2 - Rook");
                    System.out.println("3 - Queen");

                    int choice = sc.nextInt();
                    BoardManager.promotePawn(currentPiece.piece, choice);
                }

                System.out.println("Piece moved!");
                currentFlag = Piece.nFlag(currentFlag);  //This changes the current player turn
                BoardManager.RenderBoard();
            }
            else {
                System.out.println("Piece not moved!");
            }
            
        }
     
    }
}
