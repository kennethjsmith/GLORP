package model;
/**
 * A Passage has a riddle, answering correctly unlocks the passage
 * answering incorrectly permanently blocks the passage.
 * @author
 * @version
 */
public class Passage {
	// fields
	Riddle myRiddle;
	Room myNextRoom;
	
	Boolean locked;
	Boolean permaBlocked;
	
	
	public Passage() {	
	}
	
	public Passage(Riddle theRiddle) {
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
