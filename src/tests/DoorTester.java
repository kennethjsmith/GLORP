package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Direction;
import model.Door;
import model.Riddle;
import view.GameIcon;

class DoorTester {
    Door myNorthDoor;
    Door mySouthDoor;
    Door myEastDoor;
    Door myWestDoor;
    Riddle myRiddle;
    Door[] myDirectionDoors = new Door[4];

    @BeforeEach
    void setUp() throws Exception {
        myRiddle =  new Riddle();
        myNorthDoor = new Door(Direction.NORTH, myRiddle);
        mySouthDoor = new Door(Direction.SOUTH, myRiddle);
        myEastDoor = new Door(Direction.EAST, myRiddle);
        myWestDoor = new Door(Direction.WEST, myRiddle);
        
        myDirectionDoors[0] = myNorthDoor;
        myDirectionDoors[1] = mySouthDoor;
        myDirectionDoors[2] = myEastDoor;
        myDirectionDoors[3] = myWestDoor;
        
    }

    // Constructor testers
    @Test
    void NorthSouthDoorConstructorTest() {
        Door[] myDoors = {myNorthDoor, mySouthDoor};
        for(Door myDoor : myDoors) {
            assertFalse(myDoor.isBlocked());
            assertFalse(myDoor.isUnlocked());
            assertFalse(myDoor.getMyRiddle().equals(new Riddle())); // doesnt recognize equal riddles by state
            assertTrue(myDoor.getRoomIcon().equals(Door.NS_ROOM_LOCKED_ICON));
            assertTrue(myDoor.getMapIcon().equals(Door.NS_MAP_LOCKED_ICON));
        }
        
        assertEquals(myNorthDoor.getMapIcon(), mySouthDoor.getMapIcon());
        assertEquals(myNorthDoor.getRoomIcon(), mySouthDoor.getRoomIcon());
    }
       
    
    @Test
    void EastWestoorConstructorTest() {
        Door[] myDoors = {myEastDoor, myWestDoor};
        for(Door myDoor : myDoors) {
            assertFalse(myDoor.isBlocked());
            assertFalse(myDoor.isUnlocked());
            assertFalse(myDoor.getMyRiddle().equals(new Riddle())); // doesnt recognize equal riddles by state
            assertTrue(myDoor.getRoomIcon().equals(Door.WE_ROOM_LOCKED_ICON));
            assertTrue(myDoor.getMapIcon().equals(Door.WE_MAP_LOCKED_ICON));
        }
        
        assertEquals(myEastDoor.getMapIcon(), myWestDoor.getMapIcon());
        assertEquals(myEastDoor.getRoomIcon(), myWestDoor.getRoomIcon());
    }
    
    @Test
    void getterTests() {
     for(Door d : myDirectionDoors)   {
         assertEquals(d.getMyRiddle(), myRiddle);
     }
    }
    
    @Test
    void blockedUnlockedTests() {
     Door inDoor = new Door(Direction.NORTH, new Riddle());
     assertTrue(!inDoor.isBlocked());
     assertTrue(!inDoor.isUnlocked());
     inDoor.setBlocked();
     assertTrue(inDoor.isBlocked());
     assertTrue(!inDoor.isUnlocked());
     inDoor.setUnlocked();
     assertTrue(!inDoor.isBlocked());
     assertTrue(inDoor.isUnlocked());
    }
    

}
