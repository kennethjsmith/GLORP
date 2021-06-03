package model;

import java.awt.Point;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import sql.TFRiddleDatabase;
import view.GameIcon;

/**
 * Create the doors for the maze. 
 * @authors Heather Finch, Katelynn Oleson, Ken Smith
 * @version
 */
public class DoorFactory {
    private final ResultSet tfRiddleSet;
        
    private Room[][] myRooms;
    
    private int RIDDLECOUNT = 0; //used for testing
    
    /**
     * Default constructor.
     */
    public DoorFactory() {
        myRooms = new Room[0][0];
        TFRiddleDatabase myTrueFalseRiddles = new TFRiddleDatabase();
        tfRiddleSet = myTrueFalseRiddles.getTFRiddleSet();
        injectDoors();   
    }
    
    public DoorFactory(Room[][] theRooms) {
        //requireNonNull(theRooms, "Cannot add doors to NullRooms"); //java not recognizing this??
        checkRoomsNonNull(theRooms);
        myRooms = theRooms;
        TFRiddleDatabase myTrueFalseRiddles = new TFRiddleDatabase();
        tfRiddleSet = myTrueFalseRiddles.getTFRiddleSet();
    	injectDoors(); 
    }
    
    /**
     * Returns this door factories modified rooms, now filled with doors
     * @return
     */
    public Room[][] getRooms(){
        return myRooms;
    }
    
    /**
     * Checks that theRooms is nonNull and none of the internal rooms are null
     */
    private void checkRoomsNonNull(Room[][] theRooms) throws NullPointerException{
        if(theRooms == null) {
            throw new NullPointerException("Cannot add doors to NullRooms");
        }
        
        for(int r = 0; r < theRooms.length; r++) {
            for(int c = 0; c < theRooms[r].length; c++) {
               if(theRooms[r][c] == null) {
                   throw new NullPointerException("Cannot add doors to NullRooms");
               }
            }
        }
    }
    
    /**
     * Traverse the rooms to fill them so that 
     * the doors of adjacent rooms correspond correctly 
     */
    private void injectDoors() {
    	 
        for(int r = 0; r < myRooms.length; r++) {
            for(int c = 0; c < myRooms[r].length; c++) {
            	HashMap<Direction, Door> doorMap = new HashMap<Direction, Door>();

                // fill the array of doors
                if(r > 0) { // if there is a connecting room above
                    // grab its bottom door for this rooms top door
                	Door currDoor = myRooms[r-1][c].getDoors().get(Direction.SOUTH);
                	doorMap.put(Direction.NORTH, currDoor);
                	
                } else doorMap.put(Direction.NORTH, new Door(Direction.NORTH, getNextRiddle(RiddleType.TRUE_FALSE)));
                
                if(c > 0) { // if there is a connecting room to the left
                    // grab its right door for this rooms left door
                	Door currDoor = myRooms[r][c-1].getDoors().get(Direction.EAST);
                	doorMap.put(Direction.WEST, currDoor);                	
                } else doorMap.put(Direction.WEST, new Door(Direction.WEST, getNextRiddle(RiddleType.TRUE_FALSE)));
                
                
            	//tfRiddleSet.next();
                doorMap.put(Direction.EAST, new Door(Direction.EAST, getNextRiddle(RiddleType.TRUE_FALSE)));
            	//tfRiddleSet.next();
                doorMap.put(Direction.SOUTH, new Door(Direction.SOUTH, getNextRiddle(RiddleType.TRUE_FALSE)));

                myRooms[r][c].setDoors(doorMap); // fill the room with new door array
            }
        }
    }
    
    private Riddle getNextRiddle(RiddleType theType) {
    	Riddle currRiddle = null;
    	try {
	    	if(theType.getLabel().equals("tf") && tfRiddleSet.next()){
						RIDDLECOUNT++;
		            	currRiddle = new TFRiddle(tfRiddleSet.getString("question"), tfRiddleSet.getString("answer"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

    	return currRiddle; // TODO null handling
    }

}
