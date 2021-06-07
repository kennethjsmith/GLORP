package model;

import java.util.ArrayList;

/**
 * A Riddle is a question/answer pair.
 * @author Ken Smith, Heather Finch, Katelynn Oleson 
 * @version 
 */

// TODO should this be an interface instead?
public class Riddle {

	// fields
	private String myQuestion;
	private String myAnswer; 
	private ArrayList<String> myWrongAnswerOptions;
	private String myExplanation;
	private RiddleType myType;
	
	/**
	 * Constructor creates a riddle based off of the question, answer, and type of riddle. 
	 */
	public Riddle (String theQuestion, String theAnswer, ArrayList<String> theWrongAnswerOptions, String theExplanation, RiddleType theType) {
		myQuestion = theQuestion;
		myAnswer = theAnswer;
		myWrongAnswerOptions = theWrongAnswerOptions;
		myExplanation = theExplanation;
		myType = theType;
	}

	/**
	 * Default for testing purposes
	 */
	public Riddle() {
        myQuestion = "Test Question";
        myAnswer = "correct Answer";
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
	 * Compares the attempted answer to the correct answer, 
	 * if identical 
	 * Does checks for spelling, puncuation, capitolization
	 * @param theInput
	 * @return boolean isCorrect 
	 */
	public boolean checkAnswer(String theInput) {
	    return true;	    
	}
	
	/**
     * Compares the attempted answer to the correct answer
     * If within error margin, returns true
     * Does checks for spelling, puncuation, capitolization
     * @param theInput
     * @return boolean isCorrect 
     */
    public boolean checkAnswer(String theInput, double theErrorMargin) {
        return true;
    }
	
    /**
     * Returns the correct answer for this riddle
     * @return
     */
	public String getAnswer() {
	    return myAnswer;
	}


	 /**
	  * return array list of answer options
	  * wrong options + the correct answer
	  * 
	 */

	public ArrayList<String> getAnswerOptions(){
	    //TODO: Modify this so that is randomizes the options
	    ArrayList<String> inAnswers = (ArrayList<String>) myWrongAnswerOptions.clone();
	    inAnswers.add(myAnswer);
		return inAnswers;
	}

	
	/**
	 * @return type of riddle enum
	 */
	public RiddleType getType() {
		return myType;
	}
	
	//TODO modify this so it works for every riddle type
	//May need to wait to see what GUI does to complete this
	public boolean verifyAnswer(String thePlayersAnswer) {
		System.out.println("The players answer: " + thePlayersAnswer + ", correct answer: " + myAnswer);
		if(thePlayersAnswer.toLowerCase().equals(myAnswer.toLowerCase())) return true;
		return false;
	}
}
