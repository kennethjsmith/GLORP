package model;

import java.awt.Point;
import java.util.ArrayList;

import javax.swing.ImageIcon;

/**
 * A room can has 4 doors an item, and contains flags indicating 
 * player presence and whether or not the room has been visited.
 * @author
 * @version
 */
//TODO: create subclasses (start and end rooms)
public class Room {
    private Door[] myDoors; // for now... 4 doors each 
	private ArrayList<GamePiece> myGamePieces; // array list, no max GamePieces
	private final ImageIcon myLargeIcon;
	private final ImageIcon mySmallIcon;
	private boolean isWinRoom;
	
	//Map<Door, Room> myDoors= new TreeMap<>();
	//private boolean containsPlayer; //will be tracked by map (current room) 
	//private boolean visitedFlag; // will be tracked by map? 
	
    public Room() {
        myGamePieces = new ArrayList<GamePiece>();
        myLargeIcon = null; 
        mySmallIcon = null;
    }
    
	public Room(ImageIcon theLargeIcon, ImageIcon theSmallIcon) { // how will rooms get their icons? And riddles? 
	    myGamePieces = new ArrayList<GamePiece>();
	    myLargeIcon = theLargeIcon; 
	    mySmallIcon = theSmallIcon; 
	}
	
	/**
     * If this rooms doors have not been initailized already,
     * sets this rooms door array to the passed in door array.
     * @param Door[] theDoors
     */
    public void setDoors(Door[] theDoors) {
        if(myDoors == null) {
            myDoors = theDoors;
        }
    }
    
    public Door[] getDoors() throws NullPointerException{
        if(myDoors == null) { 
            throw new NullPointerException("This room has no doors.");
        }else
            return myDoors;
    }
    
	
//	/*
//	 * Retrieves Riddles from Game 
//	 */
//	private Riddle[] getRiddles() {
//	    // scanner? 
//        // Game Model or Maze has file name for data base of riddles 
//        // When Room is created, room prompts the Maze MAX_DOORS times for number of needed riddles (SCANNER)
//        // Maze reads 
//        // Injector type stuff? - riddles are a dependencie? 
//	    return new Riddle[MAX_DOORS];
//	}
	
	

//	/**
//	 * Get the item from this room.
//	 * @return the myItem
//	 */
//	public GamePiece removeGamePiece(Point theCoordinates) throws IllegalArgumentException{ // pass in player coordinates? or player object?
//	    //if not passing in GamePiece itself, create a dummy GamePiece 
//	    GamePiece inDummy = new Player(); // doesnt really matter what implementation the GamePiece is
//	    
//	    for(GamePiece p : myGamePieces) {
//	        if (((Player) p).compareTo(inDummy) == 0) { // will need to 
//	            inDummy = p;
//	        }
//	    }
//	    
//	    if(!(myGamePieces.remove(inDummy))) { // if dummy was set to p, it will contain it and skip the below statement
//	        throw new IllegalArgumentException("Error: no GamePiece at these coordinates.");
//	    }
//	    
//		return inDummy; 
//	}
	
	   /**
     * Get the item from this room.
     * @return Boolean indicator if the add was a success
     */
    public Boolean addPlayer(Player thePlayer, Door theUnlockedDoor) throws IllegalArgumentException{ // pass in player coordinates? or player object?
        Boolean inSuccess = false;
        if(theUnlockedDoor.isUnlocked()) { //first check if unlockedDoor is even unlocked
            for(Door d : myDoors) {
                if(theUnlockedDoor.equals(d)) {
                    
                    inSuccess = true;
                }
            }
        }
        return inSuccess;
       
    }

	/**
	 * Place an item in this room
	 * @param myItem the myItem to set
	 */
	public void addGamePiece(GamePiece theGamePiece) throws NullPointerException{
	    if(theGamePiece == null) {
	        throw new NullPointerException("GamePiece cannot be null.");
	    }
		myGamePieces.add(theGamePiece);
	}
	
	public ImageIcon getLargeIcon() {
	    return null;
	}
	
	public ImageIcon getSmallIcon() {
        return null;
    }
	
	// ADDED METHOD BELOW
	public void setWinRoom() {
		isWinRoom = true;
	}

	// ADDED METHOD BELOW
	public boolean isWinRoom() {
		return isWinRoom;
	}
	
//	public Point[] getDoorCoordinates() {
//	    Point[] inCoordinates = new Point[MAX_DOORS];
//	    for(int i = 0; i < MAX_DOORS; i++) {
//	        inCoordinates[i] = myDoors[i].getCoordinate(); //will any of the doors ever be null? 
//	    }
//	    return inCoordinates;
//	}
	
}
