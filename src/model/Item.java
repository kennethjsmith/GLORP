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
	private ItemType myType;
	
	private final static int MAX_SIZE = 75;
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
		myType = theType;
		myRoomIcon = ItemType.getRoomIcon(theType);
		myItemPanelIcon = ItemType.getItemPanelIcon(theType);
		myIconArea = new Rectangle(thePoint, ItemType.getDimension(theType));
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
	 * @return myType my ItemType
	 */
	public ItemType getType() {
		return myType;
	}

	/**
	 * @return the max size
	 */
	public static int getMaxSize() {
		return MAX_SIZE;
	}

}
