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
//TODO: create subclasses (start and end rooms)
public class Room {
    private static int MAX_DOORS = 4;
    private Door[] myDoors; // for now... 4 doors each 
    private HashMap<Direction, Door> myDoorMap;
	private Map<Item, PiecePoint> myItems; 
	private Fixture myFixture;
	private Player myPlayer;
	private GameIcon myLargeIcon;
	private GameIcon mySmallIcon;
	private boolean isCurrentRoom;
	private boolean isWinRoom;
	private boolean isVisited;
	private Rectangle myArea;

	private final RoomIndex myIndex;
	private final static int SIZE = 500;
	
	
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
    	myItems = new HashMap<>();
    	myFixture = null;
        myLargeIcon = null; 
        mySmallIcon = null;
        myIndex = null;
        myPlayer = null;
        isVisited = false;
    	myArea = null;
    }

	public Room(int theRow, int theCol) { // how will rooms get their riddles? 
		myDoorMap = null;
		myItems = new HashMap<>();
	    setRandomFloor(); 
	    mySmallIcon = MAP_ICON; 
	    myIndex = new RoomIndex(theRow, theCol);
	    myPlayer = null;
	    isVisited = false;
	    myArea = new Rectangle(new Point(0,0), new Dimension(SIZE,SIZE));
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
	 * @return the myItems
	 */
	public Map<Item, PiecePoint> getItems() {
		return myItems;
	}

	/**
	 * Place an item in this room
	 * @param myItem the myItem to set
	 */
	public void addItem(Item theGamePiece, PiecePoint theCoordinates) throws NullPointerException{
	    if(theGamePiece == null) {
	        throw new NullPointerException("GamePiece cannot be null.");
	    }
		myItems.put(theGamePiece, theCoordinates);
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

	public RoomIndex getIndex() {
		return myIndex;
	}
	
	/**
	 * @return the size
	 */
	public static int getSize() {
		return SIZE;
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

	/*
	 * Returns a valid direction
	 */
	public Direction validateDirection(Player thePlayer, Direction theDirection) throws CloneNotSupportedException {
		Player playerProjected = (Player) thePlayer.clone();
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
	
	public boolean isValidLocation(Player playerProjected) {
//		int inXCoordinate = (int)thePoint.getX();
//		int inYCoordinate = (int)thePoint.getY();
		
		// check for out of room bounds
		//TODO: give room a rectangle for easy bounds?
		if(this.myArea.contains(playerProjected.getArea())) {
			return false;
		}
//		if(inYCoordinate > (SIZE - Player.getSpeed() - Skin.getSize()) || inYCoordinate < Player.getSpeed()) {
//			return false;
//		}
		
		// check for fixture overlap
		//TODO:why is it not finding that the rectangle contains the point?
		//TODO: once this works, and printlns not needed, 
		//combine both the if conditions into 1 statement
		if(myFixture != null) {
			System.out.println(myFixture.getBase());
			System.out.println(myFixture.getBase().intersects(playerProjected.getArea()));
			if(myFixture.getBase().intersects(playerProjected.getArea())) {
				return false;
			}
		}
		return true;
	}
}