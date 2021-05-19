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
	//TODO: get rid of magic number, establish speed constant somewhere, maybe in player
	//TODO: add borders
	public void move(Direction theDirection) {
		//System.out.println(theDirection);
		//System.out.println(x);
		//System.out.println(y);
		//TODO: there has to be a better way to do this. maybe by setting int values in direction? 
		if (theDirection == Direction.EAST) x = x+1;
		if (theDirection == Direction.WEST) x = x-1;
		if (theDirection == Direction.NORTH) y = y-1;
		if (theDirection == Direction.SOUTH) y = y+1;
		if (theDirection == Direction.NORTHEAST) {
			y = y-1;
			x = x+1;
		}
		if (theDirection == Direction.NORTHWEST) {
			y = y-1;
			x = x-1;
		}
		if (theDirection == Direction.SOUTHEAST) {
			y = y+1;
			x = x+1;
		}
		if (theDirection == Direction.SOUTHWEST) {
			y = y+1;
			x = x-1;
		}
		//System.out.println(x);
		//System.out.println(y);
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
	
	@Override
	public String toString() {
		return("("+x+", "+y+")");
	}
}
