package model;

import java.awt.event.KeyEvent;
import java.util.Set;

public enum Direction {
	NORTH("N", 0, -1),
	NORTHEAST("NE", 1, -1),
	EAST("E", 1, 0),
	SOUTHEAST("SE", 1, 1),
	SOUTH("S", 0, 1),
	SOUTHWEST("SW", -1, 1),
	WEST("W", -1, 0),
	NORTHWEST("NW", -1, -1);

	public final String myLabel;
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
	//TODO: magic number for wall boundary, consider if this is more controller functionality and 
	//change location for alot of this code without breaking current functionality
	public static Direction generateDirection(Set<Integer> thePressedKeys, Player thePlayer, Room theRoom) {
		Direction inXDirection = null;
		Direction inYDirection = null;
		Direction inComboDirection;
		// going west
		if((thePressedKeys.contains(KeyEvent.VK_A) || thePressedKeys.contains(KeyEvent.VK_LEFT)) &&
				(!thePressedKeys.contains(KeyEvent.VK_D) || !thePressedKeys.contains(KeyEvent.VK_RIGHT)) &&
					thePlayer.getCoordinate().getX() >= 5) {
			inXDirection = Direction.WEST;
		}
		// going east
		if((thePressedKeys.contains(KeyEvent.VK_D) || thePressedKeys.contains(KeyEvent.VK_RIGHT)) &&
				(!thePressedKeys.contains(KeyEvent.VK_A) || !thePressedKeys.contains(KeyEvent.VK_LEFT)) &&
				thePlayer.getCoordinate().getX() <= theRoom.getSize() -thePlayer.getSkin().getSize() - 5) {
			inXDirection = Direction.EAST;
		}
		// going north
		if((thePressedKeys.contains(KeyEvent.VK_W) || thePressedKeys.contains(KeyEvent.VK_UP)) &&
				(!thePressedKeys.contains(KeyEvent.VK_S) || !thePressedKeys.contains(KeyEvent.VK_DOWN)) &&
				thePlayer.getCoordinate().getY() >= 5) {
			inYDirection = Direction.NORTH;
		}
		// going south
		if((thePressedKeys.contains(KeyEvent.VK_S) || thePressedKeys.contains(KeyEvent.VK_DOWN)) &&
				(!thePressedKeys.contains(KeyEvent.VK_W) || !thePressedKeys.contains(KeyEvent.VK_UP)) &&
				thePlayer.getCoordinate().getY() <= theRoom.getSize() - thePlayer.getSkin().getSize() - 5) {
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
