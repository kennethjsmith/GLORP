package model;

import java.awt.event.KeyEvent;
import java.util.Set;

public enum Direction {
	NORTH("N"),
	NORTHEAST("NE"),
	EAST("E"),
	SOUTHEAST("SE"),
	SOUTH("S"),
	SOUTHWEST("SW"),
	WEST("W"),
	NORTHWEST("NW");

	public final String myLabel;


	private Direction(String theLabel) {
    	myLabel = theLabel;
    }
    
	/**
	 * @return the myLabel
	 */
	public String getLabel() {
		return myLabel;
	}
	
	public static Direction generateDirection(Set<Integer> thePressedKeys) {
		Direction inXDirection = null;
		Direction inYDirection = null;
		Direction inComboDirection;
		// going west
		if((thePressedKeys.contains(KeyEvent.VK_A) || thePressedKeys.contains(KeyEvent.VK_LEFT)) &&
				(!thePressedKeys.contains(KeyEvent.VK_D) || !thePressedKeys.contains(KeyEvent.VK_RIGHT))) {
			inXDirection = Direction.WEST;
		}
		// going east
		if((thePressedKeys.contains(KeyEvent.VK_D) || thePressedKeys.contains(KeyEvent.VK_RIGHT)) &&
				(!thePressedKeys.contains(KeyEvent.VK_A) || !thePressedKeys.contains(KeyEvent.VK_LEFT))) {
			inXDirection = Direction.EAST;
		}
		// going north
		if((thePressedKeys.contains(KeyEvent.VK_W) || thePressedKeys.contains(KeyEvent.VK_UP)) &&
				(!thePressedKeys.contains(KeyEvent.VK_S) || !thePressedKeys.contains(KeyEvent.VK_DOWN))) {
			inYDirection = Direction.NORTH;
		}
		// going south
		if((thePressedKeys.contains(KeyEvent.VK_S) || thePressedKeys.contains(KeyEvent.VK_DOWN)) &&
				(!thePressedKeys.contains(KeyEvent.VK_W) || !thePressedKeys.contains(KeyEvent.VK_UP))) {
			inYDirection = Direction.SOUTH;
		}
		//System.out.println(inXDirection);
		//System.out.println(inYDirection);
		inComboDirection = getComboDirection(inXDirection, inYDirection);
		//System.out.println(inComboDirection);
		return inComboDirection;
	}

	public static Direction getComboDirection(Direction theXDirection, Direction theYDirection) {
		
		//northwest or northeast
		if(theYDirection == Direction.NORTH) {
			if(theXDirection == Direction.WEST) return Direction.NORTHWEST;
			if(theXDirection == Direction.EAST) return Direction.NORTHEAST;
		}
		//southwest or southeast
		if(theYDirection == Direction.SOUTH) {
			if(theXDirection == Direction.WEST) return Direction.SOUTHWEST;
			if(theXDirection == Direction.EAST) return Direction.SOUTHEAST;
		}
		
		//north or south
		if(theXDirection == null) return theYDirection;
		//west or east
		if(theYDirection == null) return theXDirection;
		
		return null; //TODO: fix to better way. this return should never happen
	}	
}
