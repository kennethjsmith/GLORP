package model;

import java.awt.Point;

import javax.swing.ImageIcon;

/**
 * A Passage has a riddle, answering correctly unlocks the passage
 * answering incorrectly permanently blocks the passage.
 * @author
 * @version
 */
public class Door {
    // fields
    Riddle myRiddle;
    Point myCoordinate;
    ImageIcon myIcon;
    Boolean myUnlockedFlag;
    Boolean myPermaBlockedFlag;

    // or will they just give their coordinates 
    // and room/maze uses those to decide how the item is attempting to leave the room
    // remove item, that call add item elsewhere 
    
    public Door(){
        myRiddle = new Riddle();
        myCoordinate = new Point();
        myIcon = new ImageIcon();
        myUnlockedFlag = false;
        myPermaBlockedFlag = false; 
    }
    
    public Door(Point theCoordinate){
        myRiddle = new Riddle();
        myCoordinate = theCoordinate;
        myIcon = new ImageIcon();
        myUnlockedFlag = false;
        myPermaBlockedFlag = false; 
    }
    
    public Door(Riddle theRiddle, Point theCoordinate) {  // for console based game
        myRiddle = theRiddle;
        myCoordinate = theCoordinate;
        myIcon = new ImageIcon();
        myUnlockedFlag = false;
        myPermaBlockedFlag = false;
    }
    
    public Door(Riddle theRiddle, Point theCoordinate, ImageIcon theIcon) {
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
	
	void setUnlocked() { //package, want limited access so only riddle can change this
        myUnlockedFlag = true;
    }
	
	void setBlocked() { //package, want limited access so only riddle can change this
        myPermaBlockedFlag = true;
    }
}
