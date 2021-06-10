package model;

import java.awt.Rectangle;
import java.io.Serializable;
import java.util.Objects;

import view.GameIcon;

/**
 * This class represents Fixture GamePieces. They are immovable, and the player can walk behind them. 
 * @authors Heather Finch, Katelynn Oleson, Ken Smith
 * @version 1.0
 */
public class Fixture extends GamePiece implements Serializable{	
	// Serialized ID for serialization.
    private static final long serialVersionUID = -5492848388078336772L;
    
    private Rectangle myBase; // The base is an area that cannot be walked over. 
	private Rectangle myIconArea;
	private Rectangle myInteractionZone = null;
	private FixtureType myType;
	private int myXCoordinate;
	private int myYCoordinate;
	private GameIcon myIcon;
	
	/**
	 * Constructs a fixture at the given coordinates.
	 * @param theXCoordinate the x coordinate of the fixture
	 * @param theYCoordinate the y coordinate of the fixture
	 * @param FixtureType the type of fixture
	 */
	public Fixture(int theXCoordinate, int theYCoordinate, FixtureType theType) {
		super();
		myType = Objects.requireNonNull(theType);
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
	 * Getter for the fixtures icon.
	 * @return GameIcon the icon for this fixture
	 */
	public GameIcon getIcon() {
		return myIcon;
	}

	/**
	 * Setter for the fixtures icon.
	 * @param GameIcon the new icon
	 */
	public void setIcon(GameIcon theIcon) {
		myIcon = Objects.requireNonNull(theIcon);
	}

	/**
	 * Getter for the base of the rectangle.
	 * @return Rectangle this fixtures base
	 */
	public Rectangle getBase() {
		return myBase;
	}
	
	/**
	 * Setter for the base of the rectangle.
	 * @param Rectangle the base of the fixture
	 */
	public void setBase(Rectangle theBase) {
		myBase = Objects.requireNonNull(theBase);
	}

	/**
	 * Getter for this fixtures interaction zone.
	 * @return Rectangle the interaction zone
	 */
	public Rectangle getInteractionZone() {
		return myInteractionZone;
	}

	/**
	 * Setter for this fixtures interaction zone.
	 * @param Rectangle the interaction zone
	 */
	public void setInteractionZone(Rectangle theInteractionZone) {
		myInteractionZone = Objects.requireNonNull(theInteractionZone);
	}
	
	/**
	 * Getter for this fixtures icon area.
	 * @return Rectangle the fixtures icon area
	 */
	public Rectangle getIconArea() {
		return myIconArea;
	}
	
	/**
	 * Setter for this fixtures icon area.
	 * @param Rectangle the new icon area
	 */
	public void setIconArea(Rectangle theIconArea) {
		myIconArea = Objects.requireNonNull(theIconArea);
	}

	/**
	 * Getter for the x coordiante of this fixture.
	 * @return the x coordiante of this fixture
	 */
	public int getMyXCoordinate() {
		return myXCoordinate;
	}
	
	/**
	 * Setter for the x coordinate of this fixture.
	 * @param theXCoordinate the new x coordinate for the fixture
	 */
	public void setMyXCoordinate(int theXCoordinate) {
		myXCoordinate = theXCoordinate;
	}

	/**
	 * Getter for the y coordiante of this fixture.
	 * @return the y coordinate of this fixture
	 */
	public int getMyYCoordinate() {
		return myYCoordinate;
	}
	
	/**
	 * Setter for the y coordinate of this fixture.
	 * @param theYCoordinate the new y coordinate for the fixture
	 */
	public void setMyYCoordinate(int theYCoordinate) {
		myYCoordinate = theYCoordinate;
	}

	
	/**
	 * Getter for the type of fixture that this is.
	 * @return FixtureType the type of fixture
	 */
	public FixtureType getType() {
		return myType;
	}
}
