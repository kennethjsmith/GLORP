package model;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Objects;
import java.util.Random;

import view.GameIcon;

/**
 * A room has 4 doors and can contain items. Each rooms has flags indicating 
 * player presence and whether or not the room has been visited.
 * @author Heather Finch, Katelynn Oleson, Ken Smith
 * @version 1.0.
 */
public class Room implements Serializable{
	// A serialized ID for serialization.
    private static final long serialVersionUID = -8977518956610218988L;
    
    // This HashMap contains all 4 directions mapped to the doors for the room: North, South, East, and West.
    private HashMap<Direction, Door> myDoorMap;
	private Item myItem; 
	private Fixture myFixture;
	private Player myPlayer;
	
	private transient GameIcon myLargeIcon; 
	private transient GameIcon mySmallIcon; 
	private boolean isChestRoom;
	private boolean isStartRoom;
	private boolean isCurrentRoom;
	private boolean isVisited;
	
	private final static int SIZE = 500;
	private static final Rectangle AREA = new Rectangle(new Point(0,0), new Dimension(SIZE,SIZE));
	private static final Rectangle NORTH_DOOR_ZONE = new Rectangle(new Point(200,0), new Dimension(100,20));;
	private static final Rectangle SOUTH_DOOR_ZONE = new Rectangle(new Point(200,480), new Dimension(100,20));;
	private static final Rectangle WEST_DOOR_ZONE = new Rectangle(new Point(0,200), new Dimension(20,100));;
	private static final Rectangle EAST_DOOR_ZONE = new Rectangle(new Point(480,200), new Dimension(20,100));;
	private final RoomIndex myIndex;
	
	private static final Carpet CARPETS = new Carpet();
	private static final int MAP_ICON_SIZE = 36;
	// The different floor (carpet) icons for the map.
	private static final GameIcon MAP_ICON = new GameIcon("src/icons/map_icon.png", MAP_ICON_SIZE);
	private static final GameIcon MAP_ICON_CURRENT = new GameIcon("src/icons/map_icon_current.png", MAP_ICON_SIZE);
	private static final GameIcon MAP_ICON_WIN = new GameIcon("src/icons/map_icon_win.png", MAP_ICON_SIZE);
	private static final GameIcon MAP_ICON_CURRENT_WIN = new GameIcon("src/icons/map_icon_current_win.png", MAP_ICON_SIZE);
	private static final GameIcon MAP_ICON_KEY = new GameIcon("src/icons/map_icon_key.png", MAP_ICON_SIZE);
	private static final GameIcon MAP_ICON_CURRENT_KEY = new GameIcon("src/icons/map_icon_current_key.png", MAP_ICON_SIZE);
	private static final GameIcon MAP_ICON_START = new GameIcon("src/icons/map_icon_start.png", MAP_ICON_SIZE);
	private static final GameIcon MAP_ICON_CURRENT_START = new GameIcon("src/icons/map_icon_current_start.png", MAP_ICON_SIZE);
	private final static Random RAND = new Random();

	/**
	 * Creates a new room based on it's row and column in the maze.
	 * @param theRow The row of the room.
	 * @param theCol The column of the room.
	 */

