/**
 * 
 */
package model;

import java.awt.Point;
import java.util.Set;

import javax.swing.ImageIcon;

/**
 * @author 12538
 *
 */
public abstract class GamePiece {
	 // fields
	 private Point myCoordinate;
	 private ImageIcon myRoomIcon;
	 private ImageIcon myMapIcon;
	 private int mySize;
	 private Set<ImageIcon> myImageIconSet;
	 
	 public GamePiece() {
		 
	 }
	 
	 public Point getCoordinate() {
		return myCoordinate;
		 
	 }
	 
	 public void setCoordinate() {
		 
	 }
	 
	 public ImageIcon getRoomIcon() {
		return myRoomIcon;
		 
	 }
	 
	 public void setRoomIcon() {
		 
	 }
	 
	 public ImageIcon getMapIcon() {
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
