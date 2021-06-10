package model;

import java.util.Objects;
import java.util.Random;
import java.io.Serializable;
import java.util.HashMap;

import controller.SerializeGame;

/**
 * The maze has all the rooms in a 2D array with a buffer of size 1 around the border.
 * Maze uses a singleton pattern.
 * @author Ken Smith, Heather Finch, Katelynn Oleson 
 * @version 1.0.
 */
public class Maze implements Serializable{

	// A serialized ID for serialization.
    private static final long serialVersionUID = 7714896058615502865L;
    
    //Data map for serialization.
    private static HashMap<String, Object> myDataMap;

	// The 2D array that stores each room.
	private static Room[][] myMaze;
	
	// The player.
	private static Player myPlayer; 
	
	// The room our Player is currently in.
	private static Room myCurrentRoom;

	// The room the player starts in.
	private static Room myStartRoom;
	
	// The room that contains the key (used to unlock chest in the chest room).
	private static Room myKeyRoom;

	// The room the player must get to so that they can get the Item needed to win.
	private static Room myChestRoom;
	
	// True if we can access the chest room, false otherwise.
	private static boolean myCanAccessChestRoom;
	
	// True if we can access the key room, false otherwise.
	private static boolean myCanAccessKeyRoom;
	
	// The number of rows in the maze that store rooms.
	private final int LENGTH = 7;
	
	// The number of columns in the maze that store rooms.
	private final int WIDTH = 7;
	
	// The border around the entire room is 1 space wide on each side.
	// This is 2 to account for the buffer on both sides.
	private final int BORDER_BUFFER = 2;
	
	// Creates the maze. Maze is static due to singleton pattern.
    private final static Maze THISMAZE = new Maze(); 

    // Constructor - private due to singleton pattern.
    private Maze() {
		
		// Initialize with row-major: Room[rows][columns]
		myMaze = new Room[LENGTH+BORDER_BUFFER][WIDTH+BORDER_BUFFER];
		
		myPlayer = new Player(SkinType.ALIEN);
		addRooms(); // Fills out the 2d array, myMaze, with rooms.
		DoorFactory inFactory = new DoorFactory(myMaze); // Fills rooms with doors.
        myMaze = inFactory.getRooms(); // Grabs rooms, now filled with doors.
        blockBorderRooms(); // Create "border wall" of completely blocked rooms surounding map.
        myCanAccessChestRoom = true; 
        myCanAccessKeyRoom = true;
        setUpNameObjectMap();
	}
	
	/**
	 * Getter for the instance of the Maze.
	 * @return THISMAZE an instance of the maze
	 */
	public static Maze getInstance() {
		return THISMAZE;
    }
	
	// Creates and adds rooms to every index of the 2d array.
	private void addRooms() {
		for(int row = 0; row < LENGTH+BORDER_BUFFER; row++) {
			for(int col = 0; col < WIDTH+BORDER_BUFFER; col++) {
				myMaze[row][col] = new Room(row, col);
			}
		}
		designateStartKeyChestRooms(); // Randomly sets the location for the start, key, and chest rooms.
		myCurrentRoom = myStartRoom;
	}

	// Blocks all doors along the border to simulate a wall.
	private void blockBorderRooms() {
       for(int row = 0; row < LENGTH+BORDER_BUFFER; row++) { 
           blockDoors(myMaze[row][0]); // Blocks doors in top row of rooms.
           blockDoors(myMaze[row][WIDTH+BORDER_BUFFER - 1]); // Blocks doors in bottom row of rooms.
       }     
       for(int col = 1; col < WIDTH+BORDER_BUFFER; col++) {
           blockDoors(myMaze[0][col]); // Blocks doors in left column of rooms.
           blockDoors(myMaze[LENGTH+BORDER_BUFFER - 1][col]); // Blocks doors in right column of rooms.
       }
    }
	
	// Block all doors in a room.
    private void blockDoors(Room theRoom) {
       for(Door d : theRoom.getDoors().values()) {
           d.setBlocked();
       }
    }
   
	// Randomly sets the locations of the startRoom, keyRoom, and chestRoom.
    // Neither room can be on the edge of the maze.
    // The rooms must be 1/3 of the length of the maze away from each other.
	// Adds chest and key to their respective rooms.
	private void designateStartKeyChestRooms() {
	    int inStartRow = generateRandom(BORDER_BUFFER, LENGTH - 2);
	    int inStartCol = generateRandom(BORDER_BUFFER, WIDTH - 2);
	    
		myStartRoom = this.getRoom(inStartRow, inStartCol);
		myStartRoom.designateStartRoom();
		
		int inChestRow = 0, inChestCol = 0;
		// Chest room must be 1/3 of the maze away from the start room. 
		while(inChestRow == 0 || Math.abs(inChestRow - inStartRow) < LENGTH / 3) {
		    inChestRow = generateRandom(BORDER_BUFFER, LENGTH - 2);
		    
		}
	    while(inChestCol == 0 || Math.abs(inChestCol - inStartCol) < WIDTH / 3) {
	            inChestCol = generateRandom(BORDER_BUFFER, WIDTH - 2);
	    }
	    
		myChestRoom = this.getRoom(inChestRow, inChestCol);
		myChestRoom.designateChestRoom(true);
		myChestRoom.setFixture(new Fixture(175, 200, FixtureType.CHEST)); // Add chest to chestRoom.
		placeItems(inStartRow, inStartCol, inChestRow, inChestCol); // Find and add key to keyRoom.
	}
	
