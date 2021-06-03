package model;

import java.util.ArrayList;

/**
 * A Riddle is a question/answer pair.
 * @author Ken Smith, Heather Finch, Katelynn Oleson 
 * @version 
 */

// TODO should this be an interface instead?
public class Riddle { // doesnt really need to be abstract

	// fields
	private String myQuestion;
	private String myAnswer; 
	private ArrayList<String> myWrongOptions; //array list may be better bc easier to eliminate half the options
	private RiddleType myType; // not really needed
	
	/**
	 * Constructor creates a riddle based off of the question, answer, and type of riddle. 
	 */
	public Riddle (String theQuestion, String theAnswer, ArrayList<String> theWrongOptions, RiddleType theType) {
		myQuestion = theQuestion;
		myAnswer = theAnswer;
		myWrongOptions = theWrongOptions;
		myType = theType;
	}

	/**
	 * Default for testing purposes
	 */
	public Riddle() {
        myQuestion = "Test Question";
        myAnswer = "correct Answer";
        myWrongOptions = new ArrayList<String>();
            myWrongOptions.add("wrong answer 1");
            myWrongOptions.add("wrong answer 2");
    }
	
	public Riddle(String theQuestion, String theAnswer) {
	    myQuestion = theQuestion;
	    myAnswer = theAnswer;
	    myWrongOptions = new ArrayList<String>();
	}
	
	public Riddle(String theQuestion, String theAnswer, ArrayList<String> theWrongOptions) {
	    myQuestion = theQuestion;
        myAnswer = theAnswer;
        myWrongOptions = theWrongOptions;
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
	    return myWrongOptions; // deep copy? 
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
	 */
	public ArrayList<String> getAnswerOptions(){
	    ArrayList<String> inAnswers = (ArrayList<String>) myWrongOptions.clone();
	    inAnswers.add(myAnswer);
		return inAnswers;
	}

	
	/**
	 * @return type of riddle enum
	 */
	public RiddleType getType() {
		return myType;
	}
	
	public boolean verifyAnswer(String thePlayersAnswer) {
		System.out.println("The players answer: " + thePlayersAnswer + ", correct answer: " + myAnswer);
		if(thePlayersAnswer.toLowerCase().equals(myAnswer)) return true;
		return false;
	}
}
