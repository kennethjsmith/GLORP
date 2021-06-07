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
	private Rectangle myInteractionZone = null;
	private FixtureType myType;
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
	public Fixture(int theXCoordinate, int theYCoordinate, FixtureType theType) {
		super();
		myType = theType;
		myXCoordinate = theXCoordinate;
		myYCoordinate = theYCoordinate;
		// TODO: refactor to model Item/ItemType
		
		if(theType == FixtureType.CHEST) {
			myIcon = FixtureType.getChestIcon();
			myIconArea = new Rectangle(theXCoordinate, theYCoordinate-5, myIcon.getIconWidth(), myIcon.getIconHeight()+5);
			myBase = new Rectangle(theXCoordinate, theYCoordinate + (myIcon.getIconHeight()/2), myIcon.getIconWidth(), (myIcon.getIconHeight()/2));
			myInteractionZone = new Rectangle(theXCoordinate, theYCoordinate + myIcon.getIconHeight(), myIcon.getIconWidth(), (myIcon.getIconHeight()/5));
		}
		if(theType == FixtureType.SHIP) {
			myIcon = FixtureType.getShipIcon();
			myIconArea = new Rectangle(theXCoordinate, theYCoordinate, myIcon.getIconWidth(), myIcon.getIconHeight()-50);
			myBase = new Rectangle(theXCoordinate, theYCoordinate + (myIcon.getIconHeight()-30), myIcon.getIconWidth(), 20);
			myInteractionZone = new Rectangle(theXCoordinate, theYCoordinate + myIcon.getIconHeight(), myIcon.getIconWidth(), (myIcon.getIconHeight()/5));
		}
		
		if(theType == FixtureType.ALTSHIP) {
			myIcon = FixtureType.getAltShipIcon();
			myIconArea = new Rectangle(theXCoordinate, theYCoordinate, myIcon.getIconWidth(), myIcon.getIconHeight()-50);
			myBase = new Rectangle(theXCoordinate, theYCoordinate + (myIcon.getIconHeight()-30), myIcon.getIconWidth(), 20);
			myInteractionZone = new Rectangle(theXCoordinate, theYCoordinate + myIcon.getIconHeight(), myIcon.getIconWidth(), (myIcon.getIconHeight()/5));
		}
	}

	/**
	 * @return the myIcon
	 */
	public GameIcon getIcon() {
		return myIcon;
	}

	/**
	 * @param myIcon the myIcon to set
	 */
	public void setIcon(GameIcon theIcon) {
		myIcon = theIcon;
	}

	/**
	 * @return the myBase
	 */
	public Rectangle getBase() {
		return myBase;
	}
	
	/**
	 * @param myBase the myBase to set
	 */
	public void setBase(Rectangle theBase) {
		myBase = theBase;
	}

	/**
	 * @return the myRectangle
	 */
	public Rectangle getInteractionZone() {
		return myInteractionZone;
	}

	/**
	 * @param
	 */
	public void setInteractionZone(Rectangle theInteractionZone) {
		myInteractionZone = theInteractionZone;
	}
	
	/**
	 * @return the myIconArea
	 */
	public Rectangle getIconArea() {
		return myIconArea;
	}
	
	/**
	 * @param
	 */
	public void setIconArea(Rectangle theIconArea) {
		myIconArea = theIconArea;
	}

	/**
	 * @return the myXCoordinate
	 */
	public int getMyXCoordinate() {
		return myXCoordinate;
	}
	
	/**
	 * 
	 */
	public void setMyXCoordinate(int theXCoordinate) {
		myXCoordinate = theXCoordinate;
	}

	/**
	 * @return the myYCoordinate
	 */
	public int getMyYCoordinate() {
		return myYCoordinate;
	}
	
	/**
	 * 
	 */
	public void setMyYCoordinate(int theYCoordinate) {
		myYCoordinate = theYCoordinate;
	}

	public FixtureType getType() {
		return myType;
	}
}
