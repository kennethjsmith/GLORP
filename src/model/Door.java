package model;

import java.io.Serializable;
import java.util.Objects;

import view.GameIcon;

/**
 * A Passage has a riddle, answering correctly unlocks the passage
 * answering incorrectly permanently blocks the passage.
 * @author Heather Finch, Katelynn Oleson, Ken Smith
 * @version 1.0
 */
public class Door implements Serializable{

	// Serialized ID for serialization.
    private static final long serialVersionUID = 8488154775165313034L;
    
    // Fields
    private Riddle myRiddle;
    private GameIcon myRoomIcon; // The floor icon for room panel.
    private GameIcon myMapIcon; // The floor icon for map panel.
	private Boolean myUnlockedFlag; // True if this door is unlocked.
    private Boolean myPermaBlockedFlag; // True if this door is permanently blocked.
    private Direction myDirection; // North/South = NS door, and WEST/EAST = WE door
    
    // Green door icons, these are for unlocked doors.
    private static final GameIcon WE_ROOM_UNLOCKED_ICON = new GameIcon("src/icons/door_green.png", 20, 100);
    private static final GameIcon NS_ROOM_UNLOCKED_ICON = new GameIcon("src/icons/door_green.png", 100, 20);
    private static final GameIcon WE_MAP_UNLOCKED_ICON = new GameIcon("src/icons/door_green.png", 2, 10);
    private static final GameIcon NS_MAP_UNLOCKED_ICON = new GameIcon("src/icons/door_green.png", 10, 2);
    // Yellow door icons, these are for locked doors.
    private static final GameIcon WE_ROOM_LOCKED_ICON = new GameIcon("src/icons/door_yellow.png", 20, 100);
    private static final GameIcon NS_ROOM_LOCKED_ICON = new GameIcon("src/icons/door_yellow.png", 100, 20);
    private static final GameIcon WE_MAP_LOCKED_ICON = new GameIcon("src/icons/door_yellow.png", 2, 10);
    private static final GameIcon NS_MAP_LOCKED_ICON = new GameIcon("src/icons/door_yellow.png", 10, 2);
    // Red door icons, these are for permanently blocked doors.
    private static final GameIcon WE_ROOM_BLOCKED_ICON = new GameIcon("src/icons/door_red.png", 20, 100);
    private static final GameIcon NS_ROOM_BLOCKED_ICON = new GameIcon("src/icons/door_red.png", 100, 20);
    private static final GameIcon WE_MAP_BLOCKED_ICON = new GameIcon("src/icons/door_red.png", 2, 10);
    private static final GameIcon NS_MAP_BLOCKED_ICON = new GameIcon("src/icons/door_red.png", 10, 2);
    
    /**
     * Creates a door.
     * @param theDirection the area of the room that this door is in (ie North, South, West, East)
     * @param theRiddle the Riddle this door will store
     */
    public Door(Direction theDirection, Riddle theRiddle){
        myDirection = Objects.requireNonNull(theDirection);
    	myRiddle = Objects.requireNonNull(theRiddle);
        initializeIcons(theDirection);
        // Doors are automatically set to locked.
        myUnlockedFlag = false; 
        myPermaBlockedFlag = false; 
    }
    
    // Initializes the map icon and room icon based on what side of the room the door is on.
    // North / South doors have a horizontal rectangle icon
    // East / West doors have a verticle rectangle icon
	private void initializeIcons(Direction theDirection) {
		Objects.requireNonNull(theDirection);
		if(theDirection == Direction.NORTH || theDirection == Direction.SOUTH) {
			myRoomIcon = NS_ROOM_LOCKED_ICON;
			myMapIcon = NS_MAP_LOCKED_ICON;
		}
		if(theDirection == Direction.WEST || theDirection == Direction.EAST) {
			myRoomIcon = WE_ROOM_LOCKED_ICON;
			myMapIcon = WE_MAP_LOCKED_ICON;
		}		
	}

	/**
	 * Getter for whether the door is permanently blocked.
	 * @return true if the door is permanently blocked, false otherwise
	 */
	public Boolean isBlocked() {
		return myPermaBlockedFlag;
	}
    
	
    /**
     * Getter for whether the door is unlocked.
     * @return true if the door is unlocked, false otherwise
     */
    public Boolean isUnlocked() {
        return myUnlockedFlag;
    }
	
	/**
	 * Getter for the riddle that this door stores.
	 * @return Riddle this doors riddle
	 */
	public Riddle getMyRiddle() {  
		return myRiddle;
	}
	
	/**
	 * Unlocks the door when the riddle was answered correctly. 
	 */
	public void setUnlocked() { 
		myUnlockedFlag = true;
        myPermaBlockedFlag = false;
        
        // Update the icons.
        if(myDirection == Direction.NORTH || myDirection == Direction.SOUTH) {
			myRoomIcon = NS_ROOM_UNLOCKED_ICON;
			myMapIcon = NS_MAP_UNLOCKED_ICON;
		}
		if(myDirection == Direction.WEST || myDirection == Direction.EAST) {
			myRoomIcon = WE_ROOM_UNLOCKED_ICON;
			myMapIcon = WE_MAP_UNLOCKED_ICON;
		}
    }
	
	/**
	 * Permanently blocks the door when the riddle was answered incorrectly.
	 */
	public void setBlocked() { 
		myUnlockedFlag = false;
        myPermaBlockedFlag = true;
        
        // Update the icons.
        if(myDirection == Direction.NORTH || myDirection == Direction.SOUTH) {
			myRoomIcon = NS_ROOM_BLOCKED_ICON;
			myMapIcon = NS_MAP_BLOCKED_ICON;
		}
		if(myDirection == Direction.WEST || myDirection == Direction.EAST) {
			myRoomIcon = WE_ROOM_BLOCKED_ICON;
			myMapIcon = WE_MAP_BLOCKED_ICON;
		}
    }
	
	/**
	 * Getter for the doors icon for the map.
	 * @return GameIcon the doors icon for the map
	 */
    public GameIcon getMapIcon() {
		return myMapIcon;
	}
    
    /**
     * Getter for the doors icon for the room.
     * @return GameIcon the doors icon for the room
     */
    public GameIcon getRoomIcon() {
		return myRoomIcon;
	}
}
