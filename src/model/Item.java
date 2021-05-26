package model;

import view.GameIcon;

public class Item  extends GamePiece{
	private final static int WIDTH = 50;
	private final static int HEIGHT = 75;
	private final static GameIcon ICON = new GameIcon("src/icons/anhk_key.png", WIDTH, HEIGHT);
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
