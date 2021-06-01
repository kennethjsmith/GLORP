package model;

import java.awt.Rectangle;

import view.GameIcon;

/**
 * This class represents Fixture GamePieces. They are immovable, and the player can walk behind them. 
 * @authors Heather Finch, Katelynn Oleson, Ken Smith
 * @version
 */
public class Fixture extends GamePiece{
	private Rectangle myBase;
	private Rectangle myIconArea;
	private FixtureType myType;
	private int myWidth;
	private int myHeight;
	private int myXCoordinate;
	private int myYCoordinate;
	private GameIcon myIcon;
	
	
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
	public Fixture(int theXCoordinate, int theYCoordinate, FixtureType theFixtureType) {
		super();
		myXCoordinate = theXCoordinate;
		myYCoordinate = theYCoordinate;
		
		if(theFixtureType == FixtureType.CHEST) {
			myIcon = FixtureType.getChestIcon();
			myIconArea = new Rectangle(theXCoordinate, theYCoordinate, myIcon.getIconWidth(), myIcon.getIconHeight());
			myBase = new Rectangle(theXCoordinate, theYCoordinate + (myIcon.getIconHeight()/2), myIcon.getIconWidth(), (myIcon.getIconHeight()/2));
		
		}
		if(theFixtureType == FixtureType.SHIP) {
			myIcon = FixtureType.getShipIcon();
			myIconArea = new Rectangle(theXCoordinate, theYCoordinate, myIcon.getIconWidth(), myIcon.getIconHeight()-50);
			myBase = new Rectangle(theXCoordinate, theYCoordinate + (myIcon.getIconHeight()-30), myIcon.getIconWidth(), 20);
		}
	}

	/**
	 * @return the myIcon
	 */
	public GameIcon getIcon() {
		return myIcon;
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

	/**
	 * @return the myXCoordinate
	 */
	public int getMyXCoordinate() {
		return myXCoordinate;
	}

	/**
	 * @return the myYCoordinate
	 */
	public int getMyYCoordinate() {
		return myYCoordinate;
	}
	
}
