package view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.util.HashMap;
import java.util.Map;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.Maze;
import model.Room;

public class MapPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final String TITLE = "Map";
	private final static int WIDTH = 350;
	private final static int HEIGHT = 300;
    private static final int ICON_SIZE = 100;
    private Maze myMaze;
    private Map <Room, Point> myRooms;
    private Room myCurrentRoom;
    
    
    
	// Constructor 
	public MapPanel() {
		// Sets the size of the JPanel
        setPreferredSize(new Dimension(WIDTH,HEIGHT));
        
        // Gives the JLabel a name "Map"
        JLabel label = new JLabel(TITLE, JLabel.LEFT);
        setLayout(new FlowLayout());
        add(label);
        
        // Creates a new maze: TODO Should the MapPanel receive the Maze a parameter?
        myMaze = Maze.getInstance();
        
        // Creates a map of all the rooms
        myRooms = new HashMap<Room, Point>();
        addRooms();
        
        myCurrentRoom = myMaze.getCurrRoom();
    }
	
	// Using the maze, adds all of the rooms to the myRooms map with their icon and dimension
	private void addRooms() {
		//Room[][] myCurrentRooms = myMaze.getRooms();		

		for(int row = 0; row < myMaze.getLength(); row++) {
			for(int col = 0; col < myMaze.getWidth(); col++) {
				
				// TODO Add on a if room.isvisited boolean here
				if(myMaze.containsRoom(row, col)) {
					ImageIcon currentIcon = myMaze.getRoom(row, col).getSmallIcon();
					int iconCol = currentIcon.getIconWidth();
					int iconRow = currentIcon.getIconHeight();
					Point roomCoordinates = new Point(col * iconCol, row * iconRow);
					myRooms.put(myMaze.getRoom(row, col), roomCoordinates);
				}
			}
		}
		repaint();
	}
    
    @Override
    public void paintComponent(Graphics theGraphics) {
    	super.paintComponent(theGraphics);
    	final Graphics2D g2d = (Graphics2D) theGraphics;
    	for(Room theRoom : myRooms.keySet()) {
			int xCoordinate = myRooms.get(theRoom).x;
			int yCoordinate = myRooms.get(theRoom).y;
			Icon roomIcon = theRoom.getSmallIcon();
			roomIcon.paintIcon(this, g2d, xCoordinate, yCoordinate);
    	}    	
    }	
}
