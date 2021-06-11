package model;

import java.io.Serializable;

/**
 * The index of the current room in the 2d array that is the maze.
 * @author Ken Smith, Heather Finch, Katelynn Oleson 
 * @version 1.0.
 */
public class RoomIndex implements Serializable{
     // A serialized ID for serialization.
    private static final long serialVersionUID = 1409522571120625654L;
    private int myRow;
	private int myCol;
		
	/**
	 * Constructs a RoomIndex based on the row and column.
	 * @param theRow The row for this room.
	 * @param theCol The column for this room.
	 */
	RoomIndex(int theRow, int theCol) {
		myRow = theRow;
		myCol = theCol;
	}
		
	/**
	 * A getter for the row of this RoomIndex.
	 * @return The row of this RoomIndex, an int
	 */
	public int getRow() {
		return myRow;
	}
	
	/**
	 * Getter for the column of this RoomIndex.
	 * @return The column of this RoomIndex, an int
	 */
	public int getCol() {
		return myCol;
	}
}
