package model;

import java.util.ArrayList;
import java.util.Set;

import javax.swing.ImageIcon;

import view.GameIcon;

public class Player extends GamePiece {
	
	// fields
	private PiecePoint myCoordinate;
	private GameIcon myRoomIcon;
	private final GameIcon myMapIcon = new GameIcon("src/icons/alien_map_icon.png"); //TODO: add mapIcon
	
	private ArrayList<Item> myInventory;
	private Skin mySkin;
	private IconDirection myIconDirection;
	private int myStride; // 0 for standing, 1,2,3,4 for different run icons
	private static boolean skipFrame;


	private static final int SPEED = 5;
	private static final int PLAYER_MAP_ICON_SIZE = 10;
	private static final Skin DEFAULT_SKIN = new Skin(SkinType.ALIEN);
	
	public Player() {
		super();
		myCoordinate = new PiecePoint();
		mySkin = DEFAULT_SKIN;
		myIconDirection = IconDirection.RIGHT;
		myStride = 0;
		myRoomIcon = mySkin.getIcon(myIconDirection, myStride);
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
		//System.out.println(myCoordinate);
		myCoordinate.move(theDirection);
		//System.out.println(myCoordinate);
	}
	
	public void updateRoomIcon() {
		myRoomIcon = mySkin.getIcon(myIconDirection, myStride);
		//System.out.println(myRoomIcon);
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
		Player.skipFrame = skipFrame;
	}

	public String toString() {
		return myCoordinate.toString();
	}
}
