package model;

import java.awt.Dimension;
import java.util.Objects;

import view.GameIcon;

/**
 * An enumerated type representing different Item types. 
 * @authors Heather Finch, Katelynn Oleson, Ken Smith
 * @version 1.0.
 */
public enum ItemType {
	KEY("KEY"),
	GEM("GEM");
	
	private final String myLabel;
	private final static int KEY_WIDTH = 50;
	private final static int KEY_HEIGHT = 75;
	private final static int GEM_SIZE = 25;
	private final static int ITEM_PANEL_ICON_SIZE = 75;	

	private final static GameIcon KEY_ROOM_ICON = new GameIcon("src/icons/anhk_key.png", KEY_WIDTH, KEY_HEIGHT);
	private final static GameIcon GEM_ROOM_ICON = new GameIcon("src/icons/gem.png", GEM_SIZE);
	private final static GameIcon KEY_ITEM_PANEL_ICON = new GameIcon("src/icons/item_icon_key.png", ITEM_PANEL_ICON_SIZE);
	private final static GameIcon GEM_ITEM_PANEL_ICON = new GameIcon("src/icons/item_icon_gem.png", ITEM_PANEL_ICON_SIZE);

	// Private constructor for the ItemType
    private ItemType(String theLabel) {
    	myLabel = theLabel;
    }
    
    /**
     * Getter for this ItemTypes label
	 * @return This ItemTypes label, a String
	 */
	public String getLabel() {
		return myLabel;
	}
	
	/**
	 * Getter for the key width.
	 * @return the keys width, an int
	 */
	public static int getKeyWidth() {
		return KEY_WIDTH;
	}
	
	/**
	 * Getter for the key height.
	 * @return the keys height, an int
	 */
	public static int getKeyHeight() {
		return KEY_HEIGHT;
	}
	
	/**
	 * Getter for the size of the gem item.
	 * @return the size of the gem  item, an int
	 */
	public static int getGemSize() {
		return GEM_SIZE;
	}
	
	/**
	 * Getter for the room icon for this ItemType.
	 * @param ItemType the type of item that the icon is needed for
	 * @return GameIcon the room icon for the ItemType provided
	 */
	public static GameIcon getRoomIcon(ItemType theType) {
		Objects.requireNonNull(theType);
		if(theType == ItemType.GEM) return GEM_ROOM_ICON;
		else return KEY_ROOM_ICON;
	}
	
	/**
	 * Getter for the item panel icon for this ItemType.
	 * @param ItemType the type of item that the icon is needed for
	 * @return GameIcon the itemp panel icon for the ItemType provided
	 */
	public static GameIcon getItemPanelIcon(ItemType theType) {
		Objects.requireNonNull(theType);
		if(theType == ItemType.GEM) return GEM_ITEM_PANEL_ICON;
		else return KEY_ITEM_PANEL_ICON;
	}
	
	/**
	 * Getter for the dimension of this ItemType.
	 * @param ItemType the type of item that the dimension is needed for
	 * @return Dimension the dimension of the ItemType provided
	 */
	public static Dimension getDimension(ItemType theType) {
		Objects.requireNonNull(theType);
		if(theType == ItemType.GEM) return new Dimension(GEM_SIZE,GEM_SIZE);
		else return new Dimension(KEY_WIDTH,KEY_HEIGHT);
	}
	
}
