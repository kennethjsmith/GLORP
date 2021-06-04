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
	private RiddleType myType;
	
	/**
	 * Constructor creates a riddle based off of the question, answer, and type of riddle. 
	 */
	public Riddle (String theQuestion, String theAnswer, ArrayList<String> theAnswerOptions, RiddleType theType) {
		myQuestion = theQuestion;
		myAnswer = theAnswer;
		myWrongAnswerOptions = theAnswerOptions;
		myType = theType;
	}

	/**
	 * @return the myQuestion
	 */
	public String getMyQuestion() {
		return myQuestion;
	}


	/**
	 * @return the myAnswer
	 */
	public String getMyAnswer() {
		return myAnswer;
	}
	
	/**
	 * @return array list of answer options
	 */
	public ArrayList<String> getWrongAnswerOptions(){
		return myWrongAnswerOptions;
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
