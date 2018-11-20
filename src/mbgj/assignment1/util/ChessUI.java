
package mbgj.assignment1.util;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import mbgj.assignment1.game.*;

public class ChessUI extends JFrame {
    private JPanel grid;
    private JButton[][] buttons;
    private JMenuBar menu;
    private JMenu game;
    private JMenuItem newGame, saveGame, loadGame;
    
    Flag currentFlag = Flag.WHITE;
    private int currentPlayer;
    
    public ChessUI(){
        initComponents();
    }
    
    private void initComponents(){
        //Init
        menu = new JMenuBar();
        game = new JMenu();
        newGame = new JMenuItem();
        saveGame = new JMenuItem();
        loadGame = new JMenuItem();
        grid = new JPanel();
        
        //Panel Setup
        grid.setLayout(new GridLayout(8, 8));
        grid.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)));
        
        //Menu Setup
        game.setText("Game");
        newGame.setText("New Game");
        newGame.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                BoardManager.init();
                chessSetup();
            }
        });
        game.add(newGame);
        saveGame.setText("Save Game");
        saveGame.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                currentPlayer = currentFlag == Flag.WHITE ? 0 : 1;
                BoardManager.saveGame(currentPlayer);
            }
        });
        game.add(saveGame);
        loadGame.setText("Load Game");
        loadGame.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                currentPlayer = BoardManager.loadGame("SavedGame.txt");
                currentFlag = currentPlayer == 0 ? Flag.WHITE : Flag.BLACK;
            }
        });
        game.add(loadGame);
        
        menu.add(game);
        
        setJMenuBar(menu);
        
        //Window Setup
        
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
                layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(grid, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(grid, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        
        pack();
        //End of Window Setup
        
        
    }
    
    private void chessSetup(){
        //Chess Board Setup
        buttons = new JButton[8][8]; //Does it accept multi-d arrays? 
        
        for (int i = 0; i < 8; i++) 
        {
            for(int j = 0; j < 8; j++)
            {
                buttons[i][j] = new JButton("");
                buttons[i][j].setFont(new Font("Verdana", Font.BOLD, 20));
                grid.add(buttons[i][j]);
                buttons[i][j].addActionListener(new ActionListener() 
                {
                    @Override
                    public void actionPerformed(ActionEvent ae) 
                    {
                        //Set the actions here.
                    }
                });
            }
        }
    }
}
