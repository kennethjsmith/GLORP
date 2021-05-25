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
    private final Point myCoordinate;
    private GameIcon myIcon;
	private Boolean myUnlockedFlag;
    private Boolean myPermaBlockedFlag;
    
    private static final GameIcon UNLOCKED_ICON = new GameIcon("src/icons/door_green.png");
    private static final GameIcon LOCKED_ICON = new GameIcon("src/icons/door_yellow.png");
    private static final GameIcon BLOCKED_ICON = new GameIcon("src/icons/door_red.png");



    // or will they just give their coordinates 
    // and room/maze uses those to decide how the item is attempting to leave the room
    // remove item, that call add item elsewhere 
    
    public Door(){
        myRiddle = new Riddle();
        myCoordinate = new Point();
        
        myIcon = LOCKED_ICON;
        myUnlockedFlag = false;
        myPermaBlockedFlag = false; 
    }
    
    public Door(Point theCoordinate){
        myRiddle = new Riddle();
        myCoordinate = theCoordinate;
        myIcon = LOCKED_ICON;
        myUnlockedFlag = false;
        myPermaBlockedFlag = false; 
    }
    
    public Door(Riddle theRiddle, Point theCoordinate) {  // for console based game
        myRiddle = theRiddle;
        myCoordinate = theCoordinate;
        myIcon = LOCKED_ICON;
        myUnlockedFlag = false;
        myPermaBlockedFlag = false;
    }
    
    public Door(Riddle theRiddle, Point theCoordinate, GameIcon theIcon) {
        myRiddle = theRiddle;
        myCoordinate = theCoordinate;
        myIcon = theIcon;
        myUnlockedFlag = false;
        myPermaBlockedFlag = false;
    }
	
	public Boolean isBlocked() {
		return myPermaBlockedFlag;
	}
    
    public Boolean isUnlocked() {
        return myUnlockedFlag;
    }
	
	public Point getCoordinate() {
	    return myCoordinate;
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
        myIcon = UNLOCKED_ICON;
    }
	
	void setBlocked() { //package, want limited access so only riddle can change this
        myPermaBlockedFlag = true;
        myIcon = BLOCKED_ICON;
    }
	
    public GameIcon getMyIcon() {
		return myIcon;
	}

    // TODO get rid of this method ??
	public void setMyIcon(GameIcon theIcon) {
		this.myIcon = theIcon;
	}
}
