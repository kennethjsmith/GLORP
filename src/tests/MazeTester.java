package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import model.Direction;
import model.Door;
import model.Maze;
import model.Player;
import model.Room;

class MazeTester {
	
    // Tests canWin method at the start of the game
    @Test
    void testcanWinNoMoves() {
    	Maze myMaze = Maze.getInstance();
    	assertTrue(myMaze.canWin());
    }
    
    // Test canWin with one door in one room blocked
    @Test
    void testcanWinOneDoorBlocked() {
    	Maze myMaze = Maze.getInstance();
    	Room room = myMaze.getMyCurrentRoom();
    	Door northDoor = room.getDoors().get(Direction.NORTH);
    	northDoor.setBlocked();
    	assertTrue(myMaze.canWin());
    }
    
    // Test canWin with two doors in one room blocked
    @Test
    void testcanWinTwoDoorsBlocked() {
    	Maze myMaze = Maze.getInstance();
    	Room room = myMaze.getMyCurrentRoom();
    	Door northDoor = room.getDoors().get(Direction.NORTH);
    	Door southDoor = room.getDoors().get(Direction.SOUTH);
    	northDoor.setBlocked();
    	southDoor.setBlocked();
    	assertTrue(myMaze.canWin());
    }
    
    // Test canWin with three doors in one room blocked
    @Test
    void testcanWinThreeDoorsBlocked() {
    	Maze myMaze = Maze.getInstance();
    	Room room = myMaze.getMyCurrentRoom();
    	Door northDoor = room.getDoors().get(Direction.NORTH);
    	Door southDoor = room.getDoors().get(Direction.SOUTH);
    	Door eastDoor = room.getDoors().get(Direction.EAST);
    	northDoor.setBlocked();
    	southDoor.setBlocked();
    	eastDoor.setBlocked();
    	assertTrue(myMaze.canWin());
    }
    
    // Test canWin with four doors in one room blocked
    @Test
    void testcanWinFourDoorsBlocked() {
    	Maze myMaze = Maze.getInstance();
    	Room room = myMaze.getMyCurrentRoom();
    	for(Door d : room.getDoors().values()) {
    		d.setBlocked();
    	}
    	assertTrue(!myMaze.canWin());
    }
    
    // Tests isValidTraversal method for one move north
    @Test
    void testisValidTraversalNorth() {
    	Maze myMaze = Maze.getInstance();
    	Room room = myMaze.getMyCurrentRoom();
    	Door northDoor = room.getDoors().get(Direction.NORTH);
    	northDoor.setUnlocked();
    	
    	assertTrue(myMaze.isValidTraversal(Direction.NORTH, room));
    }
    
    // Tests isValidTraversal method for one move south
    @Test
    void testisValidTraversalSouth() {
    	Maze myMaze = Maze.getInstance();
    	Room room = myMaze.getMyCurrentRoom();
    	Door southDoor = room.getDoors().get(Direction.SOUTH);
    	southDoor.setUnlocked();
    	
    	assertTrue(myMaze.isValidTraversal(Direction.SOUTH, room));
    }
    
    // Tests isValidTraversal method for one move west
    @Test
    void testisValidTraversalWest() {
    	Maze myMaze = Maze.getInstance();
    	Room room = myMaze.getMyCurrentRoom();
    	Door westDoor = room.getDoors().get(Direction.WEST);
    	westDoor.setUnlocked();
    	
    	assertTrue(myMaze.isValidTraversal(Direction.WEST, room));
    }
    
    // Tests isValidTraversal method for one move west
    @Test
    void testisValidTraversalEast() {
    	Maze myMaze = Maze.getInstance();
    	Room room = myMaze.getMyCurrentRoom();
    	Door eastDoor = room.getDoors().get(Direction.EAST);
    	eastDoor.setUnlocked();
    	
    	assertTrue(myMaze.isValidTraversal(Direction.EAST, room));
    }
    
    // Tests isValidTraversal method for move east through blocked door
    @Test
    void testisValidTraversalEastBlockedDoor() {
    	Maze myMaze = Maze.getInstance();
    	Room room = myMaze.getMyCurrentRoom();
    	Door eastDoor = room.getDoors().get(Direction.EAST);
    	eastDoor.setBlocked();
    	
    	assertTrue(!myMaze.isValidTraversal(Direction.EAST, room));
    }
    
