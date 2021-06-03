package model;

import java.awt.Dimension;
import java.awt.Rectangle;

import view.GameIcon;

/**
 * This class represents Item and extends GamePiece.  
 * @authors Heather Finch, Katelynn Oleson, Ken Smith
 * @version
 */
public class Item  extends GamePiece{
	// fields
	private Rectangle myIconArea;
	private GameIcon myRoomIcon;
	private GameIcon myItemPanelIcon;
	
	private final static int KEY_WIDTH = 50;
	private final static int KEY_HEIGHT = 75;
	private final static int GEM_SIZE = 25;
	private final static int MAX_SIZE = 75;
	private final static int ITEM_PANEL_ICON_SIZE = 75;
	private final static GameIcon KEY_ROOM_ICON = new GameIcon("src/icons/anhk_key.png", KEY_WIDTH, KEY_HEIGHT);
	private final static GameIcon GEM_ROOM_ICON = new GameIcon("src/icons/gem.png", GEM_SIZE);
	private final static GameIcon KEY_ITEM_PANEL_ICON = new GameIcon("src/icons/item_icon_key.png", ITEM_PANEL_ICON_SIZE);
	private final static GameIcon GEM_ITEM_PANEL_ICON = new GameIcon("src/icons/item_icon_gem.png", ITEM_PANEL_ICON_SIZE);
	
	// TODO: should this be private to prevent instantiation? Fixture too?
	/**
	 * A default constructor.
	 */
	public Item() {
		super();
		myIconArea = null;
	}
	
	/**
	 * Constructs an item at the given point.
	 * @param thePoint
	 */
	public Item(PiecePoint thePoint, ItemType theType) {
		super();
		if(theType == ItemType.GEM) {
			myRoomIcon = GEM_ROOM_ICON;
			myItemPanelIcon = GEM_ITEM_PANEL_ICON;
			myIconArea = new Rectangle(thePoint, new Dimension(GEM_SIZE,GEM_SIZE));
		}
		if(theType == ItemType.KEY) {
			myRoomIcon = KEY_ROOM_ICON;
			myItemPanelIcon = KEY_ITEM_PANEL_ICON;
			myIconArea = new Rectangle(thePoint, new Dimension(KEY_WIDTH,KEY_HEIGHT));
		}
	}

	/**
	 * @return the ROOM_ICON
	 */
	public GameIcon getRoomIcon() {
		return myRoomIcon;
	}
	
	/**
	 * @return the ITEM_PANEL_ICON
	 */
	public GameIcon getItemPanelIcon() {
		return myItemPanelIcon;
	}

	/**
	 * @return the myIconArea
	 */
	public Rectangle getIconArea() {
		return myIconArea;
	}

	/**
	 * @return the max size
	 */
	public static int getMaxSize() {
		return MAX_SIZE;
	}

}
