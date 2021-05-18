package model;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Set;

import javax.swing.ImageIcon;

import view.GameIcon;

public class Player extends GamePiece {
	
	// fields
	private Point myCoordinate;
	private GameIcon myRoomIcon;
	private final GameIcon myMapIcon = new GameIcon("src/icons/alien_map_icon.png"); //TODO: add mapIcon
	
	private ArrayList<Item> myInventory;
	private Skin mySkin;
	private IconDirection myIconDirection;
	private int myStride; // 0 for standing, 1,2,3,4 for different run icons
	
	private static final int PLAYER_MAP_ICON_SIZE = 10;
	private static final Skin DEFAULT_SKIN = new Skin(SkinType.ALIEN);
	
	public Player() {
		super();
		mySkin = DEFAULT_SKIN;
		myIconDirection = IconDirection.RIGHT;
		myStride = 0;
		myRoomIcon = mySkin.getIcon(myIconDirection, myStride);
	}
	
	public Player(Skin theSkin) {
		super();
		mySkin = theSkin;
		myIconDirection = IconDirection.RIGHT;
		myStride = 0;
		myRoomIcon = mySkin.getIcon(myIconDirection, myStride);
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
	//TODO:move up to controller?
	public void move(Direction theDirection){
		//myCoordinate.move(myCoordinate.getX + theDirection.dx(), myCoordinate.getY + theDirection.dy());
		if (theDirection == Direction.EAST) myIconDirection = IconDirection.RIGHT;
		if (theDirection == Direction.WEST) myIconDirection = IconDirection.LEFT;
		myStride++;
		if(myStride > 4) myStride = 1;
		updateRoomIcon();
	}

}
