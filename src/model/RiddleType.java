/*
 * GLORP: Revenge of the Sphinx
 */

package model;

import java.util.Objects;

/**
 * An enum class that represents the different types of riddles. 
 * @author Heather Finch, Katelynn Oleson, Ken Smith
 * @version 1.0.
 */
public enum RiddleType {
	TRUE_FALSE("tf"),
	MULTIPLE_CHOICE("mc"),
	FILL_IN_BLANK("fb");
	
	private final String myLabel;
	
	// Private constructor creates a new RiddleType.
	private RiddleType(String theLabel) {
		myLabel = Objects.requireNonNull(theLabel);
	}
	
	/**
	 * Returns the label for the RiddleType.
	 * @return String The RiddleTypes label
	 */
	public String getLabel() {
		return myLabel;
	}
}
