package model;

import java.awt.Point;
import java.io.Serializable;
import java.util.Objects;

import view.GameIcon;

/**
 * This abstract class represents GamePieces; including Item, Player, and Fixture.  
 * @authors Heather Finch, Katelynn Oleson, Ken Smith
 * @version 1.0.
 */
public abstract class GamePiece implements Serializable{

	// Serialized ID for serializiation. 
    private static final long serialVersionUID = 2512409428926537464L;
	private PiecePoint myCoordinate;
	private GameIcon myRoomIcon;
	private GameIcon myMapIcon;
	 
	/**
	 * Constructor creates a new GamePiece.
	 */
	public GamePiece() {
		myCoordinate = new PiecePoint();
	}
	 
	/**
	 * Getter for the GamePiece coordinate.
	 * @return Point the coordinate of this GamePiece
	 */
	public Point getCoordinate() {
		return myCoordinate;
	}
	
	/**
	 * Getter for the room icon for this GamePiece.
	 * @return GameIcon the icon for this GamePiece
	 */
	public GameIcon getRoomIcon() {
		return myRoomIcon; 
	}
 
	/**
	 * Setter for the room icon for this GamePiece.
	 * @param GameIcon the new icon for this GamePiece
	 */
	public void setRoomIcon(GameIcon theIcon) {
		myRoomIcon = Objects.requireNonNull(theIcon);
	}
 
	/**
	 * Getter for the map icon for this GamePiece.
	 * @return GameIcon the map icon for this GamePiece
	 */
	public GameIcon getMapIcon() {	
		return myMapIcon; 
	}
}
