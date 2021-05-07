package model;

public class InRoomCoordinates {
	//fields
	private int myXCoordinate;
	private int myYCoordinate;
	
	public InRoomCoordinates() {
		myXCoordinate = 0;
		myYCoordinate = 0;
	}
	
	public InRoomCoordinates(int theXCoordinate, int theYCoordinate) {
		myXCoordinate = theXCoordinate;
		myYCoordinate = theYCoordinate;
	}
	
	/**
	 * @return the xCoordinate
	 */
	public int getxCoordinate() {
		return myXCoordinate;
	}
	/**
	 * @param xCoordinate the xCoordinate to set
	 */
	public void setxCoordinate(int theXCoordinate) {
		myXCoordinate = theXCoordinate;
	}
	/**
	 * @return the yCoordinate
	 */
	public int getyCoordinate() {
		return myYCoordinate;
	}
	/**
	 * @param yCoordinate the yCoordinate to set
	 */
	public void setyCoordinate(int theYCoordinate) {
		myYCoordinate = theYCoordinate;
	}
	
}
