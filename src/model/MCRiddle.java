package model;

import java.util.ArrayList;
import java.util.Arrays;

public class MCRiddle extends Riddle{

	public MCRiddle(String theQuestion, String theAnswer, String wrongAnswer1, String wrongAnswer2, String wrongAnswer3) {
		super(theQuestion, theAnswer, new ArrayList<String>(Arrays.asList(wrongAnswer1, wrongAnswer2, wrongAnswer3)), RiddleType.MULTIPLE_CHOICE);
	}

}
