package model;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

import view.GameIcon;
import view.SoundEffect;

/**
 * Represents the player that the user will move throughout the maze.
 * @author Ken Smith, Heather Finch, Katelynn Oleson 
 * @version 1.0.
 */
public class Player extends GamePiece implements Cloneable, Serializable {
	// Serialized ID for serializatoin.
    private static final long serialVersionUID = -8371632604355137402L;
    
	private PiecePoint myCoordinate;
	private GameIcon myRoomIcon;
	private ArrayList<ItemType> myInventory; // The items the player has in their inventory.
	private Skin mySkin;
	private IconDirection myIconDirection;
	private int myStride; // 0 for standing, 1,2,3,4 for different run icons.
	private boolean skipFrame;
	private Rectangle myIconArea;
	private Rectangle myBase;
	private boolean isFixed;
	private boolean isClone;
	
	private static final GameIcon MAP_ICON = new GameIcon("src/icons/alien_map_icon.png");
	private static final int SPEED = 10;
	private static final int PLAYER_ROOM_ICON_SIZE = 100;
	private static final Skin DEFAULT_SKIN = new Skin(SkinType.ALIEN);
	
	/**
	 * Constructor creates a new player. Their skin is set to the Glorp skin.
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
	 * Constructor creates a new player whose skin is set to the SkinType passed in.
	 * @param SkinType The skin that the Player will have
	 */
	public Player(SkinType theSkinType) {
		super();
		Objects.requireNonNull(theSkinType);
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
	 * Drops an item out of the Players inventory.
	 * @param Item The item that will be added to inventory. 
	 */
	public void dropItem(Item theItem){ 
		Objects.requireNonNull(theItem);
		myInventory.remove(theItem);
	}
	
	/**
	 * Getter for the Players inventory.
	 * @return ArrayList<ItemType> The Players inventory
	 */
	public ArrayList<ItemType> getInventory() {
		return myInventory;
	}
	
	/**
	 * Moves the Player to a new location in the room.
	 * @param Direction The direction the Player will move in
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
	 * Updates the Rectangle locations of the Players base and IconArea.
	 */
	public void updateRectangles() {
		myIconArea.setLocation((int)myCoordinate.getX(), (int)myCoordinate.getY());
		myBase.setLocation((int)myCoordinate.getX()+10, (int)myCoordinate.getY() + (2*PLAYER_ROOM_ICON_SIZE/3));
	}
	
	/**
	 * Updates the room icon based on which way the Player is facing.
	 */
	public void updateRoomIcon() {
		if(!isFixed) myRoomIcon = mySkin.getIcon(myIconDirection, myStride);
	}
	
	
	/**
	 * Getter for the Players area.
	 * @return Rectangle The Players area
	 */
	public Rectangle getIconArea() {
		return myIconArea;
	}

	/**
	 * Getter for the Players base.
	 * @return Rectangle The Players base
	 */
	public Rectangle getBase() {
		return myBase;
	}

	/**
	 * Getter for the Players room icon.
	 * @return GameIcon The Players room icon
	 */
	public GameIcon getRoomIcon() {
		return myRoomIcon;	 
	}
	
	/**
	 * Getter for the Players map icon
	 * @return GameIcon The Players map icon
	 */
	public GameIcon getMapIcon() {
		return MAP_ICON;
	}
	
	/**
	 * Getter for the Players current coordinates.
	 * @return PiecePoint The Players coordinates
	 */
	public PiecePoint getCoordinate() {
		return myCoordinate;
		
	}
	
	/**
	 * Setter for the Players coordinates.
	 * @param PiecePoint the new coordinates of the Player
	 */
	 public void setCoordinate(PiecePoint theCoordinate) {
		myCoordinate = Objects.requireNonNull(theCoordinate); 
	 }
	
	/**
	 * Getter for the Players speed.
	 * @return The players speed, an int
	 */
	public static int getSpeed() {
		return SPEED;
	}
	

	/**
	 * Getter for the Players skin type.
	 * @return Skin The Players skin type
	 */
	public Skin getSkin() {
		return mySkin;
	}
	
	/**
	 * Setter for the Players skin type.
	 * @param SkinType The new skin type for the player
	 */
	public void setSkin(SkinType theType) {
		Objects.requireNonNull(theType);
		mySkin = new Skin(theType);
	}

	/**
	 * Setter for the Players string.
	 * @param theString The new string for the Player
	 */
	public void setStride(int theStride) {
		myStride = theStride;
		updateRoomIcon();
	}

	/**
	 * Setter for the skip frame.
	 * @param skipFrame The skipFrame to set
	 */
	public void setSkipFrame(boolean skipFrame) {
		this.skipFrame = skipFrame;
	}
	
	/*
	 * Creates a deep clone of the Player.
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
	  * Setter for the Players room icon.
	  * @param GameIcon the new room icon
	  */
	 public void setRoomIcon(GameIcon theIcon) {
		 Objects.requireNonNull(theIcon);
		 if(!isFixed) myRoomIcon = theIcon;
	 }

	/**
	 * Checks if the Player can interact with the Fixture passed in.
	 * @param Fixture The Fixture the Player is trying to interact with
	 * @return boolean True if the Player can interact with the Fixture, false otherwise.
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
	 * Getter for whether the Player is fixed in place or not.
	 * @return boolean True if the Player is fixed, false otherwise
	 */
	public boolean getFixed() {
		return isFixed;
	}
	
	/**
	 * Setter for making the Player fixed or un-fixed.
	 * @param boolean True if the Player should be fixed, false otherwise
	 */
	public void setFixed(boolean isFixed) {
		this.isFixed = Objects.requireNonNull(isFixed);
	}
	
}