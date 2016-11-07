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
      g.drawLine(100, 250, 350, 250); //horizontal bottm
      
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

}
