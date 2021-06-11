package model;

import java.util.Objects;

/**
 * An enumerated type for different Skins, like ALIEN, MARIO, etc.
 * @author Ken Smith, Heather Finch, Katelynn Oleson 
 * @version 1.0.
 */
public enum SkinType {
	MOONINITE("IGNIGNOKT", 110, 100),
	ALIEN("ALIEN", 100, 100);
	
	private final String myLabel;
	private final int myWidth;
	private final int myHeight;
	
	// Private constructor creates the SkinType based on the label, it's width, and height.
    private SkinType(String theLabel, int theWidth, int theHeight) {
    	myLabel = Objects.requireNonNull(theLabel);
    	myWidth = Objects.requireNonNull(theWidth);
    	myHeight = Objects.requireNonNull(theHeight);
    }
    
    /**
     * Getter for the SkinTypes label.
	 * @return String The label for the SkinType
	 */
	public String getLabel() {
		return myLabel;
	}
	
	/**
	 * Getter for the SkinTypes width.
	 * @return The width of the SkinType, an int
	 */
	public int getMyWidth() {
		return myWidth;
	}
	
	/**
	 * Getter for the SkinTypes height.
	 * @return The SkinTypes height, an int
	 */
	public int getMyHeight() {
		return myHeight;
	}
}
