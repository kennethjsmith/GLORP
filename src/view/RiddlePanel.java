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
public class RiddlePanel extends JPanel implements Runnable{
    /** The number of columns in width of the TextField. */
    private static final int TEXT_FIELD_COLUMNS = 5;
    
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
	private JLabel myQuestionTitle;
	private JLabel myQuestionLabel;
	
	/**
	 * 
	 */
	public RiddlePanel() {   
	    myRiddleStatus = false;
	    myCurrentRiddle = null; // TODO: change to be mock null riddle object
	    
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
        myQuestionTitle = new JLabel("My inquiry for you is ..."); 
  //      later figure out how to nicely arrange title and question
        myQuestionLabel = new JLabel("No Question yet");
       
        myQuestionTitle.setBackground(Color.WHITE);
        myQuestionTitle.setOpaque(true);
        myQuestionPanel.add(myQuestionTitle);
        myQuestionPanel.add(myQuestionLabel);
        c.insets = new Insets(20,0,0,0);
        c.gridx = 0;
        c.gridy = 0;
        add(myQuestionPanel, c);
        myQuestionPanel.setVisible(false);
        
        myInputPanel = new InputPanel();
        //myInputPanel.setPreferredSize(new Dimension(100, 300));
        c.gridx = 0;
        c.gridy = 2;
        c.insets = new Insets(300,0,0,0);
        c.anchor = GridBagConstraints.PAGE_END;
        
        add(myInputPanel, c);
        myInputPanel.setVisible(false); // replace with a block or something, so looks like sphinx sitting on table
        
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
	    }else if(theAnswers.size() == 1)  {  // open ended question, just the answer
	        // open ended, display a text box
	        JTextField inField = new JTextField();
	        inField.setText("Enter your answer here...");
	        inField.setEditable(true);
	        inField.setColumns(TEXT_FIELD_COLUMNS);
	        inField.setHorizontalAlignment(JTextField.RIGHT);
	        
	        inComponents.add(inField);
	        
	    }
	    
	    return inComponents;
	}
	
	//lambda statements for submit button listeners 
	// look at menu listeners in GlorpGUI
	
	
	/**
	 * Check the status of the player and 
	 * update the panel accordingly
	 */
	public void startUp(Riddle theRiddle) {
	    myRiddleStatus = true; 
	    myCurrentRiddle = theRiddle; 
	    myQuestionLabel.setText(myCurrentRiddle.getQuestion()); 
        myInputPanel.setAnswerOptions(setUpAnswers(myCurrentRiddle.getAnswerOptions()));
        
        myQuestionPanel.setVisible(true);
        myInputPanel.setVisible(true);
        
        System.out.println("Started up riddle panel");
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
    	}
    	SPHINX.paintIcon(this, g, 70, 200);
    	
    }
	
//	public boolean hasRetreated() {
//	    return myInputPanel.hasRetreated();
//	}

    public boolean hasResponse() {
        return myInputPanel.hasSubmitted();
    }

    public String getResponse() {
        return myInputPanel.getResponse();
    }
    
    public Riddle getRiddle() {
        return myCurrentRiddle;
    }
    
    
    public void sphinxResponse(String theResponse) {
        myInputPanel.setVisible(false);
        myInputPanel.reset();
        
        myQuestionLabel.setText("");
        myQuestionTitle.setText(theResponse);
    }

    
    /*
     * Shut down the riddle prompt 
     * and terminate the producer thread
     */
    public void shutDown() {
        myRiddleStatus = false; //setting this to false ends producer thread
        myQuestionPanel.setVisible(false);
        
        myQuestionTitle.setText("My inquiry for you is ...");
        
        System.out.println("Shut down riddle panel");
        
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