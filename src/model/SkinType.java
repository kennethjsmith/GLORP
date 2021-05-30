package model;
/**
 * An enumerated type for different Skins, like ALIEN, MARIO, etc.
 * @author Ken Smith, Heather Finch, Katelynn Oleson 
 * @version 
 */
public enum SkinType {
	ALIEN("ALIEN");
	
	public final String myLabel;

    private SkinType(String theLabel) {
    	myLabel = theLabel;
    }
    /**
	 * @return the myLabel
	 */
	public String getLabel() {
		return myLabel;
	}
}