	// A helper method. Places a key item in a room. They room cannot be the same as the startRoom or chestRoom.
	private void placeItems(int theStartRow, int theStartCol, int theChestRow, int theChestCol) {
		PiecePoint randomCoordinates = PiecePoint.randomPoint(Room.getSize()-Item.getMaxSize(), Room.getSize()-Item.getMaxSize());
		int keyRow = theStartRow;
		int keyCol = theStartCol;
		while(keyRow == theStartRow || keyRow == theChestRow) {
			keyRow = generateRandom(BORDER_BUFFER, LENGTH - 2);
		}
		while(keyCol == theStartCol || keyCol == theChestCol) {
			keyCol = generateRandom(BORDER_BUFFER, LENGTH - 2);
		}
		myMaze[keyRow][keyCol].addItem(new Item(randomCoordinates, ItemType.KEY));
		myKeyRoom = myMaze[keyRow][keyCol];
		myKeyRoom.setSmallIcon(Room.getKeyMapIcon());

	}

	// Generates a random index between two numbers (min val, max val).
	private int generateRandom(int theMin, int theMax) {
		Random rand = new Random();
		return rand.nextInt(theMax - theMin + 1) + theMin;
	}
     
	/**
	 * Check if moving in a certain direction is valid.
	 * Returns true if the user is not trying to move past the border of the maze AND if the door is not blocked.
	 * Does not check if the door is locked or unlocked.
	 * @param theDirection the direction the player wants to move in
	 * @return boolean canMove "yes" if the player can move in the direction provided, "no" otherwise
	 */
	public boolean isValidTraversal(Direction theDirection, Room theRoom) {
		Objects.requireNonNull(theDirection);
		Objects.requireNonNull(theRoom);
		
	    RoomIndex currIndex = myCurrentRoom.getIndex();
        int row = currIndex.getRow();
        int col = currIndex.getCol();
        
	    return (theDirection.getLabel().equals("N") && row >= BORDER_BUFFER/2 + 1 && !theRoom.getDoors().get(Direction.NORTH).isBlocked())||  // Go North
        (theDirection.getLabel().equals("S") && row < LENGTH && !theRoom.getDoors().get(Direction.SOUTH).isBlocked()) ||  // Go South
        (theDirection.getLabel().equals("E") && col < WIDTH && !theRoom.getDoors().get(Direction.EAST).isBlocked()) ||   // Go East
        (theDirection.getLabel().equals("W") && col >= BORDER_BUFFER/2 + 1 && !theRoom.getDoors().get(Direction.WEST).isBlocked());    // Go West
	
	}
	
	/**
	 * Move in a direction through the maze.
	 * @param theDirection the direction the player will move in
	 */
	public void traverseMaze(Direction theDirection) {
		Objects.requireNonNull(theDirection);
		
		// tempCurrentRoom is needed so we can reset the currentRoom once we find the new room.
		Room tempCurrentRoom = myCurrentRoom; 
		RoomIndex currIndex = myCurrentRoom.getIndex();
		int inRow = currIndex.getRow();
		int inCol = currIndex.getCol();

		// Checks if the move is valid and the door is unlocked
		if(!(isValidTraversal(theDirection, myCurrentRoom) || !myCurrentRoom.getDoors().get(theDirection).isUnlocked())) {
			throw new IllegalArgumentException("You cannot move that way");
	    }
		
		if(theDirection.getLabel().equals("N")) myCurrentRoom = myMaze[inRow-1][inCol]; // Go North.
        else if(theDirection.getLabel().equals("S")) myCurrentRoom = myMaze[inRow+1][inCol]; // Go South.
        else if(theDirection.getLabel().equals("E")) myCurrentRoom = myMaze[inRow][inCol+1]; // Go East.
        else if(theDirection.getLabel().equals("W")) myCurrentRoom = myMaze[inRow][inCol-1]; // Go West.
		
		tempCurrentRoom.setCurrentRoom(false);
		tempCurrentRoom.setPlayer(null);
		
		myCurrentRoom.setCurrentRoom(true);
		myCurrentRoom.setPlayer(myPlayer); 
	}
	
