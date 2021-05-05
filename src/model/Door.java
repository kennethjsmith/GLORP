package model;
/**
 * A Passage has a riddle, answering correctly unlocks the passage
 * answering incorrectly permanently blocks the passage.
 * @author
 * @version
 */
public class Door {
	// fields
	Riddle myRiddle;
	Room myNextRoom;
	
	Boolean locked;
	Boolean permaBlocked;
	
	
	public Door() {	
	}
	
	public Door(Riddle theRiddle) {
		myRiddle = theRiddle;
		locked = false;
		permaBlocked = false;
	}
	
	public void setBlocked() {
		permaBlocked = true;
	}
	
	public Boolean isBlocked() {
		return permaBlocked;
	}
	
	/**
	 * @return the myRiddle
	 */
	public Riddle getMyRiddle() {
		return myRiddle;
	}
}
