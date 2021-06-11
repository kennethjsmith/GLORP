/*
 * GLORP: Revenge of the Sphinx
 */

package view;

import java.awt.Image;

import javax.swing.ImageIcon;
/**
 * Extends ImageIcon; allows for easy resizing and size to be set in the constructor.
 * @author Ken Smith, Heather Finch, Katelynn Oleson 
 * @version 1.0.
 */
public class GameIcon extends ImageIcon{
	// A serialized ID for serialization.
    private static final long serialVersionUID = -4223394385991587942L;
	
	/**
	 * Creates a GameIcon based on the String passed in.
	 * @param String The file name of the icon
	 */
	public GameIcon(String theString) {
		super(theString);
	}
	
	/**
	 * Creates a GameIcon based on the String and size passed in.
	 * @param String The file name of the icon
	 * @param The size of the icon, an int
	 */
	public GameIcon(String theString, int theSize) {
		super(theString);
		resize(theSize);
	}
	
	/**
	 * Creates a GameIcon based on the String, width, and height passed in.
	 * @param String The file name of the icon
	 * @param The width of the icon, an int
	 * @param The height of the icon, an int
	 */
	public GameIcon(String theString, int theWidth, int theHeight) {
		super(theString);
		resize(theWidth, theHeight);
	}
	
	// Resized the GameIcon based on size.
	private void resize(int theSize) {
		Image inTempImage;
    	// Resize left facing standing icon.
    	inTempImage = this.getImage(); // Transform it.
		inTempImage = inTempImage.getScaledInstance(theSize, theSize,  java.awt.Image.SCALE_SMOOTH); // Scale it the smooth way.  
		this.setImage(inTempImage); // Transform it back.
	}
	
	// Resizes the GameIcon based on width and height.
	private void resize(int theWidth, int theHeight) {
		Image inTempImage;
    	// Resize left facing standing icon.
    	inTempImage = this.getImage(); // Transform it.
		inTempImage = inTempImage.getScaledInstance(theWidth, theHeight,  java.awt.Image.SCALE_SMOOTH); // Scale it the smooth way.  
		this.setImage(inTempImage); // Transform it back.
	}
}
