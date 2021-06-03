package model;

import java.util.ArrayList;
import java.util.Arrays;

public class TFRiddle extends Riddle{
	
	public TFRiddle(String theQuestion, String theAnswer) {
		super(theQuestion, theAnswer, new ArrayList<String>(Arrays.asList("true", "false")), RiddleType.TRUE_FALSE);
	}
}
