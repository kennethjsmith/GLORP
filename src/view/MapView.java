package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

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
	private final static int SIZE = 256;
    private Maze myMaze;
    private Map <Room, Point> myRooms;
    private Map<Door, Point> myDoors;
    //private Room myCurrentRoom;
    
	// Constructor 
	public MapView() {
		// Sets the size of the JPanel
        setPreferredSize(new Dimension(SIZE, SIZE));
        setBackground(Color.darkGray);
        // Creates a new maze: TODO Should the MapView receive the Maze a parameter?
        myMaze = Maze.getInstance();
        // Creates a map of all the rooms and a map of the doors
        myRooms = new HashMap<>();
        myDoors = new HashMap<>();
        initializeHashMaps();
        
        //myCurrentRoom = myMaze.getCurrRoom();
    }
	
	// maps rooms to coordinates
	// map doors to coordinates
	private void initializeHashMaps() {
		//Room[][] myCurrentRooms = myMaze.getRooms();
		
		// This is the buffer between the top right corner of the room, and the top right corner of the door icon
    	// TODO generalize this so it's not just "-5", and can change based on window size.
    	//int doorIconPlacement = Room.getMapIconSize()/2 - 5;
		
		for(int row = 0; row < myMaze.getLength(); row++) {
			for(int col = 0; col < myMaze.getWidth(); col++) {			
				// TODO Add on a if room.isvisited boolean here to hide unvisited rooms (doesnt actually go here)
				
				int x = col * Room.getMapIconSize() + 2;
				int y = row * Room.getMapIconSize() + 2;
				Point roomCoordinates = new Point(x, y);
				Room currRoom = myMaze.getRoom(row+1, col+1);
				myRooms.put(currRoom, roomCoordinates);
				
//				Point doorCoordinates = null;
//				for(Direction d: currRoom.getDoors().keySet()) {
//					if(d == Direction.NORTH || d == Direction.SOUTH) {
//						doorCoordinates = new Point(x + doorIconPlacement, y - 2);
//					}
//					if(d == Direction.WEST || d == Direction.EAST) {
//						doorCoordinates = new Point(x - 2, y + doorIconPlacement);
//					}
//					myDoors.put(currRoom.getDoors().get(d), doorCoordinates);
//				}
			}
		}
	}
    
    @Override
    public void paintComponent(Graphics theGraphics) {
    	super.paintComponent(theGraphics);
    	final Graphics2D g2d = (Graphics2D) theGraphics;
    	
    	int doorIconPlacement = Room.getMapIconSize()/2 - 5;
    	
    	// paint rooms
    	for(Room theRoom : myRooms.keySet()) {
    		if(theRoom.isVisited()) {
				int xCoordinate = myRooms.get(theRoom).x;
				int yCoordinate = myRooms.get(theRoom).y;
				GameIcon roomIcon = theRoom.getSmallIcon();
				roomIcon.paintIcon(this, g2d, xCoordinate, yCoordinate);
    		}
    	}
    	// paint doors
    	for(Room theRoom : myRooms.keySet()) {
    		if(theRoom.isVisited()) {
				int xCoordinate = myRooms.get(theRoom).x;
				int yCoordinate = myRooms.get(theRoom).y;
				// paint the doors, these overlap
				for(Direction d: theRoom.getDoors().keySet()) {
					GameIcon doorIcon = theRoom.getDoors().get(d).getMapIcon();
					if(d == Direction.NORTH) {
						doorIcon.paintIcon(this, g2d, xCoordinate + doorIconPlacement, yCoordinate-2);
					}
					if(d == Direction.SOUTH) {
						doorIcon.paintIcon(this, g2d, xCoordinate + doorIconPlacement, yCoordinate + Room.getMapIconSize());
					}
					if(d == Direction.WEST) {
						doorIcon.paintIcon(this, g2d, xCoordinate - 2, yCoordinate + doorIconPlacement);
					}
					if(d == Direction.EAST) {
						doorIcon.paintIcon(this, g2d, xCoordinate + Room.getMapIconSize(), yCoordinate + doorIconPlacement);
					}
					
				}
    		}
    	}
    	
    	for(Door currDoor : myDoors.keySet()) {
    			currDoor.getMapIcon().paintIcon(this, g2d, myDoors.get(currDoor).x, myDoors.get(currDoor).y);
    	}

    	
//    	for(Room theRoom : myRooms.keySet()) {
//			int xCoordinate = myRooms.get(theRoom).x;
//			int yCoordinate = myRooms.get(theRoom).y;
//			
//			
//			GameIcon northDoorIcon = theRoom.getDoors().get(Direction.NORTH).getMyMapIcon();
//			northDoorIcon.paintIcon(this, g2d, xCoordinate + doorIconPlacement, yCoordinate - 2);
//			
//			GameIcon eastDoorIcon = theRoom.getDoors().get(Direction.EAST).getMyMapIcon();
//			eastDoorIcon.paintIcon(this, g2d, xCoordinate - 2, yCoordinate + doorIconPlacement);
//			
//			GameIcon southDoorIcon = theRoom.getDoors().get(Direction.SOUTH).getMyMapIcon();
//			southDoorIcon.paintIcon(this, g2d, xCoordinate + doorIconPlacement, yCoordinate - 2);
//			
//			GameIcon westDoorIcon = theRoom.getDoors().get(Direction.WEST).getMyMapIcon();
//			westDoorIcon.paintIcon(this, g2d, xCoordinate - 2, yCoordinate + doorIconPlacement);
//    	}
    }	
}
