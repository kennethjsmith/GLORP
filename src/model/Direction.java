package model;

import java.awt.event.KeyEvent;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

/**
 * An enum class representing cardinal and ordinal directions.
 * @authors Heather Finch, Katelynn Oleson, Ken Smith
 * @version 1.0
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

	// Fields
	private final String myLabel;
	private final int dxMultiplier;
	private final int dyMultiplier;

	// Private constructor creates every direction with their label and multipliers. 
	private Direction(String theLabel, int theDXMultiplier, int theDYMultiplier) {
    	myLabel = theLabel;
    	dxMultiplier = theDXMultiplier;
    	dyMultiplier = theDYMultiplier;
    }
    
	/**
	 * Getter for the directions label.
	 * @return the myLabel
	 */
	public String getLabel() {
		return myLabel;
	}
	
	/**
	 * A static method, generates a direction from a set of pressed keys.
	 * @param thePressedKeys the keys that have been pressed
	 */
	public static Direction generateDirection(Set<Integer> thePressedKeys) {
		Objects.requireNonNull(thePressedKeys);
		Direction inXDirection = null;
		Direction inYDirection = null;
		Direction inComboDirection;
		
		// Going west.
		if((thePressedKeys.contains(KeyEvent.VK_A) || thePressedKeys.contains(KeyEvent.VK_LEFT)) &&
				(!thePressedKeys.contains(KeyEvent.VK_D) || !thePressedKeys.contains(KeyEvent.VK_RIGHT))) {
			inXDirection = Direction.WEST;
		}
		// Going east.
		if((thePressedKeys.contains(KeyEvent.VK_D) || thePressedKeys.contains(KeyEvent.VK_RIGHT)) &&
				(!thePressedKeys.contains(KeyEvent.VK_A) || !thePressedKeys.contains(KeyEvent.VK_LEFT))) {
			inXDirection = Direction.EAST;
		}
		// Going north.
		if((thePressedKeys.contains(KeyEvent.VK_W) || thePressedKeys.contains(KeyEvent.VK_UP)) &&
				(!thePressedKeys.contains(KeyEvent.VK_S) || !thePressedKeys.contains(KeyEvent.VK_DOWN))) {
			inYDirection = Direction.NORTH;
		}
		// Going south.
		if((thePressedKeys.contains(KeyEvent.VK_S) || thePressedKeys.contains(KeyEvent.VK_DOWN)) &&
				(!thePressedKeys.contains(KeyEvent.VK_W) || !thePressedKeys.contains(KeyEvent.VK_UP))) {
			inYDirection = Direction.SOUTH;
		}
		
		inComboDirection = getComboDirection(inXDirection, inYDirection);
		return inComboDirection;
	}
	
	/**
	 * A helper method, combines x and y directions.
	 * @param theXDirection the x direction Player is currently going.
	 * @param theYDirection the y direction Player is currently going.
	 */
	private static Direction getComboDirection(Direction theXDirection, Direction theYDirection) {
		
		// Northwest or northeast.
		if(theYDirection == Direction.NORTH) {
			if(theXDirection == Direction.WEST) return Direction.NORTHWEST;
			if(theXDirection == Direction.EAST) return Direction.NORTHEAST;
		}
		// Southwest or southeast.
		if(theYDirection == Direction.SOUTH) {
			if(theXDirection == Direction.WEST) return Direction.SOUTHWEST;
			if(theXDirection == Direction.EAST) return Direction.SOUTHEAST;
		}
		
		if(theXDirection == null) return theYDirection; // North or south.
		if(theYDirection == null) return theXDirection; // West or east.
		
		return null; //TODO: fix to better way. this return should never happen
	}

	/**
	 * Getter for the dx multiplier.
	 * @return the dxMultiplier
	 */
	public int getDXMultiplier() {
		return dxMultiplier;
	}

	/**
	 * Getter for the dy multiplier.
	 * @return the dyMultiplier
	 */
	public int getDYMultiplier() {
		return dyMultiplier;
	}	
}
