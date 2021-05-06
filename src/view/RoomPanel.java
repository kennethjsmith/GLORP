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

import model.Alien;

/**
 * ref: https://stackoverflow.com/questions/22766209/i-need-a-little-help-implementing-player-movement-on-java-grid
 * @author 12538
 *
 */
public class RoomPanel extends JPanel {

    // fields
	private static final long serialVersionUID = 1L;
	//TODO: add front/rear facing icons
    //left facing alien icons
    private static ImageIcon[] LEFT_RUN_ICONS = new ImageIcon[] 
    		{new ImageIcon("src/icons/left_running_alien1.png"), 
    			new ImageIcon("src/icons/left_running_alien2.png"),
    				new ImageIcon("src/icons/left_running_alien3.png"),
    					new ImageIcon("src/icons/left_running_alien4.png")};
    private static ImageIcon LEFT_STANDING_ICON = new ImageIcon("src/icons/left_standing_alien.png");
    //right facing alien icons
    private static ImageIcon[] RIGHT_RUN_ICONS = new ImageIcon[] 
    		{new ImageIcon("src/icons/right_running_alien1.png"), 
    			new ImageIcon("src/icons/right_running_alien2.png"),
    				new ImageIcon("src/icons/right_running_alien3.png"),
    					new ImageIcon("src/icons/right_running_alien4.png")};
    private static ImageIcon RIGHT_STANDING_ICON = new ImageIcon("src/icons/right_standing_alien.png");
    private static final int ICON_SIZE = 100;
    private int myCurrentRunIconValue; //used to determine which running icon
    private ImageIcon myCurrentIcon;
    private boolean skipFrame;
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

    public RoomPanel() {
        setPreferredSize(new Dimension(SIZE,SIZE));
        // add border
        Border blackline = BorderFactory.createLineBorder(Color.black);
        setBorder(blackline);
        //resize all image icons
        resizeImageIcons();
        
        myCurrentRunIconValue = 0;
        myPlayerDirection = "RIGHT";
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
    
    /**
     * Helper for resizing single icon
     */
    private static void resizeImageIcons() {
    	Image inTempImage;
    	//resize left facing standing icon
    	inTempImage = LEFT_STANDING_ICON.getImage(); // transform it 
		inTempImage = inTempImage.getScaledInstance(ICON_SIZE, ICON_SIZE,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
		LEFT_STANDING_ICON = new ImageIcon(inTempImage);  // transform it back
    	
		//resize left facing standing icon
    	inTempImage = RIGHT_STANDING_ICON.getImage(); // transform it 
		inTempImage = inTempImage.getScaledInstance(ICON_SIZE, ICON_SIZE,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
		RIGHT_STANDING_ICON = new ImageIcon(inTempImage);  // transform it back
    
		resizeImageIconArrays();
    }
    
    
    /**
     * Helper method, resizes to standard icon size.
     * @param theIcons
     */
    private static void resizeImageIconArrays() {

    	//resize left facing run icons
    	for(int i = 0; i < LEFT_RUN_ICONS.length; i++){
    		Image inTempImage = LEFT_RUN_ICONS[i].getImage(); // transform it 
    		inTempImage = inTempImage.getScaledInstance(ICON_SIZE, ICON_SIZE,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
    		LEFT_RUN_ICONS[i] = new ImageIcon(inTempImage);  // transform it back
    	}
  
    	//resize right facing run icons
    	for(int i = 0; i < RIGHT_RUN_ICONS.length; i++){
    		Image inTempImage = RIGHT_RUN_ICONS[i].getImage(); // transform it 
    		inTempImage = inTempImage.getScaledInstance(ICON_SIZE, ICON_SIZE,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
    		RIGHT_RUN_ICONS[i] = new ImageIcon(inTempImage);  // transform it back
    	}
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
    		myCurrentIcon = LEFT_RUN_ICONS[myCurrentRunIconValue];
    	else if(myPlayerDirection == PLAYER_DIRECTIONS[1])
    		myCurrentIcon = RIGHT_RUN_ICONS[myCurrentRunIconValue];
    	
        int k = e.getKeyCode();
        pressedKeys.add(k);
        myXVelocity = 0;
        myYVelocity = 0;
        //TODO: this results in always facing left when simultaneous left/right keys are pressed
        //make velocity increment speed a class constant
        if(pressedKeys.contains(KeyEvent.VK_D)) {
        	myXVelocity += myPlayerSpeed;
        	myPlayerDirection = "RIGHT";
        }
    	if(pressedKeys.contains(KeyEvent.VK_A)) {
    		myXVelocity -= myPlayerSpeed;
    		myPlayerDirection = "LEFT";
    	}
    	if(pressedKeys.contains(KeyEvent.VK_S)) myYVelocity += myPlayerSpeed;
    	if(pressedKeys.contains(KeyEvent.VK_W)) myYVelocity -= myPlayerSpeed;
        
    	
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