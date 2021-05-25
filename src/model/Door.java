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
    private static final GameIcon WE_MAP_UNLOCKED_ICON = new GameIcon("src/icons/door_green.png", 3, 10);
    private static final GameIcon NS_MAP_UNLOCKED_ICON = new GameIcon("src/icons/door_green.png", 10, 3);
    // yellow door icons
    private static final GameIcon WE_ROOM_LOCKED_ICON = new GameIcon("src/icons/door_yellow.png", 20, 100);
    private static final GameIcon NS_ROOM_LOCKED_ICON = new GameIcon("src/icons/door_yellow.png", 100, 20);
    private static final GameIcon WE_MAP_LOCKED_ICON = new GameIcon("src/icons/door_yellow.png", 3, 10);
    private static final GameIcon NS_MAP_LOCKED_ICON = new GameIcon("src/icons/door_yellow.png", 10, 3);
    // red door icons
    private static final GameIcon WE_ROOM_BLOCKED_ICON = new GameIcon("src/icons/door_red.png", 20, 100);
    private static final GameIcon NS_ROOM_BLOCKED_ICON = new GameIcon("src/icons/door_red.png", 100, 20);
    private static final GameIcon WE_MAP_BLOCKED_ICON = new GameIcon("src/icons/door_red.png", 3, 10);
    private static final GameIcon NS_MAP_BLOCKED_ICON = new GameIcon("src/icons/door_red.png", 10, 3);

    // or will they just give their coordinates 
    // and room/maze uses those to decide how the item is attempting to leave the room
    // remove item, that call add item elsewhere 
    
    private Door(){
        myRiddle = new Riddle();
        myDirection = null;
        myRoomIcon = null;
        myMapIcon = null;
        myUnlockedFlag = false;
        myPermaBlockedFlag = false;
        
    }
    
    public Door(Direction theDirection){
        myRiddle = new Riddle();
        myDirection = theDirection;
        initializeIcons(theDirection);
        myUnlockedFlag = false;
        myPermaBlockedFlag = false; 
    }
    
	
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
	
	public void setUnlocked() { //package, want limited access so only riddle can change this
        myUnlockedFlag = true;
        System.out.println(this);
        if(myDirection == Direction.NORTH || myDirection == Direction.SOUTH) {
			myRoomIcon = NS_ROOM_UNLOCKED_ICON;
			myMapIcon = NS_MAP_UNLOCKED_ICON;
		}
		if(myDirection == Direction.WEST || myDirection == Direction.EAST) {
			myRoomIcon = WE_ROOM_UNLOCKED_ICON;
			myMapIcon = WE_MAP_UNLOCKED_ICON;
		}
    }
	
	void setBlocked() { //package, want limited access so only riddle can change this
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
	
    public GameIcon getMapIcon() {
		return myMapIcon;
	}
    
    public GameIcon getRoomIcon() {
		return myRoomIcon;
	}
    
    public String toString() {
    	return "Unlocked: " + myUnlockedFlag + ", Direction: " + myDirection;
    }
}
