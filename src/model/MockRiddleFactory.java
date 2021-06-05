package model;

import java.util.ArrayList;
import java.util.Random;

class MockRiddleFactory {
    
    private static final MockRiddleFactory INSTANCE = new MockRiddleFactory();
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
    private MockRiddleFactory () {
        setUp();
    }
    
    public static MockRiddleFactory getInstance() {
        return INSTANCE;
    }
    
    Riddle[] myRiddles;
    
    private void setUp() {
        myRiddles = new Riddle[3];
        
        ArrayList<String> TFWrong = new ArrayList<String>();
        TFWrong.add("False");
        ArrayList<String> MultiWrong = new ArrayList<String>();
        MultiWrong.add("Wrong Answer 1");
        MultiWrong.add("Wrong Answer 2");
        MultiWrong.add("Wrong Answer 3");
        
        myRiddles[0] = new Riddle("T/F Question", "True", TFWrong);
        myRiddles[1] = new Riddle("Multi Choice Question", "Correct Answer", MultiWrong);
        myRiddles[2] = new Riddle("Open Ended Answer... type \"Answer\"", "Answer");
    }
         
    
    /**
     * Generates a new Riddle from the SQL Data Base
     * @return
     */
    Riddle generatetRiddle() { 
        Random rand = new Random();
        return myRiddles[rand.nextInt(3)];
    }

}
