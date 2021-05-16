package model;

import java.awt.Image;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;

import view.GameIcon;

public class Skin {
	// fields
	private SkinType myType;
	private Map<IconDirection, GameIcon[]> myImageIconMap;
	//TODO: add front/rear facing icons
	private static final int DEFAULT_SIZE = 100;
	
	
	public Skin(SkinType theType){
    	myType = theType;
        myImageIconMap = new HashMap<>();
        
        GameIcon[] myLeftIcons = loadMyIcons(IconDirection.LEFT, myType);
        GameIcon[] myRightIcons = loadMyIcons(IconDirection.RIGHT, myType);
        myImageIconMap.put(IconDirection.LEFT, myLeftIcons);
        myImageIconMap.put(IconDirection.RIGHT, myRightIcons);
	}
	
	/*
	 * Generates array of icons for a given icon direction and skin type.
	 */
	private GameIcon[] loadMyIcons(IconDirection theIconDirection, SkinType theSkinType) {
		GameIcon[] inIcons = new GameIcon[5];
		for (int i = 0; i <= 4; i++) { 
		    String inFileName = getImageFileName(theIconDirection.getLabel(), theSkinType.getLabel(), i);
		    inIcons[i] = new GameIcon(inFileName);
		    inIcons[i].resize(DEFAULT_SIZE);
		}
		return inIcons;
	}

	/**
     * Builds a string representation of the objects image file name.
     * 
     * @return sb.toString() the string representation
     */
    private String getImageFileName(String theDirectionLabel, String theSkinTypeLabel, int i) {
    	
        StringBuilder sb = new StringBuilder();
       
        sb.append(theDirectionLabel.toLowerCase());
        sb.append("_");
        sb.append(theSkinTypeLabel.toLowerCase());
        sb.append("i");
        sb.append(".png");
  
        return sb.toString(); 
    }		
	
	public GameIcon getIcon(IconDirection theIconDirection, int theStride) {
		return myImageIconMap.get(theIconDirection)[theStride];
	}
}
