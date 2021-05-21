package view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
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
    private Map <ImageIcon, Dimension> myRooms;
    
    
    
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
        myRooms = new HashMap<ImageIcon, Dimension>();
        addRooms();
    }
	
	// Using the maze, adds all of the rooms to the myRooms map with their icon and dimension
	private void addRooms() {
		Room[][] myCurrentRooms = myMaze.getRooms();		

		for(int i = 0; i < myCurrentRooms.length; i++) {
			for(int j = 0; j < myCurrentRooms[i].length; j++) {
				
				// Add on a if room.isvisited boolean here
				if(myMaze.containsRoom(i, j)) {
					ImageIcon currentIcon = myMaze.getRoom(i, j).getSmallIcon();
					int iconWidth = currentIcon.getIconWidth();
					int iconHeight = currentIcon.getIconHeight();
					Dimension currentDimension = new Dimension(i * iconWidth, j * iconHeight);
					myRooms.put(new ImageIcon(currentIcon.getImage()), currentDimension);
				}
			}
		}
		repaint();
	}
    
    @Override
    public void paintComponent(Graphics theGraphics) {
    	super.paintComponent(theGraphics);
    	final Graphics2D g2d = (Graphics2D) theGraphics;
    	for(Map.Entry<ImageIcon, Dimension> theRoom : myRooms.entrySet()) {
			int xCoordinate = theRoom.getValue().width;
			int yCoordinate = theRoom.getValue().height;
			Icon currIcon = theRoom.getKey();
			currIcon.paintIcon(this, g2d, xCoordinate, yCoordinate);
    	}
    }	
}
