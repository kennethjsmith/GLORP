package model;

public class RoomIndex {
	private int myRow;
	private int myCol;
	
	protected RoomIndex(int theRow, int thecol) {
		myRow = 0;
		myCol = 0;
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
