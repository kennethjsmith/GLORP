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

import model.Direction;
import model.Door;
import model.Maze;
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
    
    private Player myCurrentPlayer;
    private final static int SIZE = 500;
    
    public RoomPanel() {
        setPreferredSize(new Dimension(SIZE,SIZE));

        setLayout(new FlowLayout());
        myCurrentPlayer = Maze.getInstance().getPlayer(); // TODO Getting instance of Maze in RoomPanel violates MVC?
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
    		Room currRoom = Maze.getInstance().getCurrRoom();

    		//System.out.println((int)myCurrentPlayer.getCoordinate().getX());
    		//System.out.println((int)myCurrentPlayer.getCoordinate().getY());
    		//TODO: this floor icon will need to come from the room class
    		currRoom.getLargeIcon().paintIcon(this, g, 0, 0);
    		
    		
    		//TODO: fix this HARDCODED doors
    		
    		Door westDoor = currRoom.getDoors().get(Direction.WEST);
			GameIcon westDoorIcon = westDoor.getMyIcon();
			westDoorIcon.resize(20, 100);
			westDoorIcon.paintIcon(this, g, 0, 200);
    		
    		Door eastDoor = currRoom.getDoors().get(Direction.EAST);
			GameIcon eastDoorIcon = eastDoor.getMyIcon();
			eastDoorIcon.resize(20, 100);
			eastDoorIcon.paintIcon(this, g, 480, 200);
    		
    		Door northDoor = currRoom.getDoors().get(Direction.NORTH);
			GameIcon northDoorIcon = northDoor.getMyIcon();
			northDoorIcon.resize(100, 20);
			northDoorIcon.paintIcon(this, g, 200, 0);
    		
    		Door southDoor = currRoom.getDoors().get(Direction.SOUTH);
    		GameIcon southDoorIcon = southDoor.getMyIcon();
    		southDoorIcon.resize(100, 20);
    		southDoorIcon.paintIcon(this, g, 200, 480);
    		
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