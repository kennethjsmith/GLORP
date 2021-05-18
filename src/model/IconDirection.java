package model;

public enum IconDirection {
	LEFT("LEFT"),
	RIGHT("RIGHT");
	
	public final String myLabel;

    private IconDirection(String theLabel) {
    	myLabel = theLabel;
    }
    /**
	 * @return the myLabel
	 */
	public String getLabel() {
		return myLabel;
	}
}
