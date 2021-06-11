/*
 * GLORP: Revenge of the Sphinx
 */

package model;

import java.awt.Rectangle;
import java.io.Serializable;
import java.util.Objects;

import view.GameIcon;

/**
 * This class represents Item and extends GamePiece.  
 * @authors Heather Finch, Katelynn Oleson, Ken Smith
 * @version 1.0.
 */
public class Item  extends GamePiece implements Serializable{
    // A serialized ID for serialization.
    private static final long serialVersionUID = 4350554592080926262L;
   	private Rectangle myIconArea;
	private GameIcon myRoomIcon;
	private GameIcon myItemPanelIcon;
	private ItemType myType;
	private final static int MAX_SIZE = 75;
	
	/**
	 * Constructs an item at the given point.
	 * @param PiecePoint the given point
	 * @param ItemType the type of this item
	 */
	public Item(PiecePoint thePoint, ItemType theType) {
		super();
		Objects.requireNonNull(thePoint);
		myType = Objects.requireNonNull(theType);
		myRoomIcon = ItemType.getRoomIcon(theType);
		myItemPanelIcon = ItemType.getItemPanelIcon(theType);
		myIconArea = new Rectangle(thePoint, ItemType.getDimension(theType));
	}

	/**
	 * Getter for this items room icon.
	 * @return GameIcon this icons room icon
	 */
	public GameIcon getRoomIcon() {
		return myRoomIcon;
	}
	
	/**
	 * Getter for this items icon for the item panel.
	 * @return GameIcon the icon for the item panel
	 */
	public GameIcon getItemPanelIcon() {
		return myItemPanelIcon;
	}

	/**
	 * Getter for this icons area.
	 * @return Rectangle this icons area
	 */
	public Rectangle getIconArea() {
		return myIconArea;
	}
	
	/**
	 * Getter for this icons ItemType.
	 * @return ItemType the type of item that this icon is
	 */
	public ItemType getType() {
		return myType;
	}

	/**
	 * Getter for the maximum size of this item.
	 * @return the max size of this item, an int
	 */
	public static int getMaxSize() {
		return MAX_SIZE;
	}

}
