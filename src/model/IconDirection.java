package model;

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
	
	public static IconDirection generateIconDirection(Direction theDirection) {
		IconDirection inIconDirection = DEFAULT_ICON_DIRECTION;
		
		if (theDirection == Direction.EAST || theDirection == Direction.NORTHEAST
				|| theDirection == Direction.SOUTHEAST) inIconDirection = IconDirection.RIGHT;
		if (theDirection == Direction.WEST || theDirection == Direction.NORTHWEST
				|| theDirection == Direction.SOUTHWEST) inIconDirection = IconDirection.LEFT;
		return inIconDirection;
	}
}