    // Tests isValidTraversal method for move south through blocked door
    @Test
    void testisValidTraversalSouthBlockedDoor() {
    	Maze myMaze = Maze.getInstance();
    	Room room = myMaze.getMyCurrentRoom();
    	Door southDoor = room.getDoors().get(Direction.SOUTH);
    	southDoor.setBlocked();
    	
    	assertTrue(!myMaze.isValidTraversal(Direction.SOUTH, room));
    }
    
    // Tests isValidTraversal method for move north through blocked door
    @Test
    void testisValidTraversalNorthBlockedDoor() {
    	Maze myMaze = Maze.getInstance();
    	Room room = myMaze.getMyCurrentRoom();
    	Door northDoor = room.getDoors().get(Direction.NORTH);
    	northDoor.setBlocked();
    	
    	assertTrue(!myMaze.isValidTraversal(Direction.NORTH, room));
    }
    
    // Tests isValidTraversal method for move west through blocked door
    @Test
    void testisValidTraversalWestBlockedDoor() {
    	Maze myMaze = Maze.getInstance();
    	Room room = myMaze.getMyCurrentRoom();
    	Door westDoor = room.getDoors().get(Direction.WEST);
    	westDoor.setBlocked();
    	
    	assertTrue(!myMaze.isValidTraversal(Direction.WEST, room));
    }
    
    // Tests isValidTraversal method for east move past edge of maze
    @Test
    void testisValidTraversalEastEdgeOfMaze() {
    	Maze myMaze = Maze.getInstance();
    	Room room = myMaze.getRoom(7, 7);
    	Door eastDoor = room.getDoors().get(Direction.EAST);
    	eastDoor.setUnlocked();
    	
    	assertTrue(!myMaze.isValidTraversal(Direction.EAST, room));
    }
    
    // Tests isValidTraversal method for west move past edge of maze
    @Test
    void testisValidTraversalWestEdgeOfMaze() {
    	Maze myMaze = Maze.getInstance();
    	Room room = myMaze.getRoom(1, 1);
    	Door westDoor = room.getDoors().get(Direction.WEST);
    	westDoor.setUnlocked();
    	
    	assertTrue(!myMaze.isValidTraversal(Direction.WEST, room));
    }
    
    // Tests isValidTraversal method for south move past edge of maze
    @Test
    void testisValidTraversalSouthEdgeOfMaze() {
    	Maze myMaze = Maze.getInstance();
    	Room room = myMaze.getRoom(7, 7);
    	Door southDoor = room.getDoors().get(Direction.SOUTH);
    	southDoor.setUnlocked();
    	
    	assertTrue(!myMaze.isValidTraversal(Direction.SOUTH, room));
    }
    
    // Tests isValidTraversal method for north move past edge of maze
    @Test
    void testisValidTraversalNorthEdgeOfMaze() {
    	Maze myMaze = Maze.getInstance();
    	Room room = myMaze.getRoom(1, 1);
    	Door northDoor = room.getDoors().get(Direction.NORTH);
    	northDoor.setUnlocked();
    	
    	assertTrue(!myMaze.isValidTraversal(Direction.NORTH, room));
    }
    
    // Tests traverseMaze method for one move east
    @Test
    void testTraverseMazeValidMoveEast() {
    	Maze myMaze = Maze.getInstance();
    	Room room = myMaze.getCurrRoom();
    	Door eastDoor = room.getDoors().get(Direction.EAST);
    	eastDoor.setUnlocked();
    	Room eastRoom = myMaze.getRoom(room.getIndex().getRow(), room.getIndex().getCol() + 1);
    	myMaze.traverseMaze(Direction.EAST);
    	room = myMaze.getCurrRoom();
    	
    	assertEquals(room, eastRoom);
    }
    
    // Tests traverseMaze method for one move west
    @Test
    void testTraverseMazeValidMovWest() {
    	Maze myMaze = Maze.getInstance();
    	Room room = myMaze.getCurrRoom();
    	Door westDoor = room.getDoors().get(Direction.WEST);
    	westDoor.setUnlocked();
    	Room westRoom = myMaze.getRoom(room.getIndex().getRow(), room.getIndex().getCol() - 1);
    	myMaze.traverseMaze(Direction.WEST);
    	room = myMaze.getCurrRoom();
    	
    	assertEquals(room, westRoom);
    }
    
