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
	private int MAX_DOORS = 4;
	
    private Door[] myDoors; // for now... 4 doors each 
	//private ArrayList<Piece> myPieces; // array list, no max pieces
	private final ImageIcon myLargeIcon;
	private final ImageIcon mySmallIcon;
//ADDED 1 Line below
	private boolean isWinRoom;
	
	//Map<Door, Room> myDoors= new TreeMap<>();
	//private boolean containsPlayer; //will be tracked by map (current room) 
	//private boolean visitedFlag; // will be tracked by map? 
	
    public Room() {
        //myPieces = new ArrayList<Piece>();
        myDoors = new Door[MAX_DOORS];
        
// CHANGED both image icons below from null to room_for_map
        myLargeIcon = new ImageIcon("src/icons/room_for_map.png"); 
        mySmallIcon = new ImageIcon("src/icons/room_for_map.png"); ;
//ADDED 1 Line below
        isWinRoom = false;

// CHANGE BELOW LINE TO THE FOLLOWING LINE:
        //createDoors(getRiddles());
        createDoors();
    }
    
	public Room(ImageIcon theLargeIcon, ImageIcon theSmallIcon) { // how will rooms get their icons? And riddles? 
	    //myPieces = new ArrayList<Piece>();
	    myDoors = new Door[MAX_DOORS];
	    myLargeIcon = theLargeIcon; 
	    mySmallIcon = theSmallIcon;
// ADDED 1 line below
        isWinRoom = false;
        
// CHANGE BELOW LINE TO THE FOLLOWING LINE
	    //createDoors(getRiddles()); 
        createDoors();
	}
	
	/*
	 * Retrieves Riddles from Game 
	 */
	private Riddle[] getRiddles() {
	    // scanner? 
        // Game Model or Maze has file name for data base of riddles 
        // When Room is created, room prompts the Maze MAX_DOORS times for number of needed riddles (SCANNER)
        // Maze reads 
        // Injector type stuff? - riddles are a dependencie? 
	    return new Riddle[MAX_DOORS];
	}
	
	/*
	 * Can rewrite to enable more doors
	 */
// CHANGED THE METHOD SIGNATURE
	//private void createDoors(Riddle[] theRiddles) {
	private void createDoors() {
		// TODO Auto-generated method stub
	    // create doors with coordinates relative to rooms 
		
// CREATED EVERYTHING IN THIS METHOD BELOW:
		for(int i = 0; i < myDoors.length; i++) {
			Door currDoor = new Door();
			myDoors[i] = currDoor;
		}
		
	}
	
	public boolean isNorthDoorBlocked() {
		if(myDoors[0].isBlocked()) return true; 
		else return false;
	}
	
	public boolean isEastDoorBlocked() {
		if(myDoors[1].isBlocked()) return true; 
		else return false;
	}
	
	public boolean isSouthDoorBlocked() {
		if(myDoors[2].isBlocked()) return true; 
		else return false;
	}
	
	public boolean isWestDoorBlocked() {
		if(myDoors[3].isBlocked()) return true; 
		else return false;
	}

//	/**
//	 * Get the item from this room.
//	 * @return the myItem
//	 */
//	public Piece removePiece(Point theCoordinates) throws IllegalArgumentException{ // pass in player coordinates? or player object?
//	    //if not passing in piece itself, create a dummy piece 
//	    Piece inDummy = new Alien(); // doesnt really matter what implementation the piece is
//	    
//	    for(Piece p : myPieces) {
//	        if (((Alien) p).compareTo(inDummy) == 0) { // will need to 
//	            inDummy = p;
//	        }
//	    }
//	    
//	    if(!(myPieces.remove(inDummy))) { // if dummy was set to p, it will contain it and skip the below statement
//	        throw new IllegalArgumentException("Error: no piece at these coordinates.");
//	    }
//	    
//		return inDummy; 
//	}
	
	   /**
     * Get the item from this room.
     * @return Boolean indicator if the add was a success
     */
    public Boolean addCharacter(Player theCharacter, Door theUnlockedDoor) throws IllegalArgumentException{ // pass in player coordinates? or player object?
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

//	/**
//	 * Place an item in this room
//	 * @param myItem the myItem to set
//	 */
//	public void addPiece(Piece thePiece) throws NullPointerException{
//	    if(thePiece == null) {
//	        throw new NullPointerException("Piece cannot be null.");
//	    }
//		myPieces.add(thePiece);
//	}
	
	public ImageIcon getLargeIcon() {
	    return myLargeIcon;
	}
	
	public ImageIcon getSmallIcon() {
        return mySmallIcon;
    }
	
	public Point[] getDoorCoordinates() {
	    Point[] inCoordinates = new Point[MAX_DOORS];
	    for(int i = 0; i < MAX_DOORS; i++) {
	        inCoordinates[i] = myDoors[i].getCoordinate(); //will any of the doors ever be null? 
	    }
	    return inCoordinates;
	}
	
	
// ADDED METHOD BELOW
	public void setWinRoom() {
		isWinRoom = true;
	}
	
// ADDED METHOD BELOW
	public boolean isWinRoom() {
		return isWinRoom;
	}
	
}
