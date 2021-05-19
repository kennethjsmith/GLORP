package controller;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JFrame;

import model.Direction;
import model.Maze;
import model.Player;
import model.Skin;
import model.SkinType;
import view.GlorpGUI;

public class GlorpController implements KeyListener{
	// fields
	Maze myMaze;
	Player myPlayer;
	GlorpGUI myWindow; 
	
	private final Set<Integer> myPressedKeys = new HashSet<Integer>();
	
	public GlorpController(){
		myMaze = new Maze();
		
		//hard ref to character for room panel work
		myPlayer = new Player();
		
		myWindow = new GlorpGUI();
        myWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myWindow.setVisible(true);
        myWindow.setTitle("GLORP");
        myWindow.addKeyListener(this);
        
        myWindow.paintRoomPanel(myPlayer);
        
	}
	
	@Override
    public void keyPressed(KeyEvent e) {
		int k = e.getKeyCode();
        myPressedKeys.add(k);
        
        Direction inDirection = Direction.generateDirection(myPressedKeys);
        //System.out.println(inDirection);
        //System.out.println(myPlayer);
		myPlayer.move(inDirection);
		//System.out.println(myPlayer);
        myWindow.paintRoomPanel(myPlayer); //TODO: this method call should pass a room
    }

    @Override
    public void keyReleased(KeyEvent e) {
		int inKey = e.getKeyCode();
		myPressedKeys.remove(inKey);
		myWindow.paintRoomPanel(myPlayer);
    }

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub	
	}

	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}