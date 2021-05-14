package view;

import java.awt.EventQueue;

import javax.swing.JFrame;

import model.Maze;

public class GlorpMain {
    /**
     * Private constructor to prevent construction of instances.
     */
    private GlorpMain() {
        // do nothing
    }

    /**
     * Constructs the main GUI window frame.
     * 
     * @param theArgs Command line arguments (ignored).
     */
    public static void main(final String... theArgs) {
    	Maze myMaze = new Maze();
    	System.out.println(myMaze.toString());
    	EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
            	GlorpGUI window = new GlorpGUI();
                window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                window.setVisible(true);
                window.setTitle("GLORP");   
            }
        });
        
    }
}