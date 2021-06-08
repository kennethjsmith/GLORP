package model;

import java.io.Serializable;

import view.GameIcon;

public enum FixtureType implements Serializable{
    
    // no generated ID... dont need to serialize enums? 
    
	CHEST("CHEST"),
	SHIP("SHIP"),
	ALTSHIP("ALTSHIP");
	
	public final String myLabel;
	
	private final static GameIcon CHEST_ICON = new GameIcon("src/icons/chest.png", 150, 100);
	private final static GameIcon SHIP_ICON = new GameIcon("src/icons/ship.png", 200, 150);
	private final static GameIcon ALT_SHIP_ICON = new GameIcon("src/icons/alt_ship.png", 200, 275);
	
    private FixtureType(String theLabel) {
    	myLabel = theLabel;
    }
    /**
	 * @return the myLabel
	 */
	public String getLabel() {
		return myLabel;
	}
	/**
	 * @return the chestIcon
	 */
	public static GameIcon getChestIcon() {
		return CHEST_ICON;
	}
	
	/**
	 * @return the shipIcon
	 */
	public static GameIcon getShipIcon() {
		return SHIP_ICON;
	}
	
	/**
	 * @return the shipIcon
	 */
	public static GameIcon getAltShipIcon() {
		return ALT_SHIP_ICON;
	}
}
