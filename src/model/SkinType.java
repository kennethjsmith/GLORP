package model;
/**
 * An enumerated type for different Skins, like ALIEN, MARIO, etc.
 * @author Ken Smith, Heather Finch, Katelynn Oleson 
 * @version 
 */
public enum SkinType {
	MOONINITE("IGNIGNOKT", 110, 100),
	ALIEN("ALIEN", 100, 100);
	
	private final String myLabel;
	private final int myWidth;
	private final int myHeight;
	

    private SkinType(String theLabel, int theWidth, int theHeight) {
    	myLabel = theLabel;
    	myWidth = theWidth;
    	myHeight = theHeight;
    }
    /**
	 * @return the myLabel
	 */
	public String getLabel() {
		return myLabel;
	}
	/**
	 * @return the myWidth
	 */
	public int getMyWidth() {
		return myWidth;
	}
	/**
	 * @return the myHeight
	 */
	public int getMyHeight() {
		return myHeight;
	}
}
