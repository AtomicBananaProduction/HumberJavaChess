package mbgj.assignment1;

import java.util.Scanner;

import mbgj.assignment1.game.*;
import mbgj.assignment1.util.Coordinate;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Flag currentFlag = Flag.WHITE;
        
        //Board initializing
        BoardManager.init();
        System.out.println("Board initialized\n");
        
        //Board Render (First Time)        
        BoardManager.RenderBoard();
        
        //Input play
        //This part is still in development, this placeholder is just a test to see if pieces are moving
        System.out.println("Enter your move, start with the coordinates the piece you want to move and where you want it to move.\n First numbers for row and column of origin and the next ones for destination (ex: 1 4 2 4): \n");
        
        while(true){
            String firstInput = sc.next();
            if (firstInput.equals("end")) {
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
                currentFlag = Piece.nFlag(currentFlag);
                BoardManager.RenderBoard();
            }
            else {
                System.out.println("Piece not moved!");
            }
            
        }
        
    
    }
}
