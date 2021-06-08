package model;

import java.io.Serializable;

// TODO: discuss visibility as a team, I add public to some other methods in another class. I might have broke
// the JUnit tests? Sorry! -Ken
/**
 * 
 * @author Ken Smith, Heather Finch, Katelynn Oleson 
 * @version 
 */
public class RoomIndex implements Serializable{
	/**
     * 
     */
    private static final long serialVersionUID = 1409522571120625654L;
    
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
