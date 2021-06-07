package controller;

import java.awt.EventQueue;

import javax.sound.sampled.LineUnavailableException;

/**
 * The main, runs the game and creates the controller. 
 * @authors Heather Finch, Katelynn Oleson, Ken Smith
 * @version
 */
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
    	EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
            try {
				GlorpController controller = new GlorpController();
			} catch (LineUnavailableException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }});

    }
}