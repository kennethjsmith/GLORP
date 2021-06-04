package model;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
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
// TODO: create subclasses (start and end rooms)
public class Room {
    private HashMap<Direction, Door> myDoorMap;
	private Item myItem; 
	private Fixture myFixture;
	private Player myPlayer;
    // why does room contain the player? 
	
	private GameIcon myLargeIcon;
	private GameIcon mySmallIcon;
	private boolean isCurrentRoom;
	private boolean isWinRoom;
	private boolean isVisited;
	
	private final static int SIZE = 500;
	private static final Rectangle AREA = new Rectangle(new Point(0,0), new Dimension(SIZE,SIZE));
	private static final Rectangle NORTH_DOOR_ZONE = new Rectangle(new Point(200,0), new Dimension(100,20));;
	private static final Rectangle SOUTH_DOOR_ZONE = new Rectangle(new Point(200,480), new Dimension(100,20));;
	private static final Rectangle WEST_DOOR_ZONE = new Rectangle(new Point(0,200), new Dimension(20,100));;
	private static final Rectangle EAST_DOOR_ZONE = new Rectangle(new Point(480,200), new Dimension(20,100));;
//	private static final Rectangle[] myDoorZones = {NORTH_DOOR_ZONE, SOUTH_DOOR_ZONE, WEST_DOOR_ZONE, EAST_DOOR_ZONE};
	private final RoomIndex myIndex;
	
	
	//TODO: should these be enumerated types? might help get rid of boolean fields upon refactor
	private static final Carpet CARPETS = new Carpet();
	private static final int MAP_ICON_SIZE = 36;
	private static final GameIcon MAP_ICON = new GameIcon("src/icons/map_icon.png", MAP_ICON_SIZE);
	private static final GameIcon MAP_ICON_CURRENT = new GameIcon("src/icons/map_icon_current.png", MAP_ICON_SIZE);
	private static final GameIcon MAP_ICON_WIN = new GameIcon("src/icons/map_icon_win.png", MAP_ICON_SIZE);
	private static final GameIcon MAP_ICON_CURRENT_WIN = new GameIcon("src/icons/map_icon_current_win.png", MAP_ICON_SIZE);
	private static final GameIcon MAP_ICON_KEY = new GameIcon("src/icons/map_icon_key.png", MAP_ICON_SIZE);
	private static final GameIcon MAP_ICON_CURRENT_KEY = new GameIcon("src/icons/map_icon_current_key.png", MAP_ICON_SIZE);
	private final static Random RAND = new Random();
	
	
	//Map<Door, Room> myDoors= new TreeMap<>();
	//private boolean containsPlayer; //will be tracked by map (current room) 
	//private boolean visitedFlag; // will be tracked by map? 
	
    /**
     * 
     */
    public Room() {
    	myDoorMap = null;
    	myItem = null;
    	myFixture = null;
        myLargeIcon = null; 
        mySmallIcon = null;
        myIndex = null;
        myPlayer = null;
        isVisited = false;
    }

	/**
	 * @param theRow
	 * @param theCol
	 */
	public Room(int theRow, int theCol) { // how will rooms get their riddles? 
		myDoorMap = null;
		myItem = null;
	    setRandomFloor(); 
	    mySmallIcon = MAP_ICON; 
	    myIndex = new RoomIndex(theRow, theCol);
	    myPlayer = null;
	    isVisited = false;
	}


	/**
     * If this rooms doors have not been initailized already,
     * sets this rooms door array to the passed in door array.
     * @param Door[] theDoors
     */
    public void setDoors(HashMap<Direction, Door> theDoors) {
    	myDoorMap = theDoors;
    }
    

    /**
     * @return
     */
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
	 * @return the myItems
	 */
	public Item getItem() {
		return myItem;
	}

	/**
	 * 
	 */
	public void setItem(Item theItem) {
		myItem = theItem;
		if (theItem == null) mySmallIcon = MAP_ICON_KEY;
	}
	
