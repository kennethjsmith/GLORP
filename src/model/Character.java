package model;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Set;

import javax.swing.ImageIcon;

public class Character extends GamePiece {
	
	// fields
	private Point myCoordinate;
	private ImageIcon myRoomIcon;
	private ImageIcon myMapIcon;
	private int mySize;
	private Set<ImageIcon> myImageIconSet;
	
	private ArrayList<Item> myInventory;
	private Skin mySkin;
	private IconDirection myDirection;
	private int myStride; // 0 for standing, 1,2,3,4 for different run icons
	
	public Character() {
		super();
	}
	
	public void dropItem(Item theItem){ 
		myInventory.remove(theItem);
	}
	
	public void updateRoomIcon() {
		myRoomIcon = mySkin.getIcon(myDirection, myStride);
	}
	
	public ArrayList<Item> getInventory() {
		return myInventory;
	}
	
	public void move(Direction theDirection){
		//TODO: reconcile IconDirection and Direction
		myCoordinate.move(myCoordinate.getX + theDirection.dx(), myCoordinate.getY + theDirection.dy());
		if (theDirection.getLabel().equals("E"))
		myStride++;
		if(myStride > 4) myStride = 1;
		updateRoomIcon();
	} //TODO: include skipframe?

}
