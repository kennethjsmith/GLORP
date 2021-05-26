package model;

import java.awt.Rectangle;

import view.GameIcon;

public class Fixture extends GamePiece{
	private final static int ICON_WIDTH = 150;
	private final static int ICON_HEIGHT = 125;
	private final static GameIcon ICON = new GameIcon("src/icons/chest_closed.png", ICON_WIDTH, ICON_HEIGHT);
	private Rectangle myRectangle; // vicinity
	
	private int myIconXCoordinate;
	private int myIconYCoordinate;

	
	public Fixture() {
		super();
	}
	
	public Fixture(int theXCoordinate, int theYCoordinate) {
		super();
		myIconXCoordinate = theXCoordinate;
		myIconYCoordinate = theYCoordinate;
		myRectangle = new Rectangle(theXCoordinate, theYCoordinate + (ICON_HEIGHT/2), ICON_WIDTH, ICON_HEIGHT/2);
	}

	/**
	 * @return the myIcon
	 */
	public GameIcon getIcon() {
		return ICON;
	}

	/**
	 * @return the myRectangle
	 */
	public Rectangle getRectangle() {
		return myRectangle;
	}
	
}
