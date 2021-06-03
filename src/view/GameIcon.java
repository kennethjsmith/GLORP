package view;

import java.awt.Image;

import javax.swing.ImageIcon;
/**
 * Extends ImageIcon; allows for easy resizing and size to be set in the constructor.
 * @author Ken Smith, Heather Finch, Katelynn Oleson 
 * @version 
 */
public class GameIcon extends ImageIcon{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public GameIcon() {
		super();
	}
	
	/**
	 * 
	 * @param theString
	 */
	public GameIcon(String theString) {
		super(theString);
	}
	
	/**
	 * 
	 * @param theString
	 * @param theSize
	 */
	public GameIcon(String theString, int theSize) {
		super(theString);
		resize(theSize);
	}
	
	/**
	 * 
	 * @param theString
	 * @param theWidth
	 * @param theHeight
	 */
	public GameIcon(String theString, int theWidth, int theHeight) {
		super(theString);
		resize(theWidth, theHeight);
	}
	
	/**
	 * 
	 * @param theSize
	 */
	public void resize(int theSize) {
		Image inTempImage;
    	//resize left facing standing icon
    	inTempImage = this.getImage(); // transform it 
		inTempImage = inTempImage.getScaledInstance(theSize, theSize,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
		this.setImage(inTempImage); // transform it back
    
	}
	
	/**
	 * Resizes the Icon.
	 * @param theWidth
	 * @param theHeight
	 */
	public void resize(int theWidth, int theHeight) {
		Image inTempImage;
    	//resize left facing standing icon
    	inTempImage = this.getImage(); // transform it 
		inTempImage = inTempImage.getScaledInstance(theWidth, theHeight,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
		this.setImage(inTempImage); // transform it back
    
	}
}
