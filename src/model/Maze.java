package model;

import java.awt.Dimension;
import java.util.Random;
import java.util.Stack;

import view.GameIcon;

/**
 * The maze has all the rooms in a 2D array with a buffer of size 1 around the border.
 * Maze uses a singleton pattern.
 * @author Ken Smith, Heather Finch
 * @version 5.14.21
 * Ask Heather if you have any questions about this class
 *
 */
public class Maze {
	
	// The 2D array that stores each room
	private Room[][] myMaze;
	
	// The player
	private final Player myPlayer;
	
	// The room our Player is currently in
	private Room myCurrentRoom;
	
	// The room the player starts in
	private Room myStartRoom;
	
	// The room the player must get to so that they can win
	private Room myWinRoom;
	
	// True if the Player can still access the winroom via unlocked or locked doors
	private boolean canAccessWinRoom;
	
	// The number of rows in the maze that store rooms
	private final int LENGTH = 7;
	
	// The number of columns in the maze that store rooms
	private final int WIDTH = 7;
	
	// The border around the entire room is 1 space wide on each side.
	// This is 2 to account for the buffer on both sides.
	private final int BORDER_BUFFER = 2;
	
	// Creates the maze.
    private static final Maze THISMAZE = new Maze();

	
    // Constructor is private due to singleton pattern.
	private Maze() {
		
		// Initialize with row-major: Room[rows][columns]
		myMaze = new Room[LENGTH+BORDER_BUFFER][WIDTH+BORDER_BUFFER];
		
		// TODO Allow option for skin types
		myPlayer = new Player();
		
		// Fills out the 2d array, myMaze, with rooms
		addRooms();
		
		// this is set to true initially
		canAccessWinRoom = true;
	}
	
	// Returns the maze.
	public static Maze getInstance() {
		return THISMAZE;
    }
	

	// Creates and adds rooms to myMaze.
	private void addRooms() {
		
		// iterate through the 2d array
		for(int row = 1; row <= LENGTH; row++) {
			for(int col = 1; col <= WIDTH; col++) {
				myMaze[row][col] = new Room(row, col);
			}
		}
		//DoorFactory theFactory = new DoorFactory(myMaze);
		
		designateWinStartRooms();
		myCurrentRoom = myStartRoom;
		
	}
	
	
	// Randomly sets the WinRoom and StartRoom.
	private void designateWinStartRooms() {
		
		// Finds start room indexes
		int startCol = 1;
		startCol = generateRandomStartIndex(startCol, LENGTH);
		int startRow = 1;
		startRow = generateRandomStartIndex(startRow, WIDTH);
		System.out.println("Start Room. Row: " + startRow + ", Column: " + startCol);
		
		// Sets start room
		myStartRoom = myMaze[startRow][startCol];
		myStartRoom.setCurrentRoom(true);
		myStartRoom.setPlayer(myPlayer);
		
		// Finds win room indexes
		int winRow = 1;
		winRow = generateRandomWinIndex(winRow, LENGTH, startRow);
				int winCol = 1;
		winCol = generateRandomWinIndex(winCol, WIDTH, startCol);
		
		System.out.println("Win Room. Row: " + winRow + ", Column: " + winCol);
		
		// Sets win room
		myWinRoom = myMaze[winRow][winCol];
		//myWinRoom.setLargeIcon(myWinRoomIcon);
		myWinRoom.setWinRoom(true);
		
	}

	// Finds a random index in the 2d grid for the start room. TheDiameter represents either the LENGTH or WIDTH.
	private int generateRandomStartIndex(int theIndex, int theDiameter) {
		Random rand = new Random();

		// loops until theIndex is not on the edge of the maze.
		while(theIndex == 1 || theIndex == theDiameter) {
			// Uses random number generator to pick a spot for the start room. 
			// theDiameter - 1 indicates the upper bound of the number generated.
			// The +1 is to ensure that we do not go out of bounds on the lower bounds -> .nextInt has a lower bound of 0.
			theIndex = rand.nextInt(theDiameter - 1) + 1;
		}	
		return theIndex;
	}
	
	// Finds a random index in teh 2d grid for the win room.
	// TheDiameter represents either LENGTH or WIDTH
	private int generateRandomWinIndex(int theIndex, int theDiameter, int theStartIndex) {
		Random rand = new Random();
		
		// Loops until the win room is not on the border
		// And is at least 1/3 the length of the maze away from the start room (this is why it's "/ 3"
		while(theIndex == 1 || theIndex == theDiameter || Math.abs(theIndex - theStartIndex) < theDiameter / 3) {
			theIndex = rand.nextInt(LENGTH - 1) + 1;
		}
		return theIndex;
	}

	// TODO Right now move in Maze uses Direction - we may want to make a custom direction class
	// TODO add exception handeling to move method
	public void move(Direction theDirection) {
		Room tempCurrentRoom = myCurrentRoom;
		RoomIndex currIndex = myCurrentRoom.getMyIndex();
		int row = currIndex.getRow();
		int col = currIndex.getCol();

		if(theDirection.getLabel().equals("N") && row >= 2) {	// Go North
			myCurrentRoom = myMaze[row-1][col];

		} else if(theDirection.getLabel().equals("S") && row < LENGTH) {	// Go South
			myCurrentRoom = myMaze[row+1][col];

		} else if(theDirection.getLabel().equals("E") && col < WIDTH) {	// Go East
			myCurrentRoom = myMaze[row][col+1];

		} else if(theDirection.getLabel().equals("W") && col >= 2) {	// Go West
			myCurrentRoom = myMaze[row][col-1];
			
		} else throw new IllegalArgumentException("You cannot move past the border of the maze");
		
		// TODO Icon handling
		tempCurrentRoom.setCurrentRoom(false);
		tempCurrentRoom.setPlayer(null);
		
		myCurrentRoom.setCurrentRoom(true);
		myCurrentRoom.setPlayer(myPlayer); //TODO use add player instead when we have doors
	}
		