	/**
	 * Getter for the current room the player is in.
	 * @return the current room
	 */
	public Room getCurrRoom() {
	    return myCurrentRoom;
	}
	
	/**
	 * Getter for the Player
	 * @return the player
	 */
	public Player getPlayer() {
	    return myPlayer;
	}
	
	/**
	 * Finds and returns the room at the provided row and column.
	 * @param the row of the room
	 * @param the column of the room
	 * @return Room the room at the provided row and column
	 */
	public Room getRoom(int theRow, int theColumn) {
		if(theRow < 0 || theColumn < 0) throw new 
				IllegalArgumentException("getRoom error: The index of the rooms cannot be negative");
		else if(theRow > (LENGTH) || theColumn > (WIDTH)) 
		    throw new IllegalArgumentException("getRoom error: The index of the rooms cannot be greater than the size of the maze");
		return myMaze[theRow][theColumn];
	}
	
	/**
	 * Returns the room the player started at.
	 * @return Room the room the player started at
	 */
	public Room getMyStartRoom() {
		return myStartRoom;
	}
	
	/**
	 * Gets the room the player is currently in.
	 * @return Room the room the player is in
	 */
	public Room getMyCurrentRoom() {
		return myCurrentRoom;
	}
	
	/**
	 * Getter for the room that contain the chest.
	 * @return Room the room that contains the chest
	 */
	public Room getMyChestRoom() {
		return myChestRoom;
	}
	
	/**
	 * Returns the length of this mazes 2D Room array.
	 * @return the length of the maze, an int
	 */
	public int getLength() {
	    return LENGTH;
	}
	
	/**
     * Returns the length of this mazes 2D Room array 
     * @return the width of the maze, an int
     */
	public int getWidth() {
        return WIDTH;
    }
	 
	/**
	 * Searches for a path from the current room to the key room to the chest room.
	 * If the player already has the key in inventory, then it only searches for a path to the chest room.
	 * @return true if the player still has a path to victory, false otherwise
	 */
	public boolean canWin() {
		// Used for DFS, indicates if a room has been visited yet to see if it contains the relevant items.
		boolean [][] visited = new boolean[LENGTH + BORDER_BUFFER][WIDTH + BORDER_BUFFER];
		
		// Check if key has been picked up.
		if(myPlayer.getInventory().size() == 0) {
			myCanAccessKeyRoom = false;
			// Check if we can access the room with the key.
	        depthFirstSearchMaze(myCurrentRoom.getIndex().getRow(),  myCurrentRoom.getIndex().getCol(), visited, "key", myKeyRoom);  	        
	        if(myCanAccessKeyRoom == false) return false;
		}
		
		// Check if we can access the win room.
		visited = new boolean[LENGTH + BORDER_BUFFER][WIDTH + BORDER_BUFFER];
		myCanAccessChestRoom = false;
        depthFirstSearchMaze(myCurrentRoom.getIndex().getRow(),  myCurrentRoom.getIndex().getCol(), visited, "win", myChestRoom);     
        return myCanAccessChestRoom;
	}
	
	
	// Helper method for canWin. Uses depth first search to see if the relevant room is accessible.
	private void depthFirstSearchMaze(int theRow, int theColumn, boolean[][] theVisitedRooms, String theRoom, Room theGoalRoom) {				
		if(theGoalRoom.equals(myKeyRoom) && myCanAccessKeyRoom == true) return;
		else if(theGoalRoom.equals(myChestRoom) && myCanAccessChestRoom == true) return;		
		
		// Return if we've hit the end of the maze.
	    if (theRow <= 0 || theColumn <= 0 || theRow > LENGTH + BORDER_BUFFER - 1 || theColumn > WIDTH + BORDER_BUFFER - 1 || theVisitedRooms[theRow][theColumn]) {
	    	return;
	    }
	    
	    Room currentRoom = myMaze[theRow][theColumn];
	    
	    // Return if we've found the chest room
	    if(currentRoom == theGoalRoom) {
	    	if(theGoalRoom.equals(myKeyRoom)) myCanAccessKeyRoom = true;
	    	else myCanAccessChestRoom = true;
	    	return;
	    } else {
	    	
		    // Mark the cell visited.
		    theVisitedRooms[theRow][theColumn] = true;
		    
		    // If the right door is not blocked traverse to the right.
		    if(!currentRoom.getDoors().get(Direction.EAST).isBlocked()) depthFirstSearchMaze(theRow, theColumn + 1, theVisitedRooms, theRoom, theGoalRoom); // go right
		    		    
		    // If the left door is not blocked traverse to the left.
		    if(!currentRoom.getDoors().get(Direction.WEST).isBlocked()) depthFirstSearchMaze(theRow, theColumn - 1, theVisitedRooms, theRoom, theGoalRoom); //go left
		    
		    // If the bottom door is not blocked traverse down.
		    if(!currentRoom.getDoors().get(Direction.SOUTH).isBlocked()) depthFirstSearchMaze(theRow + 1, theColumn, theVisitedRooms, theRoom, theGoalRoom); //go down
		    
		    // If the top door is not blocked traverse up.
		    if(!currentRoom.getDoors().get(Direction.NORTH).isBlocked()) depthFirstSearchMaze(theRow - 1, theColumn, theVisitedRooms, theRoom, theGoalRoom); // go up
		}
	}

