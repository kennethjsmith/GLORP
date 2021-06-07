package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.BorderFactory;
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
public class InputPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5022637539885702888L;
	private final static int WIDTH = 275;
	private final static int HEIGHT = 125;
	
	 private JButton mySubmitButton;
	 private boolean hasSubmitted;
	 private TFInputView myTFInputView;
	 private MCInputView myMCInputView;
	 private FBInputView myFBInputView;
	
	/**
	 * 
	 */
	public InputPanel(){
		super();
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setPreferredSize(new Dimension(WIDTH,HEIGHT));
		// Create a border
		
	    Border whiteline = BorderFactory.createLineBorder(Color.WHITE);
	    setBorder(whiteline);

	    myTFInputView = null;
	    myMCInputView = null;
	    myFBInputView = null;
	    
        hasSubmitted = false;
        mySubmitButton = new JButton("Submit");
        mySubmitButton.addActionListener(e -> {
        	hasSubmitted = true;
        });
	}
	
	/**
	 * 
	 */
	@Override
    public void paintComponent(Graphics g) {
    	super.paintComponent(g);
    }
	
	// TODO make this work for all 3 riddle types
	public String getResponse(Riddle theRiddle) {
	    if(theRiddle.getType().getLabel().equals("tf")) return myTFInputView.getAnswer();
	    if(theRiddle.getType().getLabel().equals("mc")) return myMCInputView.getAnswer();
	    if(theRiddle.getType().getLabel().equals("fb")) return myFBInputView.getAnswer();
	    return null;

	}
	
	public boolean hasSubmitted() {
        return hasSubmitted;
    }
    
    public void reset() {
        hasSubmitted = false;
        myTFInputView = null;
        myMCInputView = null;
        myFBInputView = null;
        this.removeAll();
        
    }
    
    public void setupView(Riddle theRiddle) {
    	if(theRiddle.getType().getLabel().equals("tf")) {
    		myTFInputView = new TFInputView();
            this.add(myTFInputView,0);
    	}
    	if(theRiddle.getType().getLabel().equals("mc")) {
    		myMCInputView = new MCInputView(theRiddle);
    		this.add(myMCInputView, 0);
    	}
    	if(theRiddle.getType().getLabel().equals("fb")) {
    		myFBInputView = new FBInputView();
    		this.add(myFBInputView, 0);
    	}
        this.add(mySubmitButton, 1);    	
    }    
    
//    private class retreatActionListener implements ActionListener{
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            hasRetreated = true;
//            System.out.println("retreated");
//        }
//    }
    
//    @Override
//    public void keyTyped(KeyEvent e) {
//        // TODO Auto-generated method stub
//        
//    }
//
//    @Override
//    public void keyPressed(KeyEvent e) {
//        // lose focus 
//        
//    }
//
//    @Override
//    public void keyReleased(KeyEvent e) {
//        // TODO Auto-generated method stub      
//    }
}

