package model;

import java.awt.Point;

public class PiecePoint extends Point{
	
	private static final long serialVersionUID = 1L;
	private int x;
	private int y;
	
	public PiecePoint() {
		super();
		x = 5;
		y = 5;
	}
	//TODO: add borders
	public void move(Direction theDirection) {
		//System.out.println(theDirection);
		//System.out.println(x);
		//System.out.println(y); 
//		if (theDirection == Direction.EAST) x = x+Player.getSpeed();
//		if (theDirection == Direction.WEST) x = x-Player.getSpeed();
//		if (theDirection == Direction.NORTH) y = y-Player.getSpeed();
//		if (theDirection == Direction.SOUTH) y = y+Player.getSpeed();
//		if (theDirection == Direction.NORTHEAST) {
//			y = y-Player.getSpeed();
//			x = x+Player.getSpeed();
//		}
//		if (theDirection == Direction.NORTHWEST) {
//			y = y-Player.getSpeed();
//			x = x-Player.getSpeed();
//		}
//		if (theDirection == Direction.SOUTHEAST) {
//			y = y+Player.getSpeed();
//			x = x+Player.getSpeed();
//		}
//		if (theDirection == Direction.SOUTHWEST) {
//			y = y+Player.getSpeed();
//			x = x-Player.getSpeed();
//		}
		if(theDirection != null) {
			int inXVelocity = Player.getSpeed()*theDirection.getDXMultiplier();
			int inYVelocity = Player.getSpeed()*theDirection.getDYMultiplier();
			x = x + (inXVelocity);
			y = y + (inYVelocity);
		}
		//TODO: use helper methods to establish borders
		//if(xVelocity > 0 && canMoveRight())
		
		
		
		//System.out.println(x);
		//System.out.println(y);
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
	
	@Override
	public String toString() {
		return("("+x+", "+y+")");
	}
	
}
