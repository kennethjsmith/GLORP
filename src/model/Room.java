package model;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
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
    private HashMap<Direction, Door> myDoorMap;
	private ArrayList<Item> myItems; // array list, no max GamePieces
	private Player myPlayer;
	private GameIcon myLargeIcon;
	private GameIcon mySmallIcon;
	private boolean isCurrentRoom;
	private boolean isWinRoom;
	private boolean isVisited;

	private final RoomIndex myIndex;
	
	
	
	//TODO: should these be enumerated types? might help get rid of boolean fields upon refactor
	private static final Carpet CARPETS = new Carpet();
	private static final int MAP_ICON_SIZE = 36;
	private static final GameIcon MAP_ICON = new GameIcon("src/icons/map_icon.png", MAP_ICON_SIZE);
	private static final GameIcon MAP_ICON_CURRENT = new GameIcon("src/icons/map_icon_current.png", MAP_ICON_SIZE);
	private static final GameIcon MAP_ICON_WIN = new GameIcon("src/icons/map_icon_win.png", MAP_ICON_SIZE);
	private static final GameIcon MAP_ICON_CURRENT_WIN = new GameIcon("src/icons/map_icon_current_win.png", MAP_ICON_SIZE);
	
	private final static Random RAND = new Random();
	
	
	//Map<Door, Room> myDoors= new TreeMap<>();
	//private boolean containsPlayer; //will be tracked by map (current room) 
	//private boolean visitedFlag; // will be tracked by map? 
	
    public Room() {
    	myDoorMap = null;
    	myItems = new ArrayList<Item>();
        myLargeIcon = null; 
        mySmallIcon = null;
        myIndex = null;
        myPlayer = null;
        isVisited = false;
    }

	public Room(int theRow, int theCol) { // how will rooms get their riddles? 
		myDoorMap = null;
		myItems = new ArrayList<Item>();
	    setRandomFloor(); 
	    mySmallIcon = MAP_ICON; 
	    myIndex = new RoomIndex(theRow, theCol);
	    myPlayer = null;
	    isVisited = false;
	}
	
    
    void setDoors(HashMap<Direction, Door> theDoors) {
    	myDoorMap = theDoors;
    }
    

    
    public HashMap<Direction, Door> getDoors() {
    	return myDoorMap;
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
		isVisited = true;
	}
	
	public Player getPlayer() {
		return Objects.requireNonNull(myPlayer, "No player has been set in this room.");
	}
	
	/**
	 * @param myLargeIcon the myLargeIcon to set
	 */
	public void setLargeIcon(GameIcon myLargeIcon) {
		this.myLargeIcon = myLargeIcon;
	}

	public GameIcon getLargeIcon() {
	    return myLargeIcon;
	}
	
	public void setSmallIcon(GameIcon theSmallIcon) {
		Objects.requireNonNull(theSmallIcon);
		mySmallIcon = theSmallIcon;
	}
	
	
	public GameIcon getSmallIcon() {
        return mySmallIcon;
    }
	
	public RoomIndex getMyIndex() {
		return myIndex;
	}
	
	public void setCurrentRoom(boolean isCurrentRoom) {
		this.isCurrentRoom = isCurrentRoom;
		
		if(isCurrentRoom) {
			if(isWinRoom) mySmallIcon = MAP_ICON_CURRENT_WIN;
			else mySmallIcon = MAP_ICON_CURRENT;
		}
		else if (isWinRoom) mySmallIcon = MAP_ICON_WIN;
		else mySmallIcon = MAP_ICON;
	}
	
	public void setWinRoom(boolean isWinRoom) {
		this.isWinRoom = isWinRoom;
		
		if(isWinRoom) mySmallIcon = MAP_ICON_WIN;
		else mySmallIcon = MAP_ICON;
	}
	
	/**
	 * @return the isVisited
	 */
	public boolean isVisited() {
		return isVisited;
	}
	
	/**
	 * @return the mapIconSize
	 */
	public static int getMapIconSize() {
		return MAP_ICON_SIZE;
	}

	public String toString() {
		return myIndex.toString();
	}
	
	private void setRandomFloor() {
		myLargeIcon = CARPETS.getFloors()[RAND.nextInt(12)];
	}
	
	
}