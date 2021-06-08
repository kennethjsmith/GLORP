package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.Border;

import model.Riddle;


/**
 * The panel for player input.
 * @author Ken Smith, Heather Finch, Katelynn Oleson 
 * @version 
 */
public class InputPanel extends RoundJPanel {
	// fields
	private static final long serialVersionUID = 5022637539885702888L;
	
	private JButton mySubmitButton;
	private boolean hasSubmitted;
	private JPanel myInputView;
	
	private final static int WIDTH = 250;
	private final static int HEIGHT = 100;
	private final Color TRANSPARENT_NEON_GREEN = new Color(57,255,020,150);
	
	/**
	 * 
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
	
	// TODO we can create an InputView interface to avoid having to cast
	public String getResponse(Riddle theRiddle) {
	    if(theRiddle.getType().getLabel().equals("tf")) return ((TFInputView) myInputView).getAnswer();
	    if(theRiddle.getType().getLabel().equals("mc")) return ((MCInputView) myInputView).getAnswer();
	    if(theRiddle.getType().getLabel().equals("fb")) return ((FBInputView) myInputView).getAnswer();
	    return null;

	}
	
	public boolean hasSubmitted() {
        return hasSubmitted;
    }
    
    public void reset() {
        hasSubmitted = false;
        myInputView = null;
        this.removeAll();    
    }
    
    // TODO: enums for riddle types could simplify this and other sections
    public void setupView(Riddle theRiddle) {
    	if(theRiddle.getType().getLabel().equals("tf")) {
    		myInputView = new TFInputView();
    	}
    	if(theRiddle.getType().getLabel().equals("mc")) {
    		myInputView = new MCInputView(theRiddle);
    	}
    	if(theRiddle.getType().getLabel().equals("fb")) {
    		myInputView = new FBInputView();
    	}
    	add(myInputView);
    	mySubmitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(mySubmitButton);
        add(Box.createRigidArea(new Dimension(0, 10)));
    }    
}

