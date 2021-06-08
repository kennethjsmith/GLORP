package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

public class QuestionPanel extends RoundJPanel {
	// fields
	private JTextPane myQuestionPane;
	
	private final static int WIDTH = 250;
	private final static int HEIGHT = 175;
	private final Color TRANSPARENT_BLACK = new Color(0,0,0,150);
	private final Color OFF_WHITE = new Color(248,248,255);
	
	/**
	 * 
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
	 * @param the String the myQuestionTextArea to set
	 */
	public void setText(String theString) {
		myQuestionPane.setText(theString);
	}
	
	/**
	 * 
	 */
	@Override
    public void paintComponent(Graphics g) {
    	super.paintComponent(g);
    }
}