	// Cheat method unlocks every door in the maze except doors that lead to border rooms
	public void unlockAllDoors() {
		
		// Unlocks all doors in the middle group of rooms.
		for(int i = 2; i < LENGTH; i++) {
			for(int j = 2; j < WIDTH; j++) {
				Room currRoom = myMaze[i][j];
				currRoom.setPlayer(myPlayer);
				for(Door currDoor : currRoom.getDoors().values()) {
					currDoor.setUnlocked();
				}
			}
		}
		
		// Unlocks north/south doors in the first and last col (not incl border rooms).  
		for(int i = 2; i < LENGTH; i++) {
			Room firstCol = myMaze[i][1];
			Room lastCol = myMaze[i][WIDTH];
			firstCol.setPlayer(myPlayer);
			lastCol.setPlayer(myPlayer);
			
			firstCol.getDoors().get(Direction.NORTH).setUnlocked();
			
			lastCol.getDoors().get(Direction.NORTH).setUnlocked();

			firstCol.getDoors().get(Direction.SOUTH).setUnlocked();

			lastCol.getDoors().get(Direction.SOUTH).setUnlocked();
		}
		
		// Unlocks east/west doors in the first and last row (not incl border rooms).  
		for(int i = 2; i < WIDTH; i++) {
			Room firstRow = myMaze[1][i];
			Room lastRow= myMaze[LENGTH][i];
			firstRow.setPlayer(myPlayer);
			lastRow.setPlayer(myPlayer);
			
			firstRow.getDoors().get(Direction.EAST).setUnlocked();
			
			lastRow.getDoors().get(Direction.EAST).setUnlocked();
			
			firstRow.getDoors().get(Direction.WEST).setUnlocked();

			lastRow.getDoors().get(Direction.WEST).setUnlocked();
		}
		getRoom(1, 1).setPlayer(myPlayer);
		getRoom(1, WIDTH).setPlayer(myPlayer);
		getRoom(LENGTH, 1).setPlayer(myPlayer);
		getRoom(LENGTH, WIDTH).setPlayer(myPlayer);
	}
	
	// Set up the object map for serializaiton.
	private void setUpNameObjectMap() {
	    myDataMap = new HashMap<String, Object>();
	    
	    myDataMap.put("Maze", myMaze);
	    myDataMap.put("Player", myPlayer);    
	    myDataMap.put("StartRoom", myStartRoom);
	    myDataMap.put("WinRoom", myChestRoom);
	    myDataMap.put("KeyRoom", myKeyRoom);
	    myDataMap.put("CurrentRoom", myCurrentRoom);
	    myDataMap.put("CanWin", myCanAccessChestRoom);
	    myDataMap.put("CanKey", myCanAccessKeyRoom);  
	}
	
	/**
	 * Serialize the objects in the object map.
	 */
	public static void serializeMyObjects() {
	    if(myDataMap != null) {
    	    for(String fileName : myDataMap.keySet()) {
    	        SerializeGame.serializeMe(myDataMap.get(fileName), fileName);
    	    }
	    }else
	        System.out.println("Maze Data map has not been initialized yet!");
	}
	
	// Deserialize the objects in the object map.
	public static void deserializeMyObjects() {
	    if(myDataMap != null) {
                myMaze = (Room[][]) SerializeGame.deserializeMe(myDataMap.get("Maze"), "Maze");
                myPlayer = (Player) SerializeGame.deserializeMe(myDataMap.get("Player"), "Player");    
                myStartRoom = (Room) SerializeGame.deserializeMe(myDataMap.get("StartRoom"), "StartRoom");
                myChestRoom = (Room) SerializeGame.deserializeMe(myDataMap.get("WinRoom"), "WinRoom");
                myKeyRoom = (Room) SerializeGame.deserializeMe(myDataMap.get("KeyRoom"),"KeyRoom");
                myCurrentRoom = (Room) SerializeGame.deserializeMe(myDataMap.get("CurrentRoom"), "CurrentRoom");
                myCanAccessChestRoom = (boolean) SerializeGame.deserializeMe(myDataMap.get("CanWin"), "CanWin");
                myCanAccessKeyRoom = (boolean) SerializeGame.deserializeMe(myDataMap.get("CanKey"), "CanKey");               
	    } else System.out.println("Maze Data map has not been initialized yet!");
    }
}
