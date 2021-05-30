package model;

import java.awt.Rectangle;

import view.GameIcon;

/**
 * This class represents Fixture GamePieces. They are immovable, and the player can walk behind them. 
 * @authors Heather Finch, Katelynn Oleson, Ken Smith
 * @version
 */
public class Fixture extends GamePiece{
	private final static int ICON_WIDTH = 150;
	private final static int ICON_HEIGHT = 100;
	private final static GameIcon ICON = new GameIcon("src/icons/chest_closed.png", ICON_WIDTH, ICON_HEIGHT);
	private Rectangle myBase;
	private Rectangle myIconArea;
	
	/**
	 * A default constructor.
	 */
	// TODO: initialize Rectangles???
	public Fixture() {
		super();
	}
	
	/**
	 * Constructs a fixture at the given coordinates.
	 * @param theXCoordinate
	 * @param theYCoordinate
	 */
	public Fixture(int theXCoordinate, int theYCoordinate) {
		super();
		myIconArea = new Rectangle(theXCoordinate, theYCoordinate, ICON_WIDTH, ICON_HEIGHT);
		myBase = new Rectangle(theXCoordinate, theYCoordinate + (ICON_HEIGHT/2), ICON_WIDTH, (ICON_HEIGHT/2));
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
	public Rectangle getBase() {
		return myBase;
	}

	/**
	 * @return the myIconArea
	 */
	public Rectangle getIconArea() {
		return myIconArea;
	}
	
}
