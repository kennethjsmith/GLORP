package model;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Set;

import javax.swing.ImageIcon;

import view.GameIcon;

public class Player extends GamePiece implements Cloneable {
	
	// fields
	private PiecePoint myCoordinate;
	private GameIcon myRoomIcon;
	private final GameIcon myMapIcon = new GameIcon("src/icons/alien_map_icon.png"); //TODO: add mapIcon
	
	private ArrayList<Item> myInventory;
	private Skin mySkin;
	private IconDirection myIconDirection;
	private int myStride; // 0 for standing, 1,2,3,4 for different run icons
	private boolean skipFrame;
	private Rectangle myArea;


	private static final int SPEED = 5;
	private static final int PLAYER_MAP_ICON_SIZE = 10;
	private static final Skin DEFAULT_SKIN = new Skin(SkinType.ALIEN);
	
	public Player() {
		super();
		myCoordinate = new PiecePoint(5, 5);
		mySkin = DEFAULT_SKIN;
		myIconDirection = IconDirection.RIGHT;
		myStride = 0;
		myRoomIcon = mySkin.getIcon(myIconDirection, myStride);
		myArea = new Rectangle(myCoordinate, new Dimension (this.getSize(), this.getSize()));
		//setArea(new Rectangle(myCoordinate, new Dimension (this.getSize(), this.getSize())));
	}
	
	public Player(Skin theSkin) {
		super();
		myCoordinate = new PiecePoint();
		mySkin = theSkin;
		myIconDirection = IconDirection.RIGHT;
		myStride = 0;
		myRoomIcon = mySkin.getIcon(myIconDirection, myStride);
		skipFrame = false;
	}
	
	public void dropItem(Item theItem){ 
		myInventory.remove(theItem);
	}
	
	public ArrayList<Item> getInventory() {
		return myInventory;
	}
	
	public void move(Direction theDirection){
		// update room icon
		myIconDirection = IconDirection.generateIconDirection(theDirection, myIconDirection);
		if(!skipFrame) {
			myStride++;
		}
		skipFrame = !skipFrame;
		if(myStride > 4) myStride = 1;
		updateRoomIcon();
		// move coordinates
		System.out.println(myCoordinate);
		myCoordinate.move(theDirection);
		System.out.println(myCoordinate);
		updateArea();
	}
	
	public void updateArea() {
		myArea.setLocation((int)myCoordinate.getX(), (int)myCoordinate.getY());
	}
	
	public void updateRoomIcon() {
		myRoomIcon = mySkin.getIcon(myIconDirection, myStride);
		//System.out.println(myRoomIcon);
	}
	
	
	/**
	 * @return the myArea
	 */
	public Rectangle getArea() {
		return myArea;
	}

	/**
	 * @param myArea the myArea to set
	 */
	public void setArea(Rectangle myArea) {
		this.myArea = myArea;
	}

	public GameIcon getRoomIcon() {
		return myRoomIcon;	 
	}
	
	public GameIcon getMapIcon() {
		return myMapIcon;
	}
	
	public PiecePoint getCoordinate() {
		return myCoordinate;
		
	}
	
	/**
	 * @return the speed
	 */
	public static int getSpeed() {
		return SPEED;
	}
	

	/**
	 * @return the mySkin
	 */
	public Skin getSkin() {
		return mySkin;
	}

	/**
	 * @param myStride the myStride to set
	 */
	public void setStride(int theStride) {
		myStride = theStride;
		updateRoomIcon();
	}

	/**
	 * @param skipFrame the skipFrame to set
	 */
	public void setSkipFrame(boolean skipFrame) {
		this.skipFrame = skipFrame;
	}
	
	/*
	 * Create a deep clone
	 */
	@Override
	protected Object clone(){
		Player clone = null;
	    try {
	        clone = (Player) super.clone();
	    } catch (CloneNotSupportedException e) {
	        clone = new Player(this.getSkin());
	    }
	    clone.skipFrame = this.skipFrame;
	    clone.myCoordinate = (PiecePoint) this.myCoordinate.clone();
	    clone.myArea = (Rectangle) this.myArea.clone();
	    clone.myIconDirection = this.myIconDirection;
	    clone.myStride = this.myStride;

	    return clone;
	}

	public String toString() {
		return myCoordinate.toString();
	}
}