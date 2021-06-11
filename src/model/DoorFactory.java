/*
 * GLORP: Revenge of the Sphinx
 */

package model;

import java.util.HashMap;
import java.util.Objects;


/**
 * Create the doors for the maze and assigns them to rooms.
 * Adjacent doors share the same door.
 * @authors Heather Finch, Katelynn Oleson, Ken Smith
 * @version 1.0
 */
public class DoorFactory {
    // The array of rooms in the maze.        
    private Room[][] myRooms;
    // Grabs the riddles to store in the door.
    RiddleFactory myRiddles;
    
    /**
     * Constructor creates the door factory.
     * @param theRooms the rooms that doors will be added to
     */
    public DoorFactory(Room[][] theRooms) {
        checkRoomsNonNull(theRooms);
        myRooms = theRooms; 
        myRiddles = new RiddleFactory();
    	injectDoors();
    	myRiddles.close();
    }
    
    /**
     * Returns this door factories modified rooms, now filled with doors.
     * @return Room[][] the 2d array of rooms
     */
    public Room[][] getRooms(){
        return myRooms;
    }
    
    // Checks that theRooms is nonNull and none of the internal rooms are null
    private void checkRoomsNonNull(Room[][] theRooms) throws NullPointerException{
    	Objects.requireNonNull(theRooms);
        
        for(int r = 0; r < theRooms.length; r++) {
            for(int c = 0; c < theRooms[r].length; c++) {
               if(theRooms[r][c] == null) {
                   throw new NullPointerException("Cannot add doors to NullRooms");
               }
            }
        }
    }
    
    // Traverse the rooms to fill them so that adjacent rooms share the same door. 
    private void injectDoors() {
    	 
    	// Iterate through 2d array of rooms.
        for(int r = 0; r < myRooms.length; r++) {
            for(int c = 0; c < myRooms[r].length; c++) {
            	
            	// Each rooms will store this HashMap.
            	HashMap<Direction, Door> doorMap = new HashMap<Direction, Door>();
            	
                // Fill the HashMap of directions -> doors.
                if(r > 0) { // If there is a connecting room above grab its bottom door for this rooms top door.
                	Door currDoor = myRooms[r-1][c].getDoors().get(Direction.SOUTH);
                	doorMap.put(Direction.NORTH, currDoor);
        		// Else there was no connecting room above, so make a new door.
                } else doorMap.put(Direction.NORTH, new Door(Direction.NORTH, myRiddles.getNextRiddle()));
                if(c > 0) { // if there is a connecting room to the left grab its right door for this rooms left door.
                	Door currDoor = myRooms[r][c-1].getDoors().get(Direction.EAST);
                	doorMap.put(Direction.WEST, currDoor);
            	// Else there was no connecting room to the left, so make a new door.
                } else doorMap.put(Direction.WEST, new Door(Direction.WEST, myRiddles.getNextRiddle()));
                
                doorMap.put(Direction.EAST, new Door(Direction.EAST, myRiddles.getNextRiddle())); // Add right door.
                doorMap.put(Direction.SOUTH, new Door(Direction.SOUTH, myRiddles.getNextRiddle())); // Add bottom door.

                myRooms[r][c].setDoors(doorMap); // Fill the room with new door HashMap.
            }
        }
    }
}
