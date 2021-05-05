package model;
/**
 * A room can have between 2 and 4 Passages. Each Passage has a riddle, if the riddle is answered incorrectly 
 * the passage becomes blocked
 * @author
 * @version
 */
//TODO: create subclasses (start and end rooms)
public class Room {
	Door myNorthPassage;
	Door mySouthPassage;
	Door myWestPassage;
	Door myEastPassage;
	
	Item myItem;
	int myItemXCoordinate;
	int myItemYCoordinate;
	
	private boolean visitedFlag;
	
	public Room() {
		visitedFlag = false;
	}
	
	public void setVisitedFlag() {
		visitedFlag = true;
	}
	
	public boolean isVisited() {
		return visitedFlag;
	}
	
	
}
