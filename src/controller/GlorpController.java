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
import model.Maze;
import model.Player;
import model.Room;
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
		myPlayer = myMaze.getPlayer();
		
		myWindow = new GlorpGUI();

        myWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myWindow.setVisible(true);
        myWindow.setTitle("GLORP");
        myWindow.addKeyListener(this);
        
        myWindow.repaint();
    }
	
	@Override
    public void keyPressed(KeyEvent e) {
		int k = e.getKeyCode();
        myPressedKeys.add(k);
        //TODO: get the room size for this
        Direction inDirection = Direction.generateDirection(myPressedKeys);
        
        Direction validDirection = myMaze.getCurrRoom().validateDirection(myPlayer, inDirection);
        myPlayer.move(validDirection);
		System.out.println(myPlayer);
        checkInteractions();
        myWindow.repaint();
    }

    private void checkInteractions() {
		// TODO: fix this hardcoded door interaction
    	//NOTES:
    	//5 and 395 = Room.getSize()-Skin.getSize()-Player.getSpeed(): the plane through the door
    	//150 and 250 = the sides of the door
    	//^^ if within both, INTERACT
    	
    	//10 = 2x Player.getSpeed() (1 step away from reinteracting with door after going through)
    	//390 == roomsize-skinsize-2xspeed (1 step away from reinteracting with door after going through)
    	//150 and 250 = the sides of the door area
    	
    	//200 an x or y coordinate to place player in center of other door after going through
    	
    	//east door zone
    	if(myPlayer.getCoordinate().getX() == 395 &&
    			myPlayer.getCoordinate().getY() >= 150 &&
    				myPlayer.getCoordinate().getY() <= 250) {
    	    if(attemptMapMove(Direction.EAST)) {
    	        myPlayer.getCoordinate().setLocation(10, 200);
    	    }	
    	}
    	//west door zone
    	else if(myPlayer.getCoordinate().getX() == 5 &&
    			myPlayer.getCoordinate().getY() >= 150 &&
    				myPlayer.getCoordinate().getY() <= 250) {
    	    if(attemptMapMove(Direction.WEST)) {
    	        myPlayer.getCoordinate().setLocation(390, 200);
    	    }
    		
    	}
    	//north door zone
    	else if(myPlayer.getCoordinate().getY() == 5 &&
    			myPlayer.getCoordinate().getX() >= 150 &&
    				myPlayer.getCoordinate().getX() <= 250) {
    	    if(attemptMapMove(Direction.NORTH)) {
    	        myPlayer.getCoordinate().setLocation(200, 390);
    	    }
    		

    	}
    	//south door zone
    	else if(myPlayer.getCoordinate().getY() == 395 &&
    			myPlayer.getCoordinate().getX() >= 150 &&
    				myPlayer.getCoordinate().getX() <= 250) {
    	    if(attemptMapMove(Direction.SOUTH)) {
    	        myPlayer.getCoordinate().setLocation(200, 10);
    	    }
    	}
	}
    /*
     * Returns a success boolean
     */
    private boolean attemptMapMove(Direction theDirection) {
        if(myMaze.canMove(theDirection, Maze.getInstance().getCurrRoom())) {
            myMaze.move(theDirection);
            return true;
        }else { 
        	
            // TODO hardcoded unlocking of doors: This should all be handles by the riddle

        	// ask them if they want to unlock the door
        	//Scanner scan = new Scanner(System.in);
        	//System.out.println("Woud you like to unlock this door?");
        	//String input = scan.next();
        	//if(input.toLowerCase().charAt(0) == 'y') {
        		Room room = myMaze.getMyCurrentRoom();
            	int row = room.getIndex().getRow();
            	int col = room.getIndex().getCol();    	
            	Room adjRoom = null;
            	if(theDirection.getLabel() == "N" && row > 1) adjRoom = myMaze.getRoom(row - 1, col);
            	else if(theDirection.getLabel() == "S" && row < myMaze.getLength()) adjRoom = myMaze.getRoom(row + 1, col);
            	else if(theDirection.getLabel() == "E" && col < myMaze.getWidth()) adjRoom = myMaze.getRoom(row, col + 1);
            	else if(theDirection.getLabel() == "W" && col > 1) adjRoom = myMaze.getRoom(row, col - 1);
            	
            	if(adjRoom != null) {
            		Door adjDoor = myMaze.getSameDoor(room, adjRoom);
            		adjDoor.setUnlocked();
            		myMaze.move(theDirection);
            		return true;
            	}
            	System.out.println("Sorry, you can't go that way :(");
        	//}
        	return false;
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
		myWindow.repaint();
	}

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub	
	}

	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}