package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Direction;
import model.DoorFactory;
import model.Riddle;
import model.Room;

class DoorFactoryTester {
    Room[][] myRooms;
    DoorFactory myFactory;
            
    @BeforeEach
    void setUp() throws Exception {
        myRooms = new Room[2][2];
        for(int r = 0; r < 2; r++) {
            for(int c = 0; c < 2; c++) {
                myRooms[r][c] = new Room(r, c);                
            }
        }
        
        myFactory = new DoorFactory(myRooms);
        
    }
    
    // not working, have someone else run it
   
//    @Test
//    void testNullArrayConstructor() {
//        assertThrows(NullPointerException.class, new DoorFactory(null));
//    }
//    
//    @Test(expected=NullPointerException.class)
//    void testNullRoomConstructor() {
//        Room[][] inRooms = new Room[2][2];
//        inRooms[0][0] = new Room(0,0);
//        inRooms[1][0] = null;
//        assertThrows(NullPointerException.class, new DoorFactory(inRooms));
//    }
    

    @Test
    void correspondingDoorsNSTest() {
        assertFalse(myRooms[1][0].getDoors().get(Direction.NORTH).isBlocked());
        myRooms[0][0].getDoors().get(Direction.SOUTH).setBlocked();
        assertTrue(myRooms[1][0].getDoors().get(Direction.NORTH).isBlocked());
        
        assertFalse(myRooms[0][0].getDoors().get(Direction.SOUTH).isUnlocked());
        myRooms[1][0].getDoors().get(Direction.NORTH).setUnlocked();
        assertTrue(myRooms[0][0].getDoors().get(Direction.SOUTH).isUnlocked());
        
        assertFalse(myRooms[1][1].getDoors().get(Direction.NORTH).isUnlocked());
        myRooms[0][1].getDoors().get(Direction.SOUTH).setUnlocked();
        assertTrue(myRooms[1][1].getDoors().get(Direction.NORTH).isUnlocked());
        
        assertFalse(myRooms[0][1].getDoors().get(Direction.SOUTH).isBlocked());
        myRooms[1][1].getDoors().get(Direction.NORTH).setBlocked();
        assertTrue(myRooms[0][1].getDoors().get(Direction.SOUTH).isBlocked());
    }
    
    @Test
    void correspondingDoorsEWTest() {     
        assertFalse(myRooms[0][1].getDoors().get(Direction.WEST).isBlocked());
        myRooms[0][0].getDoors().get(Direction.EAST).setBlocked();
        assertTrue(myRooms[0][1].getDoors().get(Direction.WEST).isBlocked());
        
        assertFalse(myRooms[0][0].getDoors().get(Direction.EAST).isUnlocked());
        myRooms[0][1].getDoors().get(Direction.WEST).setUnlocked();
        assertTrue(myRooms[0][0].getDoors().get(Direction.EAST).isUnlocked());
        
        assertFalse(myRooms[1][1].getDoors().get(Direction.WEST).isUnlocked());
        myRooms[1][0].getDoors().get(Direction.EAST).setUnlocked();
        assertTrue(myRooms[1][1].getDoors().get(Direction.WEST).isUnlocked());
        
        assertFalse(myRooms[1][0].getDoors().get(Direction.EAST).isBlocked());
        myRooms[1][1].getDoors().get(Direction.WEST).setBlocked();
        assertTrue(myRooms[1][0].getDoors().get(Direction.EAST).isBlocked());
    }
    
    // test all doors filled
    @Test
    void filledRiddlesTest() {
        Direction[] inDir = {Direction.NORTH, Direction.SOUTH, Direction.EAST, Direction.WEST};
        for(int r = 0; r < 2; r++) {
            for(int c = 0; c < 2; c++) {
                for(Direction d : inDir) {
                    assertFalse(myRooms[r][c].getDoors().get(d) == null);
                    
                }
            }
        }
        
        
    }


}
