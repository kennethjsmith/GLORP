package model;

import java.util.HashMap;
import java.util.Map;

import view.GameIcon;

/**
 * This class holds all the GameIcons representing the Player.
 * @author Ken Smith, Heather Finch, Katelynn Oleson 
 * @version 
 */
public class Skin {
	// fields
	private SkinType myType;
	private GameIcon myMapIcon;
	private int myIconWidth;
	private int myIconHeight;

	GameIcon[] myLeftIcons;
	GameIcon[] myRightIcons;
	private Map<IconDirection, GameIcon[]> myRoomIconMap;
	// TODO: add front/rear facing icons
	private static final int DEFAULT_SIZE = 100;

	/**
	 * @param theType
	 */
	public Skin(SkinType theType){
    	myType = theType;
        myRoomIconMap = new HashMap<>();
        myLeftIcons = loadRoomIcons(IconDirection.LEFT, myType);
        myRightIcons = loadRoomIcons(IconDirection.RIGHT, myType);
        myRoomIconMap.put(IconDirection.LEFT, myLeftIcons);
        myRoomIconMap.put(IconDirection.RIGHT, myRightIcons);
        myMapIcon = loadMapIcon(theType);
        myIconWidth = theType.getMyWidth();
        myIconHeight = theType.getMyHeight();
	}
	
	/*
	 * Generates array of icons for a given icon direction and skin type.
	 */
	private GameIcon[] loadRoomIcons(IconDirection theIconDirection, SkinType theSkinType) {
		GameIcon[] inRoomIcons = new GameIcon[5];
		for (int i = 0; i <= 4; i++) { 
		    String inFileName = getRoomIconFileName(theIconDirection.getLabel(), theSkinType.getLabel(), i);
		    inRoomIcons[i] = new GameIcon(inFileName, theSkinType.getMyWidth(), theSkinType.getMyHeight());
		}
		return inRoomIcons;
	}

	private GameIcon loadMapIcon(SkinType theSkinType) {
		GameIcon inMapIcon = new GameIcon(getMapIconFileName(theSkinType.getLabel()));
		return inMapIcon;
	}
	
	/**
     * Builds a string representation of the objects image file name.
     * 
     * @return sb.toString() the string representation
     */
    private String getRoomIconFileName(String theDirectionLabel, String theSkinTypeLabel, int i) {
        StringBuilder sb = new StringBuilder();
        sb.append("src/icons/");
        sb.append(theDirectionLabel.toLowerCase());
        sb.append("_");
        sb.append(theSkinTypeLabel.toLowerCase());
        sb.append(i);
        sb.append(".png");
        return sb.toString(); 
    }
    
    /**
     * @param theSkinTypeLabel
     * @return
     */
    private String getMapIconFileName(String theSkinTypeLabel) {
        StringBuilder sb = new StringBuilder();
        sb.append("src/icons/");
        sb.append(theSkinTypeLabel.toLowerCase());
        sb.append("_map_icon");
        sb.append(".png");
        return sb.toString(); 
    }
	
	/**
	 * @param theIconDirection
	 * @param theStride
	 * @return
	 */
	public GameIcon getIcon(IconDirection theIconDirection, int theStride) {
		//System.out.println(myImageIconMap.get(theIconDirection)[theStride]);
		return myRoomIconMap.get(theIconDirection)[theStride];
	}
	
	/**
	 * @return
	 */
	public static int getSize() {
		return DEFAULT_SIZE;
	}

	/**
	 * @return the myIconWidth
	 */
	public int getIconWidth() {
		return myIconWidth;
	}
	
	/**
	 * @return the myIconHeight
	 */
	public int getIconHeight() {
		return myIconHeight;
	}

	/**
	 * @return the myType
	 */
	public SkinType getType() {
		return myType;
	}

	/**
	 * @param myType the myType to set
	 */
	public void setType(SkinType theType) {
		myType = theType;
		myLeftIcons = loadRoomIcons(IconDirection.LEFT, theType);
        myRightIcons = loadRoomIcons(IconDirection.RIGHT, theType);
        myRoomIconMap.put(IconDirection.LEFT, myLeftIcons);
        myRoomIconMap.put(IconDirection.RIGHT, myRightIcons);
        myMapIcon = loadMapIcon(theType);
	}
}