	public Room(int theRow, int theCol) {  
		myDoorMap = null;
		myItem = null;
	    setRandomFloor(); 
	    mySmallIcon = MAP_ICON; 
	    myIndex = new RoomIndex(theRow, theCol);
	    myPlayer = null;
	    isVisited = false;
	    isCurrentRoom = false;
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
     * Getter for the Directions and Doors in the Room.
     * @return HashMap<Direction, Door> The directions and doors in the room
     */
    public HashMap<Direction, Door> getDoors() {
    	return myDoorMap;
    }
    
	/**
	 * Getter for the Item in this room.
	 * @return Item The Item in this room
	 */
	public Item getItem() {
		return myItem;
	}

	/**
	 * Adds an Item to this room.
	 * @param Item The new Item for the room
	 */
	public void setItem(Item theItem) {
		myItem = theItem;
		if (theItem == null) mySmallIcon = MAP_ICON_KEY;
	}
	
	/**
	 * Place an item in this room
	 * @param myItem the myItem to add to the room
	 */
	public void addItem(Item theGamePiece) {
		myItem = theGamePiece;
	}
	
	/**
	 * Place the player in this room.
	 * @param myItem the myItem to set
	 */
	public void setPlayer(Player thePlayer) {
		myPlayer = thePlayer;
		isVisited = true;
	}
	
	/**
	 * Getter for this Rooms Player
	 * @return Player The Player in this Room
	 */
	public Player getPlayer() {
		return Objects.requireNonNull(myPlayer, "No player has been set in this room.");
	}
	
	/**
	 * Getter for this Rooms large icon.
	 * @return GameIcon The large icon for this Room
	 */
	public GameIcon getLargeIcon() {
	    return myLargeIcon;
	}
	
	/**
	 * Setter for the large icon.
     * @param myLargeIcon the myLargeIcon to set
     */
	public void setLargeIcon(GameIcon theLargeIcon) {
	    Objects.requireNonNull(theLargeIcon);
	    myLargeIcon = theLargeIcon;
	}
	   

	/**
	 * Setter for the small icon.
	 * @param theSmallIcon The small icon to set
	 */
	public void setSmallIcon(GameIcon theSmallIcon) {
		Objects.requireNonNull(theSmallIcon);
		mySmallIcon = theSmallIcon;
	}
	
	
	/**
	 * Getter for the small icon.
	 * @return GameIcon This Rooms small icon
	 */
	public GameIcon getSmallIcon() {
        return mySmallIcon;
    }
	
	/**
	 * Getter for the Fixture in this Room.
	 * @return Fixture The Fixture in this Room
	 */
	public Fixture getFixture() {
		return myFixture;
	}

	/**
	 * Adds a Fixture to this Room.
	 * @param Fixture The Fixture to add to the Room
	 */
	public void setFixture(Fixture myFixture) {
		this.myFixture = myFixture;
	}

	/**
	 * Getter for this Rooms index.
	 * @return RoomIndex The index of this Room
	 */
	public RoomIndex getIndex() {
		return myIndex;
	}
	
	/**
	 * Getter for the size of the Room.
	 * @return The size of the room, an int
	 */
	public static int getSize() {
		return SIZE;
	}

	/**
	 * Sets to be the current Room or not.
	 * @param boolean True if this should be the current Room, false otherwise.
	 */
	public void setCurrentRoom(boolean isCurrentRoom) {
		this.isCurrentRoom = isCurrentRoom;
		
		// Updates the icons.
		if(isCurrentRoom) {
			if(isChestRoom) mySmallIcon = MAP_ICON_CURRENT_WIN;
			else if(isStartRoom) mySmallIcon = MAP_ICON_CURRENT_START;
			else if (myItem != null) mySmallIcon = MAP_ICON_CURRENT_KEY;
			else mySmallIcon = MAP_ICON_CURRENT;
		} else {
			if (isChestRoom) mySmallIcon = MAP_ICON_WIN;
			else if(isStartRoom) mySmallIcon = MAP_ICON_START;
			else if (myItem != null) mySmallIcon = MAP_ICON_KEY;
			else mySmallIcon = MAP_ICON;
		}
	}
	
	/**
	 * Sets this to be the start room.
	 */
	public void designateStartRoom() {
		isStartRoom = true;
		mySmallIcon = MAP_ICON_START;
		setCurrentRoom(true);
		setFixture(new Fixture(150, 150, FixtureType.SHIP)); // Add ship
		setPlayer(myPlayer);
	}
	
	/**
	 * Sets this to be the ChestRoom.
	 * @param True if this should be the Chest Room, false otherwise
	 */
	public void designateChestRoom(boolean isChestRoom) {
		this.isChestRoom = isChestRoom;
		
		// Update icons.
		if(isChestRoom) {
			mySmallIcon = MAP_ICON_WIN;
			setLargeIcon(Carpet.getSpecialIcon());
		} else mySmallIcon = MAP_ICON;
	}
	
	/**
	 * Getter for the map icon of the key for the room.
	 * @return the map icon with the key in it
	 */
	public static GameIcon getKeyMapIcon() {
		return MAP_ICON_KEY;

	}
	
	/**
	 * Getter for whether this room has been visited or not.
	 * A room has been visited if one of its 4 doors have been unlocked.
	 * @return True if the room has been visited, false otherwise
	 */
	public boolean isVisited() {
		return isVisited;
	}
	
	/**
	 * Getter for the size of the map icon.
	 * @return The map icon size, an int
	 */
	public static int getMapIconSize() {
		return MAP_ICON_SIZE;
	}

	/**
	 * Getter for the north door zone.
	 * @return Rectangle The North door zone
	 */
	public static Rectangle getNorthDoorZone() {
		return NORTH_DOOR_ZONE;
	}

	/**
	 * Getter for the south door zone.
	 * @return Rectangle The south door zone
	 */
	public static Rectangle getSouthDoorZone() {
		return SOUTH_DOOR_ZONE;
	}

	/**
	 * Getter for the west door zone.
	 * @return Rectangle The west door zone
	 */
	public static Rectangle getWestDoorZone() {
		return WEST_DOOR_ZONE;
	}

	/**
	 * Getter for the east door zone.
	 * @return Rectangle The east door zone
	 */
	public static Rectangle getEastDoorZone() {
		return EAST_DOOR_ZONE;
	}
	
	// Selects a random floor icon for this room.
	private void setRandomFloor() {
		myLargeIcon = CARPETS.getFloors()[RAND.nextInt(12)];
	}

	/**
	 * Returns a valid direction.
	 * @param Player The Player that is trying to move
	 * @param Direction The Direction the Player is trying to move in
	 * @return Direction The Direction the Player will move in
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
	
	/**
	 * Checks the location for the Player is valid.
	 * The location is not valid if it is overlapping with Fixture,
	 * or if it is outside of the Rooms walls.
	 * @param Player The Player with their projected location
	 * @return True if the location of the projected player is valid, false otherwise
	 */
	public boolean isValidLocation(Player playerProjected) {
		if(!AREA.contains(playerProjected.getIconArea())) return false; // Check for out of room bounds
		if(myFixture != null && myFixture.getBase().intersects(playerProjected.getBase())) return false; // check for fixture overlap
		return true;
	}
}