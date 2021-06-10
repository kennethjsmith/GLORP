package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

/**
 * A Riddle contains a question, answer, a list of wrong answer options, and a type.
 * Riddle types include true/false, multiple choice, and fill-in-the-blank.
 * @author Ken Smith, Heather Finch, Katelynn Oleson 
 * @version 1.0. 
 */
public class Riddle implements Serializable {
	// A serialized ID for serialization.
    private static final long serialVersionUID = -8875389615271997933L;    
    private String myQuestion;
	private String myAnswer; 
	private ArrayList<String> myWrongAnswerOptions;
	private String myExplanation;
	private RiddleType myType; // There are three types: true/fase, multiple-choice, and fill-in-the-blank.
	
	/**
	 * Constructor creates a riddle based off of the question, answer, and type of riddle. 
	 */
	public Riddle (String theQuestion, String theAnswer, ArrayList<String> theWrongAnswerOptions, String theExplanation, RiddleType theType) {
		myQuestion = Objects.requireNonNull(theQuestion);
		myAnswer = Objects.requireNonNull(theAnswer);
		myWrongAnswerOptions = Objects.requireNonNull(theWrongAnswerOptions);
		myExplanation = Objects.requireNonNull(theExplanation);
		myType = Objects.requireNonNull(theType);
	}
	
    /**
     * Getter for this Riddle's Question.
	 * @return String The question this Riddle stores
	 */
	public String getQuestion() {
	    return myQuestion;
	}

	/**
	 * Returns wrong answer options.
	 * If the Riddle is multiple-choice, the list will have 3 wrong options.
	 * If the Riddle is true/fase, the list will have 1 option.
	 * If this returns an empty list, then the riddle is open answer.
	 * @return theWrongOptions An ArrayList<String> of the wrong answer option for this Riddle
	 */
	public ArrayList<String> getWrongOptions() {
	    return myWrongAnswerOptions; 
    }
	
	/**
	 * Returns explanation about answer. 
	 * @return String The Riddles explanation
	 */
	public String getExplanation() {
		return myExplanation;
	}
	
    /**
     * Returns the correct answer for this riddle
     * @return String The correct answer
     */
	public String getAnswer() {
	    return myAnswer;
	}

	
	/**
	 * Returns the type of Riddle
	 * @return RiddleType An enumerated type
	 */
	public RiddleType getType() {
		return myType;
	}
	
	/**
	 * Compares the attempted answer to the correct answer.
	 * Ignores capitalization.
	 * Answer must be correctly spelled without punctuation. 
	 * @param the players answer to the question
	 * @return true if the answer was correct, false otherwise 
	 */
	public boolean verifyAnswer(String thePlayersAnswer) {
		Objects.requireNonNull(thePlayersAnswer, "thePlayersAnswer cannot be null");
		if(thePlayersAnswer.toLowerCase().equals(myAnswer.toLowerCase())) return true;
		return false;
	}
}
