package model;

import java.awt.Point;
import java.util.HashMap;

import view.GameIcon;

/**
 * Create the doors for the maze 
 * and fill the mazes room with doors
 * @author katel
 *
 */
public class DoorFactory {
    private static int MAX_DOORS = 4; 
    // currently matching the doors according to position in array 
    //[top, left, right, bottom] 
    private static int TOP_INDEX = 0;
    private static int LEFT_INDEX = 1;
    private static int RIGHT_INDEX = 2;
    private static int BOTTOM_INDEX = 3;
    
    // could make this an array list and connect according to coordinates
   
    // fix these so they make sense within a room panel 
    // or can make getters for these (enums?), and let the panel interpret from there
    private static Point TOP_COORDINATE = new Point(0,1);
    private static Point BOTTOM_COORDINATE = new Point(0, -1);
    private static Point LEFT_COORDINATE = new Point(-1, 0);
    private static Point RIGHT_COORDINATE = new Point(1,0);
        
    private Room[][] myRooms;
    
    public DoorFactory() {
        myRooms = new Room[0][0];
        injectDoors();
    }
    
    public DoorFactory(Room[][] theRooms) {
        //requireNonNull(theRooms, "Cannot add doors to NullRooms"); //java not recognizing this??
        checkRoomsNonNull(theRooms);
        myRooms = theRooms;
        injectDoors(); 
    }
    
    /**
     * Returns this door factories modified rooms, now filled with doors
     * @return
     */
    public Room[][] getRooms(){
        return myRooms;
    }
    
    /*
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
//    
//    /*
//     * Traverse the rooms to fill them so that 
//     * the doors of adjacent rooms coorespond correctly 
//     */
//    private void injectDoors() {
//    	HashMap<Door, Direction> doorMap;
//        Door[] inDoors;  
//        for(int r = 0; r < myRooms.length; r++) {
//            for(int c = 0; c < myRooms[r].length; c++) {
//                // create new array of doors
//                inDoors = new Door[MAX_DOORS]; //[top, left, right, bottom]
//                
//                // fill the array of doors
//                if(r > 0) { // if there is a connecting room above
//                    // grab its bottom door for this rooms top door
//                    inDoors[TOP_INDEX] = myRooms[r-1][c].getDoors()[BOTTOM_INDEX];
//                }else
//                    inDoors[TOP_INDEX] = new Door(TOP_COORDINATE);
//                
//                if(c > 0) { // if there is a connecting room to the left
//                    //grab its right door for this rooms left door
//                    inDoors[LEFT_INDEX] = myRooms[r][c-1].getDoors()[RIGHT_INDEX];
//                }else 
//                    inDoors[LEFT_INDEX] = new Door(LEFT_COORDINATE);
//                
//                inDoors[RIGHT_INDEX] = new Door(RIGHT_COORDINATE);
//                inDoors[BOTTOM_INDEX] = new Door(BOTTOM_COORDINATE);
//
//                myRooms[r][c].setDoors(inDoors); // fill the room with new door array
//            }
//        }
//    } 
    
    /*
     * Traverse the rooms to fill them so that 
     * the doors of adjacent rooms coorespond correctly 
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
                	
                } else doorMap.put(Direction.NORTH, new Door(TOP_COORDINATE));
                
                if(c > 0) { // if there is a connecting room to the left
                    //grab its right door for this rooms left door
                	Door currDoor = myRooms[r][c-1].getDoors().get(Direction.EAST);
                	doorMap.put(Direction.WEST, currDoor);                	
                } else doorMap.put(Direction.WEST, new Door(LEFT_COORDINATE));
                
                doorMap.put(Direction.EAST, new Door(RIGHT_COORDINATE));
                doorMap.put(Direction.SOUTH, new Door(BOTTOM_COORDINATE));

                myRooms[r][c].setDoors(doorMap); // fill the room with new door array
            }
        }
    }  

}
