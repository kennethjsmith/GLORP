package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.util.HashSet;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

/**
 * ref: https://stackoverflow.com/questions/22766209/i-need-a-little-help-implementing-player-movement-on-java-grid
 * @author 12538
 *
 */
public class RoomPanel extends JPanel {

    // fields
	private static final long serialVersionUID = 1L;

    private String myPlayerDirection;
    private static final int myPlayerSpeed = 5;
    private static final String[] PLAYER_DIRECTIONS = {"LEFT", "RIGHT"};
   
    
    // Set of currently pressed keys
    //TODO: create a movement/controller class?
    private final Set<Integer> pressedKeys = new HashSet<Integer>();
    private int myXVelocity;
    private int myYVelocity;
    private int myXCoordinate = 0;
    private int myYCoordinate = 0;
    
    private final String TITLE = "Room";
    private final static int SIZE = 500;
    private final static GameIcon TEST_ICON = new GameIcon("right_alien1.png");
    //private boolean skipFrame; //move to Player, or client???
    
    public RoomPanel() {
        setPreferredSize(new Dimension(SIZE,SIZE));
        // add border
        Border blackline = BorderFactory.createLineBorder(Color.black);
        setBorder(blackline);
        JLabel label = new JLabel(TITLE, JLabel.LEFT);
        setLayout(new FlowLayout());
        add(label); 
        
        skipFrame = false;
    }

    @Override
    public void update(Graphics g) {
        paint(g);
    }
 
    @Override
    public void paintComponent(Graphics g) {
        
    	super.paintComponent(g);
        //Graphics2D g2d = (Graphics2D) g;
        TEST_ICON.paintIcon(this, g, myXCoordinate, myYCoordinate);
    }

    public void keyPressed(KeyEvent e) {
    	//skip frame used to slow animation, might move to Player
//    	if(!skipFrame) {
//	    	myCurrentRunIconValue++;
//	    	if(myCurrentRunIconValue == 4) myCurrentRunIconValue = 0;
//    	}
//    	skipFrame = !skipFrame;
    	
    	//determine direction
    	if(myPlayerDirection == PLAYER_DIRECTIONS[0])
    		myCurrentIcon = LEFT_RUN_ICONS[myCurrentRunIconValue];
    	else if(myPlayerDirection == PLAYER_DIRECTIONS[1])
    		myCurrentIcon = RIGHT_RUN_ICONS[myCurrentRunIconValue];
    	
        int k = e.getKeyCode();
        pressedKeys.add(k);
        myXVelocity = 0;
        myYVelocity = 0;
        //TODO: this results in always facing left when simultaneous left/right keys are pressed
        //make velocity increment speed a class constant
        if(pressedKeys.contains(KeyEvent.VK_D) || pressedKeys.contains(KeyEvent.VK_RIGHT)) {
        	myXVelocity += myPlayerSpeed;
        	myPlayerDirection = "RIGHT";
        }
    	if(pressedKeys.contains(KeyEvent.VK_A) || pressedKeys.contains(KeyEvent.VK_LEFT)) {
    		myXVelocity -= myPlayerSpeed;
    		myPlayerDirection = "LEFT";
    	}
    	if(pressedKeys.contains(KeyEvent.VK_S) || pressedKeys.contains(KeyEvent.VK_DOWN)) myYVelocity += myPlayerSpeed;
    	if(pressedKeys.contains(KeyEvent.VK_W) || pressedKeys.contains(KeyEvent.VK_UP)) myYVelocity -= myPlayerSpeed;
        
    	
        if((myXCoordinate >= 0 && myXVelocity < 0) || 
        		(myXCoordinate <= SIZE-ICON_SIZE && myXVelocity > 0)) myXCoordinate += myXVelocity;
        if((myYCoordinate >= 0 && myYVelocity < 0) || 
        		(myYCoordinate <= SIZE-ICON_SIZE && myYVelocity > 0)) myYCoordinate += myYVelocity;
    }

	public void keyReleased(KeyEvent e) {
		int k = e.getKeyCode();
		pressedKeys.remove(k);
		
		if(myPlayerDirection == PLAYER_DIRECTIONS[0])
    		myCurrentIcon = LEFT_STANDING_ICON;
    	else if(myPlayerDirection == PLAYER_DIRECTIONS[1])
    		myCurrentIcon = RIGHT_STANDING_ICON;

		myCurrentRunIconValue = 0;
	}
}