	/**
	 * Place an item in this room
	 * @param myItem the myItem to set
	 */
	public void addItem(Item theGamePiece, PiecePoint theCoordinates) throws NullPointerException{
	    if(theGamePiece == null) {
	        throw new NullPointerException("GamePiece cannot be null.");
	    }
		myItem = theGamePiece;
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
	 * @return
	 */
	public GameIcon getLargeIcon() {
	    return myLargeIcon;
	}
	
	/**
     * @param myLargeIcon the myLargeIcon to set
     */
	public void setLargeIcon(GameIcon theLargeIcon) {
	    Objects.requireNonNull(theLargeIcon);
	    myLargeIcon = theLargeIcon;
	}
	   

	/**
	 * @param theSmallIcon
	 */
	public void setSmallIcon(GameIcon theSmallIcon) {
		Objects.requireNonNull(theSmallIcon);
		mySmallIcon = theSmallIcon;
	}
	
	
	/**
	 * @return
	 */
	public GameIcon getSmallIcon() {
        return mySmallIcon;
    }
	
	/**
	 * @return the myFixture
	 */
	public Fixture getFixture() {
		return myFixture;
	}

	/**
	 * @param myFixture the myFixture to set
	 */
	public void setFixture(Fixture myFixture) {
		this.myFixture = myFixture;
	}

	/**
	 * @return
	 */
	public RoomIndex getIndex() {
		return myIndex;
	}
	
	/**
	 * @return the size
	 */
	public static int getSize() {
		return SIZE;
	}

	/**
	 * @param isCurrentRoom
	 */
	public void setCurrentRoom(boolean isCurrentRoom) {
		this.isCurrentRoom = isCurrentRoom;
		
		if(isCurrentRoom) {
			if(isWinRoom) mySmallIcon = MAP_ICON_CURRENT_WIN;
			else if (myItem != null) mySmallIcon = MAP_ICON_CURRENT_KEY;
			else mySmallIcon = MAP_ICON_CURRENT;
		}
		else {
			if (isWinRoom) mySmallIcon = MAP_ICON_WIN;
			else if (myItem != null) mySmallIcon = MAP_ICON_KEY;
			else mySmallIcon = MAP_ICON;
		}
	}
	
	/**
	 * @param isWinRoom
	 */
	public void setWinRoom(boolean isWinRoom) {
		this.isWinRoom = isWinRoom;
		
		if(isWinRoom) mySmallIcon = MAP_ICON_WIN;
		else mySmallIcon = MAP_ICON;
	}
	
	/**
	 * @return the map icon with the key in it
	 */
	public static GameIcon getKeyMapIcon() {
		return MAP_ICON_KEY;

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

	/**
	 * @return the northDoorZone
	 */
	public static Rectangle getNorthDoorZone() {
		return NORTH_DOOR_ZONE;
	}

	/**
	 * @return the southDoorZone
	 */
	public static Rectangle getSouthDoorZone() {
		return SOUTH_DOOR_ZONE;
	}

	/**
	 * @return the westDoorZone
	 */
	public static Rectangle getWestDoorZone() {
		return WEST_DOOR_ZONE;
	}

	/**
	 * @return the eastDoorZone
	 */
	public static Rectangle getEastDoorZone() {
		return EAST_DOOR_ZONE;
	}

	/**
	 *
	 */
	public String toString() {
		return myIndex.toString();
	}
	
	/**
	 * 
	 */
	private void setRandomFloor() {
		myLargeIcon = CARPETS.getFloors()[RAND.nextInt(12)];
	}

	/*
	 * Returns a valid direction
	 */
	public Direction validateDirection(Player thePlayer, Direction theDirection) throws CloneNotSupportedException {
		Player playerProjected = (Player) thePlayer.clone();
		//playerProjected.setArea(thePlayer.getArea());
		playerProjected.move(theDirection);
		Direction validDirection = null;
		
		if(isValidLocation(playerProjected)) validDirection = theDirection;
		// recursion for compound directions
		else if(theDirection == Direction.NORTHEAST) {
			validDirection = validateDirection(thePlayer, Direction.NORTH);
			if(validDirection == null) validDirection = validateDirection(thePlayer, Direction.EAST);
		}
		else if(theDirection == Direction.NORTHWEST) {
			validDirection = validateDirection(thePlayer, Direction.NORTH);
			if(validDirection == null) validDirection = validateDirection(thePlayer, Direction.WEST);
		}
		else if(theDirection == Direction.SOUTHEAST) {
			validDirection = validateDirection(thePlayer, Direction.SOUTH);
			if(validDirection == null) validDirection = validateDirection(thePlayer, Direction.EAST);
		}
		else if(theDirection == Direction.SOUTHWEST) {
			validDirection = validateDirection(thePlayer, Direction.SOUTH);
			if(validDirection == null) validDirection = validateDirection(thePlayer, Direction.WEST);
		}
		return validDirection;
	}
	
	/**
	 * @param playerProjected
	 * @return
	 */
	public boolean isValidLocation(Player playerProjected) {
		// check for out of room bounds
		if(!AREA.contains(playerProjected.getIconArea())) {
			return false;
		}
		// check for fixture overlap
		if(myFixture != null && myFixture.getBase().intersects(playerProjected.getBase())) {
				return false;
		}
		return true;
	}
	
	// this would only work if we make class doorZone with a rectangle and a direction
//    public Rectangle[] getDoorZones() {
//        return
//    }
}