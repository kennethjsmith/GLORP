package model;

import view.GameIcon;

public enum FixtureType {
	CHEST("CHEST"),
	SHIP("SHIP");
	
	public final String myLabel;
	
	private final static GameIcon CHEST_ICON = new GameIcon("src/icons/chest_closed.png", 150, 100);
	private final static GameIcon SHIP_ICON = new GameIcon("src/icons/ship.png", 200, 250);

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
}
