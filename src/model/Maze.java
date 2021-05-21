package model;

import java.awt.Dimension;
import java.util.Random;
import view.GameIcon;

/**
 * The maze has all the rooms in a 2D array with a buffer of size 1 around the border.
 * Maze uses a singleton pattern.
 * @author Ken Smith, Heather Finch, Katelynn Oleson 
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
	
	// The icon for a plain room
	private final GameIcon myPlainRoomIcon = new GameIcon("src/icons/room_for_map.png"); 
	
	// The icon for start room
	private final GameIcon myStartRoomIcon = new GameIcon("src/icons/start_room_for_map.png");
	
	// The icon for the win room
	private final GameIcon myWinRoomIcon = new GameIcon("src/icons/win_room_for_map.png");
	
	// The icon for the current room
	private final GameIcon myCurrRoomIcon = new GameIcon("src/icons/current_room_for_map.png");
	
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
		
		DoorFactory inFactory = new DoorFactory(myMaze); // fill rooms with doors
        myMaze = inFactory.getRooms();
        
        blockBorderRooms(); // create "border wall" surounding map
		
		// this is set to true initially
		canAccessWinRoom = true;
	}
	
	/**
	 * Getter for the maze object instance
	 * @return
	 */
	public static Maze getInstance() {
		return THISMAZE;
    }
	

	/*
	 * Creates and adds rooms to myMaze 
	 */
	private void addRooms() {
		for(int r = 0; r < LENGTH+BORDER_BUFFER; r++) {
			for(int c = 0; c < WIDTH+BORDER_BUFFER; c++) {
				myMaze[r][c] = new Room(myPlainRoomIcon, myPlainRoomIcon, r, c);
			}
		}
		designateWinStartRooms();
		myCurrentRoom = myStartRoom;
	}

   /*
   * Blocks all doors along the boarder to simulate a wall
   */
   private void blockBorderRooms() {
       for(int r = 0; r < LENGTH+BORDER_BUFFER; r++) { 
           blockDoors(myMaze[r][0]);
           blockDoors(myMaze[r][WIDTH+BORDER_BUFFER - 1]);
       }     
       for(int c = 0; c < WIDTH+BORDER_BUFFER; c++) {
           blockDoors(myMaze[0][c]);
           blockDoors(myMaze[LENGTH+BORDER_BUFFER - 1][c]);
       }
    }
	
   /*
    * Block all doors in a room 
    */
   private void blockDoors(Room theRoom) {
       for(Door d : theRoom.getDoors()) {
           d.setBlocked();
       }
   }
   
	/*
	 * Randomly sets the WinRoom and StartRoom coordinates 
	 * so that they are not on the edge of the maze
	 */
	private void designateWinStartRooms() {
	    int inStartRow = generateRandom(BORDER_BUFFER/2 + 1, LENGTH - 2);
	    int inStartCol = generateRandom(BORDER_BUFFER/2 + 1, WIDTH - 2);
	    
		myStartRoom = this.getRoom(inStartRow, inStartCol);
		myStartRoom.setLargeIcon(myStartRoomIcon);
		myStartRoom.setSmallIcon(myStartRoomIcon);
		
		int inWinRow = 0, inWinCol = 0;
		// win and start room must be 1/3 of the maze away - helper method? 
		while(inWinRow == 0 || Math.abs(inWinRow - inStartRow) < LENGTH / 3) {
		    inWinRow = generateRandom(BORDER_BUFFER/2 + 1, LENGTH - 2);
		    
		}
	    while(inWinCol == 0 || Math.abs(inWinCol - inStartCol) < WIDTH / 3) {
	            inWinCol = generateRandom(BORDER_BUFFER/2 + 1, WIDTH - 2);
	    }
	    
		myWinRoom = this.getRoom(inWinRow, inWinCol);
		myWinRoom.setLargeIcon(myWinRoomIcon);
		myWinRoom.setSmallIcon(myWinRoomIcon);
		
       System.out.println("Win Room: (" + inStartRow + ", " + inStartCol + ").");
       System.out.println("Win Room: (" + inWinRow + ", " + inWinCol + ").");
		
	}

    /*
	 * Generates a random index between two numbers (min val, max val) 
	 */
	 private int generateRandom(int theMin, int theMax) {
	     Random rand = new Random();
	     return rand.nextInt(theMax - theMin + 1) + theMin;
	     // highest val is ((theMax - theMin + 1) - 1) + theMin = theMax
	     // lowest val is (0) + theMin = theMin
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
		
		tempCurrentRoom.setLargeIcon(myPlainRoomIcon); // TODO Icon handling
		tempCurrentRoom.setSmallIcon(myPlainRoomIcon);
		tempCurrentRoom.setPlayer(null);
		
		myCurrentRoom.setLargeIcon(myCurrRoomIcon);
		myCurrentRoom.setSmallIcon(myCurrRoomIcon);
		myCurrentRoom.setPlayer(myPlayer); //TODO use add player instead when we have doors
	}
	
	/**
	 * Getter for the current room the player is in
	 * @return
	 */
	public Room getCurrRoom() {
	    return myCurrentRoom;
	}
	
	/**
	 * Getter for the Player
	 * @return
	 */
	public Player getPlayer() {
	    return myPlayer;
	}

	
	/**
	 * Returns the room at the provided row and column
	 * @param theRow
	 * @param theColumn
	 * @return
	 */
	public Room getRoom(int theRow, int theColumn) {
	 // Logic is so the client does not know about the buffer rooms and cannot access the buffer rooms
		if(theRow < 0 || theColumn < 0) throw new 
				IllegalArgumentException("getRoom error: The index of the rooms cannot be negative");
		else if(theRow >= (LENGTH) || theColumn >= (WIDTH)) 
		    throw new IllegalArgumentException("getRoom error: The index of the rooms cannot be greater than the size of the maze");
		
		return myMaze[theRow + BORDER_BUFFER/2][theColumn + BORDER_BUFFER/2];
	}

	/**
	 * Returns the length of this mazes 2D Room array 
	 * @return
	 */
	public int getLength() {
	    return LENGTH;
	}
	
	/**
     * Returns the length of this mazes 2D Room array 
     * @return
     */
	public int getWidth() {
        return WIDTH;
    }
	

	// Returns true if the index contains a room, and false otherwise
	// TODO containsRoom method is redundant unless we decide to make some spaces in the grid not exist as rooms.
	public boolean containsRoom(int theRow, int theColumn) {
		if(myMaze[theRow][theColumn] != null) return true;
		else return false;
	}
	
	 
	
	/**
	 * Searches for a path from the current room to the win room 
	 * to see if the user can win the game
	 * @return
	 */
	public boolean canWin() {
		boolean [][] visited = new boolean[LENGTH + BORDER_BUFFER][WIDTH + BORDER_BUFFER];
		canAccessWinRoom = false;
        depthFirstSearchMaze(BORDER_BUFFER/2,  BORDER_BUFFER/2, visited);     
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
