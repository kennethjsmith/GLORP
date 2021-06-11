package model;

import java.io.Serializable;
import java.util.Objects;

import view.GameIcon;

/**
 * The enumerated type FixtureType is the type of each fixture.
 * There are currently three fixture types used in Glorp.
 * @author Heather Finch, Katelynn Oleson, Ken Smith.
 * @version 1.0
 */
public enum FixtureType implements Serializable{
        
	CHEST("CHEST"),
	SHIP("SHIP"),
	ALTSHIP("ALTSHIP");
	
	private final String myLabel;
	private final static GameIcon CHEST_ICON = new GameIcon("src/icons/chest.png", 150, 100);
	private final static GameIcon SHIP_ICON = new GameIcon("src/icons/ship.png", 200, 150);
	private final static GameIcon ALT_SHIP_ICON = new GameIcon("src/icons/alt_ship.png", 200, 275);
	
	// Constructor for the FixtureType.
    private FixtureType(String theLabel) {
    	myLabel = Objects.requireNonNull(theLabel);
    }
    
    /**
     * Getter for the label for this FixtureType.
	 * @return String the label for this FixtureType
	 */
	public String getLabel() {
		return myLabel;
	}
	
	/**
	 * Getter for the icon for the Chest Fixture.
	 * @return GameIcon the icon for the chest fixture
	 */
	public static GameIcon getChestIcon() {
		return CHEST_ICON;
	}
	
	/**
	 * Getter for the icon for the Ship Fixture. This is the fixture that Glorp uses.
	 * @return GameIcon the icon for the ship fixture
	 */
	public static GameIcon getShipIcon() {
		return SHIP_ICON;
	}
	
	/**
	 * Getter for the icon for the alternative ship Fixture. This is the fixture ignignokt uses.
	 * @return GameIcon the icon for the alternative ship fixture
	 */
	public static GameIcon getAltShipIcon() {
		return ALT_SHIP_ICON;
	}
}
