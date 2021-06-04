package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import model.Player;
import model.Riddle;
/**
 * Displays the Door's Riddle and also contains the Input Panel.
 * @author Ken Smith, Heather Finch, Katelynn Oleson 
 * @version 
 */
public class RiddlePanel extends JPanel{
	// fields
    /** The size of the increase/decrease buttons. */
    private static final Dimension BUTTON_SIZE = new Dimension(26, 26);
    
	private final static int WIDTH = 300;
	private final static int HEIGHT = 500;
	private final String TITLE = "Riddle";
	private final GameIcon SPHINX = new GameIcon("src/icons/sphinx.png", 225,162);
	private final GameIcon SPEECH_BUBBLE = new GameIcon("src/icons/speech_bubble.png", 290, 250);
	private final GameIcon BACKGROUND = new GameIcon("src/icons/sand.png", WIDTH, HEIGHT);
	
	private boolean myRiddleStatus;
	private Riddle myCurrentRiddle;
	private InputPanel myInputPanel;
	private JPanel myQuestionPanel; 
	//private	SubmitPanel mySubmitPanel;
	private JLabel myQuestion;
	//private ArrayList<JRadioButton> myAnswerOptions; 
	
	/**
	 * 
	 */
	public RiddlePanel() {   
	    myRiddleStatus = false;
	    myCurrentRiddle = null; // TODO: change to be mock null riddle object
	  //  myAnswerOptions = new ArrayList<JRadioButton>();
	    
        setPreferredSize(new Dimension(WIDTH,HEIGHT));
        // add border
//        TitledBorder border = BorderFactory.createTitledBorder(TITLE);
//        border.setTitleColor(Color.YELLOW);
//        border.setTitleJustification(TitledBorder.CENTER);
//        setBorder(border);
        Border raisedbevel = BorderFactory.createRaisedBevelBorder();
		Border loweredbevel = BorderFactory.createLoweredBevelBorder();
		Border compound = BorderFactory.createCompoundBorder(raisedbevel, loweredbevel);
		setBorder(compound);
        
        setLayout(new GridBagLayout()); 
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.NONE;
        
        setBackground(new Color(194,178,128));
        
        // helpers to set up question and answer panels? 
        myQuestionPanel = new JPanel();
        myQuestionPanel.setBackground(Color.WHITE);
        JLabel inTitle = new JLabel("My inquiry for you is ..."); 
  //      later figure out how to nicely arrange title and question
        myQuestion = new JLabel("No Question yet");
       
        inTitle.setBackground(Color.WHITE);
        inTitle.setOpaque(true);
        myQuestionPanel.add(inTitle);
        myQuestionPanel.add(myQuestion);
        c.insets = new Insets(20,0,0,0);
        c.gridx = 0;
        c.gridy = 0;
        add(myQuestionPanel, c);
        myQuestionPanel.setVisible(false);
        
        myInputPanel = new InputPanel();
        c.gridx = 0;
        c.gridy = 2;
        c.insets = new Insets(300,0,0,0);
        c.anchor = GridBagConstraints.PAGE_END;
        add(myInputPanel, c);
        myInputPanel.setVisible(false); // replace with a block or something, so looks like sphinx sitting on table
        
//        mySubmitPanel = new SubmitPanel();
//        c.gridx = 0;
//        c.gridy = 3;
//        c.insets = new Insets(300,0,0,0);
//        c.anchor = GridBagConstraints.PAGE_END;
//        add(mySubmitPanel, c);
//        mySubmitPanel.setVisible(false);
        
    }

	
	private ArrayList<Component> setUpAnswers(ArrayList<String> theAnswers) {
	    ArrayList<Component> inComponents = new ArrayList<Component>();
	    
	    if(theAnswers.size() > 1) { 
	        ButtonGroup inAnswerOptions = new ButtonGroup();
	        for(String s : theAnswers) {
	            JRadioButton inButton = new JRadioButton(s);
	            inAnswerOptions.add(inButton); //figure out how to only allow one selection
	            inComponents.add(inButton);
	        }
	    }else { 
	        // open ended, display a text box
	    }
	    
	    return inComponents;
	}
	
	//lambda statements for submit button listeners 
	// look at menu listeners in GlorpGUI
	
	
	/**
	 * Check the status of the player and 
	 * update the panel accordingly
	 */
	public void update(boolean theRiddlePromptStatus, Riddle theRiddle) {
	    myRiddleStatus = theRiddlePromptStatus; 
	    
	    if(theRiddlePromptStatus) {
	        myCurrentRiddle = theRiddle;  
	    }
	}
	
	/**
	 * 
	 */
	@Override
    public void paintComponent(Graphics g) {  
    	super.paintComponent(g);
    	BACKGROUND.paintIcon(this, g, 0, 0);

    	if(myRiddleStatus) {
    	    SPEECH_BUBBLE.paintIcon(this, g, 5, 20); // incorporate this into the riddle status? 
    	    myQuestion.setText(myCurrentRiddle.getQuestion()); 
    	    myInputPanel.setAnswerOptions(setUpAnswers(myCurrentRiddle.getAnswerOptions()));
    	    System.out.println("Riddle Prompt displayed");
    	}
    	
    	myQuestionPanel.setVisible(myRiddleStatus);
        myInputPanel.setVisible(myRiddleStatus);
    	    
    	SPHINX.paintIcon(this, g, 70, 200);
    	
    }

    public boolean hasAnswer() {
        return myInputPanel.hasSubmitted();
    }

    public String getAnswer() {
        return myInputPanel.getAnswer();
    }
    
//    
//    /**
//     * Seperate thread so that the player can "walk away" from the riddle
//     */
//    @Override
//    public void run() {
//        while(myRiddleStatus && !(myInputPanel.hasRetreated())) { // while the player is actively at the riddle 
//            myQuestionPanel.setVisible(true); // view the riddle
//            myInputPanel.setVisible(true);
//        }
//        
//        myRiddleStatus = false;
//        
//        myQuestionPanel.setVisible(false);
//        myInputPanel.setVisible(false);
//        
//    }
//	
//	private synchronized void sendMessage(String message){
//	    while(!(myInputPanel.hasSubmitted())) { // wait till submitted to send the message
//	        try {
//                wait();
//            } catch (InterruptedException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            }
//	    }
//	    
//	    notify();
//	}	
}