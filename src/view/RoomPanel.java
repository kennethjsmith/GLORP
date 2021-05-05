package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.HashSet;
import java.util.Set;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.Alien;

/**
 * ref: https://stackoverflow.com/questions/22766209/i-need-a-little-help-implementing-player-movement-on-java-grid
 * @author 12538
 *
 */
public class RoomPanel extends JPanel {

    // fields
	private static final long serialVersionUID = 1L;
	
    //left facing alien icons
    private ImageIcon[] LEFT_RUNNING_ICONS = new ImageIcon[] 
    		{new ImageIcon("src/icons/left_running_alien1.png"), 
    			new ImageIcon("src/icons/left_running_alien2.png"),
    				new ImageIcon("src/icons/left_running_alien3.png"),
    					new ImageIcon("src/icons/left_running_alien4.png")};
    private ImageIcon LEFT_STANDING_ICON = new ImageIcon("src/icons/left_standing_alien.png");
    //right facing alien icons
    private ImageIcon[] RIGHT_RUNNING_ICONS = new ImageIcon[] 
    		{new ImageIcon("src/icons/right_running_alien1.png"), 
    			new ImageIcon("src/icons/right_running_alien2.png"),
    				new ImageIcon("src/icons/right_running_alien3.png"),
    					new ImageIcon("src/icons/right_running_alien4.png")};
    private ImageIcon RIGHT_STANDING_ICON = new ImageIcon("src/icons/right_standing_alien.png");
    
    private int myCurrentRunIconValue; //used to determine which running icon
    private ImageIcon myCurrentIcon;
    private boolean skipFrame;
    private String myPlayerDirection;
    private String myPlayerMovementStatus;
    private static final String[] PLAYER_DIRECTIONS = {"LEFT", "RIGHT"};
    private static final String[] PLAYER_MOVEMENT_STATUS = {"RUNNING", "STANDING"};
    
    // Set of currently pressed keys
    //TODO: create a movement/controller class?
    private final Set<Integer> pressedKeys = new HashSet<Integer>();
    private int myXVelocity;
    private int myYVelocity;
    private int myXCoordinate = 0;
    private int myYCoordinate = 0;
    
    private final String TITLE = "Room";

    public RoomPanel() {
    	//get rid of magic numbers
        setPreferredSize(new Dimension(600,600));
        //this gets set to 3 so that it get
        myCurrentRunIconValue = 3;
        myPlayerMovementStatus = "STANDING";
        myCurrentIcon = RIGHT_STANDING_ICON;
        skipFrame = true;
        JLabel label = new JLabel(TITLE, JLabel.LEFT);
        setLayout(new FlowLayout());
        add(label); 
    }

    @Override
    public void update(Graphics g) {
        paint(g);
    }

    @Override
    public void paintComponent(Graphics g) {
        
    	super.paintComponent(g);
        //Graphics2D g2d = (Graphics2D) g;
        myCurrentIcon.paintIcon(this, g, myXCoordinate, myYCoordinate);
    }

    public void keyPressed(KeyEvent e) {
    	//skip frame used to slow animation, could instead add more images
    	if(!skipFrame) {
	    	myCurrentRunIconValue++;
	    	if(myCurrentRunIconValue == 4) myCurrentRunIconValue = 0;
    	}
    	skipFrame = !skipFrame;
    	
    	//TODO: create getIcon() class
    	//determine direction
    	if(myPlayerDirection == PLAYER_DIRECTIONS[0])
    		myCurrentIcon = LEFT_RUNNING_ICONS[myCurrentRunIconValue];
    	else if(myPlayerDirection == PLAYER_DIRECTIONS[1])
    		myCurrentIcon = RIGHT_RUNNING_ICONS[myCurrentRunIconValue];
    	
        int k = e.getKeyCode();
        pressedKeys.add(k);
        myXVelocity = 0;
        myYVelocity = 0;
        //TODO: this results in always facing left when simultaneous left/right keys are pressed
        //make velocity increment speed a class constant
        if(pressedKeys.contains(KeyEvent.VK_D)) {
        	myXVelocity += 4;
        	myPlayerDirection = "RIGHT";
        }
    	if(pressedKeys.contains(KeyEvent.VK_A)) {
    		myXVelocity -= 4;
    		myPlayerDirection = "LEFT";
    	}
    	if(pressedKeys.contains(KeyEvent.VK_S)) myYVelocity += 4;
    	if(pressedKeys.contains(KeyEvent.VK_W)) myYVelocity -= 4;
        
    	//TODO: Upperbound of 515 is temporary, image size should be adjusted to a class constant (same with
    	//panel size) remove magic numbers
        if((myXCoordinate >= 0 && myXVelocity < 0) || 
        		(myXCoordinate <= 515 && myXVelocity > 0)) myXCoordinate += myXVelocity;
        if((myYCoordinate >= 0 && myYVelocity < 0) || 
        		(myYCoordinate <= 515 && myYVelocity > 0)) myYCoordinate += myYVelocity;
    }

	public void keyReleased(KeyEvent e) {
		int k = e.getKeyCode();
		pressedKeys.remove(k);
		
		if(myPlayerDirection == PLAYER_DIRECTIONS[0])
    		myCurrentIcon = LEFT_STANDING_ICON;
    	else if(myPlayerDirection == PLAYER_DIRECTIONS[1])
    		myCurrentIcon = RIGHT_STANDING_ICON;

		myCurrentRunIconValue = 2;
	}
}