	// Returns the start room
	private Room getStartRoom() {
		return myStartRoom;
	}
	
	public Room getCurrRoom() {
		return myCurrentRoom;
	}
	
	public Player getPlayer() {
		return myPlayer;
	}
	
	// Returns the room at the provided index
	public Room getRoom(int theRow, int theColumn) {
		//TODO clean up magic numbers in getRoom
		if(theRow < 1 || theColumn < 1) throw new 
				IllegalArgumentException("getRoom error: The index of the rooms cannot be less than (1,1)");
		else if(theRow > LENGTH || theColumn > WIDTH) throw new 
				IllegalArgumentException("getRoom error: The index of the rooms cannot be greater than the size of the maze");
		
		return myMaze[theRow][theColumn];
	}
	
	// Returns the 2d array of all rooms.
	// This is public right now so that the MapPanel can access the rooms. Maybe there is a way to change this?
	public Room[][] getRooms(){
		return myMaze;
	}
	
	// Returns true if the index contains a room, and false otherwise
	// TODO containsRoom method is redundant unless we decide to make some spaces in the grid not exist as rooms.
	public boolean containsRoom(int theRow, int theColumn) {
		if(myMaze[theRow][theColumn] != null) return true;
		else return false;
	}
	
	// Searches from the current room to the win room to see if the user can still win the game
	// Uses depth first traversal 
	public boolean canWin() {
		boolean [][] visited = new boolean[LENGTH + 2][WIDTH + 2];
		canAccessWinRoom = false;
        depthFirstSearchMaze(1,  1, visited);     
        return canAccessWinRoom;
	}
	
	
	// Helper method for canWin. Uses depth first search to see if the win room is accessible
	private void depthFirstSearchMaze(int theRow, int theColumn, boolean[][] theVisitedRooms) {
		System.out.println("the row: " + theRow + " the column: " + theColumn);
		
		
	    // return if we've hit the end of the maze.
	    if (theRow < 0 || theColumn < 0 || theRow > LENGTH || theColumn > WIDTH || theVisitedRooms[theRow][theColumn]) {
	    	return;
	    }
	    
	    // return if this room doesn't exist
	    if(!this.containsRoom(theRow, theColumn)){
	    	return;
	    }

	    
	    Room currentRoom = this.getRoom(theRow, theColumn);
	    
	    // return if we've found the win room
	    // TODO, should I use a "break" to exit the depthFirstSearchMaze method once the winRoom has been found?
	    // Otherwise the DFS could recurse further.
	    if(currentRoom == myWinRoom) {
	    	canAccessWinRoom = true;
	    	return;
	    } else {
	
		    //mark the cell visited
		    theVisitedRooms[theRow][theColumn] = true;
		    
		    // if the right door is unlocked:
		    if(!isEastDoorBlocked(theRow, theColumn)) depthFirstSearchMaze(theRow+ 1, theColumn, theVisitedRooms); // go right
		    		    
		    // if the left door is unlocked:
		    if(!isWestDoorBlocked(theRow, theColumn)) depthFirstSearchMaze(theRow - 1, theColumn, theVisitedRooms); //go left
		    
		    // if the bottom door is unlocked:
		    if(!isSouthDoorBlocked(theRow, theColumn))	depthFirstSearchMaze(theRow, theColumn + 1, theVisitedRooms); //go down
		    
		    // if the top door is unlocked:
		    if(!isNorthDoorBlocked(theRow, theColumn)) depthFirstSearchMaze(theRow, theColumn - 1, theVisitedRooms); // go up
		}	
	}
	

	
	private boolean isEastDoorBlocked(int theRow, int theColumn) {
		Door inDoor = getSameDoor(myMaze[theRow][theColumn], myMaze[theRow][theColumn+1]);
		return inDoor.isBlocked();
	}
	
	private boolean isWestDoorBlocked(int theRow, int theColumn) {
		Door inDoor = getSameDoor(myMaze[theRow][theColumn], myMaze[theRow][theColumn-1]);
		return inDoor.isBlocked();
	}
	
	private boolean isNorthDoorBlocked(int theRow, int theColumn) {
		Door inDoor = getSameDoor(myMaze[theRow][theColumn], myMaze[theRow+1][theColumn]);
		return inDoor.isBlocked();
	}

	private boolean isSouthDoorBlocked(int theRow, int theColumn) {
		Door inDoor = getSameDoor(myMaze[theRow][theColumn], myMaze[theRow-1][theColumn]);
		return inDoor.isBlocked();
	}

	private Door getSameDoor(Room theCurrRoom, Room theAdjRoom) {
		//Door[] inDoors = new Door[theCurrRoom.getDoors().length];
		for(Door d : theCurrRoom.getDoors()) {
			for(Door o : theAdjRoom.getDoors()) {
				if(d == o) {
					return d;
				}
			}
		}
		throw new IllegalArgumentException("There is no shared door between these rooms.");
	}


	public String toString() {
		StringBuilder sb = new StringBuilder();
		for(int row = 1; row <= LENGTH; row++) {
			for(int col = 1; col <= WIDTH; col++) {
				Room currRoom = getRoom(row, col);

				if(getRoom(row, col).equals(myCurrentRoom)) {
					sb.append(" current");
				} else if (currRoom == myWinRoom) {
					sb.append("  win   ");
				} else sb.append("   x    ");
			}
			sb.append("\n");
		}
		return sb.toString();
	}
}
