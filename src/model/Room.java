package model;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

import javax.swing.ImageIcon;

import view.GameIcon;

/**
 * A room can has 4 doors an item, and contains flags indicating 
 * player presence and whether or not the room has been visited.
 * @author
 * @version
 */
//TODO: create subclasses (start and end rooms)
public class Room {
    private static int MAX_DOORS = 4;
    private Door[] myDoors; // for now... 4 doors each 
	private ArrayList<Item> myItems; // array list, no max GamePieces
	private Player myPlayer;
    // why does room contain the player? 
	
	private GameIcon myLargeIcon;
	private GameIcon mySmallIcon;
	private final RoomIndex myIndex;
	
	
	private final static GameIcon[] FLOORS = loadFloors();
	private final static GameIcon MAP_ICON = new GameIcon("src/icons/room_for_map.png");
	private final static Random RAND = new Random();
	
	//Map<Door, Room> myDoors= new TreeMap<>();
	//private boolean containsPlayer; //will be tracked by map (current room) 
	//private boolean visitedFlag; // will be tracked by map? 
	
    public Room() {
        myItems = new ArrayList<Item>();
        myLargeIcon = null; 
        mySmallIcon = null;
        myIndex = null;
        myPlayer = null;
    }
    

	public Room(int theRow, int theCol) { // how will rooms get their icons? And riddles? 
	    myItems = new ArrayList<Item>();
	    setRandomFloor(); 
	    mySmallIcon = MAP_ICON; 
	    myIndex = new RoomIndex(theRow, theCol);
	    myPlayer = null;
	}
	   public Room(GameIcon theLargeIcon, GameIcon theSmallIcon, int theRow, int theCol) { // how will rooms get their icons? And riddles? 
	        myItems = new ArrayList<Item>();
	        myLargeIcon = theLargeIcon; 
	        mySmallIcon = theSmallIcon; 
	        //myIndex = new RoomIndex(0,0);
	        myIndex = new RoomIndex(theRow, theCol);
	        myPlayer = null;
	    }

	/**
     * If this rooms doors have not been initailized already,
     * sets this rooms door array to the passed in door array.
     * @param Door[] theDoors
     */
    void setDoors(Door[] theDoors) {
        if(myDoors == null) {
            myDoors = theDoors;
        }
    }
    
    Door[] getDoors() throws NullPointerException{
        if(myDoors == null) { 
            //throw new NullPointerException("This room has no doors.");
            return new Door[MAX_DOORS];
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
	


	/**
	 * Place an item in this room
	 * @param myItem the myItem to set
	 */
	public void addItem(Item theGamePiece) throws NullPointerException{
	    if(theGamePiece == null) {
	        throw new NullPointerException("GamePiece cannot be null.");
	    }
		myItems.add(theGamePiece);
	}
	
	/**
	 * Place the player in this room
	 * @param myItem the myItem to set
	 */
	public void setPlayer(Player thePlayer) {
		myPlayer = thePlayer;
	}
	
	public Player getPlayer() {
		return Objects.requireNonNull(myPlayer, "No player has been set in this room.");
	}
	
//	public void setLargeIcon(GameIcon theLargeIcon) {
//		Objects.requireNonNull(theLargeIcon);
//		myLargeIcon = theLargeIcon;
//	}
	
	public GameIcon getLargeIcon() {
	    return myLargeIcon;
	}
	
	public void setLargeIcon(GameIcon theLargeIcon) {
	    Objects.requireNonNull(theLargeIcon);
	    myLargeIcon = theLargeIcon;
	}
	   
	public void setSmallIcon(GameIcon theSmallIcon) {
		Objects.requireNonNull(theSmallIcon);
		mySmallIcon = theSmallIcon;
	}
	
	
	public ImageIcon getSmallIcon() {
        return mySmallIcon;
    }
	
	public RoomIndex getMyIndex() {
		return myIndex;
	}
	
//	public Point[] getDoorCoordinates() {
//	    Point[] inCoordinates = new Point[MAX_DOORS];
//	    for(int i = 0; i < MAX_DOORS; i++) {
//	        inCoordinates[i] = myDoors[i].getCoordinate(); //will any of the doors ever be null? 
//	    }
//	    return inCoordinates;
//	}
	private static GameIcon[] loadFloors() {
		GameIcon[] floorIcons = new GameIcon[12];
		for (int i = 1; i <= 12; i++) { 
		    String inFileName = ("src/icons/floor" + i + ".png");
		    floorIcons[i-1] = new GameIcon(inFileName);
		    floorIcons[i-1].resize(500);
		}
		return floorIcons;
	}
	
	private void setRandomFloor() {
		myLargeIcon = FLOORS[RAND.nextInt(12)];
	}
}