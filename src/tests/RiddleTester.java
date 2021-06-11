package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import model.Maze;
import model.Riddle;
import model.RiddleFactory;
import model.RiddleType;
import model.Room;

/**
 * Tests the Riddle class.
 * @author Heather Finch, Katelynn Oleson, Ken Smith
 * @version 1.0.
 *
 */
public class RiddleTester {
	private static Riddle myTestRiddle;
    
	@BeforeAll
	static void setUp() {
		myTestRiddle = new Riddle("question", "answer", 
				new ArrayList<String>(Arrays.asList("true", "false")), "explanation", RiddleType.TRUE_FALSE);
	}
	
	// Tests get question  
    @Test
    void testGetQuestion() {
    	assertEquals(myTestRiddle.getQuestion(), "question");
    }
    
	// Tests get answer  
    @Test
    void testGetAnswer() {
    	assertEquals(myTestRiddle.getAnswer(), "answer");
    }
    
	// Tests getting the wrong answer options  
    @Test
    void testGetWrongOptions() {
    	ArrayList<String> testList = new ArrayList<>(Arrays.asList("true", "false"));
     	assertEquals(myTestRiddle.getWrongOptions(), testList);
    }
    
	// Tests getting the explanation  
    @Test
    void testGetExplanation() {
     	assertEquals(myTestRiddle.getExplanation(), "explanation");
    }
    
	// Tests getting the type  
    @Test
    void testGetType() {
     	assertEquals(myTestRiddle.getType(), RiddleType.TRUE_FALSE);
    }
    
	// Tests verifying with correct answer option  
    @Test
    void testVerifyCorrectAnswer() {
    	assertTrue(myTestRiddle.verifyAnswer("answer"));
    }
    
	// Tests verifying with correct answer option  
    @Test
    void testVerifyWrongAnswer() {
    	assertFalse(myTestRiddle.verifyAnswer("wrong"));
    }
}
