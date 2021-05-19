package model;

// Creates all of the rooms
// Gives rooms their icons 
// Picks win room and start room

public class MazeFactory {
	// number of columns
	private final int WIDTH;
	
	// number of rows
	private final int LENGTH;
	
	// The border around the entire room is 1 space wide on each side.
	// This is 2 to account for the buffer on both sides.
	private final int BORDER_BUFFER = 2;
	
	private Room[][] myMaze;

	public MazeFactory(Room[][] theMaze) {
		WIDTH = 9;
		LENGTH = 9;
		myMaze = 
		addRooms();
	}
	
	private void addRooms() {
		for(int i = 1; i <= LENGTH; i++) {
			for(int j = 1; j <= WIDTH; j++) {
				
				
				myMaze[i][j] = new Room();	
			}
		}
	}
}
