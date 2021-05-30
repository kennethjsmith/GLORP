package model;
/**
 * A Riddle is a question/answer pair.
 * @author Ken Smith, Heather Finch, Katelynn Oleson 
 * @version 
 */
public class Riddle {
	// TODO: should this be an interface for different riddle types?
	// TODO: figure out how to read from SQLite to import riddles randomly without repeats (Hashset? RNG?)
	// fields
	private String myQuestion;
	private String myAnswer;
	
	/**
	 * 
	 */
	public Riddle () {
		myQuestion = "Type \"Y\"";
		myAnswer = "Y";
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
}
