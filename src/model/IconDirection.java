/*
 * GLORP: Revenge of the Sphinx
 */

package model;

import java.util.Objects;

/**
 * This enumerated type represents the directions that an icon can move. 
 * @authors Heather Finch, Katelynn Oleson, Ken Smith
 * @version 1.0.
 */
public enum IconDirection {
	LEFT("LEFT"),
	RIGHT("RIGHT");
	
	private final String myLabel;
	
	// Private constructor creates the IconDirection.
    private IconDirection(String theLabel) {
    	myLabel = Objects.requireNonNull(theLabel);
    }
    
    /**
     * Getter for the label for this IconDirection.
	 * @return String this IconDirection label
	 */
	public String getLabel() {
		return myLabel;
	}
	
	/**
	 * Generates a new IconDirection.
	 * @param Direction the direction for this IconDirection
	 * @param IconDirection the current IconDirection
	 * @return IconDirection the new IconDirection
	 */
	public static IconDirection generateIconDirection(Direction theDirection, IconDirection theCurrentIconDirection) {
		IconDirection inIconDirection = Objects.requireNonNull(theCurrentIconDirection);
		
		if (theDirection == Direction.EAST || theDirection == Direction.NORTHEAST
				|| theDirection == Direction.SOUTHEAST) inIconDirection = IconDirection.RIGHT;
		if (theDirection == Direction.WEST || theDirection == Direction.NORTHWEST
				|| theDirection == Direction.SOUTHWEST) inIconDirection = IconDirection.LEFT;
		return inIconDirection;
	}
}