    // Tests traverseMaze method for one move south
    @Test
    void testTraverseMazeValidMoveSouth() {
    	Maze myMaze = Maze.getInstance();
    	Room room = myMaze.getCurrRoom();
    	Door southDoor = room.getDoors().get(Direction.SOUTH);
    	southDoor.setUnlocked();
    	Room southRoom = myMaze.getRoom(room.getIndex().getRow() + 1, room.getIndex().getCol());
    	myMaze.traverseMaze(Direction.SOUTH);
    	room = myMaze.getCurrRoom();
    	
    	assertEquals(room, southRoom);
    }
    
    // Tests traverseMaze method for one move north
    @Test
    void testTraverseMazeValidMoveNorth() {
    	Maze myMaze = Maze.getInstance();
    	Room room = myMaze.getCurrRoom();
    	Door northDoor = room.getDoors().get(Direction.NORTH);
    	northDoor.setUnlocked();
    	Room northRoom = myMaze.getRoom(room.getIndex().getRow() - 1, room.getIndex().getCol());
    	myMaze.traverseMaze(Direction.NORTH);
    	room = myMaze.getCurrRoom();
    	
    	assertEquals(room, northRoom);
    }
    
    // Tests traverseMaze method for invalid move due to blocked door
    @Test
    void testTraverseMazeInvalidMove() {
    	Maze myMaze = Maze.getInstance();
    	Room room = myMaze.getCurrRoom();
    	Door eastDoor = room.getDoors().get(Direction.EAST);
    	eastDoor.setBlocked();
    	
    	assertThrows(IllegalArgumentException.class, () -> {
    		myMaze.traverseMaze(Direction.EAST);
    	});
    }
    
    // Tests getPlayer 
    @Test
    void testGetPlayer() {
    	Maze myMaze = Maze.getInstance();
    	Player player = myMaze.getPlayer();

    	assertEquals(player, myMaze.getCurrRoom().getPlayer());
    }
    
    
    // Tests getRoom with correct room 
    @Test
    void testGetRoom() {
    	Maze myMaze = Maze.getInstance();
    	Room room = myMaze.getCurrRoom();
    	int row = room.getIndex().getRow();
    	int col = room.getIndex().getCol();

    	assertEquals(room, myMaze.getRoom(row, col));
    }
    
    // Tests getRoom with out of bound negative index 
    @Test
    void testGetRoomOutOfBoundsNegativeIndex() {
    	Maze myMaze = Maze.getInstance();
    	assertThrows(IllegalArgumentException.class, () -> myMaze.getRoom(-1, -1));
    }
    
    // Tests getRoom with out of bound positive index 
    @Test
    void testGetRoomOutOfBoundsIndex() {
    	Maze myMaze = Maze.getInstance();
    	assertThrows(IllegalArgumentException.class, () -> {
    		myMaze.getRoom(myMaze.getLength() + 1, myMaze.getWidth() + 1);
    	});
    }
    
    // Tests getStartRoom 
    @Test
    void testGetStartRoom() {
    	Maze myMaze = Maze.getInstance();
    	Room startRoom = myMaze.getMyStartRoom();
    	assertEquals(startRoom.getFixture().getType().getLabel(), "SHIP");
    }

    // Tests getChestRoom 
    @Test
    void testGetChestRoom() {
    	Maze myMaze = Maze.getInstance();
    	Room chestRoom = myMaze.getMyChestRoom();
    	assertEquals(chestRoom.getFixture().getType().getLabel(), "CHEST");
    }
    
    // Test unlockAllDoors cheat
    // This test looks at the middle group of doors only
    @Test
    void testUnlockAllDoors() {
    	Maze myMaze = Maze.getInstance();
    	myMaze.unlockAllDoors();
    	boolean isLocked = false;
    	for(int row = 2; row < myMaze.getLength(); row++) {
    		for (int col = 2; col < myMaze.getWidth(); col++) {
    			Room room = myMaze.getRoom(row, col);
    			for(Door d : room.getDoors().values()) {
    				if(!d.isUnlocked()) isLocked = false;
    			}
    		}
    	}
    	assertEquals(false, isLocked);
    }
}
