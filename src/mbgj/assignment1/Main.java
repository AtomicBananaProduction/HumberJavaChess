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
        System.out.println("Enter your move, start with the coordinates and the name of the piece you want to move as well as the flag.\n First number for row and then second number for column  (ex: Pawn White 1 4): \n");
        
        String pieceName;
        String flagColor;
        int coordinateX;
        int coordinateY;
        
        String input = sc.nextLine();
        String[] value = input.split(" ");
    
    }
}
