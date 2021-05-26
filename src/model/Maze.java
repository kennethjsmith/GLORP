package model;

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
		placeItems();
		DoorFactory inFactory = new DoorFactory(myMaze); // fill rooms with doors
        myMaze = inFactory.getRooms();
        
        blockBorderRooms(); // create "border wall" of completely blocked rooms surounding map
		
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
		for(int row = 0; row < LENGTH+BORDER_BUFFER; row++) {
			for(int col = 0; col < WIDTH+BORDER_BUFFER; col++) {
				myMaze[row][col] = new Room(row, col);
			}
		}
		designateWinStartRooms();
		myCurrentRoom = myStartRoom;
	}

   /*
   * Blocks all doors along the border to simulate a wall
   */
   private void blockBorderRooms() {
       for(int row = 0; row < LENGTH+BORDER_BUFFER; row++) { 
           blockDoors(myMaze[row][0]);
           blockDoors(myMaze[row][WIDTH+BORDER_BUFFER - 1]);
       }     
       for(int col = 1; col < WIDTH+BORDER_BUFFER; col++) {
           blockDoors(myMaze[0][col]);
           blockDoors(myMaze[LENGTH+BORDER_BUFFER - 1][col]);
       }
    }
	
   /*
    * Block all doors in a room 
    */
   private void blockDoors(Room theRoom) {
       for(Door d : theRoom.getDoors().values()) {
           d.setBlocked();
       }
   }
   
	/*
	 * Randomly sets the WinRoom and StartRoom coordinates 
	 * so that they are not on the edge of the maze
	 */
	private void designateWinStartRooms() {
		
		// Why is this divided by 2 + 1 and then length/width -2?
	    int inStartRow = generateRandom(BORDER_BUFFER/2 + 1, LENGTH - 2);
	    int inStartCol = generateRandom(BORDER_BUFFER/2 + 1, WIDTH - 2);
	    
		myStartRoom = this.getRoom(inStartRow, inStartCol);
		myStartRoom.setCurrentRoom(true);
		myStartRoom.setPlayer(myPlayer);
		
		int inWinRow = 0, inWinCol = 0;
		// win and start room must be 1/3 of the maze away - helper method? 
		while(inWinRow == 0 || Math.abs(inWinRow - inStartRow) < LENGTH / 3) {
		    inWinRow = generateRandom(BORDER_BUFFER/2 + 1, LENGTH - 2);
		    
		}
	    while(inWinCol == 0 || Math.abs(inWinCol - inStartCol) < WIDTH / 3) {
	            inWinCol = generateRandom(BORDER_BUFFER/2 + 1, WIDTH - 2);
	    }
	    
		myWinRoom = this.getRoom(inWinRow, inWinCol);
		myWinRoom.setWinRoom(true);
		myWinRoom.setLargeIcon(Carpet.getSpecialIcon());
		myWinRoom.setFixture(new Fixture(175, 200));
		System.out.println(myWinRoom.getFixture().getRectangle());
	}

    /*
	 * Generates a random index between two numbers (min val, max val)
	 * make utility?? 
	 */
	 private int generateRandom(int theMin, int theMax) {
	     Random rand = new Random();
	     return rand.nextInt(theMax - theMin + 1) + theMin;
	     // highest val is ((theMax - theMin + 1) - 1) + theMin = theMax
	     // lowest val is (0) + theMin = theMin
	 }
	 
	/**
	 * Check if moving in a certain direction is valid.
	 * Returns true if the move is valid.
	 * @param theDirection
	 * @return boolean canMove
	 */
	public boolean canMove(Direction theDirection, Room theRoom) {
	    RoomIndex currIndex = myCurrentRoom.getIndex();
        int row = currIndex.getRow();
        int col = currIndex.getCol();
        
	    return (theDirection.getLabel().equals("N") && row >= BORDER_BUFFER/2 + 1 && isNorthDoorUnlocked(row, col))||  // Go North
        (theDirection.getLabel().equals("S") && row < LENGTH && isSouthDoorUnlocked(row, col)) ||  // Go South
        (theDirection.getLabel().equals("E") && col < WIDTH && isEastDoorUnlocked(row, col)) ||   // Go East
        (theDirection.getLabel().equals("W") && col >= BORDER_BUFFER/2 + 1 && isWestDoorUnlocked(row, col));    // Go West
	
	}
	
	// TODO Right now move in Maze uses Direction - we may want to make a custom direction class
	// TODO add exception handeling to move method
	public void move(Direction theDirection) {
		Room tempCurrentRoom = myCurrentRoom;
		RoomIndex currIndex = myCurrentRoom.getIndex();
		int inRow = currIndex.getRow();
		int inCol = currIndex.getCol();

		if(!(canMove(theDirection, myCurrentRoom))) {
			throw new IllegalArgumentException("You cannot move past the border of the maze");
	    }
		
		if(theDirection.getLabel().equals("N")) { // Go North
            myCurrentRoom = myMaze[inRow-1][inCol];

        } else if(theDirection.getLabel().equals("S")) {  // Go South
            myCurrentRoom = myMaze[inRow+1][inCol];

        } else if(theDirection.getLabel().equals("E")) {   // Go East
            myCurrentRoom = myMaze[inRow][inCol+1];

        } else if(theDirection.getLabel().equals("W")) {    // Go West
            myCurrentRoom = myMaze[inRow][inCol-1];  
		}
		
		tempCurrentRoom.setCurrentRoom(false);
		tempCurrentRoom.setPlayer(null);
		
		myCurrentRoom.setCurrentRoom(true);
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
		if(theRow < 0 || theColumn < 0) throw new 
				IllegalArgumentException("getRoom error: The index of the rooms cannot be negative");
		else if(theRow > (LENGTH) || theColumn > (WIDTH)) 
		    throw new IllegalArgumentException("getRoom error: The index of the rooms cannot be greater than the size of the maze");
		
		// Using the line below does not return the correct room for testing purposes.
		// Not sure if this method will be needed anywhere else. 
		// Logic is so the client does not know about the buffer rooms and cannot access the buffer rooms
		//return myMaze[theRow + BORDER_BUFFER/2][theColumn + BORDER_BUFFER/2];
	
		return myMaze[theRow][theColumn];
	}
	
	public Room getMyStartRoom() {
		return myStartRoom;
	}

	public void setMyStartRoom(Room theStartRoom) {
		this.myStartRoom = theStartRoom;
	}
	
	public Room getMyCurrentRoom() {
		return myCurrentRoom;
	}

	public void setMyCurrentRoom(Room myCurrentRoom) {
		this.myCurrentRoom = myCurrentRoom;
	}
	
	public Room getMyWinRoom() {
		return myWinRoom;
	}

	public void setMyWinRoom(Room theWinRoom) {
		this.myWinRoom = theWinRoom;
	}
	
	//places an item at a random point in room 2, 2
	public void placeItems() {
		myMaze[2][2].addItem(new Item(), PiecePoint.randomPoint(Room.getSize(), Room.getSize()));
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
	

	// Returns true if the row and column are valid, and false otherwise
	// TODO containsRoom method is redundant unless we decide to make some spaces in the grid not exist as rooms.
	public boolean containsRoom(int theRow, int theColumn) {
	    if(theRow < 0 || theColumn < 0) return false;
	    else if(theRow >= (LENGTH) || theColumn >= (WIDTH)) return false;
		else return true;
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
		
	    // return if we've hit the end of the maze.
	    if (theRow <= 0 || theColumn <= 0 || theRow > LENGTH || theColumn > WIDTH || theVisitedRooms[theRow][theColumn]) {
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
		    if(!isEastDoorBlocked(theRow, theColumn)) depthFirstSearchMaze(theRow, theColumn + 1, theVisitedRooms); // go right
		    		    
		    // if the left door is unlocked:
		    if(!isWestDoorBlocked(theRow, theColumn)) depthFirstSearchMaze(theRow, theColumn - 1, theVisitedRooms); //go left
		    
		    // if the bottom door is unlocked:
		    if(!isSouthDoorBlocked(theRow, theColumn))	depthFirstSearchMaze(theRow + 1, theColumn, theVisitedRooms); //go down
		    
		    // if the top door is unlocked:
		    if(!isNorthDoorBlocked(theRow, theColumn)) depthFirstSearchMaze(theRow - 1, theColumn, theVisitedRooms); // go up
		}	
	}
	

	
	private boolean isEastDoorBlocked(int theRow, int theColumn) {
		Door door = getSameDoor(myMaze[theRow][theColumn], myMaze[theRow][theColumn+1]);
		return door.isBlocked();
	}
	
	private boolean isWestDoorBlocked(int theRow, int theColumn) {
		Door door = getSameDoor(myMaze[theRow][theColumn], myMaze[theRow][theColumn-1]);
		return door.isBlocked();
	}
	
	private boolean isNorthDoorBlocked(int theRow, int theColumn) {
		Door door = getSameDoor(myMaze[theRow][theColumn], myMaze[theRow-1][theColumn]);
		return door.isBlocked();
	}

	private boolean isSouthDoorBlocked(int theRow, int theColumn) {
		Door door = getSameDoor(myMaze[theRow][theColumn], myMaze[theRow+1][theColumn]);
		return door.isBlocked();
	}
	
	private boolean isEastDoorUnlocked(int theRow, int theColumn) {
		Door door = getSameDoor(myMaze[theRow][theColumn], myMaze[theRow][theColumn+1]);
		return door.isUnlocked();
	}
	
	private boolean isWestDoorUnlocked(int theRow, int theColumn) {
		Door door = getSameDoor(myMaze[theRow][theColumn], myMaze[theRow][theColumn-1]);
		return door.isUnlocked();
	}
	
	private boolean isNorthDoorUnlocked(int theRow, int theColumn) {
		Door door = getSameDoor(myMaze[theRow][theColumn], myMaze[theRow-1][theColumn]);
		return door.isUnlocked();
	}

	private boolean isSouthDoorUnlocked(int theRow, int theColumn) {
		Door door = getSameDoor(myMaze[theRow][theColumn], myMaze[theRow+1][theColumn]);
		return door.isUnlocked();
	}

	public Door getSameDoor(Room theCurrRoom, Room theAdjRoom) {
		for(Door d : theCurrRoom.getDoors().values()) {
			for(Door o : theAdjRoom.getDoors().values()) {
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
