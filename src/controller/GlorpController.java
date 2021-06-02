package controller;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import javax.swing.JFrame;

import model.Direction;
import model.Door;
import model.Item;
import model.Maze;
import model.Player;
import model.Riddle;
import model.Room;
import model.Skin;
import model.SkinType;
import view.GlorpGUI;

/**
 * The controller, mediates the maze model and GUI. 
 * @authors Heather Finch, Katelynn Oleson, Ken Smith
 * @version
 */
public class GlorpController implements KeyListener{
	// fields
	private Maze myMaze;
	// TODO: remover ref to player piece ? can get from maze
	private Player myPlayer;
	private GlorpGUI myWindow; 
	private final Set<Integer> myPressedKeys = new HashSet<Integer>();
	
	/**
	 * Default constructor for GlorpController
	 */
	public GlorpController(){
		myMaze = Maze.getInstance();
		myPlayer = myMaze.getPlayer();		
		myWindow = new GlorpGUI();
        myWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myWindow.setVisible(true);
        myWindow.setTitle("GLORP");
        myWindow.addKeyListener(this);    
        myWindow.repaint();
    }
	
	/**
	 * Adds the pressed key's KeyCode to the set of pressed keys. Generates and validates a direction from the set,
	 * then moves the player.
	 */
	@Override
    public void keyPressed(KeyEvent e) {
		int k = e.getKeyCode();
        myPressedKeys.add(k);
        
        Direction inDirection = Direction.generateDirection(myPressedKeys);
        Direction validDirection = null;
		
        try {
			validDirection = myMaze.getCurrRoom().validateDirection(myPlayer, inDirection);
			myPlayer.move(validDirection);
		} catch (CloneNotSupportedException e1) {
			e1.printStackTrace();
		}
        checkInteractions();
        myWindow.repaint();
    }

	/**
	 * A helper method, checks for item and door interactions after a key event.
	 */
	// TODO: split into multiple methods? deal with duplicate code
    private void checkInteractions() {
    	//check items
    	Item inItem = myMaze.getCurrRoom().getItem();
    	if(inItem != null && myPlayer.getIconArea().intersects(inItem.getIconArea())) {
    		myPlayer.getInventory().add(myMaze.getCurrRoom().getItem());
    		myMaze.getCurrRoom().setItem(null);
    		myMaze.getCurrRoom().setCurrentRoom(true);
    		myWindow.updateItemPanel(myPlayer);
    	}
    	// check doors
    	// east door zone
    	if(myPlayer.getIconArea().intersects(Room.getEastDoorZone())) {
    	    if(attemptMapMove(Direction.EAST)) {
    	        myPlayer.getCoordinate().setLocation(20, 200);
    	        myPlayer.updateRectangles();
    	    }	
    	}
    	// west door zone
    	else if(myPlayer.getIconArea().intersects(Room.getWestDoorZone())) {
    	    if(attemptMapMove(Direction.WEST)) {
    	        myPlayer.getCoordinate().setLocation(380, 200);
    	        myPlayer.updateRectangles();
    	    }	
    	}
    	// north door zone
    	else if(myPlayer.getIconArea().intersects(Room.getNorthDoorZone())) {
    	    if(attemptMapMove(Direction.NORTH)) {
    	        myPlayer.getCoordinate().setLocation(200, 380);
    	        myPlayer.updateRectangles();
    	    }
    	}
    	// south door zone
    	else if(myPlayer.getIconArea().intersects(Room.getSouthDoorZone())) {
    	    if(attemptMapMove(Direction.SOUTH)) {
    	        myPlayer.getCoordinate().setLocation(200, 20);
    	        myPlayer.updateRectangles();
    	    }
    	}
	}
    
    // TODO: clean this up. Should some of this go in maze????
    /**
     * A helper method, returns a boolean indicating if movement into a new room was successful.
     * @param theDirection a direction to move within the maze
     */
    private boolean attemptMapMove(Direction theDirection) {
        if(myMaze.isValidMove(theDirection, myMaze.getCurrRoom())) { // If the move is valid
        	
        	// Grab the relevant Door
        	Door currDoor = myMaze.getCurrRoom().getDoors().get(theDirection);
        	
        	// If the door is unlocked, move that direction
        	if(currDoor.isUnlocked()) {
        		myMaze.move(theDirection);
        		return true;
        	}
        	
        	// The door was locked. Give user the riddle
        	Riddle currRiddle = myMaze.getCurrRoom().getDoors().get(theDirection).getMyRiddle();
        	System.out.println(currRiddle.getMyQuestion());
        	Scanner scan = new Scanner(System.in);
        	String input = scan.next();
        	
        	// If the riddle answer is correct, unlock door and move that direction
        	if(currRiddle.verifyAnswer(input)) {
        		currDoor.setUnlocked();
                myMaze.move(theDirection);
                return true;
        	} else { // Riddle answer was not correct, block the door
        		currDoor.setBlocked();
        		return false;
        	}
        } else return false;  // move was not valid 
    }
    
    /**
     * Removes the KeyCode from the set of pressed keys. Sets stride to 0 if no keys are pressed.
     */
	@Override
    public void keyReleased(KeyEvent e) {
		int inKey = e.getKeyCode();
		myPressedKeys.remove(inKey);
		
		if(myPressedKeys.isEmpty()) {
			myPlayer.setStride(0);
			myPlayer.setSkipFrame(false);
		}
		myWindow.repaint();
	}

	public void actionPerformed(ActionEvent e) {
		// TODO: Auto-generated method stub	
	}

	public void keyTyped(KeyEvent e) {
		// TODO: Auto-generated method stub
		
	}

}