package view;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;
import javax.swing.JTextArea;

public class QuestionPanel extends JPanel {
	// fields
	private RoundJPanel myQuestionPanel; 
	private JTextArea myQuestionTextArea;
	private final Color TRANSPARENT_BLACK = new Color(248,248,255);
	private final Color OFF_WHITE = new Color(248,248,255);
	
	/**
	 * 
	 */
	public QuestionPanel(){
		super();

	}

	/**
	 * @param the String the myQuestionTextArea to set
	 */
	public void setTextArea(String theString) {
		myQuestionTextArea.setText(theString);
	}
	
	/**
	 * 
	 */
	@Override
    public void paintComponent(Graphics g) {
    	super.paintComponent(g);
    }
}
