package model;

/**
 * Create the doors for the maze 
 * and fill the mazes room with doors
 * @author katel
 *
 */
public class DoorFactory {
    private Room[][] myRooms;
    
    public DoorFactory() {
        myRooms = new Room[0][0];
    }
    
    public DoorFactory(Room[][] theRooms) {
        myRooms = theRooms;
    }
    
    public Room[][] getRooms(){
        return myRooms;
    }
    
    /*
     * Traverse the rooms to fill them so that 
     * the doors of adjacent rooms coorespond correctly 
     */
    private void fillRooms() {
        
    }
    
    

}
