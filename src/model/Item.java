package model;

import view.GameIcon;

public class Item  extends GamePiece{
	private final static int SIZE = 70;
	private final static GameIcon ICON = new GameIcon("src/icons/anhk_key.png", SIZE);
	private GameIcon myRoomIcon;
	
	public Item() {
		super();
		myRoomIcon = ICON;
	}

	/**
	 * @return the myRoomIcon
	 */
	public GameIcon getRoomIcon() {
		return myRoomIcon;
	}
}
