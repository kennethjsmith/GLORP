package model;

import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;

public class Skin {
	// fields
	SkinType myType;
	Map<IconDirection, ImageIcon[]> myImageIconMap;
	
	
	public Skin(SkinType theType){
    	myType = theType;
        myImageIconMap = new HashMap<>();
        
        ImageIcon[] myLeftIcons = loadMyIcons(IconDirection.LEFT, SkinType.ALIEN);
        ImageIcon[] myRightIcons = loadMyIcons(IconDirection.RIGHT, SkinType.ALIEN);
        myImageIconMap.put(IconDirection.LEFT, myLeftIcons);
        myImageIconMap.put(IconDirection.RIGHT, myRightIcons);
	}
	
	private ImageIcon[] loadMyIcons(IconDirection theIconDirection, SkinType theSkinType) {
		ImageIcon[] inIcons = new ImageIcon[5];
		for (int i = 0; i <= 4; i++) { 
		    String inFileName = getImageFileName(theIconDirection.getLabel(), theSkinType.getLabel(), i);
		    inIcons[i] = new ImageIcon(inFileName);
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
	
	public ImageIcon getIcon(IconDirection theIconDirection, int theStride) {
		return myImageIconMap.get(theIconDirection)[theStride];
	}
}
