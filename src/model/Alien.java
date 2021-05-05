package model;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
public class Alien {
	//fields
	//TODO: replace with array of images (animation)
	private BufferedImage myImage;
	
	public Alien() {
		myImage = null;
		try {
		    myImage = ImageIO.read(new File("icons/alien.png"));
		} catch (IOException e) {
		}
	}

	/**
	 * @return the myImage
	 */
	public BufferedImage getMyImage() {
		return myImage;
	}
}
