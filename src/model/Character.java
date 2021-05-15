package model;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Set;

import javax.swing.ImageIcon;

public class Character extends GamePiece {
	
	// fields
	private Point myCoordinate;
	private ImageIcon myRoomIcon;
	private final ImageIcon myMapIcon; //TODO: add mapIcon
	private final int SIZE = 100;
	
	private ArrayList<Item> myInventory;
	private Skin mySkin;
	private IconDirection myIconDirection;
	private int myStride; // 0 for standing, 1,2,3,4 for different run icons
	
	
	
	public Character() {
		super();
		mySkin = new Skin(SkinType.ALIEN);
		myIconDirection = IconDirection.RIGHT;
		myStride = 0;
		myRoomIcon = mySkin.getIcon(myIconDirection, myStride);
		this.myMapIcon = new ImageIcon(); // fix this
	}
	
	public void dropItem(Item theItem){ 
		myInventory.remove(theItem);
	}
	
	public void updateRoomIcon() {
		myRoomIcon = mySkin.getIcon(myIconDirection, myStride);
	}
	
	public ArrayList<Item> getInventory() {
		return myInventory;
	}
	
	public void move(Direction theDirection){
		//TODO: reconcile IconDirection and Direction
		//myCoordinate.move(myCoordinate.getX + theDirection.dx(), myCoordinate.getY + theDirection.dy());
		if (theDirection.getLabel().equals("E"))
		myStride++;
		if(myStride > 4) myStride = 1;
		updateRoomIcon();
	} //TODO: include skipframe?

}
