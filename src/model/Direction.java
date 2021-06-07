package model;

import java.awt.event.KeyEvent;
import java.io.Serializable;
import java.util.Set;

/**
 * An enum class representing cardinal and ordinal directions.
 * @authors Heather Finch, Katelynn Oleson, Ken Smith
 * @version
 */
public enum Direction implements Serializable{ // .. not generating a serialize ID
    
    NORTH("N", 0, -1),
    NORTHEAST("NE", 1, -1),
	EAST("E", 1, 0),
	SOUTHEAST("SE", 1, 1),
	SOUTH("S", 0, 1),
	SOUTHWEST("SW", -1, 1),
	WEST("W", -1, 0),
	NORTHWEST("NW", -1, -1);

	private final String myLabel;
	private final int dxMultiplier;
	private final int dyMultiplier;

	
	private Direction(String theLabel, int theDXMultiplier, int theDYMultiplier) {
    	myLabel = theLabel;
    	dxMultiplier = theDXMultiplier;
    	dyMultiplier = theDYMultiplier;
    }
    
	/**
	 * @return the myLabel
	 */
	public String getLabel() {
		return myLabel;
	}
	
	/**
	 * A static method, generates a direction from a set of pressed keys.
	 * @param thePressedKeys
	 */
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
		
		inComboDirection = getComboDirection(inXDirection, inYDirection);
		return inComboDirection;
	}
	
	/**
	 * A helper method, combines x and y directions.
	 * @param theXDirection
	 * @param theYDirection
	 */
	private static Direction getComboDirection(Direction theXDirection, Direction theYDirection) {
		
		// northwest or northeast
		if(theYDirection == Direction.NORTH) {
			if(theXDirection == Direction.WEST) return Direction.NORTHWEST;
			if(theXDirection == Direction.EAST) return Direction.NORTHEAST;
		}
		// southwest or southeast
		if(theYDirection == Direction.SOUTH) {
			if(theXDirection == Direction.WEST) return Direction.SOUTHWEST;
			if(theXDirection == Direction.EAST) return Direction.SOUTHEAST;
		}
		
		// north or south
		if(theXDirection == null) return theYDirection;
		// west or east
		if(theYDirection == null) return theXDirection;
		
		return null; //TODO: fix to better way. this return should never happen
	}

	/**
	 * @return the dxMultiplier
	 */
	public int getDXMultiplier() {
		return dxMultiplier;
	}

	/**
	 * @return the dyMultiplier
	 */
	public int getDYMultiplier() {
		return dyMultiplier;
	}	
}
