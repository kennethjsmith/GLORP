package model;

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
}
