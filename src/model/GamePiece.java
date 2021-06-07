/**
 * 
 */
package model;
import java.awt.Point;
import java.io.Serializable;
import java.util.Set;
import javax.swing.ImageIcon;
import view.GameIcon;

import view.GameIcon;

/**
 * This abstract class represents GamePieces; including Item, Player, and Fixture.  
 * @authors Heather Finch, Katelynn Oleson, Ken Smith
 * @version
 */
public abstract class GamePiece implements Serializable{
	 /**
     * 
     */
    private static final long serialVersionUID = 2512409428926537464L;
    
    // fields
	 private PiecePoint myCoordinate;
	 private GameIcon myRoomIcon;
	 private GameIcon myMapIcon;
	 
	 /**
	  * 
	  */
	 public GamePiece() {
		 myCoordinate = new PiecePoint();
	 }
	 
	 /**
	  * 
	  */
	 public Point getCoordinate() {
		return myCoordinate;
		 
	 }
	 
	 /**
	  * 
	  */
	 public void setCoordinate() {
		 
	 }
	 
	 /**
	  * 
	  */
	 public GameIcon getRoomIcon() {
		return myRoomIcon;
		 
	 }
	 
	 /**
	  * 
	  */
	 public void setRoomIcon(GameIcon theIcon) {
		 myRoomIcon = theIcon;
	 }
	 
	 /**
	  * 
	  */
	 public GameIcon getMapIcon() {
		return myMapIcon; 
		 
	 }
	 
	 /**
	  * 
	  */
	 public void setMapIcon() { 
		 
	 }
	 
	 /**
	  * 
	  */
	 public void setSize() {
		 
	 }
	 
}
