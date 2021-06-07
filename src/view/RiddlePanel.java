package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import model.Player;
import model.Riddle;
import model.RiddleType;
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
	private final GameIcon BACKGROUND = new GameIcon("src/icons/sand.png", WIDTH, HEIGHT);
	private final Color OFF_WHITE = new Color(248,248,255);
	
	private boolean myRiddleStatus;
	private Riddle myCurrentRiddle;
	private JPanel myInputBorder;
	private InputPanel myInputPanel;
	private JPanel myQuestionBorder; 
	private RoundJPanel myQuestionOuterPanel; 
	private JPanel myQuestionInnerPanel;
	private JTextPane myQuestionPane;
	
	/**
	 * 
	 */
	public RiddlePanel() {   
	    myRiddleStatus = false;
	    myCurrentRiddle = null; // TODO: change to be mock null riddle object
	    
        setPreferredSize(new Dimension(WIDTH,HEIGHT));
        // add border
        Border raisedbevel = BorderFactory.createRaisedBevelBorder();
		Border loweredbevel = BorderFactory.createLoweredBevelBorder();
		Border compound = BorderFactory.createCompoundBorder(raisedbevel, loweredbevel);
		setBorder(compound);
        setLayout(new BorderLayout()); 
        
        // helpers to set up question and answer panels? 
        
        myQuestionBorder = new JPanel();
        myQuestionBorder.setOpaque(false);
        myQuestionBorder.setBorder( new EmptyBorder(20,20,20,20));
        // TODO: This code was all put into a QuestionPanel class
        // this would be much cleaner to use, 
        // but I was having trouble getting it to show -ken
        myQuestionOuterPanel = new RoundJPanel();
        myQuestionOuterPanel.setBackground(new Color(0,0,0,150));
        myQuestionOuterPanel.setOpaque(false); // must set to false for RoundJPanel
        myQuestionOuterPanel.setPreferredSize(new Dimension(250,150));
        myQuestionOuterPanel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        
//        myQuestionInnerPanel = new JPanel();
//        myQuestionInnerPanel.setOpaque(false);
//        myQuestionInnerPanel.setPreferredSize(new Dimension(200,150));
//        myQuestionInnerPanel.setLayout(new GridBagLayout());
//        
        myQuestionPane = new JTextPane();
        myQuestionPane.setOpaque(false);
        myQuestionPane.setForeground(OFF_WHITE);
        myQuestionPane.setPreferredSize(new Dimension(200,100));
        myQuestionPane.setEditable(false);
        
        StyledDocument doc = myQuestionPane.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        doc.setParagraphAttributes(0, doc.getLength(), center, false);
        
        myQuestionOuterPanel.add(myQuestionPane,c);
        myQuestionBorder.add(myQuestionOuterPanel);
        add(myQuestionBorder, BorderLayout.PAGE_START);
        myQuestionBorder.setVisible(false);
        
        myInputBorder = new JPanel();
        myInputBorder.setOpaque(false);
        myInputBorder.setBorder(new EmptyBorder(10,10,10,10));
        
        myInputPanel = new InputPanel();
        myInputBorder.add(myInputPanel);
       
        add(myInputBorder, BorderLayout.PAGE_END);
        myInputPanel.setVisible(false); // replace with a block or something, so looks like sphinx sitting on table
    }

	
	private ArrayList<Component> setUpAnswers(RiddleType theType, String theCorrectAnswer, ArrayList<String> theWrongAnswers) {
	    ArrayList<Component> inComponents = new ArrayList<Component>();
	    
	    if(theType.getLabel() == "mc" || theType.getLabel() == "tf") {  // multiple choice
	        ButtonGroup inAnswerOptions = new ButtonGroup();
	        JRadioButton inRightAnswer = new JRadioButton(theCorrectAnswer);
	        inAnswerOptions.add(inRightAnswer);
	        inComponents.add(inRightAnswer);
	        for(String s : theWrongAnswers) {
	            JRadioButton inButton = new JRadioButton(s);
	            inAnswerOptions.add(inButton); //figure out how to only allow one selection
	            inComponents.add(inButton);
	        }
	       
	        
	    } else if(theType.getLabel() == "fb")  {  // fill in the blank
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
	    myQuestionPane.setText(myCurrentRiddle.getQuestion()); 
        myInputPanel.setAnswerOptions(setUpAnswers(myCurrentRiddle.getType(), myCurrentRiddle.getAnswer(), myCurrentRiddle.getWrongOptions()));
        
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

//    	if(myRiddleStatus) {
//    	    SPEECH_BUBBLE.paintIcon(this, g, 5, 20); // incorporate this into the riddle status?  
//    	}
    	SPHINX.paintIcon(this, g, 70, 180);
    	
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
        
        myQuestionPane.setText(theResponse);
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