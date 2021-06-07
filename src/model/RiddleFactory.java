package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import sql.FBRiddleDatabase;
import sql.MCRiddleDatabase;
import sql.TFRiddleDatabase;

public class RiddleFactory {
    /*
     * Ex: how each would look
     * 
     * Question: "T/F Question"
     * Answer: "True"
     * Wrong Answers: "False"    
     * NOTE:   myWrongOptions.length == 1
     * 
     * Question: "Multi Choice Question"
     * Answer: "Correct Answer"
     * Wrong Answers: "Wrong Answer 1", "Wrong Answer 2", "Wrong Answer 3" 
     * NOTE:  myWrongOptions.length > 1
     * 
     * Question: "Open Ended Answer"
     * Answer: "The Answer"
     * Wrong Answers: 
     * NOTE:   myWrongOptions.length == 0
     * 
     */
    
	private final TFRiddleDatabase myTrueFalseRiddles;
	private final MCRiddleDatabase myMultipleChoiceRiddles;
	private final FBRiddleDatabase myFillInBlankRiddles;
	
    private final ResultSet myTFRiddleSet;
    private final ResultSet myMCRiddleSet;
    private final ResultSet myFBRiddleSet;
    
    private int TF_RIDDLE_COUNT = 0;
    private int MC_RIDDLE_COUNT = 0;
    private int FB_RIDDLE_COUNT = 0;


    public RiddleFactory() {
        myTrueFalseRiddles = new TFRiddleDatabase();
        myTFRiddleSet = myTrueFalseRiddles.getTFRiddleSet();
        
        myMultipleChoiceRiddles = new MCRiddleDatabase();
        myMCRiddleSet = myMultipleChoiceRiddles.getMCRiddleSet();
        
        myFillInBlankRiddles = new FBRiddleDatabase();
        myFBRiddleSet = myFillInBlankRiddles.getFBRiddleSet();
    }
             
    public Riddle getNextRiddle() {
    	int randomNum = generateRandom(); //TODO be sure to select riddles in a way that is balanced
    	Riddle currRiddle = null;
    	try {
    		// If it is a true/false riddle
	    	if(randomNum == 0 && myTFRiddleSet.next()){
	    		currRiddle = new Riddle(myTFRiddleSet.getString("question"), myTFRiddleSet.getString("answer"), 
	    				new ArrayList<String>(Arrays.asList(myTFRiddleSet.getString("wrong_answer"))), myTFRiddleSet.getString("explanation"), RiddleType.TRUE_FALSE);
    			TF_RIDDLE_COUNT++;
			// If it is a multiple choice riddle
	    	} else if(randomNum == 1 && myMCRiddleSet.next()) {
				currRiddle = new Riddle(myMCRiddleSet.getString("question"), myMCRiddleSet.getString("answer"), 
	    				new ArrayList<String>(Arrays.asList(myMCRiddleSet.getString("wrong_answer1"), 
	    				myMCRiddleSet.getString("wrong_answer2"), myMCRiddleSet.getString("wrong_answer2"))), myMCRiddleSet.getString("explanation"), 
	    				RiddleType.MULTIPLE_CHOICE);
				MC_RIDDLE_COUNT++;
			// If it is a fill-in-the-blank riddle
	    	} else if(randomNum == 2 && myFBRiddleSet.next()) {
	    		currRiddle = new Riddle(myFBRiddleSet.getString("question"), myFBRiddleSet.getString("answer"), 
	    				new ArrayList<String>(), myFBRiddleSet.getString("explanation"), RiddleType.FILL_IN_BLANK);
	    		FB_RIDDLE_COUNT++;
	    	}
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	
    	if(currRiddle == null) throw new NullPointerException("RiddleFactory tried to return a null riddle");
    	return currRiddle; // TODO null handling
    }
    
	 // TODO: make this into a utility ? Very similar to one used in Maze
    /**
	  * Generates a random index between 0 and 3
	  */
	 private int generateRandom() {
	     Random rand = new Random();
	     int randomNum = rand.nextInt(3);
	     
	     if((FB_RIDDLE_COUNT > 1.3 * TF_RIDDLE_COUNT || FB_RIDDLE_COUNT > 1.3 * MC_RIDDLE_COUNT) && randomNum == 2) randomNum = 1;
	     if((MC_RIDDLE_COUNT > 1.3 * TF_RIDDLE_COUNT || MC_RIDDLE_COUNT > 1.3 * FB_RIDDLE_COUNT) && randomNum == 1) randomNum = 0;
	     if((TF_RIDDLE_COUNT > 1.3 * MC_RIDDLE_COUNT || TF_RIDDLE_COUNT > 1.3 * FB_RIDDLE_COUNT) && randomNum == 0) randomNum = 2;
	    
	     return randomNum;
	 }
    
    public void close() {
    	myTrueFalseRiddles.closeConnection();
    	myMultipleChoiceRiddles.closeConnection();
    	myFillInBlankRiddles.closeConnection();
    	
    	System.out.println("True false riddle count: " + TF_RIDDLE_COUNT);
    	System.out.println("Multiple choice riddle count: " + MC_RIDDLE_COUNT);
    	System.out.println("Fill in the blank riddle count: " + FB_RIDDLE_COUNT);

    	
    }

}
