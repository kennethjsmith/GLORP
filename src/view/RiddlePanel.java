/*
 * GLORP: Revenge of the Sphinx
 */

package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Objects;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import model.Riddle;
/**
 * Displays the Door's Riddle and also contains the Input Panel.
 * @author Ken Smith, Heather Finch, Katelynn Oleson 
 * @version 1.0.
 */
public class RiddlePanel extends JPanel {
    // A serialized ID for serialization.
	private static final long serialVersionUID = -760524911580782663L;
    
	private final static int WIDTH = 300;
	private final static int HEIGHT = 500;
	private final GameIcon SPHINX = new GameIcon("src/icons/sphinx.png", 225,162);
	private final GameIcon BACKGROUND = new GameIcon("src/icons/sand.png", WIDTH, HEIGHT);
	
	private boolean myRiddleStatus;
	private Riddle myCurrentRiddle;
	private JPanel myInputBorder;
	private InputPanel myInputPanel;
	private JPanel myQuestionBorder; 
	private QuestionPanel myQuestionPanel; 
	
	/**
	 * Constructs the RiddlePanel.
	 */
	public RiddlePanel() {   
	    myRiddleStatus = false;
	    myCurrentRiddle = null; 
	    
        setPreferredSize(new Dimension(WIDTH,HEIGHT));
        // Compound border.
        Border raisedbevel = BorderFactory.createRaisedBevelBorder();
		Border loweredbevel = BorderFactory.createLoweredBevelBorder();
		Border compound = BorderFactory.createCompoundBorder(raisedbevel, loweredbevel);
		setBorder(compound);
        setLayout(new BorderLayout()); 
        // Question.
        myQuestionBorder = new JPanel();
        myQuestionBorder.setOpaque(false);
        myQuestionBorder.setBorder(new EmptyBorder(10,10,10,10));
        myQuestionPanel = new QuestionPanel();
        myQuestionBorder.add(myQuestionPanel);
        add(myQuestionBorder, BorderLayout.PAGE_START);
        myQuestionBorder.setVisible(false);
        // Input.
        myInputBorder = new JPanel();
        myInputBorder.setOpaque(false);
        myInputBorder.setBorder(new EmptyBorder(10,10,10,10));
        myInputPanel = new InputPanel();
        myInputBorder.add(myInputPanel);
        add(myInputBorder, BorderLayout.PAGE_END);
        myInputPanel.setVisible(false); 
	}
	
	/**
	 * Check the status of the player and 
	 * update the panel accordingly.
	 * @param Riddle The riddle to be displayed
	 */
	public void startUp(Riddle theRiddle) {
		myRiddleStatus = true; 
	    myCurrentRiddle = Objects.requireNonNull(theRiddle); 
	    myQuestionPanel.setText(myCurrentRiddle.getQuestion()); 
        myInputPanel.setupView(myCurrentRiddle);
        myQuestionBorder.setVisible(true);
        myInputPanel.setVisible(true);
  	}
	
	/**
	 * Paints the RiddlePanel.
	 */
	@Override
    public void paintComponent(Graphics g) {  
    	super.paintComponent(g);
    	BACKGROUND.paintIcon(this, g, 0, 0);
    	SPHINX.paintIcon(this, g, 70, 200);
    }
	
    /**
     * Getter for whether the player has submitted their answer to the riddle.
     * @return True if the player has submitted their answer, false otherwise
     */
    public boolean hasResponse() {
        return myInputPanel.hasSubmitted();
    }
    
    /**
     * Getter for the current Riddle being displayed
     * @return Riddle The current Riddle in use
     */
    public Riddle getRiddle() {
        return myCurrentRiddle;
    }
    
    /**
     * Getter for the InputPanel.
     * @return InputPanel
     */
    public InputPanel getInputPanel() {
    	return myInputPanel;
    }
    
    /**
     * Setter for the explanation to the riddle. 
     * @param String The explanation to the riddle
     */
    public void riddleExplanation(String theExplanation) {
        myInputPanel.setVisible(false); 
        myInputPanel.reset();
        myQuestionPanel.setText(theExplanation);
    }
    
    /**
     * Setter for the Sphinx response.
     * @param String The Sphinx resposne
     */
    public void sphinxResponse(String theResponse) {
        myInputPanel.setVisible(false); 
        myInputPanel.reset();
        myQuestionPanel.setText(theResponse);
    }

    
    /**
     * Shut down the riddle prompt 
     * and terminate the producer thread.
     */
    public void shutDown() {
        myRiddleStatus = false; // Setting this to false ends producer thread.
        myInputPanel.setVisible(false);
        myInputPanel.reset();
        myQuestionBorder.setVisible(false);   
    }   
}