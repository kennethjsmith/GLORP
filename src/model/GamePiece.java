/**
 * 
 */
package model;
import java.awt.Point;
import java.util.Set;
import javax.swing.ImageIcon;
import view.GameIcon;

/**
 * @author 12538
 *
 */
public abstract class GamePiece {
	 // fields
	 private Point myCoordinate;
	 private GameIcon myRoomIcon;
	 private GameIcon myMapIcon;
	 private int mySize;
	 
	 public GamePiece() {
		 
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
		return mySize;
		 
	 }
	 
	 public void setSize() {
		 
	 }
	 
}
