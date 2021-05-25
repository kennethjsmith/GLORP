package view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.Direction;
import model.Door;
import model.Maze;
import model.Room;

public class MapView extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final String TITLE = "Map";
	private final static int SIZE = 245;
    private Maze myMaze;
    private Map <Room, Point> myRooms;
    private Map <Direction, Door> myDoors;
    private Room myCurrentRoom;
    
	// Constructor 
	public MapView() {
		// Sets the size of the JPanel
        setPreferredSize(new Dimension(SIZE, SIZE));
        // Creates a new maze: TODO Should the MapView receive the Maze a parameter?
        myMaze = Maze.getInstance();
        // Creates a map of all the rooms
        myRooms = new HashMap<Room, Point>();
        myDoors = new HashMap<Direction, Door>();
        addRooms();
        
        myCurrentRoom = myMaze.getCurrRoom();
    }
	
	// Using the maze, adds all of the rooms to the myRooms map with their icon and dimension
	private void addRooms() {
		//Room[][] myCurrentRooms = myMaze.getRooms();		
		for(int row = 0; row < myMaze.getLength(); row++) {
			for(int col = 0; col < myMaze.getWidth(); col++) {			
				// TODO Add on a if room.isvisited boolean here
				ImageIcon currentIcon = myMaze.getRoom(row + 1, col + 1).getSmallIcon();
				int iconCol = currentIcon.getIconWidth();
				int iconRow = currentIcon.getIconHeight();
				Point roomCoordinates = new Point(col * iconCol, row * iconRow);
				myRooms.put(myMaze.getRoom(row + 1, col + 1), roomCoordinates);
				
//				HashMap<Direction, Door> doors = myMaze.getRoom(row, col).getDoors();
//				for(Entry<Direction, Door> d : doors.entrySet()){
//					myDoors.put(d.getKey(), d.getValue());
//				}
				
			}
		}
		repaint();
	}
    
    @Override
    public void paintComponent(Graphics theGraphics) {
    	super.paintComponent(theGraphics);
    	final Graphics2D g2d = (Graphics2D) theGraphics;
    	GameIcon lockedWEDoor = new GameIcon("src/icons/door_yellow.png");
    	lockedWEDoor.resize(3, 10);
    	
    	GameIcon lockedNSDoor = new GameIcon("src/icons/door_yellow.png");
    	lockedNSDoor.resize(10, 3);
    	
    	for(Room theRoom : myRooms.keySet()) {
			int xCoordinate = myRooms.get(theRoom).x;
			int yCoordinate = myRooms.get(theRoom).y;
			Icon roomIcon = theRoom.getSmallIcon();
			

			roomIcon.paintIcon(this, g2d, xCoordinate, yCoordinate);
			
			// Doors
			// This is the buffer between the top right corner of the room, and the top right corner of the door icon
			// TODO generalize this so it's not just "-5", and can change based on window size.
			int doorIconPlacement = roomIcon.getIconWidth()/2 - 5;
			//if(!Maze.getInstance().canMove(Direction.NORTH, theRoom))lockedNSDoor.paintIcon(this, g2d, xCoordinate + doorIconPlacement, yCoordinate - 2);
			
			GameIcon northDoorIcon = theRoom.getDoors().get(Direction.NORTH).getMyIcon();
			northDoorIcon.resize(10, 3);
			northDoorIcon.paintIcon(this, g2d, xCoordinate + doorIconPlacement, yCoordinate - 2);
			
			GameIcon eastDoorIcon = theRoom.getDoors().get(Direction.EAST).getMyIcon();
			eastDoorIcon.resize(3, 10);
			eastDoorIcon.paintIcon(this, g2d, xCoordinate - 2, yCoordinate + doorIconPlacement);
			
			GameIcon southDoorIcon = theRoom.getDoors().get(Direction.SOUTH).getMyIcon();
			southDoorIcon.resize(10, 3);
			southDoorIcon.paintIcon(this, g2d, xCoordinate + doorIconPlacement, yCoordinate - 2);
			
			GameIcon westDoorIcon = theRoom.getDoors().get(Direction.WEST).getMyIcon();
			westDoorIcon.resize(3, 10);
			westDoorIcon.paintIcon(this, g2d, xCoordinate - 2, yCoordinate + doorIconPlacement);
    	}
    	
//    	for(Direction d : myDoors.keySet()) {
//    		GameIcon doorIcon = myDoors.get(d).getMyIcon();
//    		if(d.getLabel() == "N" || d.getLabel() == "S") {
//    			doorIcon.resize(10, 3);
//    			doorIcon.paintIcon(this, g2d, SIZE, SIZE);
//    		}
//    		doorIcon.resize(10, 3);
//    		
//    	}
    }	
}
