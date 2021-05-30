package model;
/**
 * An enumerated type representing different Item types. 
 * @authors Heather Finch, Katelynn Oleson, Ken Smith
 * @version
 */
public enum ItemType {
	KEY("KEY");
	
	public final String myLabel;

    private ItemType(String theLabel) {
    	myLabel = theLabel;
    }
    /**
	 * @return the myLabel
	 */
	public String getLabel() {
		return myLabel;
	}
}
