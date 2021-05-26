package model;

import java.awt.Point;
import java.util.Random;

public class PiecePoint extends Point{
	
	private static final long serialVersionUID = 1L;
	private int x;
	private int y;
	private static final Random RAND = new Random();
	
	public PiecePoint() {
		super();
		x = 5;
		y = 5;
	}
	
	public PiecePoint(int theX, int theY) {
		super();
		x = theX;
		y = theY;
	}
	
	public void move(Direction theDirection) {

		if(theDirection != null) {
			int inXVelocity = Player.getSpeed()*theDirection.getDXMultiplier();
			int inYVelocity = Player.getSpeed()*theDirection.getDYMultiplier();
			x = x + (inXVelocity);
			y = y + (inYVelocity);
		}
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
	
	public void setLocation(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public static PiecePoint randomPoint(int theX, int theY) {
		int inX = RAND.nextInt(theX);
		int inY = RAND.nextInt(theY);
		return new PiecePoint(inX, inY);
	}
	
	@Override
	public String toString() {
		return("("+x+", "+y+")");
	}
	
}
