package model;
/**
 * A Riddle is a question/answer pair.
 * @author
 * @version
 */
public class Riddle {
	private String myQuestion;
	private String[] myWrongOptions;
	private String myAnswer;
	
	/**
	 * Default for testing purposes
	 */
	public Riddle() {
        myQuestion = "Test Question";
        myAnswer = "correct Answer";
        myWrongOptions = new String[2];
            myWrongOptions[0] = "wrong answer 1";
            myWrongOptions[1] = "wrong answer 2";
    }
	
	public Riddle(String theQuestion, String theAnswer) {
	    myQuestion = theQuestion;
	    myAnswer = theAnswer;
	    myWrongOptions = new String[0];
	}
	
	public Riddle(String theQuestion, String theAnswer, String[] theWrongOptions) {
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
	public String[] getWrongOptions() {
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

	
}
