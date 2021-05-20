package model;

import java.util.HashMap;
import java.util.Map;

import view.GameIcon;

public class Skin {
	// fields
	private SkinType myType;
	private GameIcon myMapIcon;
	private Map<IconDirection, GameIcon[]> myRoomIconMap;
	//TODO: add front/rear facing icons
	private static final int DEFAULT_SIZE = 100;

	public Skin(SkinType theType){
    	myType = theType;
        myRoomIconMap = new HashMap<>();
        
        GameIcon[] myLeftIcons = loadRoomIcons(IconDirection.LEFT, myType);
        GameIcon[] myRightIcons = loadRoomIcons(IconDirection.RIGHT, myType);
        myRoomIconMap.put(IconDirection.LEFT, myLeftIcons);
        myRoomIconMap.put(IconDirection.RIGHT, myRightIcons);
        
        myMapIcon = loadMapIcon(theType);
	}
	
	/*
	 * Generates array of icons for a given icon direction and skin type.
	 */
	private GameIcon[] loadRoomIcons(IconDirection theIconDirection, SkinType theSkinType) {
		GameIcon[] inRoomIcons = new GameIcon[5];
		for (int i = 0; i <= 4; i++) { 
		    String inFileName = getRoomIconFileName(theIconDirection.getLabel(), theSkinType.getLabel(), i);
		    inRoomIcons[i] = new GameIcon(inFileName);
		    inRoomIcons[i].resize(DEFAULT_SIZE);
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
    
    private String getMapIconFileName(String theSkinTypeLabel) {
    	
        StringBuilder sb = new StringBuilder();
        sb.append("src/icons/");
        sb.append(theSkinTypeLabel.toLowerCase());
        sb.append("_map_icon");
        sb.append(".png");
  
        return sb.toString(); 
    }
	
	public GameIcon getIcon(IconDirection theIconDirection, int theStride) {
		//System.out.println(myImageIconMap.get(theIconDirection)[theStride]);
		return myRoomIconMap.get(theIconDirection)[theStride];
	}
	
	public int getSize() {
		return DEFAULT_SIZE;
	}
}
