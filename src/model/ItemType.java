package model;

import java.awt.Dimension;

import view.GameIcon;

/**
 * An enumerated type representing different Item types. 
 * @authors Heather Finch, Katelynn Oleson, Ken Smith
 * @version
 */
public enum ItemType {
	KEY("KEY"),
	GEM("GEM");
	
	public final String myLabel;
	
	private final static int KEY_WIDTH = 50;
	private final static int KEY_HEIGHT = 75;
	private final static int GEM_SIZE = 25;
	private final static int ITEM_PANEL_ICON_SIZE = 75;	

	private final static GameIcon KEY_ROOM_ICON = new GameIcon("src/icons/anhk_key.png", KEY_WIDTH, KEY_HEIGHT);
	private final static GameIcon GEM_ROOM_ICON = new GameIcon("src/icons/gem.png", GEM_SIZE);
	private final static GameIcon KEY_ITEM_PANEL_ICON = new GameIcon("src/icons/item_icon_key.png", ITEM_PANEL_ICON_SIZE);
	private final static GameIcon GEM_ITEM_PANEL_ICON = new GameIcon("src/icons/item_icon_gem.png", ITEM_PANEL_ICON_SIZE);

    private ItemType(String theLabel) {
    	myLabel = theLabel;
    }
    /**
	 * @return the myLabel
	 */
	public String getLabel() {
		return myLabel;
	}
	/**
	 * @return the keyWidth
	 */
	public static int getKeyWidth() {
		return KEY_WIDTH;
	}
	/**
	 * @return the keyHeight
	 */
	public static int getKeyHeight() {
		return KEY_HEIGHT;
	}
	/**
	 * @return the gemSize
	 */
	public static int getGemSize() {
		return GEM_SIZE;
	}
	/**
	 * @return 
	 */
	public static GameIcon getRoomIcon(ItemType theType) {
		if(theType == ItemType.GEM) return GEM_ROOM_ICON;
		else return KEY_ROOM_ICON;
	}
	
	/**
	 * @return 
	 */
	public static GameIcon getItemPanelIcon(ItemType theType) {
		if(theType == ItemType.GEM) return GEM_ITEM_PANEL_ICON;
		else return KEY_ITEM_PANEL_ICON;
	}
	
	/**
	 * @return the keyItemPanelIcon
	 */
	public static Dimension getDimension(ItemType theType) {
		if(theType == ItemType.GEM) return new Dimension(GEM_SIZE,GEM_SIZE);
		else return new Dimension(KEY_WIDTH,KEY_HEIGHT);
	}
	
}
