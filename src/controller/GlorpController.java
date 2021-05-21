package controller;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JFrame;

import model.Direction;
import model.Maze;
import model.Player;
import model.Skin;
import model.SkinType;
import view.GlorpGUI;

public class GlorpController implements KeyListener{
	// fields
	Maze myMaze;
	//TODO: remover ref to player piece? this should be in the maze
	Player myPlayer;
	GlorpGUI myWindow; 
	//TODO: these are temporary references to the door zones, may change later
	
	private final Set<Integer> myPressedKeys = new HashSet<Integer>();
	
	public GlorpController(){
		myMaze = Maze.getInstance();
		
		//hard ref to character for room panel work, should get player out of room
		myPlayer = new Player();
		
		myWindow = new GlorpGUI();
        myWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myWindow.setVisible(true);
        myWindow.setTitle("GLORP");
        myWindow.addKeyListener(this);
        
        myWindow.paintRoomPanel(myPlayer);
        
        //System.out.println(myMaze.canWin());
        
	}
	
	@Override
    public void keyPressed(KeyEvent e) {
		int k = e.getKeyCode();
        myPressedKeys.add(k);
        //TODO: get the room size for this
        Direction inDirection = Direction.generateDirection(myPressedKeys, myPlayer, 500);
        //System.out.println(inDirection);
        //System.out.println(myPlayer);
		myPlayer.move(inDirection);
		checkInteractions();
		//System.out.println(myPlayer);
        myWindow.paintRoomPanel(myPlayer); //TODO: this method call should pass a room
    }

    private void checkInteractions() {
		// TODO: fix this hardcoded door interaction
    	//east door zone
    	if(myPlayer.getCoordinate().getX() == 400 &&
    			myPlayer.getCoordinate().getY() >= 150 &&
    				myPlayer.getCoordinate().getY() <= 250) {
    		myPlayer.getCoordinate().setLocation(5, 200);
    	}
    	//west door zone
    	if(myPlayer.getCoordinate().getX() == 0 &&
    			myPlayer.getCoordinate().getY() >= 150 &&
    				myPlayer.getCoordinate().getY() <= 250) {
    		myPlayer.getCoordinate().setLocation(395, 200);
    	}
    	//north door zone
    	if(myPlayer.getCoordinate().getY() == 0 &&
    			myPlayer.getCoordinate().getX() >= 150 &&
    				myPlayer.getCoordinate().getX() <= 250) {
    		myPlayer.getCoordinate().setLocation(200, 395);
    	}
    	//south door zone
    	if(myPlayer.getCoordinate().getY() == 400 &&
    			myPlayer.getCoordinate().getX() >= 150 &&
    				myPlayer.getCoordinate().getX() <= 250) {
    		myPlayer.getCoordinate().setLocation(200, 5);
    	}
    		
    		
		
	}

	@Override
    public void keyReleased(KeyEvent e) {
		int inKey = e.getKeyCode();
		myPressedKeys.remove(inKey);
		
		if(myPressedKeys.isEmpty()) {
			myPlayer.setStride(0);
			myPlayer.setSkipFrame(false);
		}
		
		myWindow.paintRoomPanel(myPlayer);
    }

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub	
	}

	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}