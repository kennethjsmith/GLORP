package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.Random;

import sql.FBRiddleDatabase;
import sql.MCRiddleDatabase;
import sql.TFRiddleDatabase;

/**
 * This Riddle factory grabs information for the SQLite databases and creates a new Riddle.
 * @author Heather Finch, Katelynn Oleson, Ken Smith.
 * @version 1.0
 */
public class RiddleFactory {

	// The classes that create and access the databases that store Riddles.
	private final TFRiddleDatabase myTrueFalseRiddles;
	private final MCRiddleDatabase myMultipleChoiceRiddles;
	private final FBRiddleDatabase myFillInBlankRiddles;
	
	// A ResultSet for each type of Riddle.
    private final ResultSet myTFRiddleSet;
    private final ResultSet myMCRiddleSet;
    private final ResultSet myFBRiddleSet;
    
    // Keeps track of how many Riddles have been used so far of each type.
    private int TF_RIDDLE_COUNT = 0;
    private int MC_RIDDLE_COUNT = 0;
    private int FB_RIDDLE_COUNT = 0;


    /**
     * Creates a new RiddleFactory.
     */
    public RiddleFactory() {
        myTrueFalseRiddles = new TFRiddleDatabase();
        myTFRiddleSet = myTrueFalseRiddles.getTFRiddleSet();
        
        myMultipleChoiceRiddles = new MCRiddleDatabase();
        myMCRiddleSet = myMultipleChoiceRiddles.getMCRiddleSet();
        
        myFillInBlankRiddles = new FBRiddleDatabase();
        myFBRiddleSet = myFillInBlankRiddles.getFBRiddleSet();
    }
             
    /**
     * Returns the next Riddle. The type of Riddle is selected randomly.
     * @return Riddle The next Riddle.
     */
    public Riddle getNextRiddle() {
    	int randomNum = generateRandom(); // Get a random number.
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
	    				myMCRiddleSet.getString("wrong_answer2"), myMCRiddleSet.getString("wrong_answer3"))), myMCRiddleSet.getString("explanation"), 
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
    	return Objects.requireNonNull(currRiddle);
    }
    
	 // Generates a random index between 0 and 3.
	 // If the allocation of each type of riddle is unbalanced, 
	 // this will attempt create balance by returning a different number.
	 private int generateRandom() {
	     Random rand = new Random();
	     int randomNum = rand.nextInt(3);
	     
	     if((FB_RIDDLE_COUNT > 1.3 * TF_RIDDLE_COUNT || FB_RIDDLE_COUNT > 1.3 * MC_RIDDLE_COUNT) && randomNum == 2) randomNum = 1;
	     if((MC_RIDDLE_COUNT > 1.3 * TF_RIDDLE_COUNT || MC_RIDDLE_COUNT > 1.3 * FB_RIDDLE_COUNT) && randomNum == 1) randomNum = 0;
	     if((TF_RIDDLE_COUNT > 1.3 * MC_RIDDLE_COUNT || TF_RIDDLE_COUNT > 1.3 * FB_RIDDLE_COUNT) && randomNum == 0) randomNum = 2;
	     return randomNum;  
	 }
    
    /**
     * Closes the connections to each database.
     */
    public void close() {
    	myTrueFalseRiddles.closeConnection();
    	myMultipleChoiceRiddles.closeConnection();
    	myFillInBlankRiddles.closeConnection();
    }

}
