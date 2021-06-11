/*
 * GLORP: Revenge of the Sphinx
 */
package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JPanel;

import model.Direction;
import model.Maze;
import model.Room;

/**
 * Displays the current map of the Maze. Goes on top of MapPanel.
 * @author Ken Smith, Heather Finch, Katelynn Oleson 
 * @version 1.0.
 */
public class MapView extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private final static int SIZE = 256;
    private Maze myMaze;
    private Map <Room, Point> myRooms;
    private static final Color OFF_BLACK = new Color(15,15,15);
    
	/**
	 * Constructs the MapView.
	 */
	public MapView() {
        setPreferredSize(new Dimension(SIZE, SIZE)); // Sets the size of the JPanel
        setBackground(OFF_BLACK);
        myMaze = Maze.getInstance(); // Grabs the maze.
        myRooms = new HashMap<>(); // Creates a map of all the rooms.
        initializeHashMap();
    }
	
	// Fills the myRooms HashMap. This HashMap maps the Rooms to a Point.
	// The point is the top left location of where the rooms will be painted on the MapView.
	private void initializeHashMap() {		
		for(int row = 0; row < myMaze.getLength(); row++) {
			for(int col = 0; col < myMaze.getWidth(); col++) {							
				int x = col * Room.getMapIconSize() + 2;
				int y = row * Room.getMapIconSize() + 2;
				Point roomCoordinates = new Point(x, y);
				Room currRoom = myMaze.getRoom(row+1, col+1);
				myRooms.put(currRoom, roomCoordinates);
			}
		}
	}
    
	/**
	 * Paints the MapView.
	 */
    @Override
    public void paintComponent(Graphics theGraphics) {
    	super.paintComponent(theGraphics);
    	final Graphics2D g2d = (Graphics2D) theGraphics;
    	int doorIconPlacement = Room.getMapIconSize()/2 - 5;
    	
    	// Paint rooms.
    	for(Room theRoom : myRooms.keySet()) {
    		if(theRoom.isVisited()) {
				int xCoordinate = myRooms.get(theRoom).x;
				int yCoordinate = myRooms.get(theRoom).y;
				GameIcon roomIcon = theRoom.getSmallIcon();
				roomIcon.paintIcon(this, g2d, xCoordinate, yCoordinate);
    		}
    	}
    	
    	// Paint doors. This must be done in a separate loop from painting the rooms,
    	// otherwise the rooms will overlap with the doors.
    	for(Room theRoom : myRooms.keySet()) {
    		if(theRoom.isVisited()) {
				int xCoordinate = myRooms.get(theRoom).x;
				int yCoordinate = myRooms.get(theRoom).y;
				
				// Paint the doors, note some doors are painted more than once with how this is currently set up.
				for(Direction d: theRoom.getDoors().keySet()) {
					GameIcon doorIcon = theRoom.getDoors().get(d).getMapIcon();
					if(d == Direction.NORTH) doorIcon.paintIcon(this, g2d, xCoordinate + doorIconPlacement, yCoordinate-2);
					if(d == Direction.SOUTH) doorIcon.paintIcon(this, g2d, xCoordinate + doorIconPlacement, yCoordinate + Room.getMapIconSize());
					if(d == Direction.WEST) doorIcon.paintIcon(this, g2d, xCoordinate - 2, yCoordinate + doorIconPlacement);
					if(d == Direction.EAST)	doorIcon.paintIcon(this, g2d, xCoordinate + Room.getMapIconSize(), yCoordinate + doorIconPlacement);
				}
    		}
    	}
    }	
}
