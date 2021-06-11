/*
 * GLORP: Revenge of the Sphinx
 */

package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.Objects;

import javax.swing.JTextPane;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

/**
 * Creates the question panel that will displayed on the RiddlePanel.
 * Displays the question for each riddle.
 * @author Ken Smith, Katelynn Oleson, Heather Finch
 * @version 1.0.
 */
public class QuestionPanel extends RoundJPanel {
	// A serialized ID for serialization.
	private static final long serialVersionUID = 33188956188869938L;

	private JTextPane myQuestionPane;
	
	private final static int WIDTH = 250;
	private final static int HEIGHT = 175;
	private final static Color TRANSPARENT_BLACK = new Color(0,0,0,150);
	private final static Color OFF_WHITE = new Color(248,248,255);
	
	/**
	 * Constructs the QuestionPanel.
	 */
	public QuestionPanel(){
		super();
        setBackground(TRANSPARENT_BLACK);
        setOpaque(false); // must set to false for RoundJPanel
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setLayout(new GridBagLayout());
        
        myQuestionPane = new JTextPane();
        myQuestionPane.setOpaque(false);
        myQuestionPane.setForeground(OFF_WHITE);
        myQuestionPane.setFont(new Font("SansSerif", Font.BOLD, 12));
        myQuestionPane.setPreferredSize(new Dimension(200,125));
        myQuestionPane.setEditable(false);
        
        StyledDocument doc = myQuestionPane.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        doc.setParagraphAttributes(0, doc.getLength(), center, false);
        
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        add(myQuestionPane,c);
	}

	/**
	 * Sets the text for the question, based on the current Riddle.
	 * @param String The text to display for the question
	 */
	public void setText(String theString) {
		Objects.requireNonNull(theString);
		myQuestionPane.setText(theString);
	}
	
	/**
	 * Paints the QuestionPanel.
	 */
	@Override
    public void paintComponent(Graphics g) {
    	super.paintComponent(g);
    }
}
