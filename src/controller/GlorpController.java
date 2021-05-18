package controller;

import javax.swing.JFrame;

import model.Maze;
import model.Player;
import model.Skin;
import model.SkinType;
import view.GlorpGUI;

public class GlorpController {
	// fields
	Maze myMaze;
	Player myPlayer;
	GlorpGUI myWindow;  
	
	public GlorpController() {
		myMaze = new Maze();
		
		//hard ref to character for room panel work
		myPlayer = new Player();
		
		myWindow = new GlorpGUI();
        myWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myWindow.setVisible(true);
        myWindow.setTitle("GLORP");
        //addKeyListener(myWindow);
        
        
	}
}