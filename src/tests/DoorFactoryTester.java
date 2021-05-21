package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.DoorFactory;
import model.Maze;
import model.Room;

class DoorFactoryTester {
    //Maze myMaze;
    private final int SIZE = 4;
    Room[][] myRooms;
    DoorFactory myFactory;
    
    
    @BeforeEach
    void setUp() throws Exception {
        //myMaze = Maze.getInstance();
        myRooms = new Room[SIZE][SIZE];
        for(int r = 0; r < SIZE; r++) {
            for(int c = 0; c < SIZE; c++) {
                myRooms[r][c] = new Room();
            }
        }
        myFactory = new DoorFactory(myRooms);
    }

   // @Test(expected = NullPointerException.class) //not recognizing this 
//    void testNull() {
//        assertNull(new DoorFactory(null));
//        assertNull(new DoorFactory(new Room[4][4]));
//    }
    
    void testBoarder() {
        assertTrue(myRoom);
    }

}
