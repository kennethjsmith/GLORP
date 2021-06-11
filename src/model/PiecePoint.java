/*
 * GLORP: Revenge of the Sphinx
 */

package model;

import java.awt.Point;
import java.io.Serializable;
import java.util.Random;

/**
 * Extends point and can accept a Direction parameter to move a point.
 * Used by GamePieces.
 * @author Ken Smith, Heather Finch, Katelynn Oleson 
 * @version 1.0.
 */
public class PiecePoint extends Point implements Serializable {
    	
	private static final long serialVersionUID = 1L;
	private int x;
	private int y;
	private static final Random RAND = new Random();
	
	/**
	 * Default constructor, creates a new PiecePoint.
	 */
	public PiecePoint() {
		super();
	}
	
	/**
	 * Creates a PiecePoint based off of the x and y coordiantes provided.
	 * @param theX An int representing the x coordinate
	 * @param theY An int representing the y coordinate
	 */
	public PiecePoint(int theX, int theY) {
		super(theX, theY);
		x = theX;
		y = theY;
	}
	
	/**
	 * Moves this in a direction based on theDirection. 
	 * Uses a pythagorean equation to adjust dx and dy for diagonal movement.
	 * @param Direction The direction to move the PiecePoint
	 */
	public void move(Direction theDirection) {
		int inSpeed = Player.getSpeed();
		if(theDirection != null) {
			if(theDirection.getLabel()=="NW" || theDirection.getLabel()=="NE"
					|| theDirection.getLabel()=="SW" || theDirection.getLabel()=="SE") {
				inSpeed = (int) Math.ceil(Math.sqrt(Math.pow(inSpeed, 2))/2);
			}
			int inXVelocity = inSpeed*theDirection.getDXMultiplier();
			int inYVelocity = inSpeed*theDirection.getDYMultiplier();
			x = x + (inXVelocity);
			y = y + (inYVelocity);
		}
	}
	
	/**
	 * Getter for the x coordiante.
	 * @return The x coordinate, a double
	 */
	public double getX() {
		return x;
	}
	
	/**
	 * Getter for the y coordinate.
	 * @return The y coordinate, a double
	 */
	public double getY() {
		return y;
	}
	
	/**
	 * Setter for the x and y coordinates.
	 * @param x The x coordiante
	 * @param y The y coordinate
	 */
	public void setLocation(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Creates a random point.
	 * @param theX The upper bound for the random x point
	 * @param theY the upper bound for the random y point
	 * @return A new PiecePoint
	 */
	public static PiecePoint randomPoint(int theX, int theY) {
		int inX = RAND.nextInt(theX);
		int inY = RAND.nextInt(theY);
		return new PiecePoint(inX, inY);
	}
}
