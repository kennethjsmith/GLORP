package model;

import java.awt.image.BufferedImage;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

//TODO: move some of this up to Character abstract class/interface?
public class Alien implements Character{

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
}
