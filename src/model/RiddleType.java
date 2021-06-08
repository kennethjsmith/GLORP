package model;

/**
 * An enum class represents the different types of riddles. 
 * @author Heather Finch, Katelynn Oleson, Ken Smith
 * 
 */
public enum RiddleType {
	TRUE_FALSE("tf"),
	MULTIPLE_CHOICE("mc"),
	FILL_IN_BLANK("fb");
	
	private final String myLabel;
	
	private RiddleType(String theLabel) {
		myLabel = theLabel;
	}
	
	/**
	 * @return the myLabel
	 */
	public String getLabel() {
		return myLabel;
	}
}
