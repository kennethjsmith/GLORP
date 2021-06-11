package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.util.Objects;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import model.Riddle;


/**
 * The panel for the players reponses to the questions. 
 * This panel will hold either a MCInputView, FBInputView, or TFInputView, depending on the riddle.
 * @author Ken Smith, Heather Finch, Katelynn Oleson 
 * @version 1.0.
 */
public class InputPanel extends RoundJPanel {
	// A serialized ID for serialization.
	private static final long serialVersionUID = 5022637539885702888L;
	
	private JButton mySubmitButton;
	private boolean hasSubmitted;
	private JPanel myInputView;
	
	private final static int WIDTH = 250;
	private final static int HEIGHT = 100;
	private final static Color TRANSPARENT_NEON_GREEN = new Color(57,255,020,150);
	
	/**
	 * Creates the InputPanel.
	 */
	public InputPanel(){
		super();
		setBackground(TRANSPARENT_NEON_GREEN);
        setOpaque(false); // must set to false for RoundJPanel
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setPreferredSize(new Dimension(WIDTH,HEIGHT));

	    myInputView = null;
        hasSubmitted = false;
        mySubmitButton = new JButton("Submit");
        mySubmitButton.addActionListener(e -> {
        	hasSubmitted = true;
        });
	}
	
	/**
	 * Returns the Players response to the riddle.
	 * @param Riddle The Riddle we are retrieving the Players reponse to.
	 * @return String The Player response to the Riddle.
	 */
	public String getResponse(Riddle theRiddle) {
		Objects.requireNonNull(theRiddle);
	    if(theRiddle.getType().getLabel().equals("tf")) return ((TFInputView) myInputView).getAnswer();
	    if(theRiddle.getType().getLabel().equals("mc")) return ((MCInputView) myInputView).getAnswer();
	    if(theRiddle.getType().getLabel().equals("fb")) return ((FBInputView) myInputView).getAnswer();
	    return null;

	}
	
	/**
	 * Indicates whether the player has submitted their response to the riddle yet.
	 * @return True if the player has pressed the submit button, false otherwise
	 */
	public boolean hasSubmitted() {
        return hasSubmitted;
    }
    
    /**
     * Resets the InputPanel.
     */
    public void reset() {
        hasSubmitted = false;
        myInputView = null;
        this.removeAll();    
    }
    
    /**
     * Set up the InputPanel with the appropriate Input View based on the RiddleType.
     * @param Riddle The Riddle whose Input View we are setting up for. 
     */
    public void setupView(Riddle theRiddle) {
    	Objects.requireNonNull(theRiddle);
    	if(theRiddle.getType().getLabel().equals("tf")) myInputView = new TFInputView();
    	if(theRiddle.getType().getLabel().equals("mc")) myInputView = new MCInputView(theRiddle);
    	if(theRiddle.getType().getLabel().equals("fb")) myInputView = new FBInputView();
    	
    	add(myInputView);
    	mySubmitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(mySubmitButton);
        add(Box.createRigidArea(new Dimension(0, 10)));
    }    
}

