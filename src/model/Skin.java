/*
 * GLORP: Revenge of the Sphinx
 */

package model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import view.GameIcon;

/**
 * This class holds all the GameIcons representing the Player.
 * @author Ken Smith, Heather Finch, Katelynn Oleson 
 * @version 1.0.
 */
public class Skin implements Serializable {
	// A serialized ID for serialization.
    private static final long serialVersionUID = -7677582022891169636L;
   	private SkinType myType;
   	
	private GameIcon myMapIcon;
	private int myIconWidth;
	private int myIconHeight;

	GameIcon[] myLeftIcons;
	GameIcon[] myRightIcons;
	private Map<IconDirection, GameIcon[]> myRoomIconMap;
	private static final int DEFAULT_SIZE = 100; // Size of the player icon.

	/**
	 * Creates a new Skin.
	 * @param SkinType The type of skin we are creating
	 */
	public Skin(SkinType theType){
    	myType = Objects.requireNonNull(theType);
        myRoomIconMap = new HashMap<>();
        myLeftIcons = loadRoomIcons(IconDirection.LEFT, myType);
        myRightIcons = loadRoomIcons(IconDirection.RIGHT, myType);
        myRoomIconMap.put(IconDirection.LEFT, myLeftIcons);
        myRoomIconMap.put(IconDirection.RIGHT, myRightIcons);
        myMapIcon = loadMapIcon(theType);
        myIconWidth = theType.getMyWidth();
        myIconHeight = theType.getMyHeight();
	}
	
	// Generates array of icons for a given icon direction and skin type.
	private GameIcon[] loadRoomIcons(IconDirection theIconDirection, SkinType theSkinType) {
		GameIcon[] inRoomIcons = new GameIcon[5];
		for (int i = 0; i <= 4; i++) { 
		    String inFileName = getRoomIconFileName(theIconDirection.getLabel(), theSkinType.getLabel(), i);
		    inRoomIcons[i] = new GameIcon(inFileName, theSkinType.getMyWidth(), theSkinType.getMyHeight());
		}
		return inRoomIcons;
	}

	// Loads the map icon based on the SkinType.
	private GameIcon loadMapIcon(SkinType theSkinType) {
		Objects.requireNonNull(theSkinType);
		GameIcon inMapIcon = new GameIcon(getMapIconFileName(theSkinType.getLabel()));
		return inMapIcon;
	}
	
	// Builds a string representation of the objects image file name
    private String getRoomIconFileName(String theDirectionLabel, String theSkinTypeLabel, int i) {
    	Objects.requireNonNull(theDirectionLabel);
    	Objects.requireNonNull(theSkinTypeLabel);
        StringBuilder sb = new StringBuilder();
        sb.append("src/icons/");
        sb.append(theDirectionLabel.toLowerCase());
        sb.append("_");
        sb.append(theSkinTypeLabel.toLowerCase());
        sb.append(i);
        sb.append(".png");
        return sb.toString(); 
    }
    
    // Returns the icon name for the map icon based on the skin type.
    private String getMapIconFileName(String theSkinTypeLabel) {
    	Objects.requireNonNull(theSkinTypeLabel);
        StringBuilder sb = new StringBuilder();
        sb.append("src/icons/");
        sb.append(theSkinTypeLabel.toLowerCase());
        sb.append("_map_icon");
        sb.append(".png");
        return sb.toString(); 
    }
	
	/**
	 * A getter for this skins icon.
	 * @param Direction The Direction the icon should be facing.
	 * @param The stride of the icon, an int
	 * @return GameIcon The correct icon for this skin
	 */
	public GameIcon getIcon(IconDirection theIconDirection, int theStride) {
		Objects.requireNonNull(theIconDirection);
		Objects.requireNonNull(theStride);
		return myRoomIconMap.get(theIconDirection)[theStride];
	}
	
	/**
	 * Getter for the size of this skins icon.
	 * @return The size of the skin, an int
	 */
	public static int getSize() {
		return DEFAULT_SIZE;
	}

	/**
	 * Getter for the width of the icon.
	 * @return The width of the icon
	 */
	public int getIconWidth() {
		return myIconWidth;
	}
	
	/**
	 * Getter for the height of the icon.
	 * @return The height of the icon
	 */
	public int getIconHeight() {
		return myIconHeight;
	}

	/**
	 * Getter for the SkinType.
	 * @return SkinType The type of Skin this is
	 */
	public SkinType getType() {
		return myType;
	}

	/**
	 * Setter for the SkinType
	 * @param SkinType The myType to set
	 */
	public void setType(SkinType theType) {
		myType = Objects.requireNonNull(theType);
		myLeftIcons = loadRoomIcons(IconDirection.LEFT, theType);
        myRightIcons = loadRoomIcons(IconDirection.RIGHT, theType);
        myRoomIconMap.put(IconDirection.LEFT, myLeftIcons);
        myRoomIconMap.put(IconDirection.RIGHT, myRightIcons);
        myMapIcon = loadMapIcon(theType);
	}
}
