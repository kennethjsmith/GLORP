package view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;

import javax.swing.JPanel;

import model.Direction;
import model.Fixture;
import model.Item;
import model.Maze;
import model.Player;
import model.Room;

/**
 * Displays the current room, the Player and all Items and Fixtures within.
 * @author Ken Smith, Heather Finch, Katelynn Oleson 
 * @version 1.0.
 */
public class RoomPanel extends JPanel {

	private static final long serialVersionUID = 1L;
    private Player myCurrentPlayer;
    private final static int SIZE = 501;
    
    /**
     * Creates the RoomPanel.
     */
    public RoomPanel() {
        setPreferredSize(new Dimension(SIZE,SIZE));
        setLayout(new FlowLayout());
        myCurrentPlayer = Maze.getInstance().getPlayer();
    }

    /**
     * Updates the RoomPanel.
     */
    @Override
    public void update(Graphics g) {
        paint(g);
    }
 
    /**
     * Paints the RoomPanel.
     */
    @Override
    public void paintComponent(Graphics g) {  
    	super.paintComponent(g);
    	if(myCurrentPlayer != null) {
    		Room currRoom = Maze.getInstance().getCurrRoom();
    		currRoom.getLargeIcon().paintIcon(this, g, 0, 0);
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
    		
    		// Paint fixture and player.
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
	 * Adds the Player to this room.
	 * @param Player The Player to add to this room.
	 */
	public void setCurrentPlayer(Player myCurrentPlayer) {
		this.myCurrentPlayer = myCurrentPlayer;
	}

}