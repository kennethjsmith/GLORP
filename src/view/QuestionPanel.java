package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagLayout;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

public class QuestionPanel extends JPanel {
	// fields
	private RoundJPanel myQuestionOuterPanel; 
	private JPanel myQuestionInnerPanel;
	private JLabel myQuestionTitle;
	private JTextArea myQuestionTextArea;
	private final Color TRANSPARENT_BLACK = new Color(248,248,255);
	private final Color OFF_WHITE = new Color(248,248,255);
	
	/**
	 * 
	 */
	public QuestionPanel(){
		super();
	    myQuestionOuterPanel = new RoundJPanel();
	    myQuestionOuterPanel.setBackground(TRANSPARENT_BLACK);
	    myQuestionOuterPanel.setOpaque(false); // must set to false for RoundJPanel
	    myQuestionOuterPanel.setPreferredSize(new Dimension(250,150));
	    myQuestionOuterPanel.setLayout(new GridBagLayout());
	    
	    myQuestionInnerPanel = new JPanel();
	    myQuestionInnerPanel.setOpaque(false);
	    myQuestionInnerPanel.setPreferredSize(new Dimension(200,150));
	    myQuestionInnerPanel.setLayout(new BoxLayout(myQuestionInnerPanel,BoxLayout.Y_AXIS));
	    
	    
	    myQuestionTitle = new JLabel("My inquiry for you is ...");
	    myQuestionTitle.setOpaque(false);
	    myQuestionTitle.setForeground(OFF_WHITE);
	    myQuestionTitle.setAlignmentX(JLabel.CENTER_ALIGNMENT);
	    
	    myQuestionTextArea = new JTextArea("No Question yet");
	    myQuestionTextArea.setOpaque(false);
	    myQuestionTextArea.setForeground(OFF_WHITE);
	    
	    myQuestionTextArea.setEditable(false);
	    myQuestionTextArea.setLineWrap(true);
	    myQuestionTextArea.setWrapStyleWord(true);
	    
	    myQuestionInnerPanel.add(Box.createVerticalStrut(20));
	    myQuestionInnerPanel.add(myQuestionTitle);
	    myQuestionInnerPanel.add(myQuestionTextArea);
	    myQuestionOuterPanel.add(myQuestionInnerPanel);

	}

	/**
	 * @param myQuestionTitle the myQuestionTitle to set
	 */
	public void setTitle(String theString) {
		myQuestionTitle.setText(theString);;
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
