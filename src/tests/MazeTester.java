package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Direction;
import model.Door;
import model.DoorFactory;
import model.Maze;
import model.Room;

class MazeTester {
//    //Maze myMaze;
//    private final int SIZE = 4;
//    Room[][] myRooms;
//    DoorFactory myFactory;
//    
//    
//    @BeforeEach
//    void setUp() throws Exception {
//        //myMaze = Maze.getInstance();
//        myRooms = new Room[SIZE][SIZE];
//        for(int r = 0; r < SIZE; r++) {
//            for(int c = 0; c < SIZE; c++) {
//                myRooms[r][c] = new Room();
//            }
//        }
//        myFactory = new DoorFactory(myRooms);
//        myRooms = myFactory.getRooms();
//    }
//    
    // Tests canWin method at the start of the game
    @Test
    void testcanWinNoMoves() {
    	Maze myMaze = Maze.getInstance();
    	assertTrue(myMaze.canWin());
    }
    
    // Tests canMove method for one move north
    @Test
    void testcanMoveNorth() {
    	Maze myMaze = Maze.getInstance();
    	Room room = myMaze.getMyCurrentRoom();
    	int row = room.getMyIndex().getRow();
    	int col = room.getMyIndex().getCol();    	
    	Room northRoom = myMaze.getRoom(row - 1, col);
    	Door northDoor = myMaze.getSameDoor(room, northRoom);
    	northDoor.setUnlocked();
    	
    	assertTrue(myMaze.canMove(Direction.NORTH, room));
    }
    
    // Tests canMove method for one move south
    @Test
    void testcanMoveSouth() {
    	Maze myMaze = Maze.getInstance();
    	Room room = myMaze.getMyCurrentRoom();
    	int row = room.getMyIndex().getRow();
    	int col = room.getMyIndex().getCol();    	
    	Room southRoom = myMaze.getRoom(row + 1, col);
    	Door southDoor = myMaze.getSameDoor(room, southRoom);
    	southDoor.setUnlocked();
    	
    	assertTrue(myMaze.canMove(Direction.SOUTH, room));
    }
    
    // Tests canMove method for one move west
    @Test
    void testcanMoveWest() {
    	Maze myMaze = Maze.getInstance();
    	Room room = myMaze.getMyCurrentRoom();
    	int row = room.getMyIndex().getRow();
    	int col = room.getMyIndex().getCol();    	
    	Room westRoom = myMaze.getRoom(row, col - 1);
    	Door westDoor = myMaze.getSameDoor(room, westRoom);
    	westDoor.setUnlocked();
    	
    	assertTrue(myMaze.canMove(Direction.WEST, room));
    }
    
    // Tests canMove method for one move west
    @Test
    void testcanMoveEast() {
    	Maze myMaze = Maze.getInstance();
    	Room room = myMaze.getMyCurrentRoom();
    	int row = room.getMyIndex().getRow();
    	int col = room.getMyIndex().getCol();    	
    	Room eastRoom = myMaze.getRoom(row, col + 1);
    	Door eastDoor = myMaze.getSameDoor(room, eastRoom);
    	eastDoor.setUnlocked();
    	
    	assertTrue(myMaze.canMove(Direction.EAST, room));
    }
    
//    @Test
//    void test() {
//        fail("Not yet implemented");
//    }

}
