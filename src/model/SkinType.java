package model;

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
