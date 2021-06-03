package model;

import java.awt.Point;
import java.util.Random;

/**
 * Extends point and can accept a Direction parameter to move a point.
 * @author Ken Smith, Heather Finch, Katelynn Oleson 
 * @version 5.14.21
 */
public class PiecePoint extends Point{
	
	private static final long serialVersionUID = 1L;
	private int x;
	private int y;
	private static final Random RAND = new Random();
	
	/**
	 * 
	 */
	public PiecePoint() {
		super();
	}
	
	/**
	 * @param theX
	 * @param theY
	 */
	public PiecePoint(int theX, int theY) {
		super(theX, theY);
		x = theX;
		y = theY;
	}
	
	/**
	 * @param theDirection
	 */
	public void move(Direction theDirection) {

		if(theDirection != null) {
			int inXVelocity = Player.getSpeed()*theDirection.getDXMultiplier();
			int inYVelocity = Player.getSpeed()*theDirection.getDYMultiplier();
			x = x + (inXVelocity);
			y = y + (inYVelocity);
		}
	}
	
	/**
	 *
	 */
	public double getX() {
		return x;
	}
	
	/**
	 *
	 */
	public double getY() {
		return y;
	}
	
	/**
	 *
	 */
	public void setLocation(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * @param theX
	 * @param theY
	 * @return
	 */
	public static PiecePoint randomPoint(int theX, int theY) {
		int inX = RAND.nextInt(theX);
		int inY = RAND.nextInt(theY);
		return new PiecePoint(inX, inY);
	}
	
	/**
	 * A toString() method that returns a coordinate pair as a String.
	 */
	@Override
	public String toString() {
		return("("+x+", "+y+")");
	}
	
}
