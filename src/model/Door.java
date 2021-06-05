package model;

import java.awt.Point;

import javax.swing.ImageIcon;

import view.GameIcon;

/**
 * A Passage has a riddle, answering correctly unlocks the passage
 * answering incorrectly permanently blocks the passage.
 * @author
 * @version
 */
public class Door {
    // fields
    private Riddle myRiddle;
    private GameIcon myRoomIcon;
    private GameIcon myMapIcon;
	private Boolean myUnlockedFlag;
    private Boolean myPermaBlockedFlag;
    private Direction myDirection; //North/South = NS door, and WEST/EAST = WE door
    
    // green door icons
    private static final GameIcon WE_ROOM_UNLOCKED_ICON = new GameIcon("src/icons/door_green.png", 20, 100);
    private static final GameIcon NS_ROOM_UNLOCKED_ICON = new GameIcon("src/icons/door_green.png", 100, 20);
    private static final GameIcon WE_MAP_UNLOCKED_ICON = new GameIcon("src/icons/door_green.png", 2, 10);
    private static final GameIcon NS_MAP_UNLOCKED_ICON = new GameIcon("src/icons/door_green.png", 10, 2);
    // yellow door icons
    private static final GameIcon WE_ROOM_LOCKED_ICON = new GameIcon("src/icons/door_yellow.png", 20, 100);
    private static final GameIcon NS_ROOM_LOCKED_ICON = new GameIcon("src/icons/door_yellow.png", 100, 20);
    private static final GameIcon WE_MAP_LOCKED_ICON = new GameIcon("src/icons/door_yellow.png", 2, 10);
    private static final GameIcon NS_MAP_LOCKED_ICON = new GameIcon("src/icons/door_yellow.png", 10, 2);
    // red door icons
    private static final GameIcon WE_ROOM_BLOCKED_ICON = new GameIcon("src/icons/door_red.png", 20, 100);
    private static final GameIcon NS_ROOM_BLOCKED_ICON = new GameIcon("src/icons/door_red.png", 100, 20);
    private static final GameIcon WE_MAP_BLOCKED_ICON = new GameIcon("src/icons/door_red.png", 2, 10);
    private static final GameIcon NS_MAP_BLOCKED_ICON = new GameIcon("src/icons/door_red.png", 10, 2);

    
    /**
     * 
     */
    private Door(){
        myRiddle = null;
        myDirection = null;
        myRoomIcon = null;
        myMapIcon = null;
        myUnlockedFlag = false;
        myPermaBlockedFlag = false;
        
    }
    
    /**
     * @param theDirection
     */
    public Door(Direction theDirection, Riddle theRiddle){
        myRiddle = theRiddle;
        myDirection = theDirection;
        initializeIcons(theDirection);
        myUnlockedFlag = false;
        myPermaBlockedFlag = false; 
    }
    
	/**
	 * 
	 * @param theDirection
	 */
	private void initializeIcons(Direction theDirection) {
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
	 * 
	 * @return
	 */
	public Boolean isBlocked() {
		return myPermaBlockedFlag;
	}
    
    public Boolean isUnlocked() {
        return myUnlockedFlag;
    }
	
	/**
	 * @return the myRiddle
	 */
	public Riddle getMyRiddle() { // should doors just return riddles? 
	                              // or handle the inner workings of riddles? 
		return myRiddle;
	}
	
	/**
	 * 
	 */
	public void setUnlocked() { //change to package once we have riddle, want limited access so only riddle can change this 
        myUnlockedFlag = true;
        myPermaBlockedFlag = false;
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
	 * 
	 */
	public void setBlocked() { //package, want limited access so only riddle can change this
        myPermaBlockedFlag = true;
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
	 * 
	 * @return
	 */
    public GameIcon getMapIcon() {
		return myMapIcon;
	}
    
    /**
     * 
     * @return
     */
    public GameIcon getRoomIcon() {
		return myRoomIcon;
	}
    
    /**
     * 
     */
    public String toString() {
    	return "Unlocked: " + myUnlockedFlag + ", Direction: " + myDirection;
    }
}
