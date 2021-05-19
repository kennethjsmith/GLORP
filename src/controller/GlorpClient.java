package controller;

import javax.swing.JFrame;

import model.Maze;
import view.GlorpGUI;

public class GlorpClient {
	// fields
	Maze myMaze; 
	GlorpGUI myWindow;  
	
	public GlorpClient() {
    myMaze = new Maze();
    myWindow = new GlorpGUI();
                myWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                myWindow.setVisible(true);
                myWindow.setTitle("GLORP");
	}
}