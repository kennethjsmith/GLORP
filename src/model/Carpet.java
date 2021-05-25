package model;

import view.GameIcon;

public class Carpet {

	private static final int ROOM_ICON_SIZE = 500;
	private GameIcon[] myIcons;
	private final static GameIcon mySpecialIcon = new GameIcon("src/icons/dark_floor.png", ROOM_ICON_SIZE);

	
	public Carpet() {
		myIcons = loadIcons();
	}
	
	private static GameIcon[] loadIcons() {
		GameIcon[] icons = new GameIcon[12];
		for (int i = 1; i <= 12; i++) { 
		    String inFileName = ("src/icons/floor" + i + ".png");
		    icons[i-1] = new GameIcon(inFileName, ROOM_ICON_SIZE);
		}
		return icons;
	}
	
	/**
	 * @return the fLOORS
	 */
	public GameIcon[] getFloors() {
		return myIcons;
	}

	/**
	 * @return the mySpecialIcon
	 */
	public static GameIcon getSpecialIcon() {
		return mySpecialIcon;
	}
}
