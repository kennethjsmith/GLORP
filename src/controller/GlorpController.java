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
    
    // TODO: clean this up
    /**
     * A helper method, returns a boolean indicating if movement into a new room was successful.
     * @param theDirection a direction to move within the maze
     */
    private boolean attemptMapMove(Direction theDirection) {
        if(myMaze.canMove(theDirection, Maze.getInstance().getCurrRoom())) {
            myMaze.move(theDirection);
            return true;
        }else { 
//        	
//            // TODO: hardcoded unlocking of doors: This should all be handles by the riddle
//
//        	// ask them if they want to unlock the door
//        	// Scanner scan = new Scanner(System.in);
//        	// System.out.println("Woud you like to unlock this door?");
//        	// String input = scan.next();
//        	// if(input.toLowerCase().charAt(0) == 'y') {
//        		Room room = myMaze.getMyCurrentRoom();
//            	int row = room.getIndex().getRow();
//            	int col = room.getIndex().getCol();    	
//            	Room adjRoom = null;
//            	if(theDirection.getLabel() == "N" && row > 1) adjRoom = myMaze.getRoom(row - 1, col);
//            	else if(theDirection.getLabel() == "S" && row < myMaze.getLength()) adjRoom = myMaze.getRoom(row + 1, col);
//            	else if(theDirection.getLabel() == "E" && col < myMaze.getWidth()) adjRoom = myMaze.getRoom(row, col + 1);
//            	else if(theDirection.getLabel() == "W" && col > 1) adjRoom = myMaze.getRoom(row, col - 1);
//            	
//            	if(adjRoom != null) {
//            		Door adjDoor = myMaze.getSameDoor(room, adjRoom);
//            		adjDoor.setUnlocked();
//            		myMaze.move(theDirection);
//            		return true;
//            	}
//            	System.out.println("Sorry, you can't go that way :(");
//        	//}
        	return false;
        }
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