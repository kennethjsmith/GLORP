package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

/**
 * A Riddle contains a question, answer, a list of wrong answer options, and a type.
 * Riddle types include true/false, multiple choice, and fill-in-the-blank.
 * @author Ken Smith, Heather Finch, Katelynn Oleson 
 * @version 
 */
public class Riddle implements Serializable {
	
	/**
     * 
     */
    private static final long serialVersionUID = -8875389615271997933L;
    
    private String myQuestion;
	private String myAnswer; 
	private ArrayList<String> myWrongAnswerOptions;
	private String myExplanation;
	private RiddleType myType;
	
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
	 * Default for testing purposes
	 */
	public Riddle() {
        myQuestion = "Test question";
        myAnswer = "correct answer";
        myWrongAnswerOptions = new ArrayList<String>();
        myWrongAnswerOptions.add("wrong answer 1");
        myWrongAnswerOptions.add("wrong answer 2");
    }
	
    /**
     * Getter for this Riddle's Question
	 * @return the myQuestion
	 */
	public String getQuestion() {
	    return myQuestion;
	}

	/**
	 * Returns wrong answer options 
	 * if returns an empty list, then the riddle is open answer
	 * @return theWrongOptions
	 */
	public ArrayList<String> getWrongOptions() {
	    return myWrongAnswerOptions; // deep copy? 
	     // or shallow copy for items that allow elimination of wrong answers (50/50 item)
	}
	
	/**
	 * Returns explanation about answer 
	 * @return myExplanation
	 */
	public String getExplanation() {
		return myExplanation;
	}
	
    /**
     * Returns the correct answer for this riddle
     * @return
     */
	public String getAnswer() {
	    return myAnswer;
	}

	
	/**
	 * @return type of riddle enum
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
