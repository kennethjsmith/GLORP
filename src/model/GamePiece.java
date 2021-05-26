/**
 * 
 */
package model;
import java.awt.Point;
import java.util.Set;
import javax.swing.ImageIcon;
import view.GameIcon;

import view.GameIcon;

/**
 * @author 12538
 *
 */
public abstract class GamePiece {
	 // fields

	 private PiecePoint myCoordinate;
	 private GameIcon myRoomIcon;
	 private GameIcon myMapIcon;
	 private int SIZE = 10;
	 
	 public GamePiece() {
		 myCoordinate = new PiecePoint();
	 }
	 
	 public Point getCoordinate() {
		return myCoordinate;
		 
	 }
	 
	 public void setCoordinate() {
		 
	 }
	 
	 public GameIcon getRoomIcon() {
		return myRoomIcon;
		 
	 }
	 
	 public void setRoomIcon() {
		 
	 }
	 
	 public GameIcon getMapIcon() {
		return myMapIcon; 
		 
	 }
	 
	 public void setMapIcon() { 
		 
	 }
	 
	 public int getSize() {
		return SIZE;
		 
	 }
	 
	 public void setSize() {
		 
	 }
	 
}
