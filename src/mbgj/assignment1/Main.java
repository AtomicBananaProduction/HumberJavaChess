package mbgj.assignment1;

import java.util.Scanner;
import mbgj.assignment1.game.BoardManager;
import mbgj.assignment1.game.Piece;
import mbgj.assignment1.game.PiecePack;
import mbgj.assignment1.game.PiecePackId;
import mbgj.assignment1.util.Coordinate;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        
        
        //Board initializing
        BoardManager.init();
        System.out.println("Board initialized\n");
        
        //Board Render (First Time)        
        BoardManager.RenderBoard();
        
        //Input play
        //This part is still in development, this placeholder is just a test to see if pieces are moving
        System.out.println("Enter your move, start with the coordinates the piece you want to move and where you want it to move.\n First numbers for row and column of origin and the next ones for destination (ex: 1 4 2 4): \n");
        
        while(!sc.next().equals("end")){
            int coordinateX = sc.nextInt();
            int coordinateY = sc.nextInt();
            int destinationX = sc.nextInt();
            int destinationY = sc.nextInt();
            
            Coordinate currentCoordinate = new Coordinate(coordinateX, coordinateY);
            Coordinate destinationCoordinate = new Coordinate(destinationX, destinationY);
            PiecePack currentPiece = BoardManager.getPieceAt(currentCoordinate);
            
            if(BoardManager.requestMove(currentPiece.piece, destinationCoordinate)){
                System.out.println("Piece moved!");
                BoardManager.RenderBoard();
            }
            else {
                System.out.println("Piece not moved!");
            }
            
        }
        
    
    }
}
