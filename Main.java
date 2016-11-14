import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.net.*;
import java.io.*;
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
    boolean isServer = true;
    String s = "";
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
        if (e.getSource() == startButton) {
            System.out.println("Start button 1 was pressed");
            JOptionPane.showMessageDialog(null,new String("Alert!!"));
            startGame();
        }
        else if (e.getSource() == connectButton)
            System.out.println("Connect button was pressed.");
        else if (e.getSource() == turnButton)
            System.out.println("turn button was pressed.");
        else
            System.out.println("End button was pressed");
    }

    public void startGame(){
        //Custom button text
        Object[] options = {"Server","Client"};
        int n = JOptionPane.showOptionDialog(null,
                "Would you like to be the client or server?",
                "Client Server",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options, options[0]);

        if(n == JOptionPane.YES_OPTION){
            isServer = true;
            serverCode();
        }
        else{
            isServer = false;
            s = (String)JOptionPane.showInputDialog(
                    null,
                    "What is the ip address of the server player?",
                    "Customized Dialog",
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    null,
                    "");

            //If a string was returned, say so.
            if ((s != null) && (s.length() > 0)) {
                
            }

            clientCode();
        }

    }

    /*
     * used Tammy's lab 1 tcp server code
     */
    public void serverCode()
    {
        String clientMessage, serverReply;

        try {
            // create socket connection to port 9999
            ServerSocket accepting = new ServerSocket(9999);

            // wait for clients to make connections
            while(true) {
                Socket connectionSocket = accepting.accept();
                BufferedReader clientIn = new BufferedReader(
                        new InputStreamReader(connectionSocket.getInputStream()));

                DataOutputStream clientOut = 
                    new DataOutputStream(connectionSocket.getOutputStream());

                // get message from client and captilatize the letters
                clientMessage = clientIn.readLine();
                JOptionPane.showMessageDialog(null,"Got the message: " + clientMessage);
                serverReply = "Ack for client at " + connectionSocket.getInetAddress() + ": " + clientMessage.toUpperCase() + "\n";

                // send client reply 
                clientOut.writeBytes(serverReply);
                connectionSocket.close();
            }
        }
        catch (Exception e) {
            
            JOptionPane.showMessageDialog(null,e.toString() + "\nAn error occurred while creating server socket or reading/writing data to/from client.");
            System.out.println("An error occurred while creating server socket or reading/writing data to/from client.");
        }
    }

    /*
     * tammy's client code lab 1
     */
    public void clientCode()
    {
        // create Strings to store message and reply
        String message, reply;

        try {
            // create reader to acquire text
            BufferedReader userIn = new BufferedReader(
                    new InputStreamReader(System.in));

            // get message from user
            message = userIn.readLine();

            // create socket connection on current machine at port 9999
            // and attach stream for writing data
            // connection is made to localhost (same machine), but could 
            // instead put in 32-bit or 128-bit unsigned number
            // for IP address or a string with the host name
            Socket clientSocket = new Socket(s, 9999);
            DataOutputStream serverOut = 
                new DataOutputStream(clientSocket.getOutputStream());

            // create stream for reading data from server
            BufferedReader serverIn = new BufferedReader(
                    new InputStreamReader(clientSocket.getInputStream()));

            // send data to server
            serverOut.writeBytes(message + '\n');
            // get data from server
            reply = serverIn.readLine();
            // print reply from server
            
            JOptionPane.showMessageDialog(null,"REPLY RECEIVED: " + reply);
            System.out.println("REPLY RECEIVED: " + reply);
            clientSocket.close();
        }
        catch(ConnectException e) {
            JOptionPane.showMessageDialog(null,"Error connecting to server. Check that server is running and accepting connections.");
            System.out.println("Error connecting to server. Check that server is running and accepting connections.");
        }
        catch(Exception e) {
             JOptionPane.showMessageDialog(null, "Exception occurred: " + e.getMessage());
            System.out.println("Exception occurred: " + e.getMessage());
        }
    }
    Button startButton, endButton, connectButton, turnButton;
    Button b0, b1, b2, b3, b4, b5, b6, b7, b8;

}
