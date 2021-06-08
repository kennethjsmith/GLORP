package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import model.Direction;
import model.Door;
import model.Fixture;
import model.Item;
import model.Maze;
import model.PiecePoint;
import model.Player;
import model.Room;

/**
 * Displays the current room, the Player and all Items and Fixtures within.
 * @author Ken Smith, Heather Finch, Katelynn Oleson 
 * @version 
 */
public class RoomPanel extends JPanel {

    // fields
	private static final long serialVersionUID = 1L;
    private Player myCurrentPlayer;
    private final static int SIZE = 501;
    
    /**
     * 
     */
    public RoomPanel() {
        setPreferredSize(new Dimension(SIZE,SIZE));
//        Border raisedbevel = BorderFactory.createRaisedBevelBorder();
//		Border loweredbevel = BorderFactory.createLoweredBevelBorder();
//		Border compound = BorderFactory.createCompoundBorder(raisedbevel, loweredbevel);
//		setBorder(compound);
        setLayout(new FlowLayout());
        myCurrentPlayer = Maze.getInstance().getPlayer(); // TODO Getting instance of Maze in RoomPanel violates MVC?
    }

    /**
     * 
     */
    @Override
    public void update(Graphics g) {
        paint(g);
    }
 
    /**
     * 
     */
    @Override
    public void paintComponent(Graphics g) {  
    	super.paintComponent(g);
    	//new ImageIcon().paintIcon(this,g,d,d);
    	if(myCurrentPlayer != null) {
    		Room currRoom = Maze.getInstance().getCurrRoom();

    		//System.out.println((int)myCurrentPlayer.getCoordinate().getX());
    		//System.out.println((int)myCurrentPlayer.getCoordinate().getY());
    		//TODO: this floor icon will need to come from the room class
    		currRoom.getLargeIcon().paintIcon(this, g, 0, 0);
    		
    		// paint items (we only have one item per room for now)
//    		Map<Item, PiecePoint> inItems = currRoom.getItem();
//    		for(Entry<Item, PiecePoint> entry : inItems.entrySet()) {
//    			entry.getKey().getRoomIcon().paintIcon(this, g, (int)entry.getValue().getX(),(int) entry.getValue().getY());
//    		}
    		Item inItem = currRoom.getItem();
    		if(inItem != null) {
    			inItem.getRoomIcon().paintIcon(this, g, (int)inItem.getIconArea().getX(),(int)inItem.getIconArea().getY());
    		}
			GameIcon westDoorIcon = currRoom.getDoors().get(Direction.WEST).getRoomIcon();
			westDoorIcon.paintIcon(this, g, 0, 200);
    		
			GameIcon eastDoorIcon = currRoom.getDoors().get(Direction.EAST).getRoomIcon();
			eastDoorIcon.paintIcon(this, g, 480, 200);
    		
			GameIcon northDoorIcon = currRoom.getDoors().get(Direction.NORTH).getRoomIcon();
			northDoorIcon.paintIcon(this, g, 200, 0);
    		
    		GameIcon southDoorIcon = currRoom.getDoors().get(Direction.SOUTH).getRoomIcon();
    		southDoorIcon.paintIcon(this, g, 200, 480);
    		
    		// paint fixture and player
    		Fixture inFixture = currRoom.getFixture();
    		if(inFixture != null && myCurrentPlayer.getBase().intersects(inFixture.getIconArea())) {
    			myCurrentPlayer.getRoomIcon().paintIcon(this, g, (int)myCurrentPlayer.getCoordinate().getX(), (int)myCurrentPlayer.getCoordinate().getY());
    			inFixture.getIcon().paintIcon(this, g, inFixture.getMyXCoordinate(), inFixture.getMyYCoordinate());
    		}
    		else if(inFixture != null) {
    			inFixture.getIcon().paintIcon(this, g, inFixture.getMyXCoordinate(), inFixture.getMyYCoordinate());
    			myCurrentPlayer.getRoomIcon().paintIcon(this, g, (int)myCurrentPlayer.getCoordinate().getX(), (int)myCurrentPlayer.getCoordinate().getY());
    		}
    		else myCurrentPlayer.getRoomIcon().paintIcon(this, g, (int)myCurrentPlayer.getCoordinate().getX(), (int)myCurrentPlayer.getCoordinate().getY());
    	}
    }

	/**
	 * @param myCurrentPlayer the myCurrentPlayer to set
	 */
	public void setCurrentPlayer(Player myCurrentPlayer) {
		this.myCurrentPlayer = myCurrentPlayer;
	}

}