package model;
/**
 * This enumerated type represents the directions that an icon can move. 
 * @authors Heather Finch, Katelynn Oleson, Ken Smith
 * @version
 */
public enum IconDirection {
	LEFT("LEFT"),
	RIGHT("RIGHT");
	
	public final String myLabel;
	public final static IconDirection DEFAULT_ICON_DIRECTION = RIGHT;

    private IconDirection(String theLabel) {
    	myLabel = theLabel;
    }
    /**
	 * @return the myLabel
	 */
	public String getLabel() {
		return myLabel;
	}
	
	/**
	 * Generates a new icon Direction.
	 * @param theDirection
	 * @param theCurrentIconDirection
	 * @return
	 */
	public static IconDirection generateIconDirection(Direction theDirection, IconDirection theCurrentIconDirection) {
		IconDirection inIconDirection = theCurrentIconDirection;
		
		if (theDirection == Direction.EAST || theDirection == Direction.NORTHEAST
				|| theDirection == Direction.SOUTHEAST) inIconDirection = IconDirection.RIGHT;
		if (theDirection == Direction.WEST || theDirection == Direction.NORTHWEST
				|| theDirection == Direction.SOUTHWEST) inIconDirection = IconDirection.LEFT;
		return inIconDirection;
	}
}
