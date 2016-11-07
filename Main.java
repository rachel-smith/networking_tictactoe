import java.applet.*;
import java.awt.*;
import java.awt.event.*;
/**
 * Write a description of class Main here.
 * 
 * @author Rachel Smith, Scott Rowland
 * @version v1 10/28/16
 */
public class Main extends Applet implements ActionListener
{
    int X_PIECE = 1;
    int O_PIECE = 2;
    int[] board = {0,X_PIECE,O_PIECE,X_PIECE,X_PIECE,X_PIECE,O_PIECE,O_PIECE,O_PIECE};
    int left_X = 100;
    int middle_X = 180;
    int right_X = 280;
    int[] xCoords = {left_X, middle_X, right_X, left_X, middle_X, right_X,left_X, middle_X, right_X};
    int top_Y = 125;
    int middle_Y = 195;
    int bottom_Y = 265;
    int[] yCoords = {top_Y, top_Y, top_Y, middle_Y, middle_Y, middle_Y, bottom_Y, bottom_Y, bottom_Y};
    int buttonWidth = 40;
    int buttonHeight = 40;
    boolean b0Displayed = false;
    public void init() {
        startButton = new Button("Start Game");
        add(startButton);
        startButton.addActionListener(this);

        endButton = new Button("End Game");
        add(endButton);
        endButton.addActionListener(this);
        
        connectButton = new Button("Connect");
        add(connectButton);
        connectButton.addActionListener(this);
        
        turnButton = new Button("Take Turn");
        add(turnButton);
        turnButton.addActionListener(this);
    }
    
    public void paint (Graphics g) {
      //Draw appropriate titles.
      g.drawString ("Networked Tic-Tac-Toe!", 135, 75);
      g.drawString("By Rachel Smith and Scott Rowland", 100, 100);
      
      //Draw game board.
      g.drawLine(175, 125, 175, 300); //vertical left
      g.drawLine(275, 125, 275, 300); //vertical right
      g.drawLine(100, 175, 350, 175); //horizontal top
      g.drawLine(100, 250, 350, 250); //horizontal bottom
      
      //Draw buttons on game board appropriately.
      for (int i = 0; i < board.length; i++){
          if(board[i] == 0){
              //draw plain button
              //if(i==0){
                  if(!b0Displayed){
                      b0 = new Button("");
                      b0.setLocation(xCoords[i], yCoords[i]);
                      b0.setSize(buttonWidth, buttonHeight);
                      b0.setBackground(Color.green);
                      b0Displayed = true;
                      add(b0);
                 }
              //}
              
          }
          else if (board[i] == X_PIECE){
              //draw X
              g.drawString("X", xCoords[i], yCoords[i]);
          }
          else if (board[i]== O_PIECE){
              //draw O
              g.drawString("O", xCoords[i], yCoords[i]);
          }
      }
      
    }
    

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == startButton) 
            System.out.println("Start button 1 was pressed");
        else if (e.getSource() == connectButton)
            System.out.println("Connect button was pressed.");
        else if (e.getSource() == turnButton)
            System.out.println("turn button was pressed.");
        else
            System.out.println("End button was pressed");
    }

    Button startButton, endButton, connectButton, turnButton;
    Button b0, b1, b2, b3, b4, b5, b6, b7, b8;

}
