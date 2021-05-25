package model;

import view.GameIcon;

public class Carpet {
	private GameIcon[] myIcons;

	private static final int ROOM_ICON_SIZE = 500;
	
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
}
