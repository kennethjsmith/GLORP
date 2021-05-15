package model;

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
