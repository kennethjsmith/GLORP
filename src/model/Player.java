package model;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Set;

import javax.swing.ImageIcon;

import view.GameIcon;
import view.Music;
import view.SoundEffect;

/**
 * Represents the player that the user will move throughout the maze.
 * @author Ken Smith, Heather Finch, Katelynn Oleson 
 * @version 
 */
public class Player extends GamePiece implements Cloneable {
	
	// fields
	private PiecePoint myCoordinate;
	private GameIcon myRoomIcon;
	private final GameIcon myMapIcon = new GameIcon("src/icons/alien_map_icon.png"); //TODO: add mapIcon
	
	private ArrayList<ItemType> myInventory;
	private Skin mySkin;
	private IconDirection myIconDirection;
	private int myStride; // 0 for standing, 1,2,3,4 for different run icons
	private boolean skipFrame;
	private Rectangle myIconArea;
	private Rectangle myBase;
	private boolean isFixed;
	private boolean isClone;

	private static final int SPEED = 20;
	private static final int PLAYER_ROOM_ICON_SIZE = 100;
	private static final int PLAYER_MAP_ICON_SIZE = 10;
	private static final Skin DEFAULT_SKIN = new Skin(SkinType.ALIEN);
	
	/**
	 * 
	 */
	public Player() {
		super();
		myInventory = new ArrayList<>();
		myCoordinate = new PiecePoint(200, 300);
		mySkin = DEFAULT_SKIN;
		myIconDirection = IconDirection.RIGHT;
		myStride = 0;
		myRoomIcon = mySkin.getIcon(myIconDirection, myStride);
		skipFrame = false;
		myIconArea = new Rectangle(myCoordinate, new Dimension (mySkin.getIconWidth(), mySkin.getIconHeight()));
		myBase = new Rectangle((int)myCoordinate.getX()+10, (int)myCoordinate.getY()+(mySkin.getIconHeight()-20), mySkin.getIconWidth()-20, 20);
		isFixed = false;
		isClone = false;
	}
	
	/**
	 * @param theSkin
	 */
	public Player(SkinType theSkinType) {
		super();
		myInventory = new ArrayList<>();
		myCoordinate = new PiecePoint(200,300);
		mySkin = new Skin(theSkinType);
		myIconDirection = IconDirection.RIGHT;
		myStride = 0;
		myRoomIcon = mySkin.getIcon(myIconDirection, myStride);
		skipFrame = false;
		myIconArea = new Rectangle(myCoordinate, new Dimension (mySkin.getIconWidth(), mySkin.getIconHeight()));
		myBase = new Rectangle((int)myCoordinate.getX()+10, (int)myCoordinate.getY()+(mySkin.getIconHeight()-20), mySkin.getIconWidth()-20, 20);
		isClone = false;	
	}
	
	/**
	 * @param theItem
	 */
	public void dropItem(Item theItem){ 
		myInventory.remove(theItem);
	}
	
	/**
	 * @return
	 */
	public ArrayList<ItemType> getInventory() {
		return myInventory;
	}
	
	/**
	 * @param theDirection
	 */
	public void move(Direction theDirection){
		if(!isFixed) {
			myIconDirection = IconDirection.generateIconDirection(theDirection, myIconDirection);
			if(!skipFrame) {
				myStride++;
				if(!isClone) {
					if(mySkin.getType() == SkinType.ALIEN) SoundEffect.WALK.play();
					if(mySkin.getType() == SkinType.MOONINITE) SoundEffect.MOON_WALK.play();
				}
			}
			skipFrame = !skipFrame;
			if(myStride > 4) myStride = 1;
			updateRoomIcon();
			myCoordinate.move(theDirection);
			updateRectangles();
		}
	}
	/**
	 * 
	 */
	public void updateRectangles() {
		myIconArea.setLocation((int)myCoordinate.getX(), (int)myCoordinate.getY());
		myBase.setLocation((int)myCoordinate.getX()+10, (int)myCoordinate.getY() + (2*PLAYER_ROOM_ICON_SIZE/3));
	}
	
	/**
	 * 
	 */
	public void updateRoomIcon() {
		if(!isFixed) myRoomIcon = mySkin.getIcon(myIconDirection, myStride);
	}
	
	
	/**
	 * @return the myArea
	 */
	public Rectangle getIconArea() {
		return myIconArea;
	}

	/**
	 * @return the myBase
	 */
	public Rectangle getBase() {
		return myBase;
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
	public GameIcon getMapIcon() {
		return myMapIcon;
	}
	
	/**
	 *
	 */
	public PiecePoint getCoordinate() {
		return myCoordinate;
		
	}
	
	/**
	 * 
	 */
	 public void setCoordinate(PiecePoint theCoordinate) {
		myCoordinate = theCoordinate; 
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
	 * 
	 */
	public void setSkin(SkinType theType) {
		mySkin = new Skin(theType);
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
	        clone = new Player(this.getSkin().getType());
	    }
	    clone.skipFrame = this.skipFrame;
	    clone.myCoordinate = (PiecePoint) this.myCoordinate.clone();
	    clone.myIconArea = (Rectangle) this.myIconArea.clone();
	    clone.myBase = (Rectangle) this.myBase.clone();
	    clone.myIconDirection = this.myIconDirection;
	    clone.myStride = this.myStride;
	    clone.isClone = true;
	    return clone;
	}
	
	/**
	  * 
	  */
	 public void setRoomIcon(GameIcon theIcon) {
		 if(!isFixed) myRoomIcon = theIcon;
	 }

	

	/**
	 * 
	 * @param inFixture
	 * @return boolean
	 */
	public boolean canInteract(Fixture inFixture) {
		if((inFixture.getType() == FixtureType.ALTSHIP
				|| inFixture.getType() == FixtureType.SHIP)) {
			for(ItemType type : myInventory) {
				if(type.getLabel().equals("GEM")) return true;
			}
		}
		if(inFixture.getType() == FixtureType.CHEST) {
			for(ItemType type : myInventory) {
				if(type.equals(ItemType.KEY)) return true;
			}
		}
		
		return false;
	}

	/**
	 * 
	 */
	public boolean getFixed() {
		return isFixed;
	}
	
	/**
	 * 
	 */
	public void setFixed(boolean isFixed) {
		this.isFixed = isFixed;
	}
	
	/**
	 *
	 */
	public String toString() {
		return myCoordinate.toString();
	}
	
}