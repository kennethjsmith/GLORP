package controller;

import java.awt.EventQueue;

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
            GlorpController game = new GlorpController();
        }});

    }
}