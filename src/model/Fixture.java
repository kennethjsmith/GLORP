package model;

import view.GameIcon;

public class Fixture extends GamePiece{
	private final static int SIZE = 200;
	private final static GameIcon ICON = new GameIcon("src/icons/chest_closed.png", 150, 125);

		
	public Fixture() {
		super();
	}


	/**
	 * @return the myIcon
	 */
	public GameIcon getIcon() {
		return ICON;
	}
	
}
