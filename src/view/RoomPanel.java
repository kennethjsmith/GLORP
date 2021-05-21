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

import model.Player;
import model.Room;

/**
 * ref: https://stackoverflow.com/questions/22766209/i-need-a-little-help-implementing-player-movement-on-java-grid
 * @author 12538
 *
 */
public class RoomPanel extends JPanel {

    // fields
	private static final long serialVersionUID = 1L;
    
    private Room myCurrentRoom; //TODO: create setter, use this not myPlayer on this panel
    private Player myCurrentPlayer;
    private final static int SIZE = 500;
    //private boolean skipFrame; //move to Player, or client???
    
    public RoomPanel() {
        setPreferredSize(new Dimension(SIZE,SIZE));

        Border blackline = BorderFactory.createLineBorder(Color.black);
        setBorder(blackline);
        setLayout(new FlowLayout());
    }

    @Override
    public void update(Graphics g) {
        paint(g);
    }
 
    @Override
    public void paintComponent(Graphics g) {  
    	super.paintComponent(g);
    	//new ImageIcon().paintIcon(this,g,d,d);
    	if(myCurrentPlayer != null) {
    		//System.out.println((int)myCurrentPlayer.getCoordinate().getX());
    		//System.out.println((int)myCurrentPlayer.getCoordinate().getY());
    		//TODO: this floor icon will need to come from the room class
    		GameIcon floor = new GameIcon("src/icons/floor.jpg");
    		floor.resize(500);
    		floor.paintIcon(this, g, 0, 0);
    		
    		//TODO: fix this HARDCODED doors
    		GameIcon westDoor = new GameIcon("src/icons/WE_door.png");
    		westDoor.resize(20,100);
    		westDoor.paintIcon(this, g, 0, 200);
    		GameIcon eastDoor = new GameIcon("src/icons/WE_door.png");
    		westDoor.resize(20,100);
    		eastDoor.paintIcon(this, g, 480, 200);
    		GameIcon northDoor = new GameIcon("src/icons/NS_door.png");
    		northDoor.resize(100, 20);
    		northDoor.paintIcon(this, g, 200, 0);
    		GameIcon southDoor = new GameIcon("src/icons/NS_door.png");
    		northDoor.resize(100, 20);
    		southDoor.paintIcon(this, g, 200, 480);
    		
    		
    		
    		myCurrentPlayer.getRoomIcon().paintIcon(this, g, (int)myCurrentPlayer.getCoordinate().getX(), (int)myCurrentPlayer.getCoordinate().getY());
    		//System.out.println(myCurrentPlayer.getRoomIcon());
    		//System.out.println(myCurrentPlayer);
    	}
    }

	/**
	 * @param myCurrentPlayer the myCurrentPlayer to set
	 */
	public void setCurrentPlayer(Player myCurrentPlayer) {
		this.myCurrentPlayer = myCurrentPlayer;
	}

}