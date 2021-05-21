package model;

public class RoomIndex {
	private int myRow;
	private int myCol;
	
	protected RoomIndex(int theRow, int theCol) {
		myRow = theRow;
		myCol = theCol;
	}
	
	void setRow(int theRow) {
		myRow = theRow;
	}
	
	void setCol(int theCol) {
		myCol = theCol;
	}
	
	int getRow() {
		return myRow;
	}
	
	int getCol() {
		return myCol;
	}
}
