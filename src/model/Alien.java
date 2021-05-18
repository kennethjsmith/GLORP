/// trash this -> replaced with player
package model;

import java.awt.Point;
import java.awt.image.BufferedImage;

import java.io.File;
import java.io.IOException;
import java.util.Comparator;

import javax.imageio.ImageIO;

//TODO: move some of this up to Character abstract class/interface?
public class Alien implements Piece, Comparable{

	//fields
	private BufferedImage myImage;
	private InRoomCoordinates myCoordinates;
	
	public Alien() {
		myImage = null;
		try {
		    myImage = ImageIO.read(new File("icons/alien.png"));
		} catch (IOException e) {
		}
		myCoordinates = new InRoomCoordinates();
	}

	/**
	 * @return the myImage
	 */
	public BufferedImage getMyImage() {
		return myImage;
	}

	/**
	 * @return the myCoordinates
	 */
	public InRoomCoordinates getMyCoordinates() {
		return myCoordinates;
	}

    @Override
    public Point getCoordinate() {
        // TODO Auto-generated method stub
        return null;
    }
    
    /**
     * compares this piece to another piece based on coordinates 
     * and the "vaccinity error constant" 
     * returns 0 if theOther piece is in the vacinity of this piece
     * @return
     */
    @Override
    public int compareTo(Object o) { //maybe works for o as a point or a piece?
        // TODO Auto-generated method stub
        return 0;
    }
}
