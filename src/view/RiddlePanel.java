package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import model.Riddle;
/**
 * Displays the Door's Riddle and also contains the Input Panel.
 * @author Ken Smith, Heather Finch, Katelynn Oleson 
 * @version 
 */
public class RiddlePanel extends JPanel implements Runnable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -760524911580782663L;
    
	// fields
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
	 * 
	 */
	public RiddlePanel() {   
	    myRiddleStatus = false;
	    myCurrentRiddle = null; // TODO: change to be mock null riddle object
	    
        setPreferredSize(new Dimension(WIDTH,HEIGHT));
        // compound border
        Border raisedbevel = BorderFactory.createRaisedBevelBorder();
		Border loweredbevel = BorderFactory.createLoweredBevelBorder();
		Border compound = BorderFactory.createCompoundBorder(raisedbevel, loweredbevel);
		setBorder(compound);
        setLayout(new BorderLayout()); 
        // question
        myQuestionBorder = new JPanel();
        myQuestionBorder.setOpaque(false);
        myQuestionBorder.setBorder(new EmptyBorder(10,10,10,10));
        myQuestionPanel = new QuestionPanel();
        myQuestionBorder.add(myQuestionPanel);
        add(myQuestionBorder, BorderLayout.PAGE_START);
        myQuestionBorder.setVisible(false);
        // input
        myInputBorder = new JPanel();
        myInputBorder.setOpaque(false);
        myInputBorder.setBorder(new EmptyBorder(10,10,10,10));
        myInputPanel = new InputPanel();
        myInputBorder.add(myInputPanel);
        add(myInputBorder, BorderLayout.PAGE_END);
        myInputPanel.setVisible(false); // replace with a block or something, so looks like sphinx sitting on table
	}
	
	/**
	 * Check the status of the player and 
	 * update the panel accordingly
	 */
	public void startUp(Riddle theRiddle) {
	    myRiddleStatus = true; 
	    myCurrentRiddle = theRiddle; 
	    myQuestionPanel.setText(myCurrentRiddle.getQuestion()); 
        myInputPanel.setupView(myCurrentRiddle);
        myQuestionBorder.setVisible(true);
        myInputPanel.setVisible(true);
  	}
	
	/**
	 * 
	 */
	@Override
    public void paintComponent(Graphics g) {  
    	super.paintComponent(g);
    	BACKGROUND.paintIcon(this, g, 0, 0);
    	SPHINX.paintIcon(this, g, 70, 200);
    }
	
//	public boolean hasRetreated() {
//	    return myInputPanel.hasRetreated();
//	}

    public boolean hasResponse() {
        return myInputPanel.hasSubmitted();
    }
    
    public Riddle getRiddle() {
        return myCurrentRiddle;
    }
    
    public InputPanel getInputPanel() {
    	return myInputPanel;
    }
    
    public void riddleExplanation(String theExplanation) {
        myInputPanel.setVisible(false);
        myInputPanel.reset();
        
        
        myQuestionPanel.setText(theExplanation);
    }
    
    public void sphinxResponse(String theResponse) {
        myInputPanel.setVisible(false);
        myInputPanel.reset();
        
        
        myQuestionPanel.setText(theResponse);
    }

    
    /*
     * Shut down the riddle prompt 
     * and terminate the producer thread
     */
    public void shutDown() {
        myRiddleStatus = false; //setting this to false ends producer thread
        myQuestionBorder.setVisible(false);   
    }   
    
    
    /**
     * Seperate thread so that the player can "walk away" from the riddle
     */
    @Override
    public void run() {
        // while riddle activated & not yet submitted
        while(myRiddleStatus && !(myInputPanel.hasSubmitted())) {     
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                System.out.println("Error in RiddlePanel run method!");
                e.printStackTrace();
            }
        } 
    }
}