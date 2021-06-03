package model;

// TODO: discuss visibility as a team, I add public to some other methods in another class. I might have broke
// the JUnit tests? Sorry! -Ken
/**
 * 
 * @author Ken Smith, Heather Finch, Katelynn Oleson 
 * @version 
 */
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
	
	/**
	 * @return
	 */
	public int getRow() {
		return myRow;
	}
	
	/**
	 * @return
	 */
	public int getCol() {
		return myCol;
	}
}
