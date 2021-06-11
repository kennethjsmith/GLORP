package model;

import java.io.Serializable;

import view.GameIcon;
/**
 * The Carpet class holds all the Icons that might represent a rooms floor. 
 * @authors Heather Finch, Katelynn Oleson, Ken Smith
 * @version 1.0
 */
public class Carpet implements Serializable{
    // Serialize the state of myIcons.
    private static final long serialVersionUID = 6671862627898128762L;
    
    // Fields
	private static final int ROOM_ICON_SIZE = 500; // Size of the icons.
	private GameIcon[] myIcons; // The icons available for the floor.
	private final static GameIcon mySpecialIcon = new GameIcon("src/icons/dark_floor.png", ROOM_ICON_SIZE); // Icon for chest room.

	/* Default constructor */
	public Carpet() {
		myIcons = loadIcons();
	}
	
	// Load all the icons into an array.
	private static GameIcon[] loadIcons() {
		GameIcon[] icons = new GameIcon[12];
		for (int i = 1; i <= 12; i++) { 
		    String inFileName = ("src/icons/floor" + i + ".png");
		    icons[i-1] = new GameIcon(inFileName, ROOM_ICON_SIZE);
		}
		return icons;
	}
	
	/**
	 * Getter for floor icons.
	 * @return myIcons the array of icons
	 */
	public GameIcon[] getFloors() {
		return myIcons;
	}

	/**
	 * Getter for the chest room icon.
	 * @return mySpecialIcon the icon for the chest room
	 */
	public static GameIcon getSpecialIcon() {
		return mySpecialIcon;
	}
}
