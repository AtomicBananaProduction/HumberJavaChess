package mbgj.assignment1;

import mbgj.assignment1.game.BoardManager;

public class Main {

    public static void main(String[] args) {
        
        //Board initializing
        BoardManager.init();                        
        System.out.println("Board initialized\n");
      
        for (int row = 0; row < 8; row++)
        {
          System.out.println("");
          System.out.println("---------------------------------");

          for (int column = 0; column < 8; column++)
          {
              System.out.print("| " + " " + " ");
          }           
        }
        System.out.println("");
        System.out.println("---------------------------------");
    
    }
}
