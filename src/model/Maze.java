package model;

import java.util.Stack;

/**
 * The maze has all the rooms in a 2D array with a buffer of size 1 around the border.
 * @author Ken Smith, Heather Finch
 * @version 5.14.21
 * Ask Heather if you have any questions about this class
 *
 */
public class Maze {
	
	// The 2D array that stores each room
	private Room[][] myMaze;
	
	private boolean canAccessWinRoom;
	
	// The number of rows in the maze that store rooms
	private final int LENGTH = 6;
	
	// The number of columns in the maze that store rooms
	private final int WIDTH = 6;
	
	// The border around the entire room is 1 space wide on each side.
	// This is 2 to account for the buffer on both sides.
	private final int BORDER_BUFFER = 2;
	
	//TODO Add item tracking for the win item
	
	
	public Maze() {
		
		// Initialize with row-major: Room[rows][columns]
		myMaze = new Room[LENGTH+BORDER_BUFFER][WIDTH+BORDER_BUFFER];
		addRooms();
		
		// Currently sets the win room to be the bottom right corner
		designateWinRoom();
		
		// this is set to true initially
		canAccessWinRoom = true;
		
	}
	
	
	//TODO: room factory? start, win, item rooms etc
	private void addRooms() {
		for(int i = 1; i <= LENGTH; i++) {
			for(int j = 1; j <= WIDTH; j++) {
				
				
				myMaze[i][j] = new Room();	
			}
		}
	}
	
	// Communicate to the controller what the start room for the game is
	// So that the controller can set the icon for the start room
	// Maybe find way to not pass room eventually
	private Room getStartRoom() {
		return myMaze[1][1];
	}
	
	// TODO if we randomize the winroom and/or start room location, we will want to create a method to make sure they are
	// 		not too close to each other. We would likely call the method from the below method, and modify the LENGTH and WIDTH 
	//		passed in to the setWinRoom method
	private void designateWinRoom() {
		//TODO pass in the win item to set the winRoom
		this.getRoom(LENGTH, WIDTH).setWinRoom();
	}
	
	// Returns the room at the current index
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
	public boolean canWin(Room theCurrRoom) {
		boolean [][] visited = new boolean[LENGTH][WIDTH];
		canAccessWinRoom = false;
        depthFirstSearchMaze(0,  0, visited);     
        return canAccessWinRoom;
	}
	
	
	// Helper method for canWin. Uses depth first search to see if the win room is accessible
	private void depthFirstSearchMaze(int theRow, int theColumn, boolean[][] theVisitedRooms) {
	
	    // return if we've hit the end of the maze.
	    if (theRow < 0 || theColumn < 0 || theRow >= LENGTH || theColumn >= WIDTH || theVisitedRooms[theRow][theColumn])
	        return;
	    
	    // return if this room doesn't exist
	    if(!this.containsRoom(theRow, theColumn)){
	    	return;
	    }
	    
	    Room currentRoom = this.getRoom(theRow, theColumn);
	    
	    // return if we've found the win room
	    // TODO, should I use a "break" to exit the depthFirstSearchMaze method once the winRoom has been found?
	    // Otherwise the DFS could recurse further.
	    if(currentRoom.isWinRoom()) {
	    	canAccessWinRoom = true;
	    	return;
	    } else {
	
		    //mark the cell visited
		    theVisitedRooms[theRow][theColumn] = true;
		    
		    // if the right door is unlocked:
		    //if(currentRoom.getDoors()[].isPermaBlocked())
		    depthFirstSearchMaze(theRow+ 1, theColumn, theVisitedRooms); // go right
		    
		    // if the left door is unlocked:
		    depthFirstSearchMaze(theRow- 1, theColumn, theVisitedRooms); //go left
		    
		    // if the bottom door is unlocked:
		    depthFirstSearchMaze(theRow, theColumn + 1, theVisitedRooms); //go down
		    
		    // if the top door is unlocked:
		    depthFirstSearchMaze(theRow, theColumn - 1, theVisitedRooms); // go up
		}	
	}
	

	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("current");
		for(int i = 2; i <= LENGTH; i++) {
			for(int j = 2; j <= WIDTH; j++) {
				Room currRoom = getRoom(i, j);
				if (currRoom.isWinRoom()) {
					sb.append("  win   ");
				} else sb.append("   x    ");
			}
			sb.append("\n");
		}
		return sb.toString();
	}
}
