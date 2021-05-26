package model;

import java.util.Random;

class RiddleFactory {
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
    
    Riddle[] myRiddles;
    
    private void setUp() {
        myRiddles = new Riddle[3];
        
        String[] TFWrong = {"False"};
        String[] MultiWrong = {"Wrong Answer 1", "Wrong Answer 2", "Wrong Answer 3"};
        
        myRiddles[0] = new Riddle("T/F Question", "True", TFWrong);
        myRiddles[1] = new Riddle("Multi Choice Question", "Correct Answer", MultiWrong);
        myRiddles[2] = new Riddle("Open Ended Answer", "The Answer");
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
