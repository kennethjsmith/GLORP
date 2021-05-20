package model;

import java.awt.Point;

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
    private static int TOP_INDEX = 1;
    private static int LEFT_INDEX = 2;
    private static int RIGHT_INDEX = 3;
    private static int BOTTOM_INDEX = 4;
    
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
    }
    
    public DoorFactory(Room[][] theRooms) {
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
     * Traverse the rooms to fill them so that 
     * the doors of adjacent rooms coorespond correctly 
     */
    private void injectDoors() {
        Door[] inDoors;  
        for(int r = 0; r < myRooms.length; r++) {
            for(int c = 0; c < myRooms[0].length; c++) {
                // create new array of doors
                inDoors = new Door[MAX_DOORS]; //[top, left, right, bottom]
                
                // fill the array of doors
                if(r > 0) { // if there is a connecting room above
                    // grab its bottom door for this rooms top door
                    inDoors[TOP_INDEX] = myRooms[r-1][c].getDoors()[BOTTOM_INDEX];
                }else
                    inDoors[TOP_INDEX] = new Door(TOP_COORDINATE);
                
                if(c > 0) { // if there is a connecting room to the left
                    //grab its right door for this rooms left door
                    inDoors[LEFT_INDEX] = myRooms[r][c-1].getDoors()[RIGHT_INDEX];
                }else 
                    inDoors[LEFT_INDEX] = new Door(LEFT_COORDINATE);
                
                inDoors[RIGHT_INDEX] = new Door(RIGHT_COORDINATE);
                inDoors[BOTTOM_INDEX] = new Door(BOTTOM_COORDINATE);
                
                myRooms[r][c].setDoors(inDoors); // fill the room with new door array
            }
        }
        blockBoarderDoors();
    }
    
    private void blockBoarderDoors() {
        for(int r = 0; r < myRooms.length; r++) {
            for(int c = 0; c < myRooms[0].length; c++) {
                if(r == 0) {
                    myRooms[r][c].getDoors()[TOP_INDEX].setBlocked();
                }else if(r == myRooms.length - 1) {
                    myRooms[r][c].getDoors()[BOTTOM_INDEX].setBlocked(); 
                }else if(c == 0) {
                    myRooms[r][c].getDoors()[LEFT_INDEX].setBlocked();
                }else if(c == myRooms[0].length - 1) {
                    myRooms[r][c].getDoors()[RIGHT_INDEX].setBlocked();
                } 
            }
        }
    }
    
//    // would fill the remaining doors with new doors (maybe needed for arrayList doors)
//    private Door[] fillRemainingDoors(Door[] theDoors) {
////        for() { // for remaining doors unfilled, fill them
////            
////        }
//        return theDoors;
//    }
    
    /*
     * Blocks all doors along the boarder to simulate a wall
     */
    
    

}
